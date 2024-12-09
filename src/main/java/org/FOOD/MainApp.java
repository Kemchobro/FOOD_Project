package org.FOOD;

import java.util.HashMap;
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

    // Map to store full details for each article
    private final Map<String, String> articleDetailsMap = new HashMap<>();

    @Override
    public void start(Stage stage) {
        // UI Components
        ListView<String> listView = new ListView<>();
        listView.setStyle("-fx-control-inner-background: #f0f4f8; -fx-font-size: 14px;");

        TextField queryField = new TextField();
        queryField.setPromptText("Enter a topic...");
        queryField.setStyle("-fx-font-size: 14px; -fx-background-color: #ffffff;");

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14px;");

        TextArea detailsArea = new TextArea();
        detailsArea.setEditable(false);
        detailsArea.setWrapText(true);
        detailsArea.setStyle("-fx-control-inner-background: #f9f9f9; -fx-font-size: 14px; -fx-border-color: #ddd;");

        // Button Action to Fetch News
        searchButton.setOnAction(event -> {
            listView.getItems().clear(); // Clear previous results
            detailsArea.clear(); // Clear details area
            String query = queryField.getText().trim();
            if (!query.isEmpty()) {
                fetchNews(query, listView);
            } else {
                listView.getItems().add("Please enter a valid topic.");
            }
        });

        // ListView Selection Listener to Show Details
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                detailsArea.setText(articleDetailsMap.getOrDefault(newValue, "No details available."));
            }
        });

        // Layout and Scene
        HBox topBar = new HBox(10, queryField, searchButton);
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #eeeeee;");

        VBox root = new VBox(10, topBar, listView, detailsArea);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #f7f7f7;");

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.setTitle("News Viewer");
        stage.show();
    }

    /**
     * Fetch news based on user input and update the ListView.
     */
    private void fetchNews(String query, ListView<String> listView) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // RequestHandler for fetching news
                RequestHandler requestHandler = new RequestHandler(query, RequestHandler.RequestType.EVERYTHING);
                requestHandler.setOnSuccessCallback(response -> {
                    for (String article : response.split("\n\n")) {
                        String[] lines = article.split("\n");
                        if (lines.length > 0) {
                            String title = lines[0]; // Extract title
                            String fullDetails = article; // Store full article details
                            listView.getItems().add(title);
                            articleDetailsMap.put(title, fullDetails); // Map title to full details
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