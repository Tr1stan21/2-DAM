package util;

import java.util.HashMap;
import java.util.Map;

public class CommandMap {
    public static void main(String[] args) {
        Map<String, String> commandMap = new HashMap<>();

        commandMap.put("list", "dir");
        commandMap.put("show", "type");
        commandMap.put("delete", "del");
        commandMap.put("quit", "exit");

        for (Map.Entry<String, String> entry : commandMap.entrySet()) {
            System.out.println("Comando: " + entry.getKey() + " -> Comando en Windows: " + entry.getValue());
        }
    }
}

