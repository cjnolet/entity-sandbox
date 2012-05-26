package sonixbp.service;

import cloudbase.core.client.Connector;
import sonixbp.acl.CloudbaseTripleStoreACLFilter;
import sonixbp.service.impl.CloudbaseTriplestore;
import sonixbp.service.impl.TriplestoreEntityService;

public class CyberpilotEntityServiceWrapper {

    Connector connector;
    CloudbaseTriplestore triplestore;
    TriplestoreEntityService entityService;

    public CyberpilotEntityServiceWrapper(Connector connector) {

        this.connector = connector;
        this.triplestore = new CloudbaseTriplestore(connector);
        this.entityService = new TriplestoreEntityService(triplestore);

        // this will guarantee that no triples with ACL's make it through by default
        triplestore.addPersistentScanFilter(CloudbaseTripleStoreACLFilter.class, null);
    }

    public EntityService getEntityService() {

        return entityService;
    }
}
