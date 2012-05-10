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

    boolean hashSeen = false;
    String beginHash;
    String endHash;

    public ChangesetIterator(Iterator<Map.Entry<Key,Value>> rowIterator, String beginHash, String endHash) {

        this.rowIterator = rowIterator;
        this.beginHash = beginHash;
        this.endHash = endHash;
    }

    public boolean hasNext() {

        return rowIterator.hasNext();
    }

    public Changeset next() {

        if(rowIterator.hasNext()) {

            Map.Entry<Key,Value> entry = rowIterator.next();

            if(!hashSeen) {

                // We may need to iterate just a tiny bit to find the row in question... given the millisecond precision
                // of our timestamps, we shouldn't need to iterate much.
                while(!entry.getKey().getColumnFamily().toString().equals(beginHash)) {

                    entry = rowIterator.next();
                }

                hashSeen = true;
            }

            return rowToChangeset(entry);
        }

        else {
            throw new IterationInterruptedException();
        }
    }

    public void remove() {

        rowIterator.remove();
    }

    private Entity deserializeEntity(String entity) {
        return new Entity("test", "test");
    }

    private Changeset rowToChangeset(Map.Entry<Key,Value> row) {

        return new Changeset(row.getKey().getColumnFamily().toString(),
                new Date(Long.parseLong(row.getKey().getRow().toString())),
                deserializeEntity(new String(row.getValue().get())));
    }
}
