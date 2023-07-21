package com.example.task.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ConnectionUtil {

    public static String fetchHtml(OkHttpClient client, String url) {

        String fetchedHtml = "";

        try {

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            fetchedHtml = response.body().string();
        } catch (IOException e) {
            e.getLocalizedMessage();
        }

        return fetchedHtml;
    }

}
