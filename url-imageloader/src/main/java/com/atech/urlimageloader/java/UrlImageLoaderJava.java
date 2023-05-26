package com.atech.urlimageloader.java;

import static com.atech.urlimageloader.utils.ValidKt.makeValidUrl;

import androidx.annotation.NonNull;

import com.atech.urlimageloader.client.RetrofitClient;
import com.atech.urlimageloader.utils.BiConsumer;

import org.jsoup.Jsoup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrlImageLoaderJava {

    public static void getImageUrl(String url, BiConsumer<String, Throwable> onCompleted) {
        try {
            RetrofitClient.Companion.getInstance(makeValidUrl(url))
                    .getClient().getHTML()
                    .enqueue(new Callback<>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            if (!response.isSuccessful()) {
                                onCompleted.accept(null, new Exception("Error: " + response.code()));
                            }
                            if (response.body() != null) {
                                var parser = Jsoup.parse(response.body());
                                var elements = parser.select("meta[property=og:image]");
                                var first = elements.first();
                                if (first != null) {
                                    var imageUrl = first.attr("content");
                                    onCompleted.accept(imageUrl, null);
                                } else {
                                    onCompleted.accept(null, new Exception("Error: No image found"));
                                }
                            }

                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                            onCompleted.accept(null, t);
                        }
                    });
        } catch (Exception e) {
            onCompleted.accept(null, e);
        }
    }
}