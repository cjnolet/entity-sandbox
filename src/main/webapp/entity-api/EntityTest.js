test('basic change set', function(){
    var fHeywood = new EntityBuilder('Person').
        attr('First Name', 'Felicity').
        attr('Last Name', 'Heywood').create();
        
    var rScott = new EntityBuilder('Person').
        attr('First Name', 'Ridley').
        attr('Last Name', 'Scott').
        attr('Favorite Color', 'Black').
        rel('wife', fHeywood.id, fHeywood.type).create();
    
    ok(rScott.id != null, 'rScott id ok');
    equals('Person', rScott.type, 'rScott type ok');
    
    var fName = Util.getSingleAttr(rScott, 'First Name');
    equals('Ridley', fName.value, 'First Name ok');
    ok(fName.id != null, 'first name id ok');
    
    var lName = Util.getSingleAttr(rScott, 'Last Name');
    equals('Scott', lName.value, 'Last Name ok');
    ok(lName.id != null, 'last name id ok');
    
    var favColor = Util.getSingleAttr(rScott, 'Favorite Color');
    equals('Black', favColor.value, 'Favorite Color value ok');
    ok(favColor.id != null, 'Favorite Color id ok');
    
    var wife1 = Util.getSingleRel(rScott, 'wife');
    equals(fHeywood.id, wife1.value, 'wife 1 is ok');
    equals(fHeywood.type, wife1.targetType, 'wife 1 is ok');
    
    var wife2 = new EntityBuilder('Person').
        attr('First Name', 'Sandy').
        attr('Last Name', 'Watson').create();
    
    var changeset = new Changeset(rScott, 'updates to ridley scott');
    changeset.rmAttr(favColor);
    changeset.attr('DOB', '30 November 1937');
    changeset.rmRel(wife1);
    changeset.rel('ex-wife', fHeywood.id, fHeywood.type);
    changeset.rel('wife', wife2.id, wife2.type);
    
    equals(rScott.id, changeset.entityId);
    equals(rScott.type, changeset.entityType);
    equals('updates to ridley scott', changeset.description);
    
    equals(1, changeset.attributeDeletions.length);
    equals(1, changeset.attributeAdditions.length);
    equals(1, changeset.relationshipDeletions.length);
    equals(2, changeset.relationshipAdditions.length);
    
    // and here is what we would POST to /entity-service/entity/changeset to apply the changes
    console.log(JSON.stringify(changeset));
});

test('continued entity creation', function(){
    var greenCar = new EntityBuilder('Car').
        attr('color', 'green').
        attr('mileage', 89000).create();
    
    ok(greenCar.id != null, 'greenCar id not null');
    equals('Car', greenCar.type, 'greenCar type ok');
    
    var color = Util.getSingleAttr(greenCar, 'color');
    ok(color.id != null, 'outback color id ok');
    equals('green', color.value, 'greenCar color ok');
    
    var mileage = Util.getSingleAttr(greenCar, 'mileage');
    ok(mileage.id != null, 'mileage id ok');
    equals(89000, mileage.value, 'greenCar mileage ok');
    
    var outback = new EntityBuilder(greenCar).
        attr('make', 'Subaru').
        attr('model', 'Outback').
        attr('year', 2002).create();
        
    equals(greenCar.id, outback.id, 'outback id matches greenCar id');
    equals(greenCar.type, outback.type, 'outback type matches greenCar');
    
    var color = Util.getSingleAttr(outback, 'color');
    ok(color.id != null, 'outback color id ok');
    equals('green', color.value, 'greenCar color ok');
    
    var mileage = Util.getSingleAttr(outback, 'mileage');
    ok(mileage.id != null, 'outback mileage id ok');
    equals(89000, mileage.value, 'outback mileage ok');
    
    var make = Util.getSingleAttr(outback, 'make');
    ok(make.id != null, 'outback make id ok');
    equals('Subaru', make.value, 'outback make ok');
    
    var model = Util.getSingleAttr(outback, 'model');
    ok(model.id != null, 'outback model id ok');
    equals('Outback', model.value, 'outback model ok');
    
    var year = Util.getSingleAttr(outback, 'year');
    ok(year.id != null, 'outback year id ok');
    equals(2002, year.value, 'outback year ok');
});

test('simple entity creation', function(){
    var rScott = new EntityBuilder('Person').
        attr('First Name', 'Ridley').
        attr('Last Name', 'Scott').create();
    
    ok(rScott.id != null, 'rScott id ok');
    equals('Person', rScott.type, 'rScott type ok');
    
    var fName = Util.getSingleAttr(rScott, 'First Name');
    equals('Ridley', fName.value, 'First Name ok');
    ok(fName.id != null, 'first name id ok');
    
    var lName = Util.getSingleAttr(rScott, 'Last Name');
    equals('Scott', lName.value, 'Last Name ok');
    ok(lName.id != null, 'last name id ok');
    
     var bladeRunner = new EntityBuilder('Movie').
        attr('title', 'Blade Runner').
        attr('year', 1982).
        attr('genre', 'Sci-fi').
        attr('genre', 'Thriller').
        attr('genre', 'Drama').
        rel('director', rScott.id, rScott.type).create();
        
        ok(bladeRunner.id != null, 'bladeRunner id ok');
        equals('Movie', bladeRunner.type, 'bladeRunner type ok');
        
        var title = Util.getSingleAttr(bladeRunner, 'title');
        equals('Blade Runner', title.value, 'title ok');
        ok(title.id != null, 'title id not null');
        
        var year = Util.getSingleAttr(bladeRunner, 'year');
        equals(1982, year.value, 'year ok');
        ok(year.id != null, 'year id not null');
        
        var genres = Util.getFullAttr(bladeRunner, 'genre');
        equals(3, genres.length, 'all genres ok');
        for(var i=0;i<genres.length;i++){
            var g = genres[i];
            ok(g.value == 'Sci-fi' || g.value == 'Thriller' || g.value == 'Drama', 'genre value <' + g.value + '> ok');
            ok(g.id != null, 'genre id <' + g.id + '> ok')
        }
        
        var director = Util.getSingleRel(bladeRunner, 'director');
        ok(director.id != null, 'director    id not null');
        equals(director.targetType, rScott.type, 'director targetType ok');
        equals(director.value, rScott.id, 'director value ok');
});