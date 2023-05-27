package com.atech.urlimageloader.java;

import static com.atech.urlimageloader.utils.ValidKt.makeValidUrl;

import androidx.annotation.NonNull;

import com.atech.urlimageloader.client.RetrofitClient;
import com.atech.urlimageloader.kotlin.UrlImageLoader;
import com.atech.urlimageloader.model.LinkDetails;
import com.atech.urlimageloader.utils.BiConsumer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrlImageLoaderJava {

    /**
     * Get the image url from a website
     *
     * @param url:         The website url
     * @param onCompleted: The callback
     * @autor aiyu
     * @see BiConsumer The callback
     * @see UrlImageLoader The Kotlin version
     */
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


    /**
     * Get the image url from a website
     *
     * @param url:         The website url
     * @param onCompleted: The callback
     * @autor aiyu
     * @see BiConsumer The callback
     * @see UrlImageLoader The Kotlin version
     * @see LinkDetails The link details
     */
    public static void getLinkDetailsUrl(String url, BiConsumer<LinkDetails, Throwable> onCompleted) {
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
                                var title = parser.select("meta[property=og:title]").first().attr("content");
                                var description = parser.select("meta[property=og:description]").first().attr("content");
                                var image = parser.select("meta[property=og:image]").first().attr("content");
                                var details = new LinkDetails(title, description, image);
                                onCompleted.accept(details, null);
                            } else {
                                onCompleted.accept(null, new Exception("Response is not successful"));
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

    /**
     * Get the image url from a website
     *
     * @param url:         The website url
     * @param onCompleted: The callback
     * @autor aiyu
     * @see BiConsumer The callback
     * @see UrlImageLoader The Kotlin version
     */
    public static void getCustomDetailsUrl(String url, BiConsumer<Document, Throwable> onCompleted) {
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
                                onCompleted.accept(parser, null);
                            } else
                                onCompleted.accept(null, new Exception("Response is not successful"));

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
