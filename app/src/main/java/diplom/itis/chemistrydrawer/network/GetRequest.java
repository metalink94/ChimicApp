package diplom.itis.chemistrydrawer.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.EntityUtils;
import diplom.itis.chemistrydrawer.utils.HttpGetWithEntity;

/**
 * Created by Денис on 05.02.2017.
 */

public class GetRequest extends AsyncTask<String, Void, Boolean> {

    static final String BASE_URL = "https://cimm.kpfu.ru/api/";
    public static final String JSON_TYPE = "application/json";
    public static final String GET = "GET";

    private String mMethod;
    private Map<String, Object> mParams;
    private String mType = "";


    public GetRequest() {
        mMethod = "";
        mParams = new HashMap<>();
    }

    public GetRequest(String methodUrl, Map<String, Object> params) {
        mMethod = methodUrl;
        mParams = params;
    }

    Object model;

    public GetRequest(String auth, Object model) {
        mMethod = auth;
        this.model = model;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    private StringEntity getWithJSONBody(Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue());
            }
            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, JSON_TYPE));
            return se;
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Boolean doInBackground(String... urls) {
        if (mType.equals(GET)) {
            return getWithEntity();
        } else {
            return postRequest();
        }
    }

    protected void onPostExecute(Boolean result) {

    }

    private boolean postRequest() {
        boolean isResponse = false;
        try {
            Gson gson = new Gson();
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(BASE_URL + mMethod);
            StringEntity postingString = null;
            postingString = new StringEntity(gson.toJson(model));
            post.setEntity(postingString);
            post.setHeader(HTTP.CONTENT_TYPE, JSON_TYPE);
            HttpResponse response = httpClient.execute(post);
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                String temp = EntityUtils.toString(response.getEntity());
                Log.i("tagPost", temp);
                isResponse = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Http Response:", e.getMessage());
            isResponse = false;
        }
        return isResponse;
    }

    private boolean getWithEntity() {
        boolean isResponse = false;
        try {
            HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity();
            httpGetWithEntity.setURI(URI.create(BASE_URL + mMethod));
            httpGetWithEntity.setHeader(HTTP.CONTENT_TYPE, JSON_TYPE);
            HttpClient httpclient = new DefaultHttpClient();
            httpGetWithEntity.setEntity(getWithJSONBody(mParams));
            HttpResponse response = httpclient.execute(httpGetWithEntity);
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                String temp = EntityUtils.toString(response.getEntity());
                Log.i("tagGet", temp);
                isResponse = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Http Response:", e.getMessage());
            isResponse = false;
        }
        return isResponse;
    }
}