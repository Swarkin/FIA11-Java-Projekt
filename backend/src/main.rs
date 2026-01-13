mod state;
mod routes;
mod model;

use axum::http::StatusCode;
use axum::routing::{delete, get, patch, post, put};
use axum::Router;
use std::env::var;
use std::net::Ipv4Addr;
use std::sync::Arc;
use std::time::Duration;
use tokio::sync::RwLock;
use tower_http::timeout::TimeoutLayer;
use tower_http::trace::TraceLayer;
use tracing::level_filters::LevelFilter;

pub type AppState = Arc<RwLock<state::State>>;

#[tokio::main]
async fn main() {
	initialize();

	let port = var("PORT")
		.expect("PORT variable not set")
		.parse::<u16>().unwrap();

	let state = Arc::new(RwLock::new(
		state::load_state()
			.expect("failed to load state")
	));

	let app = Router::new()
		.route("/", get(routes::get_wunschliste))
		.route("/batch", get(routes::get_wunschliste_batch))
		.route("/", post(routes::create_wunschliste))
		.route("/", patch(routes::patch_wunschliste))
		.route("/", delete(routes::remove_wunschliste))
		.route("/eintrag", put(routes::create_wunschliste_eintrag))
		.route("/eintrag", delete(routes::remove_wunschliste_eintrag))
		.layer(TimeoutLayer::with_status_code(StatusCode::REQUEST_TIMEOUT, Duration::from_secs(10)))
		.layer(TraceLayer::new_for_http())
		.with_state(state.clone());

	tracing::info!("Starting on port {port}");
	let listener = tokio::net::TcpListener::bind((Ipv4Addr::UNSPECIFIED, port)).await.unwrap();
	axum::serve(listener, app)
		.with_graceful_shutdown(shutdown_signal()).await.unwrap();

	tracing::info!("Saving state");
	let _ = state.write().await;
	state::save_state(Arc::try_unwrap(state).unwrap().into_inner())
		.expect("failed to save state");

	tracing::info!("Exiting");
}

fn initialize() {
	#[cfg(windows)]
	dotenvy::dotenv().unwrap();

	tracing_subscriber::fmt::fmt()
		.with_max_level(if cfg!(debug_assertions) { LevelFilter::TRACE } else { LevelFilter::DEBUG })
		.without_time()
		.init();
}

async fn shutdown_signal() {
	let ctrl_c = async {
		tokio::signal::ctrl_c().await.unwrap();
	};

	#[cfg(unix)]
	let terminate = async {
		tokio::signal::unix::signal(tokio::signal::unix::SignalKind::terminate()).unwrap().recv().await;
	};

	#[cfg(not(unix))]
	let terminate = std::future::pending::<()>();

	tokio::select! {
		_ = ctrl_c => {},
		_ = terminate => {},
	}
}
