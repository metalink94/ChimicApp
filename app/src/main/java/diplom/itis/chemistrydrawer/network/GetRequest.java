package diplom.itis.chemistrydrawer.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
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

    private String mMethod;
    private Map<String, Object> mParams;


    public GetRequest() {
        mMethod = "";
        mParams = new HashMap<>();
    }

    public GetRequest(String methodUrl, Map<String, Object> params) {
        mMethod = methodUrl;
        mParams = params;
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
                Log.i("tag", temp);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Http Response:", e.getMessage());
        }
        return false;
    }

    protected void onPostExecute(Boolean result) {

    }
}
