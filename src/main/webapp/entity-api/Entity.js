function Entity(id, type) {

	this.id = id;
	this.type = type;
	this.timestamp = new Date();
	this.relationships = [];
	this.attributes = [];
	
	return this;
}

function Entity(id, type, timestamp) {

	this.id = id;
	this.type = type;
	this.timestamp = timestamp;
	this.relationships = [];
	this.attributes = [];
	
	return this;
}
