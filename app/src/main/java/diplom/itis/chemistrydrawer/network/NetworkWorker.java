package diplom.itis.chemistrydrawer.network;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import java.io.File;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Денис on 06.05.2017.
 */

public class NetworkWorker {

    private static final String BASE_URL = "https://cimm.kpfu.ru/api/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Call getRequest(String method, Callback callback) {
        Request request = new Request.Builder()
                .url(BASE_URL + method)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Call postRequest(String method, Object model, Callback callback) {
        String json = getJson(model);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(BASE_URL + method)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    private String getJson(Object model) {
        Gson gson = new Gson();
        return gson.toJson(model);
    }

    public Call postFile(String method, File file, Callback callback) {
        RequestBody formBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"file\""),
        RequestBody.create(MediaType.parse("test/rdf"), file))
                .build();

        Request request = new Request.Builder().url(BASE_URL + method).post(formBody).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
