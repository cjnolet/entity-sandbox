package sonixbp.service.impl;

import cloudbase.core.client.Connector;
import sonixbp.domain.BasicEntity;
import sonixbp.service.EntityService;
import sonixbp.service.Triplestore;

import java.util.Collection;

public class TriplestoreEntityService implements EntityService {

    @Deprecated
    public TriplestoreEntityService(Connector connector) {

    }

    public TriplestoreEntityService(Triplestore triplestore) {

    }

    public void save(BasicEntity entity) {
    }

    public void save(BasicEntity... entities) {

    }

    public BasicEntity getByTypeAndId(String type, String id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Collection<BasicEntity> getAllByTypesAndIds(String[] types, String[] ids) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
