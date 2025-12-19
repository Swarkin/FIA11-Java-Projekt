use serde::{Deserialize, Serialize};
use std::collections::HashMap;

pub type Id = u64;

#[derive(Debug, Default, Serialize, Deserialize)]
pub struct Database {
	pub lists: HashMap<Id, Wunschliste>,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct Wunschliste {
	pub name: String,
	pub description: String,
	pub items: HashMap<Id, Item>,
}

impl Wunschliste {
	pub fn new(name: String, description: String, items: HashMap<Id, Item>) -> Self {
		Self { name, description, items }
	}
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct Item {
	pub name: String,
}

impl Item {
	pub fn new(name: String) -> Self {
		Self { name }
	}
}
