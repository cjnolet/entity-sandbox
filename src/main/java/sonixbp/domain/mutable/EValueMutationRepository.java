package sonixbp.domain.mutable;

import sonixbp.domain.EValue;
import sonixbp.domain.mutable.MutableEntityImpl;

public interface EValueMutationRepository{

    void clear();

    void apply();

    void addAttribute(EValue attribute);

    void updateAttribute(EValue attribute);

    void removeAttribute(EValue attribute);

    void addRelationship(EValue relationship);

    void updateRelationship(EValue relationship);

    void removeRelationship(EValue relationship);
}
