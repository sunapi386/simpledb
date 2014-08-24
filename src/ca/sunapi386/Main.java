package ca.sunapi386;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;

// http://www.thumbtack.com/challenges/software-engineer
public class Main {

    public static void main(String[] args) {
        Scanner scanInput = new Scanner(in);
        String[] tokens;
        List<Snapshot> snapshots = new ArrayList<Snapshot>();
        snapshots.add(new Snapshot());

        for (; ; ) {
            // always modify the last transaction state
            Snapshot currentSnapshot = snapshots.get(snapshots.size() - 1);
            tokens = scanInput.nextLine().split(" ");
            String cmd = tokens[0];
            if (cmd.equals("SET")) {
                if (tokens.length != 3) {
                    continue;
                }
                String name = tokens[1];
                String value = tokens[2];
                currentSnapshot.set(name, value);
            } else if (cmd.equals("GET")) {
                if (tokens.length != 2) {
                    continue;
                }
                String name = tokens[1];
                currentSnapshot.get(name);
            } else if (cmd.equals("UNSET")) {
                if (tokens.length != 2) {
                    continue;
                }
                String name = tokens[1];
                currentSnapshot.unset(name);
            } else if (cmd.equals("NUMEQUALTO")) {
                if (tokens.length != 2) {
                    continue;
                }
                String value = tokens[1];
                currentSnapshot.numEqualTo(value);
            } else if (cmd.equals("END")) {
                break;
            } else if (cmd.equals("BEGIN")) {
                snapshots.add(new Snapshot(currentSnapshot));
            } else if (cmd.equals("ROLLBACK")) {
                if (snapshots.size() == 1) {
                    System.out.println("NO TRANSACTION");
                } else {
                    snapshots.remove(snapshots.size() - 1);
                }
            } else if (cmd.equals("COMMIT")) {
                snapshots = new ArrayList<Snapshot>();
                snapshots.add(currentSnapshot);
            }
        }

    }

}
