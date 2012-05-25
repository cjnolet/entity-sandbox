package sonixbp.domain;

import org.apache.hadoop.util.hash.Hash;

import java.util.HashSet;
import java.util.Set;

import java.util.Date;

public class Changeset {

    Date timestamp;
    String committer;

    Entity entity;

    public String getHash() {

        // here is where we want to hash the changes (the tuples all need to be hashed collectively)
        return null;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getCommitter() {
        return committer;
    }

    public Entity getEntity() {
        return entity;
    }

    public Changeset(Date timestamp, String committer, Entity entity) {

        this.timestamp = timestamp;
        this.committer = committer;
        this.entity = entity;
    }
}