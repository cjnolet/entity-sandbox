package sonixbp.acl;

import sonixbp.domain.Attribute;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ACL {

    private Set<String> read;
    private Set<String> write;
    private Set<String> delete;

    public Set<String> getRead() {
        return read;
    }

    public void setRead(Set<String> read) {
        this.read = read;
    }

    public Set<String> getWrite() {
        return write;
    }

    public void setWrite(Set<String> write) {
        this.write = write;
    }

    public Set<String> getDelete() {
        return delete;
    }

    public void setDelete(Set<String> delete) {
        this.delete = delete;
    }

    public void addRead(String role) {

        if(read == null) {
            read = new HashSet<String>();
        }

        read.add(role);
    }

    public void addWrite(String role) {

        if(write == null) {
            write = new HashSet<String>();
        }

        write.add(role);
    }

    public void addDelete(String role) {

        if(delete == null) {
            delete = new HashSet<String>();
        }

        delete.add(role);
    }

    public void applyToAttribute(Attribute attribute) {

        Map<String, Object> metadata = attribute.getMetadata();

        if(metadata == null) {
            metadata = new HashMap<String, Object>();
            attribute.setMetadata(metadata);
        }

        metadata.put(ACLConstants.FIELD_READONLY, getRead());
        metadata.put(ACLConstants.FIELD_READWRITE, getWrite());
        metadata.put(ACLConstants.FIELD_READWRITEDELETE, getDelete());
    }
}
