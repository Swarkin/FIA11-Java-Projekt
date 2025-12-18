use crate::model::Wunschliste;
use serde::{Deserialize, Serialize};
use std::collections::HashMap;
use std::io;
use std::sync::atomic::{AtomicU64, Ordering};

#[derive(Debug, Default, Serialize, Deserialize)]
pub struct State {
	pub id: AtomicU64,
	pub listen: HashMap<u64, Wunschliste>,
}

impl State {
	pub fn next_id(&self) -> u64 {
		self.id.fetch_add(1, Ordering::SeqCst)
	}
}

pub fn load_state() -> io::Result<State> {
	let dir = get_directory()?;

	let err = std::fs::read(dir.join("state.json"));
	if let Err(e) = &err && e.kind() == io::ErrorKind::NotFound {
		return Ok(State::default());
	}

	serde_json::from_slice(&err?)
		.map_err(|err| io::Error::new(io::ErrorKind::InvalidData, err))
}

pub fn save_state(state: State) -> io::Result<()> {
	let dir = get_directory()?;

	std::fs::create_dir_all(&dir)?;

	let json = serde_json::to_string_pretty(&state)?;
	std::fs::write(dir.join("state.json"), json)
}

fn get_directory() -> io::Result<std::path::PathBuf> {
	let dir = dirs::data_dir()
		.ok_or(io::Error::other("data directory not found"))?
		.join("FIA11-Java-Projekt");

	Ok(dir)
}
