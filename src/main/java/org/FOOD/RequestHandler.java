package org.FOOD;

import org.kwabenaberko.newsapilib.NewsApiClient;
import org.kwabenaberko.newsapilib.models.request.EverythingRequest;
import org.kwabenaberko.newsapilib.models.response.ArticleResponse;

public class RequestHandler {

    NewsApiClient newsApiClient = new NewsApiClient("5d3a947c1a7d4e9e8abea0ffa1f074d7");

    public RequestHandler() {

        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q("trump")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println(response.getArticles().get(0).getTitle());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );


    }

}
