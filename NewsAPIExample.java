import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsAPIExample {
    // Replace with your NewsAPI key
    private static final String API_KEY = "2ba0e72a131a480b833a4b7448abd241";
    private static final String API_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + "2ba0e72a131a480b833a4b7448abd241";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();

                // Parse and print the response
                parseAndPrintResponse(jsonResponse);
            } else {
                System.out.println("Failed to fetch data. HTTP Code: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseAndPrintResponse(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        if (jsonObject.has("articles")) {
            JsonArray articles = jsonObject.getAsJsonArray("articles");

            System.out.println("Top News Headlines:");
            for (int i = 0; i < articles.size(); i++) {
                JsonObject article = articles.get(i).getAsJsonObject();
                String title = article.get("title").getAsString();
                String url = article.get("url").getAsString();

                System.out.println((i + 1) + ". " + title);
                System.out.println("   Link: " + url);
            }
        } else {
            System.out.println("No articles found in the API response.");
        }
    }
}