function EntityBuilder(entity){
   
	if(entity instanceof Entity ) {
        this.entity = entity;
    } 
    
    else{
        throw new Error('illegal argument');
    }    
}


EntityBuilder.prototype.attr = function(key, value){
    var attr = {id:Util.newId(), key:key, value:value};
    this.entity.attributes[key] = this.entity.attributes[key] || [];
    this.entity.attributes[key].push(attr);
    return this;
}

EntityBuilder.prototype.rel = function(key, value, targetType){
    var rel = {id:Util.newId(), key:key, value:value, targetType:targetType};
    this.entity.relationships[key] = this.entity.relationships[key] || [];
    this.entity.relationships[key].push(rel);
    return this;
}

EntityBuilder.prototype.create = function(){
    return this.entity;
}

EntityBuilder.prototype.getAttributes = function() {
	return this.entity.attributes;
}

EntityBuilder.prototype.getRelationships = function() {
	return this.entity.relationships;
}

function Changeset(entity, description){
    this.entityId = entity.id;
    this.entityType = entity.type;
    this.description = description;
    this.attributeDeletions = [];
    this.attributeAdditions = [];
    this.relationshipDeletions = [];
    this.relationshipAdditions = [];
}

Changeset.prototype.attr = function(key, value){
    this.attributeAdditions.push({id:Util.newId(), key:key, value:value});
}

Changeset.prototype.rmAttr = function(attribute){
    this.attributeDeletions.push(attribute);
}

Changeset.prototype.rel = function(key, value, targetType){
    this.relationshipAdditions.push({id:Util.newId(), key:key, value:value, targetType:targetType});
}

Changeset.prototype.rmRel = function(relationship){
    this.relationshipDeletions.push(relationship);
}