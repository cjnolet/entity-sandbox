package sonixbp.service;

import sonixbp.domain.BasicEntity;

import java.util.Collection;

public interface EntityService {

    void save(BasicEntity entity);
    void save(BasicEntity... entities);

    BasicEntity getByTypeAndId(String type, String id);

    Collection<BasicEntity> getAllByTypesAndIds(String[] types, String[] ids);
}
