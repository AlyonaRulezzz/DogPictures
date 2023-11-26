package com.example.dogpictures;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDogImage();
    }

    private void loadDogImage() {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder data = new StringBuilder();
                String result;
                do {
                    result = bufferedReader.readLine();
                    if (result != null) {
                        data.append(result);
                    }
                } while (result != null);

                JSONObject jsonObject = new JSONObject(data.toString());
                String message = jsonObject.getString("message");
                String status = jsonObject.getString("status");
                DogImage dogImage = new DogImage(message, status);

//                    Log.d("MainActivity1", data.toString());
                Log.d("MainActivity1", dogImage.toString());
            } catch (Exception e) {
//                    throw new RuntimeException(e);
                Log.d("MainActivity1", e.toString());
            }
        }).start();
    }
}