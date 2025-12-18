use crate::AppState;
use axum::extract::{State, Json, Query};
use axum::http::StatusCode;
use serde::Deserialize;
use crate::model::Wunschliste;

pub async fn get_wunschliste(State(state): State<AppState>, Json(id): Json<u64>) -> Result<Json<Wunschliste>, StatusCode> {
	let app_state = state.read().await;

	app_state.listen.get(&id)
		.ok_or(StatusCode::NOT_FOUND)
		.map(|x| Json(x.to_owned()))
}

#[derive(Deserialize)]
pub struct GetWunschlisteCount {
	pub count: u8,
}

pub async fn get_wunschliste_count(State(state): State<AppState>, Query(query): Query<GetWunschlisteCount>) -> Result<Json<Vec<Wunschliste>>, StatusCode> {
	let app_state = state.read().await;
	let mut wunschlisten = Vec::with_capacity(query.count as usize);

	for i in 0..query.count {
		match app_state.listen.get(&i.into()) {
			None => return Err(StatusCode::BAD_REQUEST),
			Some(liste) => wunschlisten.push(liste.to_owned()),
		}
	}

	Ok(Json(wunschlisten))
}

pub async fn create_wunschliste(State(state): State<AppState>, Json(wunschliste): Json<Wunschliste>) -> String {
	let mut app_state = state.write().await;

	let id = app_state.next_id();
	app_state.listen.insert(id, wunschliste);

	id.to_string()
}

pub async fn remove_wunschliste(State(state): State<AppState>, Json(id): Json<u64>) -> Result<(), StatusCode> {
	let mut app_state = state.write().await;

	app_state.listen.remove(&id)
		.ok_or(StatusCode::BAD_REQUEST)
		.map(|_| ())
}
