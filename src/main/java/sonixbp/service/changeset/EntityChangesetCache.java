package sonixbp.service.changeset;

import cloudbase.core.client.CBException;
import cloudbase.core.client.CBSecurityException;
import cloudbase.core.data.Key;
import cloudbase.core.data.Value;
import sonixbp.domain.Changeset;
import sonixbp.domain.Entity;

import java.util.Iterator;
import java.util.Map;

public interface EntityChangesetCache {

    void persistEntityChangeset(Entity entity);

    Iterator<Changeset> iterateChangesets(Changeset from, Changeset to) throws CBSecurityException, CBException;
}
