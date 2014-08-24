package ca.sunapi386;

import java.util.Scanner;

import static java.lang.System.in;

/*
 * @author: Jason Sun j53sun@uwaterloo.ca
 * http://www.thumbtack.com/challenges/software-engineer
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanInput = new Scanner(in);
        String[] tokens;
        Transaction transaction = new Transaction();

        for (; ; ) {
            tokens = scanInput.nextLine().split(" ");
            String cmd = tokens[0];
            if (cmd.equals("SET") && tokens.length == 3) {
                String name = tokens[1];
                String value = tokens[2];
                transaction.set(name, value);
            } else if (cmd.equals("GET") && tokens.length == 2) {
                String name = tokens[1];
                transaction.get(name);
            } else if (cmd.equals("UNSET") && tokens.length == 2) {
                String name = tokens[1];
                transaction.unset(name);
            } else if (cmd.equals("NUMEQUALTO") && tokens.length == 2) {
                String value = tokens[1];
                transaction.numEqualTo(value);
            } else if (cmd.equals("END")) {
                break;
            } else if (cmd.equals("BEGIN")) {
                transaction.begin();
            } else if (cmd.equals("ROLLBACK")) {
                transaction.rollback();
            } else if (cmd.equals("COMMIT")) {
                transaction.commit();
            }
        }

    }

}
