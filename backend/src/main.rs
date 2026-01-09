mod state;
mod routes;
mod model;

use axum::http::StatusCode;
use axum::routing::{delete, get, patch, post, put};
use axum::Router;
use std::sync::Arc;
use std::time::Duration;
use tokio::sync::RwLock;
use tower_http::timeout::TimeoutLayer;
use tower_http::trace::TraceLayer;
use tracing::level_filters::LevelFilter;

pub type AppState = Arc<RwLock<state::State>>;

#[tokio::main]
async fn main() {
	tracing_subscriber::fmt::fmt()
		.with_max_level(LevelFilter::TRACE)
		.without_time()
		.init();

	let state = Arc::new(RwLock::new(
		state::load_state()
			.expect("failed to load state")
	));

	let app = Router::new()
		.route("/", get(|| async { "Hello, world!" }))
		.route("/wunschliste", get(routes::get_wunschliste))
		.route("/wunschliste/batch", get(routes::get_wunschliste_batch))
		.route("/wunschliste", post(routes::create_wunschliste))
		.route("/wunschliste", patch(routes::patch_wunschliste))
		.route("/wunschliste", delete(routes::remove_wunschliste))
		.route("/wunschliste/eintrag", put(routes::crate_wunschliste_eintrag))
		.route("/wunschliste/eintrag", delete(routes::remove_wunschliste_eintrag))
		.layer(TimeoutLayer::with_status_code(StatusCode::REQUEST_TIMEOUT, Duration::from_secs(10)))
		.layer(TraceLayer::new_for_http())
		.with_state(state.clone());

	let listener = tokio::net::TcpListener::bind("0.0.0.0:3000").await.unwrap();
	axum::serve(listener, app)
		.with_graceful_shutdown(shutdown_signal()).await.unwrap();

	let _ = state.write().await;
	state::save_state(Arc::try_unwrap(state).unwrap().into_inner())
		.expect("failed to save state");
}

async fn shutdown_signal() {
	let ctrl_c = async {
		tokio::signal::ctrl_c().await.unwrap();
	};

	#[cfg(unix)]
	let terminate = async {
		signal::unix::signal(signal::unix::SignalKind::terminate()).unwrap().recv().await;
	};

	#[cfg(not(unix))]
	let terminate = std::future::pending::<()>();

	tokio::select! {
		_ = ctrl_c => {},
		_ = terminate => {},
	}
}
