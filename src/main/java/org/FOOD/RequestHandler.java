package org.FOOD;

import org.kwabenaberko.newsapilib.NewsApiClient;
import org.kwabenaberko.newsapilib.models.Article;
import org.kwabenaberko.newsapilib.models.Source;
import org.kwabenaberko.newsapilib.models.request.EverythingRequest;
import org.kwabenaberko.newsapilib.models.request.SourcesRequest;
import org.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import org.kwabenaberko.newsapilib.models.response.ArticleResponse;
import org.kwabenaberko.newsapilib.models.response.SourcesResponse;

import javax.xml.stream.events.Characters;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.*;
import java.util.Locale;

public class RequestHandler {

    NewsApiClient newsApiClient = new NewsApiClient("5d3a947c1a7d4e9e8abea0ffa1f074d7");


    public RequestHandler(String query, RequestType requestType) {


        switch (requestType) {
            case EVERYTHING ->
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
                                System.out.println(article.getContent());
                                try {

                                    // Getting the Date from String by
                                    // creating object of Instant class
                                    Instant timestamp = getDateFromString(article.getPublishedAt());

                                    // Printing the converted date

                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                            .withZone(ZoneId.systemDefault());
                                    String customFormatted = formatter.format(timestamp);
                                    System.out.println("Date: " + customFormatted);

                                }

                                // Catch block to handle exceptions
                                catch (DateTimeParseException e) {

                                    // Throws DateTimeParseException
                                    // if the string cannot be parsed
                                    System.out.println("Exception: " + e);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            System.out.println(throwable.getMessage());
                        }
                    }
            );


            case HEADLINES ->

                    newsApiClient.getTopHeadlines(
                            new TopHeadlinesRequest.Builder()
                                    .q(query)
                                    .build(),
                            new NewsApiClient.ArticlesResponseCallback() {
                                @Override
                                public void onSuccess(ArticleResponse response) {
                                    for (Article article : response.getArticles()) {
                                        System.out.println(article.getTitle());
                                        System.out.println(article.getUrl());
                                        System.out.println(article.getDescription());
                                        System.out.println(article.getContent());
                                        try {

                                            // Getting the Date from String by
                                            // creating object of Instant class
                                            Instant timestamp = getDateFromString(article.getPublishedAt());

                                            // Printing the converted date

                                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                                    .withZone(ZoneId.systemDefault());
                                            String customFormatted = formatter.format(timestamp);
                                            System.out.println("Date: " + customFormatted);

                                        }

                                        // Catch block to handle exceptions
                                        catch (DateTimeParseException e) {

                                            // Throws DateTimeParseException
                                            // if the string cannot be parsed
                                            System.out.println("Exception: " + e);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Throwable throwable) {
                                    System.out.println(throwable.getMessage());
                                }
                            }
                    );

            case SOURCES ->    newsApiClient.getSources(
                    new SourcesRequest.Builder()
                            .category(query)
                            .build(),
                    new NewsApiClient.SourcesCallback() {
                        @Override
                        public void onSuccess(SourcesResponse response) {
                            for (Source source : response.getSources()) {
                                System.out.println(source.getUrl());
                                System.out.println(source.getName());
                                System.out.println(source.getDescription());
                                System.out.println(source.getCategory());

                            }
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            System.out.println(throwable.getMessage());
                        }
                    }
            );

            default ->    newsApiClient.getEverything(
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
                                System.out.println(article.getContent());
                                try {

                                    // Getting the Date from String by
                                    // creating object of Instant class
                                    Instant timestamp = getDateFromString(article.getPublishedAt());

                                    // Printing the converted date

                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                            .withZone(ZoneId.systemDefault());
                                    String customFormatted = formatter.format(timestamp);
                                    System.out.println("Date: " + customFormatted);

                                }

                                // Catch block to handle exceptions
                                catch (DateTimeParseException e) {

                                    // Throws DateTimeParseException
                                    // if the string cannot be parsed
                                    System.out.println("Exception: " + e);
                                }
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

    public enum RequestType {
        EVERYTHING,
        HEADLINES,
        SOURCES
    }

    public static Instant getDateFromString(String string)
    {
        // Creating an instant object
        Instant timestamp = null;

        // Parsing the string to Date
        timestamp = Instant.parse(string);

        // Returning the converted timestamp
        return timestamp;
    }


}


