package com.techangelx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FinestFoodOutlet {

    public static String finestFoodOutlet(String city, int votesThreshold) {

        int page = 1;
        int totalPages = 1;

        String bestName = null;
        double bestRating = -1;
        int bestCost = Integer.MAX_VALUE;

        while (page <= totalPages) {

            String url = "https://jsonmock.hackerrank.com/api/food_outlets?city="
                    + city + "&page=" + page;

            StringBuilder response = new StringBuilder();

            try {
                URL u = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );

                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

            } catch (Exception e) {
                return "No Results Found";
            }

            String json = response.toString();

            // extract total_pages once
            if (page == 1) {
                int idx = json.indexOf("\"total_pages\":");
                if (idx != -1) {
                    int start = json.indexOf(":", idx) + 1;
                    int end = json.indexOf(",", start);
                    totalPages = Integer.parseInt(json.substring(start, end).trim());
                }
            }

            // extract the "data" array
            int dataStart = json.indexOf("\"data\":");
            int arrayStart = json.indexOf("[", dataStart);
            int arrayEnd = json.indexOf("]", arrayStart);

            if (arrayStart == -1 || arrayEnd == -1) {
                page++;
                continue;
            }

            String rawObjects = json.substring(arrayStart + 1, arrayEnd);
            if (rawObjects.trim().isEmpty()) {
                page++;
                continue;
            }

            // split into objects
            String[] objects = rawObjects.split("\\},\\{");

            for (String obj : objects) {
                obj = obj.replace("{", "").replace("}", "");

                int votes = parseInt(obj, "\"votes\":");
                if (votes < votesThreshold) continue;

                double rating = parseDouble(obj, "\"average_rating\":");
                int cost = parseInt(obj, "\"estimated_cost\":");
                String name = parseString(obj, "\"name\":\"");

                if (name == null) continue;

                // tie-breaking rules
                if (rating > bestRating ||
                        (rating == bestRating && cost < bestCost) ||
                        (rating == bestRating && cost == bestCost && name.compareTo(bestName) < 0)) {

                    bestRating = rating;
                    bestCost = cost;
                    bestName = name;
                }
            }

            page++;
        }

        return (bestName == null) ? "No Results Found" : bestName;
    }

    // parsing helpers
    private static int parseInt(String text, String key) {
        int idx = text.indexOf(key);
        if (idx == -1) return -1;
        int start = idx + key.length();
        int end = text.indexOf(",", start);
        if (end == -1) end = text.length();
        return Integer.parseInt(text.substring(start, end).trim());
    }

    private static double parseDouble(String text, String key) {
        int idx = text.indexOf(key);
        if (idx == -1) return -1;
        int start = idx + key.length();
        int end = text.indexOf(",", start);
        if (end == -1) end = text.length();
        return Double.parseDouble(text.substring(start, end).trim());
    }

    private static String parseString(String text, String key) {
        int idx = text.indexOf(key);
        if (idx == -1) return null;
        int start = idx + key.length();
        int end = text.indexOf("\"", start);
        if (end == -1) return null;
        return text.substring(start, end);
    }
}
