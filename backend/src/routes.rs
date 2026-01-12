use crate::model::{Id, Item, Wunschliste};
use crate::AppState;
use axum::extract::{Json, Query, State};
use axum::http::StatusCode;
use serde::{Deserialize, Serialize};


#[derive(Deserialize)]
pub struct GetWunschliste {
	pub id: u64,
}

pub async fn get_wunschliste(State(state): State<AppState>, Query(query): Query<GetWunschliste>) -> Result<Json<Wunschliste>, StatusCode> {
	let app_state = state.read().await;

	app_state.db.lists.get(&query.id)
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
		match app_state.db.lists.get(&id) {
			None => return Err(StatusCode::NOT_FOUND),
			Some(liste) => wunschlisten.push(liste.to_owned()),
		}
	}

	Ok(Json(wunschlisten))
}


#[derive(Deserialize)]
pub struct CreateWunschliste {
	pub name: String,
	pub description: String,
	pub items: Vec<Item>,
}

#[derive(Serialize)]
pub struct CreateWunschlisteResponse {
	pub id: Id,
	pub liste: Wunschliste,
}

pub async fn create_wunschliste(
	State(state): State<AppState>,
	Json(data): Json<CreateWunschliste>
) -> Json<CreateWunschlisteResponse> {
	let mut app_state = state.write().await;

	let wunschliste = Wunschliste::new(
		data.name,
		data.description,
		data.items.into_iter().map(|x| (app_state.next_entry_id(), x)).collect(),
	);

	let next_id = app_state.next_list_id();
	app_state.db.lists.insert(next_id, wunschliste.clone());

	Json(CreateWunschlisteResponse {
		id: next_id,
		liste: wunschliste,
	})
}


#[derive(Deserialize)]
pub struct PatchWunschliste {
	pub id: u64,
	pub name: Option<String>,
	pub description: Option<String>,
}

pub async fn patch_wunschliste(
	State(state): State<AppState>,
	Json(data): Json<PatchWunschliste>
) -> Result<(), StatusCode> {
	if data.name.is_none() && data.description.is_none() {
		return Err(StatusCode::BAD_REQUEST);
	}

	let mut app_state = state.write().await;

	app_state.db.lists.get_mut(&data.id)
		.ok_or(StatusCode::NOT_FOUND)
		.map(|x| {
			if let Some(name) = data.name {
				x.name = name;
			}
			if let Some(description) = data.description {
				x.description = description;
			}
		})
}


#[derive(Deserialize)]
pub struct RemoveWunschliste {
	pub id: u64,
}

pub async fn remove_wunschliste(
	State(state): State<AppState>,
	Json(data): Json<RemoveWunschliste>
) -> Result<(), StatusCode> {
	let mut app_state = state.write().await;

	app_state.db.lists.remove(&data.id)
		.ok_or(StatusCode::NOT_FOUND)
		.map(|_| ())
}


#[derive(Deserialize)]
pub struct CreateWunschlisteEintrag {
	pub wunschliste_id: u64,
	pub eintrag: Item,
}

#[derive(Serialize)]
pub struct CreateWunschlisteEintragResponse {
	pub id: Id,
}

pub async fn create_wunschliste_eintrag(
	State(state): State<AppState>,
	Json(eintrag): Json<CreateWunschlisteEintrag>
) -> Result<Json<CreateWunschlisteEintragResponse>, StatusCode> {
	let mut app_state = state.write().await;

	let next_id = app_state.next_entry_id();
	let liste = app_state.db.lists.get_mut(&eintrag.wunschliste_id)
		.ok_or(StatusCode::NOT_FOUND)?;

	liste.items.insert(next_id, eintrag.eintrag);

	Ok(Json(CreateWunschlisteEintragResponse { id: next_id }))
}


#[derive(Deserialize)]
pub struct RemoveWunschlisteEintrag {
	pub wunschliste_id: u64,
	pub eintrag_id: Id,
}

pub async fn remove_wunschliste_eintrag(
	State(state): State<AppState>,
	Json(data): Json<RemoveWunschlisteEintrag>
) -> Result<(), StatusCode> {
	let mut app_state = state.write().await;

	let liste = app_state.db.lists.get_mut(&data.wunschliste_id)
		.ok_or(StatusCode::NOT_FOUND)?;

	liste.items.remove(&data.eintrag_id)
		.ok_or(StatusCode::NOT_FOUND)
		.map(|_| ())
}
