package sonixbp.domain;

import java.util.Date;

public class Changeset {

    String hash;
    Date timestamp;

    Entity entity;

    public String getHash() {
        return hash;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Entity getEntity() {
        return entity;
    }

    public Changeset(String hash, Date timestamp, Entity entity) {

        this.hash = hash;
        this.timestamp = timestamp;
        this.entity = entity;
    }
}