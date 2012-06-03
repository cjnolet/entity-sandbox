package sonixbp.service.changeset;

import cloudbase.core.client.Scanner;
import cloudbase.core.data.Key;
import cloudbase.core.data.Value;
import cloudbase.core.iterators.IterationInterruptedException;
import sonixbp.domain.Changeset;

import sonixbp.domain.Entity;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;


public class ChangesetIterator implements Iterator<Changeset> {

    Iterator<Map.Entry<Key,Value>> rowIterator;

    boolean stop;
    String endHash;

    public ChangesetIterator(Iterator<Map.Entry<Key,Value>> rowIterator, String endHash) {

        this.rowIterator = rowIterator;
        this.endHash = endHash;
    }

    public boolean hasNext() {

        if(!stop) {

            return rowIterator.hasNext();
        }

        else {
            return false;
        }
    }

    public Changeset next() {

        if(!stop && rowIterator.hasNext()) {

            Map.Entry<Key,Value> entry = rowIterator.next();

            if(entry.getKey().getColumnFamily().toString().equals(entry)) {
                stop = true;
            }

            return rowToChangeset(entry);
        }

        else {

            return null;
        }
    }

    public void remove() {

        rowIterator.remove();
    }

    private Entity deserializeEntity(String entity) {
        return new Entity("test", "test");
    }

    private Changeset rowToChangeset(Map.Entry<Key,Value> row) {

        return null;
//        return new Changeset(row.getKey().getColumnFamily().toString(),
//                new Date(Long.parseLong(row.getKey().getRow().toString())),
//                deserializeEntity(new String(row.getValue().get())));
    }
}
