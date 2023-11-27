package com.example.dogpictures;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity1";

    private ImageView ivDog;
    private ProgressBar pb;
    private Button btnLoadImage;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.loadDogImage();
        mainViewModel.getDogImage().observe(this, new Observer<DogImage>() {
            @Override
            public void onChanged(DogImage dogImage) {
                Log.d(TAG, dogImage.toString());
                Glide.with(MainActivity.this)
                        .load(dogImage.getMessage())
                        .into(ivDog);
            }
        });
    }

    private void initViews() {
        ivDog = findViewById(R.id.ivDog);
        pb = findViewById(R.id.pb);
        btnLoadImage = findViewById(R.id.btnLoadImage);
    }

}