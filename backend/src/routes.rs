use crate::AppState;
use axum::extract::State;

pub async fn counter(State(state): State<AppState>) -> String {
	let mut app_state = state.write().await;
	app_state.count += 1;
	app_state.count.to_string()
}
