package Algorithms.PathFinding;

import javafx.application.Application;

import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Arena Wars");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Grid());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
