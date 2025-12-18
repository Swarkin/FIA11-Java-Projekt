use serde::{Serialize, Deserialize};

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct Wunschliste {
	pub name: String,
	pub description: String,
	pub items: Vec<Item>,
}

impl Wunschliste {
	pub fn new(name: String, description: String, items: Vec<Item>) -> Self {
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
