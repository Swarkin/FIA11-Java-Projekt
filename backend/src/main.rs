mod state;
mod routes;

use axum::http::StatusCode;
use axum::routing::get;
use axum::Router;
use std::sync::Arc;
use std::time::Duration;
use tokio::sync::RwLock;
use tower_http::timeout::TimeoutLayer;

pub type AppState = Arc<RwLock<state::State>>;

#[tokio::main]
async fn main() {
	let state = state::load_state()
		.expect("failed to load state");

	let state = Arc::new(RwLock::new(state));

	let app = Router::new()
		.layer(TimeoutLayer::with_status_code(StatusCode::REQUEST_TIMEOUT, Duration::from_secs(10)))
		.route("/", get(|| async { "Hello, world!" }))
		.route("/counter", get(routes::counter))
		.with_state(state.clone());

	let listener = tokio::net::TcpListener::bind("0.0.0.0:3000").await.unwrap();
	axum::serve(listener, app)
		.with_graceful_shutdown(shutdown_signal()).await.unwrap();

	state::save_state(state.write().await.clone())
		.unwrap();
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
