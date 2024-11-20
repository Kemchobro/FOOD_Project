package org.example;

import com.kwabenberko.newsapilib.NewsApiClient;
import com.kwabenberko.newsapilib.models.request.EverythingRequest;
import com.kwabenberko.newsapilib.models.response.ArticleResponse;

public class Main {
    public static void main(String[] args) {
        // Replace "YOUR_API_KEY" with your actual API key
        NewsApiClient newsApiClient = new NewsApiClient("YOUR_API_KEY");

        // Example for fetching news articles
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q("technology")
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        // Print the title of the first article
                        System.out.println(response.getArticles().get(0).getTitle());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        // Print the error message
                        System.out.println("Error: " + throwable.getMessage());
                    }
                }
        );
    }
}
