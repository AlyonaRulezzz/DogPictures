package com.example.dogpictures;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

        mainViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {
                if (loading) {
                    pb.setVisibility(View.VISIBLE);
                } else  {
                    pb.setVisibility(View.GONE);
                }
            }
        });

        mainViewModel.getIsLoadingError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if (isError)
                    Toast.makeText(
                            MainActivity.this,
                            R.string.loading_error_has_occurred,
                            Toast.LENGTH_SHORT)
                            .show();
            }
        });

        btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.loadDogImage();
            }
        });
    }

    private void initViews() {
        ivDog = findViewById(R.id.ivDog);
        pb = findViewById(R.id.pb);
        btnLoadImage = findViewById(R.id.btnLoadImage);
    }

}