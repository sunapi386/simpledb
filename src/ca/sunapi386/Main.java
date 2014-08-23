package ca.sunapi386;

import java.util.Scanner;

import static java.lang.System.in;

public class Main {

    public static void main(String[] args) {
        Scanner scanInput = new Scanner(in);
        String[] tokens;
        Storage storage = new Storage();


        for (; ; ) {
            tokens = scanInput.nextLine().split(" ");
            String cmd = tokens[0];
            if (cmd.equals("SET")) {
                if (tokens.length != 3) {
                    continue;
                }
                String name = tokens[1];
                String value = tokens[2];
                storage.set(name, value);
                Storage.set(name, value);
            } else if (cmd.equals("GET")) {
                if (tokens.length != 2) {
                    continue;
                }
                String name = tokens[1];
                Storage.get(name);
            } else if (cmd.equals("UNSET")) {
                if (tokens.length != 2) {
                    continue;
                }
                String name = tokens[1];
                storage.unset(name);
            } else if (cmd.equals("NUMEQUALTO")) {
                if (tokens.length != 2) {
                    continue;
                }
                String value = tokens[1];
                Storage.numEqualTo(value);
            } else if (cmd.equals("END")) {
                break;
            }
        }

    }

}
