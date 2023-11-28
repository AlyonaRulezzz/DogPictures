package com.example.dogpictures;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("image/random")
    Single<DogImage> loadDogImageRetrofit();
}
