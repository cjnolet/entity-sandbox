package sonixbp.acl;

import sonixbp.acl.exception.UserAccessDeniedException;
import sonixbp.domain.BasicEntity;
import sonixbp.service.EntityService;

import java.util.Collection;
import java.util.Set;


public class ACLEntityService {

    EntityService entityService;


    public void save(BasicEntity entity, Set<String> roles) throws UserAccessDeniedException {


        // first we need to query back all of the triples for the entity in question and verify (after querying)
        // whether or not the triples are able to be written to. If any of them can't be written to,


    }

    public BasicEntity getByTypeAndId(String type, String id) {
        return null;
    }

    public Collection<BasicEntity> getAllByTypesAndIds(String[] types, String[] ids) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
