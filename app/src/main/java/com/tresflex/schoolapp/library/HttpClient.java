package com.tresflex.schoolapp.library;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

/**
 * Created by ashish on 03/11/15.
 */
public class HttpClient {
    private static OkHttpClient client = new OkHttpClient();

    // code request code here
    String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // post request code here

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    // test data
    String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }

    public static String doPostRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String doPostRequestMultiPart(String url, String status, String file_path) throws IOException {

        String IMGUR_CLIENT_ID = "...";
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"status\""),
                        RequestBody.create(null, status))
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"file\""),
                        RequestBody.create(MEDIA_TYPE_PNG, new File(file_path)))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Error on hitting status post request " + response);

        return response.body().string();
    }

    public static void main(String[] args) throws IOException {

        // issue the Get request
        TestMain example = new TestMain();
        String getResponse = example.doGetRequest("http://www.vogella.com");
        System.out.println(getResponse);


        // issue the post request

        String json = example.bowlingJson("Jesse", "Jake");
        String postResponse = example.doPostRequest("http://www.roundsapp.com/post", json);
        System.out.println(postResponse);
    }
}

