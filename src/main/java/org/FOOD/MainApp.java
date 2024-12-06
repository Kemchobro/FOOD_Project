package org.FOOD;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        // Create the carousel container (HBox for horizontal layout)
        HBox carousel = new HBox(20); // 20px spacing between boxes
        carousel.setAlignment(Pos.CENTER); // Center align all elements
        carousel.setPadding(new Insets(20)); // Add padding around the carousel 

        // Create individual boxes for the carousel
        for (int i = 1; i <= 5; i++) {
            VBox box = createCarouselBox("Box " + i);
            carousel.getChildren().add(box);
        }

        // Create a root container (StackPane) to center the carousel
        StackPane root = new StackPane();
        root.getChildren().add(carousel);

        // Create a scene and add the carousel
        Scene scene = new Scene(root, 1000, 600); // Larger window size
        stage.setScene(scene);
        stage.setTitle("Centered Aesthetic Text Input Carousel");
        stage.show();
    }

    // Helper method to create a carousel box
    private VBox createCarouselBox(String labelText) {
        // Create a rectangle to represent the box
        Rectangle rectangle = new Rectangle(200, 150); // width: 200px, height: 150px
        rectangle.setFill(Color.CORNFLOWERBLUE);
        rectangle.setStroke(Color.DARKBLUE);
        rectangle.setStrokeWidth(2);
        rectangle.setArcWidth(30); // Rounded corners
        rectangle.setArcHeight(30);

        // Create a label for the box
        Text label = new Text(labelText);
        label.setFont(Font.font("Arial", 18));
        label.setFill(Color.WHITE);

        // Create a text field for input
        TextField textField = new TextField();
        textField.setPromptText("Enter text");
        textField.setMaxWidth(150); // Restrict the width of the input field

        // Arrange the elements in a vertical layout
        VBox box = new VBox(15); // 15px spacing
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(label, rectangle, textField);

        // Add a background color for better aesthetics
        box.setStyle("-fx-background-color: #e0f7fa; -fx-background-radius: 15; -fx-padding: 10;");

        return box;
    }

    public static void main(String[] args) {
        launch();
    }
}



// package org.FOOD;

// import javafx.application.Application;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Rectangle;
// import javafx.scene.text.Text;
// import javafx.stage.Stage;

// public class MainApp extends Application {

//     @Override
//     public void start(Stage stage) {
//         // Create the carousel container (HBox for horizontal layout)
//         HBox carousel = new HBox(10); // 10px spacing between boxes
//         carousel.setAlignment(Pos.CENTER); // Center align all elements

//         // Create individual boxes for the carousel
//         for (int i = 1; i <= 5; i++) {
//             VBox box = createCarouselBox("Box " + i);
//             carousel.getChildren().add(box);
//         }

//         // Create a scene and add the carousel
//         Scene scene = new Scene(carousel, 800, 300);
//         stage.setScene(scene);
//         stage.setTitle("Text Input Carousel");
//         stage.show();
//     }

//     // Helper method to create a carousel box
//     private VBox createCarouselBox(String labelText) {
//         // Create a rectangle to represent the box
//         Rectangle rectangle = new Rectangle(150, 100); // width: 150px, height: 100px
//         rectangle.setFill(Color.LIGHTBLUE);
//         rectangle.setArcWidth(20); // Rounded corners
//         rectangle.setArcHeight(20);

//         // Create a label for the box
//         Text label = new Text(labelText);

//         // Create a text field for input
//         TextField textField = new TextField();
//         textField.setPromptText("Enter text");

//         // Arrange the elements in a vertical layout
//         VBox box = new VBox(10); // 10px spacing
//         box.setAlignment(Pos.CENTER);
//         box.getChildren().addAll(rectangle, label, textField);

//         return box;
//     }

//     public static void main(String[] args) {
//         launch();
//     }
// }