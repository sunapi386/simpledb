package ca.sunapi386;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private Map<String, String> keyValueMap = new HashMap<String, String>();
    private Map<String, Integer> numEqualToMap = new HashMap<String, Integer>();

    public void set(String name, String value) {
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
    }

    public void numEqualTo(String value) {
        Integer count = numEqualToMap.get(value);
        System.out.println(count == null ? 0 : count);
    }

    public void unset(String name) {
        String value = keyValueMap.get(name);
        keyValueMap.remove(name); // does this break if its not in there
        int newCount = numEqualToMap.get(value) - 1;
        numEqualToMap.put(value, newCount);
    }

    public void get(String name) {
        if (keyValueMap.containsKey(name)) {
            System.out.println(keyValueMap.get(name));
        } else {
            System.out.println("NULL");
        }
    }
}
