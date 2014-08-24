package ca.sunapi386;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    // Commands are GET, SET, UNSET, and NUMEQUALTO.
    // All of these commands have expected worst-case runtime of O(log N), where N is number of variables stored.
    // Using HashMap gives us this nice property. It's necessary to use two maps so NUMEQUALTO bound O(log N).
    private Map<String, String> keyValueMap ;
    private Map<String, Integer> numEqualToMap;

    public Storage() {
        keyValueMap = new HashMap<String, String>();
        numEqualToMap = new HashMap<String, Integer>();
    }

    public Storage(Storage another) {
        this.keyValueMap = new HashMap<String, String>();
        this.keyValueMap.putAll(another.keyValueMap);
        this.numEqualToMap = new HashMap<String, Integer>();
        this.numEqualToMap.putAll(another.numEqualToMap);
    }

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
