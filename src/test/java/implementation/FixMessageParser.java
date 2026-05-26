package implementation;

import java.util.HashMap;
import java.util.Map;

public class FixMessageParser {

    public static Map<String, String> parseFixMessage(String message) {

        Map<String, String> fixMap = new HashMap<>();

        message = message.replace("\001", "|");

        String[] tags = message.split("\\|");

        for(String tag : tags) {

            String[] pair = tag.split("=");

            if(pair.length == 2) {

                fixMap.put(pair[0], pair[1]);
            }
        }

        return fixMap;
    }
}