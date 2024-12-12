package org.FOOD;

import java.util.HashMap;
import java.util.Map;

public class CitationGenerator {

     //Generates a citation from the article details.

    public static String generateCitation(String article) {
        // Parse the article details
        Map<String, String> details = parseArticleDetails(article);

        // Build citation
        StringBuilder citation = new StringBuilder();
        citation.append(details.getOrDefault("Title", "No Title")).append(". ");
        if (details.containsKey("Author") && !details.get("Author").isEmpty()) {
            citation.append(details.get("Author")).append(". ");
        }
        if (details.containsKey("Published At") && !details.get("Published At").isEmpty()) {
            citation.append("(").append(details.get("Published At")).append("). ");
        }
        if (details.containsKey("URL") && !details.get("URL").isEmpty()) {
            citation.append("Retrieved from ").append(details.get("URL"));
        }

        return citation.toString();
    }

     //Parses the article details string into a key-value map returns The full article details as a string.
     
    private static Map<String, String> parseArticleDetails(String article) {
        Map<String, String> details = new HashMap<>();
        String[] lines = article.split("\n");
        for (String line : lines) {
            if (line.contains(":")) {
                String[] keyValue = line.split(":", 2);
                if (keyValue.length == 2) {
                    details.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }
        }
        return details;
    }
}