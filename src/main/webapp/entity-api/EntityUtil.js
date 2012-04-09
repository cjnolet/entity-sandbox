var Util = {
    lastId:0,
    newId:function(){
        return ++this.lastId;
    },
    
    getAttrValue:function(entity, key){
        return entity.attributes[key][0].value;
    },
    
    getSingleAttr:function(entity, key){
        return entity.attributes[key][0];
    },
    
    getFullAttr:function(entity, key){
        return entity.attributes[key];
    },
    
    getSingleRel:function(entity, key){
        return entity.relationships[key][0];
    },
    
    getFullRel:function(entity, key){
        return entity.relationships[key];
    },
    
    isEntity:function(e){
        // lets pretend this is a good type check for now:)
        return(e.id != undefined &&
               e.type != undefined &&
               e.attributes != undefined &&
               e.relationships != undefined)
    }    
}