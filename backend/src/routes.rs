use crate::AppState;
use axum::extract::{State, Json, Query};
use axum::http::StatusCode;
use serde::Deserialize;
use crate::model::{Item, Wunschliste};

#[derive(Deserialize)]
pub struct GetWunschliste {
	pub id: u64,
}

pub async fn get_wunschliste(State(state): State<AppState>, Query(query): Query<GetWunschliste>) -> Result<Json<Wunschliste>, StatusCode> {
	let app_state = state.read().await;

	app_state.listen.get(&query.id)
		.ok_or(StatusCode::NOT_FOUND)
		.map(|x| Json(x.to_owned()))
}

#[derive(Deserialize)]
pub struct GetWunschlisteBatch {
	pub ids: Vec<u64>,
}

pub async fn get_wunschliste_batch(State(state): State<AppState>, Json(data): Json<GetWunschlisteBatch>) -> Result<Json<Vec<Wunschliste>>, StatusCode> {
	if data.ids.is_empty() {
		return Err(StatusCode::BAD_REQUEST);
	} else if data.ids.len() > 100 {
		return Err(StatusCode::PAYLOAD_TOO_LARGE);
	}

	let mut wunschlisten = Vec::with_capacity(data.ids.len());
	let app_state = state.read().await;

	for id in data.ids {
		match app_state.listen.get(&id) {
			None => return Err(StatusCode::NOT_FOUND),
			Some(liste) => wunschlisten.push(liste.to_owned()),
		}
	}

	Ok(Json(wunschlisten))
}

// #[derive(Deserialize)]
// pub struct GetWunschlisteCount {
// 	pub n: u8,
// }
//
// pub async fn get_wunschliste_count(State(state): State<AppState>, Query(query): Query<GetWunschlisteCount>) -> Result<Json<Vec<Wunschliste>>, StatusCode> {
// 	if query.n == 0 { return Err(StatusCode::BAD_REQUEST); }
//
// 	let app_state = state.read().await;
// 	let mut wunschlisten = Vec::with_capacity(query.n as usize);
//
// 	for i in 0..query.n {
// 		match app_state.listen.get(&i.into()) {
// 			None => return Err(StatusCode::BAD_REQUEST),
// 			Some(liste) => wunschlisten.push(liste.to_owned()),
// 		}
// 	}
//
// 	Ok(Json(wunschlisten))
// }

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

#[derive(Deserialize)]
pub struct CreateWunschlisteEintrag {
	pub wunschliste_id: u64,
	pub eintrag: Item,
}

pub async fn crate_wunschliste_eintrag(State(state): State<AppState>, Json(eintrag): Json<CreateWunschlisteEintrag>) -> Result<(), StatusCode> {
	let mut app_state = state.write().await;

	let liste = app_state.listen.get_mut(&eintrag.wunschliste_id)
		.ok_or(StatusCode::NOT_FOUND)?;

	liste.items.push(eintrag.eintrag);

	Ok(())
}
