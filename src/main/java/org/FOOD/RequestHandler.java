package org.FOOD;

import org.kwabenaberko.newsapilib.NewsApiClient;
import org.kwabenaberko.newsapilib.models.Article;
import org.kwabenaberko.newsapilib.models.request.EverythingRequest;
import org.kwabenaberko.newsapilib.models.response.ArticleResponse;

public class RequestHandler {

    NewsApiClient newsApiClient = new NewsApiClient("5d3a947c1a7d4e9e8abea0ffa1f074d7");

    public RequestHandler(String query) {

        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(query)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        for (Article article : response.getArticles()) {
                            System.out.println(article.getTitle());
                            System.out.println(article.getUrl());
                            System.out.println(article.getDescription());
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );


    }

}
