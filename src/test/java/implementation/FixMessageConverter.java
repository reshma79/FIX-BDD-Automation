package implementation;

import java.util.LinkedHashMap;
import java.util.Map;

public class FixMessageConverter {

    public static String convertToFixString(String rawTemplate) {

        Map<String, String> fixMap = parseTemplate(rawTemplate);

        StringBuilder builder = new StringBuilder();

        for(Map.Entry<String, String> entry : fixMap.entrySet()) {

            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("|");
        }

        return builder.toString();
    }

    private static Map<String, String> parseTemplate(String template) {

        Map<String, String> fixMap = new LinkedHashMap<>();

        String[] tags = template.split("\\|");

        for(String tag : tags) {

            String[] pair = tag.split("=");

            if(pair.length == 2) {

                fixMap.put(pair[0], pair[1]);
            }
        }

        return fixMap;
    }
}