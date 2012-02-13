package sonixbp.domain.mutable;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import sonixbp.domain.EValue;
import sonixbp.domain.Entity;
import sonixbp.domain.EntityImpl;

public class MutableEntityImplTest {

    @Test
    public void testAddAttribute(){
        MutableEntityImpl entity = new MutableEntityImpl(new EntityImpl("test", "1234"));
        EValue attr1 = new EValue("key1", "value1");
        entity.addAttribute(attr1);

        // null until applied
        assertNull(entity.getFullAttribute("key1"));

        entity.apply();
        assertNotNull(entity.getFullAttribute("key1"));
        assertEquals(1, entity.getFullAttribute("key1").size());

        EValue appliedAttr1 = entity.getSingleAttribute("key1");
        assertEquals(attr1, appliedAttr1);
    }

    @Test
    public void testUpdateAttribute(){
        MutableEntityImpl entity = new MutableEntityImpl(new EntityImpl("test", "1234"));
        EValue attr1 = new EValue("key1", "value1");
        entity.addAttribute(attr1);

        // null until applied
        assertNull(entity.getFullAttribute("key1"));

        entity.apply();
        EValue appliedAttr1 = entity.getSingleAttribute("key1");
        assertEquals(attr1, appliedAttr1);

        // update attr1's value
        // not really liking this way of updating, ideas?
        entity.updateAttribute(new EValue(attr1, "updatedValue1"));

        // unchanged until applied
        EValue unchangedAttr1 = entity.getSingleAttribute("key1");
        assertEquals("value1", unchangedAttr1.getValue());

        entity.apply();
        EValue updatedAttr1 = entity.getSingleAttribute("key1");
        assertEquals("updatedValue1", updatedAttr1.getValue());
    }

    @Test
    public void testRemoveAttribute(){
        Entity entity = new EntityImpl("test", "1234");
        EValue attr1 = new EValue("key1", "value1");
        entity.addAttribute(attr1);
        MutableEntityImpl mutableEntity = new MutableEntityImpl(entity);

        // assert it's there
        assertEquals(attr1,  mutableEntity.getSingleAttribute("key1"));
        mutableEntity.removeAttribute(attr1);

        // assert it's still there until applied
        assertEquals(attr1,  mutableEntity.getSingleAttribute("key1"));

        mutableEntity.apply();
        // all gone
        assertEquals(0,  mutableEntity.getFullAttribute("key1").size());
    }

    @Test
    public void testAddRelationship(){
        MutableEntityImpl entity = new MutableEntityImpl(new EntityImpl("test", "1234"));
        EValue attr1 = new EValue("key1", "value1");
        entity.addRelationship(attr1);

        // null until applied
        assertNull(entity.getFullRelationship("key1"));

        entity.apply();
        assertNotNull(entity.getFullRelationship("key1"));
        assertEquals(1, entity.getFullRelationship("key1").size());

        EValue appliedAttr1 = entity.getSingleRelationship("key1");
        assertEquals(attr1, appliedAttr1);
    }

    @Test
    public void testUpdateRelationship(){
        MutableEntityImpl entity = new MutableEntityImpl(new EntityImpl("test", "1234"));
        EValue attr1 = new EValue("key1", "value1");
        entity.addRelationship(attr1);

        // null until applied
        assertNull(entity.getFullRelationship("key1"));

        entity.apply();
        EValue appliedAttr1 = entity.getSingleRelationship("key1");
        assertEquals(attr1, appliedAttr1);

        // update attr1's value
        // not really liking this way of updating, ideas?
        entity.updateRelationship(new EValue(attr1, "updatedValue1"));

        // unchanged until applied
        EValue unchangedAttr1 = entity.getSingleRelationship("key1");
        assertEquals("value1", unchangedAttr1.getValue());

        entity.apply();
        EValue updatedAttr1 = entity.getSingleRelationship("key1");
        assertEquals("updatedValue1", updatedAttr1.getValue());
    }

    @Test
    public void testRemoveRelationship(){
        Entity entity = new EntityImpl("test", "1234");
        EValue attr1 = new EValue("key1", "value1");
        entity.addRelationship(attr1);
        MutableEntityImpl mutableEntity = new MutableEntityImpl(entity);

        // assert it's there
        assertEquals(attr1,  mutableEntity.getSingleRelationship("key1"));
        mutableEntity.removeRelationship(attr1);

        // assert it's still there until applied
        assertEquals(attr1,  mutableEntity.getSingleRelationship("key1"));

        mutableEntity.apply();
        // all gone
        assertEquals(0,  mutableEntity.getFullRelationship("key1").size());
    }
}
