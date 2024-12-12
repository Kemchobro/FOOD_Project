// InfoHub
// Diego Sanchez & Vidhatu Patel
// 12/12/2024
// InfoHub: A news aggregation app that compiles articles from sources like NY Times and The Guardian, offering  updates and reducing polarized clutter.
// I abide by the Academic Honesty Policy
package org.FOOD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    // Store full details for each article
    private final Map<String, String> articleDetailsMap = new HashMap<>();
    // runs when the application initially starts
    @Override
    public void start(Stage stage) {
        // UI
        ListView<String> recentSearchesView = new ListView<>();
        recentSearchesView.setStyle("-fx-control-inner-background: #f8f9fa; -fx-font-size: 14px;");

        ListView<String> fetchedArticlesView = new ListView<>();
        fetchedArticlesView.setStyle("-fx-control-inner-background: #f0f4f8; -fx-font-size: 14px;");

        TextField queryField = new TextField();
        queryField.setPromptText("Enter a topic...");
        queryField.setStyle("-fx-font-size: 14px; -fx-background-color: #ffffff;");

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14px;");

        Button clearHistoryButton = new Button("Clear History");
        clearHistoryButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14px;");

        TextArea detailsArea = new TextArea();
        detailsArea.setEditable(false);
        detailsArea.setWrapText(true);
        detailsArea.setStyle("-fx-control-inner-background: #f9f9f9; -fx-font-size: 14px; -fx-border-color: #ddd;");

        Button citationButton = new Button("Generate Citation");
        citationButton.setStyle("-fx-background-color: #ffa500; -fx-text-fill: white; -fx-font-size: 14px;");
        citationButton.setDisable(true);

        loadRecentHistory(recentSearchesView);

        // Search Button Action
        searchButton.setOnAction(event -> {
            // Clear previous results
            fetchedArticlesView.getItems().clear();
            detailsArea.clear();
            // Clear details area
            String query = queryField.getText().trim();
            if (!query.isEmpty()) {
                fetchNews(query, fetchedArticlesView);
                RecentHistory.addStrings(query);

                // Add query to recentSearchesView ListView (Recent History)
                if (!recentSearchesView.getItems().contains(query)) {
                    recentSearchesView.getItems().add(0, query);
                }
            } else {
                fetchedArticlesView.getItems().add("Please enter a valid topic.");
            }
        });

        // Clear History Button Action
        clearHistoryButton.setOnAction(event -> {
            // Clear the ListView
            recentSearchesView.getItems().clear();

            // Clear the CSV file (or clear its contents)
            RecentHistory.clearHistory();
        });


        // Shows Details and Creates Citation Button
        fetchedArticlesView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                detailsArea.setText(articleDetailsMap.getOrDefault(newValue, "No details available."));
                // Citation button
                citationButton.setDisable(false);
            }
        });

        // Citation Button Action
        citationButton.setOnAction(event -> {
            String selectedTitle = fetchedArticlesView.getSelectionModel().getSelectedItem();
            if (selectedTitle != null) {
                String articleDetails = articleDetailsMap.get(selectedTitle);
                if (articleDetails != null) {
                    String citation = CitationGenerator.generateCitation(articleDetails);
                    // Displays citation
                    detailsArea.setText("Citation:\n" + citation);
                }
            }
        });

        // Layout for Recent Searches Box (Vertical, Left Side)
        VBox recentSearchesBox = new VBox(10, recentSearchesView, clearHistoryButton);
        recentSearchesBox.setPadding(new Insets(10));
        recentSearchesBox.setStyle("-fx-background-color: #eeeeee;");
        recentSearchesBox.setMinWidth(150);

        // Layout for Articles, Description Boxes, and Citation Button (Vertically Stacked, Right Side)
        VBox articlesAndDetailsBox = new VBox(10, fetchedArticlesView, detailsArea, citationButton);
        articlesAndDetailsBox.setPadding(new Insets(10));
        articlesAndDetailsBox.setStyle("-fx-background-color: #f7f7f7;");

        // Combine Both Sections in Horizontal Layout
        HBox mainContent = new HBox(10, recentSearchesBox, articlesAndDetailsBox);
        mainContent.setPadding(new Insets(10));

        // Bind components to dynamically resize with the window
        mainContent.prefWidthProperty().bind(stage.widthProperty());
        mainContent.prefHeightProperty().bind(stage.heightProperty().subtract(80));

        recentSearchesBox.prefHeightProperty().bind(mainContent.heightProperty());
        articlesAndDetailsBox.prefHeightProperty().bind(mainContent.heightProperty());

        recentSearchesBox.prefWidthProperty().bind(mainContent.widthProperty().multiply(0.25));
        articlesAndDetailsBox.prefWidthProperty().bind(mainContent.widthProperty().multiply(0.75));


        // Top Bar (Query Field and Search Button)
        HBox topBar = new HBox(10, queryField, searchButton);
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #eeeeee;");
        topBar.prefWidthProperty().bind(stage.widthProperty());


        // Final Layout (Top Bar + Main Content)
        VBox root = new VBox(10, topBar, mainContent);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #f7f7f7;");
        root.prefWidthProperty().bind(stage.widthProperty());
        root.prefHeightProperty().bind(stage.heightProperty());

        // Scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("InfoHub");

        // Enforce minimum window size
        stage.setMinWidth(600);
        stage.setMinHeight(400);

        // Enforce aspect ratio (4:3 aspect ratio)
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            double aspectRatio = 4.0 / 3.0;
            double newHeight = newValue.doubleValue() / aspectRatio;
            stage.setHeight(Math.max(newHeight, 400));
        });

        stage.show();

    }

    // Load recent search history from the CSV into the ListView
    private void loadRecentHistory(ListView<String> listView) {
        List<String[]> recentHistory = RecentHistory.getStrings();
        for (String[] entry : recentHistory) {
            if (entry.length > 0) {
                listView.getItems().add(entry[0]);
            }
        }
    }


     //Fetch news based on user input and update the ListView.
     
    private void fetchNews(String query, ListView<String> listView) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // RequestHandler -- fetches news
                RequestHandler requestHandler = new RequestHandler(query, RequestHandler.RequestType.EVERYTHING);
                requestHandler.setOnSuccessCallback(response -> {
                    for (String article : response.split("\n\n")) {
                        String[] lines = article.split("\n");
                        if (lines.length > 0) {
                            // Extract title
                            String title = lines[0]; 
                            String fullDetails = article; 
                            // Store full article details
                            listView.getItems().add(title);
                            // Map title to full details
                            articleDetailsMap.put(title, fullDetails); 
                        }
                    }
                });
                requestHandler.setOnFailureCallback(error -> listView.getItems().add("Error: " + error));
                return null;
            }
        };

        new Thread(task).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}