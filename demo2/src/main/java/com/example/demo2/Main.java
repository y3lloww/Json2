package com.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] arg
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Aplikacja JavaFX");

        String jsonData = fetchDataFromURL("http://zasob.itmargen.com/4TD/");


        JSONObject jsonObject = new JSONObject(jsonData);


        JSONObject infoSection = jsonObject.getJSONObject("info");


        JSONObject group2Section = jsonObject.getJSONObject("Grupa2");
        String myName = group2Section.getString("4");

        VBox root = new VBox(10);
        root.getChildren().add(new Label("Przedmiot: " + infoSection.getString("przedmiot")));
        root.getChildren().add(new Label("Prowadzący: " + infoSection.getString("prowadzacy")));
        root.getChildren().add(new Label("Szkola: " + infoSection.getString("szkola")));
        root.getChildren().add(new Label("Miasto: " + infoSection.getString("miasto")));
        root.getChildren().add(new Label("Data i czas: " + infoSection.getString("dataczas")));
        root.getChildren().add(new Label("Moje imię i nazwisko : " + myName));

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String fetchDataFromURL(String url) {
        StringBuilder result = new StringBuilder();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
