package sonixbp.service.impl;

import cloudbase.core.client.Connector;
import cloudbase.core.iterators.filter.Filter;
import sonixbp.service.Triplestore;

import java.util.Map;

public class CloudbaseTriplestore implements Triplestore {

    public CloudbaseTriplestore(Connector connector) {

    }

    public void addPersistentScanFilter(Class<? extends Filter> filter, Map<String,String> config) {

        // adds a filter to every scanner used by the service

    }
}
