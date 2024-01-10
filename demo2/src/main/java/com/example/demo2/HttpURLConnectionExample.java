package com.example.demo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class HttpURLConnectionExample {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://zasob.itmargen.com/4TD/";

    public static void main(String[] args) throws IOException {
        sendGET();
        System.out.println("GET DONE");
    }

    private static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Read INFO section
            JSONObject infoSection = jsonResponse.getJSONObject("info");
            System.out.println("INFO Section:");
            System.out.println("Przedmiot: " + infoSection.getString("przedmiot"));
            System.out.println("Prowadzący: " + infoSection.getString("prowadzacy"));
            System.out.println("Szkola: " + infoSection.getString("szkola"));
            System.out.println("Miasto: " + infoSection.getString("miasto"));
            System.out.println("Data i czas: " + infoSection.getString("dataczas"));

            // Read Grupa2 section
            JSONObject grupa2Section = jsonResponse.getJSONObject("Grupa2");
            System.out.println("\nGrupa2 Section:");

            // Replace "Twoje imię i nazwisko" with your actual name and surname
            String yourName = "Ostrycharz Kacper";
            if (grupa2Section.has("1") && grupa2Section.getString("1").equals(yourName)) {
                System.out.println("1: " + yourName);
            }
            // Add similar conditions for other group members as needed

        } else {
            System.out.println("GET request did not work.");
        }
    }
}
