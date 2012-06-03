package sonixbp.service;

import cloudbase.core.client.mock.MockConnector;
import org.junit.Before;
import org.junit.Test;
import sonixbp.domain.Entity;
import sonixbp.service.impl.CloudbaseTriplestore;
import sonixbp.service.impl.TriplestoreEntityService;

import java.net.URI;
import java.net.URISyntaxException;


public class EntityRelationshipInterpreterTest {

    Triplestore triplestore = new CloudbaseTriplestore(new MockConnector("user"));
    EntityService entityService = new TriplestoreEntityService(triplestore);
    @Before
    public void setUp() {

    }

    @Test
    public void testGenerics() throws URISyntaxException {

        EntityRelationshipInterpreter interpreter = new EntityRelationshipInterpreter(entityService);

        Object entity = interpreter.interpret(new URI("HELLO://goodbye"));

        System.out.println(entity.getClass());


    }
}
