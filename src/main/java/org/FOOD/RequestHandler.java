package org.FOOD;

import java.util.function.Consumer;

import org.kwabenaberko.newsapilib.NewsApiClient;
import org.kwabenaberko.newsapilib.models.Article;
import org.kwabenaberko.newsapilib.models.Source;
import org.kwabenaberko.newsapilib.models.request.EverythingRequest;
import org.kwabenaberko.newsapilib.models.request.SourcesRequest;
import org.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import org.kwabenaberko.newsapilib.models.response.ArticleResponse;
import org.kwabenaberko.newsapilib.models.response.SourcesResponse;

import javafx.application.Platform;

public class RequestHandler {
    // uses the api so i do not have to make http requests
    NewsApiClient newsApiClient = new NewsApiClient("5d3a947c1a7d4e9e8abea0ffa1f074d7");

    private Consumer<String> onSuccessCallback;
    private Consumer<String> onFailureCallback;

    public RequestHandler(String query, RequestType requestType) {
        // lets you switch between different types of requests, but the other two from the api threw errors
        switch (requestType) {
            case EVERYTHING -> {
                newsApiClient.getEverything(
                        new EverythingRequest.Builder()
                                .q(query)
                                .build(),
                        new NewsApiClient.ArticlesResponseCallback() {
                            @Override
                            public void onSuccess(ArticleResponse response) {
                                StringBuilder result = new StringBuilder();
                                for (Article article : response.getArticles()) {
                                    result.append("Title: ").append(article.getTitle()).append("\n")
                                            .append("URL: ").append(article.getUrl()).append("\n")
                                            .append("Description: ").append(article.getDescription()).append("\n")
                                            .append("Content: ").append(article.getContent()).append("\n")
                                            .append("Published At: ").append(DateHelpers.getDate(article.getPublishedAt())).append("\n\n");
                                }
                                handleSuccess(result.toString());
                            }

                            @Override
                            public void onFailure(Throwable throwable) {
                                handleFailure(throwable);
                            }
                        });
            }
            case HEADLINES -> {
                newsApiClient.getTopHeadlines(
                        new TopHeadlinesRequest.Builder()
                                .q(query)
                                .build(),
                        new NewsApiClient.ArticlesResponseCallback() {
                            @Override
                            public void onSuccess(ArticleResponse response) {
                                StringBuilder result = new StringBuilder();
                                for (Article article : response.getArticles()) {
                                    result.append("Title: ").append(article.getTitle()).append("\n")
                                            .append("URL: ").append(article.getUrl()).append("\n")
                                            .append("Description: ").append(article.getDescription()).append("\n")
                                            .append("Content: ").append(article.getContent()).append("\n")
                                            .append("Published At: ").append(article.getPublishedAt()).append("\n\n");
                                }
                                handleSuccess(result.toString());
                            }

                            @Override
                            public void onFailure(Throwable throwable) {
                                handleFailure(throwable);
                            }
                        });
            }
            case SOURCES -> {
                newsApiClient.getSources(
                        new SourcesRequest.Builder()
                                .category(query)
                                .build(),
                        new NewsApiClient.SourcesCallback() {
                            @Override
                            public void onSuccess(SourcesResponse response) {
                                StringBuilder result = new StringBuilder();
                                for (Source source : response.getSources()) {
                                    result.append("Name: ").append(source.getName()).append("\n")
                                            .append("Description: ").append(source.getDescription()).append("\n")
                                            .append("Category: ").append(source.getCategory()).append("\n")
                                            .append("URL: ").append(source.getUrl()).append("\n\n");
                                }
                                handleSuccess(result.toString());
                            }

                            @Override
                            public void onFailure(Throwable throwable) {
                                handleFailure(throwable);
                            }
                        });
            }
        }
    }

    // Success handler
    public void handleSuccess(String response) {
        if (onSuccessCallback != null) {
            Platform.runLater(() -> onSuccessCallback.accept(response));
        }
    }

    // Failure handler
    public void handleFailure(Throwable throwable) {
        if (onFailureCallback != null) {
            Platform.runLater(() -> onFailureCallback.accept("Error: " + throwable.getMessage()));
        }
    }

    // Setters for callbacks
    public void setOnSuccessCallback(Consumer<String> callback) {
        this.onSuccessCallback = callback;
    }

    public void setOnFailureCallback(Consumer<String> callback) {
        this.onFailureCallback = callback;
    }

    public enum RequestType {
        EVERYTHING,
        HEADLINES,
        SOURCES
    }
}