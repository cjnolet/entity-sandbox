package sonixbp.service.changeset;

import cloudbase.core.client.*;
import cloudbase.core.client.Scanner;
import cloudbase.core.data.Key;
import cloudbase.core.data.Mutation;
import cloudbase.core.data.Range;
import cloudbase.core.data.Value;
import sonixbp.domain.Changeset;

import sonixbp.domain.Entity;

import java.util.*;

public class CloudbaseEntityChangesetCache implements EntityChangesetCache {

    private static String TABLE = "triplestore_changeset";
    private static int DATE_LENGTH = 16;
    Connector connector;

    BatchWriter writer;

    public CloudbaseEntityChangesetCache(Connector connector) {

        this.connector = connector;
        try {
            this.writer = connector.createBatchWriter(TABLE, 100000L, 10000L, 10);
        } catch (TableNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void persistEntityChangeset(Entity entity) {

        Mutation m = new Mutation(indexedDate(entity.getTimestamp()));
        m.put(hashEntity(entity), "", new Value(serializeEntity(entity).getBytes()));

        try {
            writer.addMutation(m);
        } catch (MutationsRejectedException e) {
            e.printStackTrace();
        }
    }

    public Iterator<Changeset> iterateChangesets(Changeset from, Changeset to) throws CBSecurityException, CBException {

        Range range = new Range(indexedDate(from.getTimestamp()), indexedDate(to.getTimestamp()));

        Scanner scanner = null;
        try {
            scanner = connector.createScanner(TABLE,
                    connector.securityOperations().getUserAuthorizations(connector.whoami()));

        } catch (TableNotFoundException e) {

            return null;
        }

        scanner.setRange(range);

        Iterator<Map.Entry<Key,Value>> iterator = scanner.iterator();
        Map.Entry<Key,Value> entry = null;

        while(iterator.hasNext() && !entry.getKey().getColumnFamily().toString().equals(from.getHash())) {
            entry = iterator.next();
        }

        return new ChangesetIterator(iterator, to.getHash());
    }


    public String indexedDate(Date date) {
        String time = Long.toString(date.getTime());
        String padding = "";
        for(int i = 0; i < DATE_LENGTH - time.length(); i++) {
            padding += "0";
        }

        return padding + time;
   }

    // TODO: This should be an MD5 hash of all of the properties that make an entity unique
    public String hashEntity(Entity entity) {
        return "HASH" + Math.random() * 5000000;
    }


    public String serializeEntity(Entity entity) {
        return entity.toString();
    }
}
