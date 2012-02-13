package sonixbp.domain.mutable;

import sonixbp.domain.EValue;
import sonixbp.domain.Entity;

public interface MutableEntity extends Entity {

    void updateAttribute(EValue attribute);

    void removeAttribute(EValue attribute);

    void updateRelationship(EValue relationship);

    void removeRelationship(EValue relationship);

    void apply();

    void undo();
}
