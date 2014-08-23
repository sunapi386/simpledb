package ca.sunapi386;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        Scanner scanInput = new Scanner(in);
        String[] tokens;
        Map<String, String> keyValueMap = new HashMap<String, String>();
        Map<String, Integer> numEqualToMap = new HashMap<String, Integer>();

        for (; ; ) {
            tokens = scanInput.nextLine().split(" ");
            String cmd = tokens[0];

            if (cmd.equals("SET")) {
                String name = tokens[1];
                String value = tokens[2];
                String previousValue = keyValueMap.containsKey(name) ? keyValueMap.get(name) : null;
                keyValueMap.put(name, value);
                if (numEqualToMap.containsKey(previousValue)) {
                    int updatedCount = numEqualToMap.get(previousValue) - 1;
                    numEqualToMap.put(previousValue, updatedCount);
                }
                if (numEqualToMap.containsKey(value)) {
                    Integer currentCount = numEqualToMap.get(value);
                    numEqualToMap.put(value, currentCount + 1);
                } else {
                    numEqualToMap.put(value, 1);
                }


            } else if (cmd.equals("GET")) {
                String name = tokens[1];
                if (keyValueMap.containsKey(name)) {
                    System.out.println(keyValueMap.get(name));
                } else {
                    System.out.println("NULL");
                }
            } else if (cmd.equals("UNSET")) {
                String name = tokens[1];
                String value = keyValueMap.get(name);
                keyValueMap.remove(name); // does this break if its not in there
                int newCount = numEqualToMap.get(value) - 1;
                numEqualToMap.put(value, newCount);
            } else if (cmd.equals("NUMEQUALTO")) {
                String value = tokens[1];
                Integer count = numEqualToMap.get(value);
                System.out.println(count == null ? 0 : count);
            } else if (cmd.equals("END")) {
                break;
            }
        }

    }
}
