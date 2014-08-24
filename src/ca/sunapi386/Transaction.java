package ca.sunapi386;

import java.util.ArrayList;
import java.util.List;

/**
 * Keep a list of snapshots and modify only the last snapshot.
 */

public class Transaction {
    List<Snapshot> snapshots = new ArrayList<Snapshot>();

    public Transaction() {
        snapshots.add(new Snapshot());
    }

    public Snapshot getCurrentSnapshot() {
        return snapshots.get(snapshots.size() - 1);
    }

    public void set(String name, String value) {
        getCurrentSnapshot().set(name, value);
    }

    public void get(String name) {
        getCurrentSnapshot().get(name);
    }

    public void unset(String name) {
        getCurrentSnapshot().unset(name);
    }

    public void numEqualTo(String value) {
        getCurrentSnapshot().numEqualTo(value);
    }

    public void begin() {
        snapshots.add(new Snapshot(getCurrentSnapshot()));
    }

    public void rollback() {
        if (snapshots.size() == 1) {
            System.out.println("NO TRANSACTION");
        } else {
            snapshots.remove(snapshots.size() - 1);
        }
    }

    public void commit() {
        Snapshot desiredSnapshot = getCurrentSnapshot();
        snapshots = new ArrayList<Snapshot>();
        snapshots.add(desiredSnapshot);
    }
}
