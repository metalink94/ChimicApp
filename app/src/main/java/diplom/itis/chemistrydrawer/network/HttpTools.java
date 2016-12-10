package diplom.itis.chemistrydrawer.network;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by denis_000 on 10.12.2016.
 */

public class HttpTools {

    public static final String CONTENT_URLENCODED = "application/x-www-form-urlencoded";
    public static final String CONTENT_JSON = "application/json";
    public static final String CONTENT_MULTI_PART = "multipart/form-data";


    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_DELETE = "DELETE";

    private static final String BOUNDARY = "****wGlUUDkf4jyBpdlBvnKMCJVWOHSsCt****";
    public static final int HTTP_UNAUTHORIZED = 401;



    public HTTPResponse postObjects(String url, String[] names, Object[] objects, String[] headersNames, String[] headersValues) throws IOException, JSONException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        OutputStream outputStream=null;
        InputStream inputStream=null;
        String ans="";
        int code;
        Map<String, List<String>> headerFields=null;
        HttpURLConnection connection=null;

        try {
            connection = getConnection(url, METHOD_POST, CONTENT_MULTI_PART, headersNames, headersValues);


            if (names!=null) {
                outputStream = new DataOutputStream(connection.getOutputStream());


                for (int i = 0; i < objects.length; i++) {

                    Object object = objects[i];
                    if (object instanceof Bitmap) {
                        Bitmap bitmap = (Bitmap) object;
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();

                        outputStream.write(("--" + BOUNDARY + "\r\n").getBytes());
                        outputStream.write(("Content-Disposition: form-data; name=\"" + names[i] + "\"; filename=\"" + "avatar" + "\"\r\n").getBytes());
                        outputStream.write("Content-Type: null\r\n".getBytes());
                        outputStream.write("\r\n".getBytes());
                        outputStream.write(byteArray);
                        outputStream.write("\r\n".getBytes());
                        continue;
                    }

                    if (object instanceof String) {
                        String value = (String) object;
                        outputStream.write(("--" + BOUNDARY + "\r\n").getBytes());
                        outputStream.write(("Content-Disposition: form-data; name=\"" + names[i] + "\"").getBytes());
                        outputStream.write("\r\n".getBytes());
                        outputStream.write("Content-Type: text/plain; charset=UTF-8".getBytes());
                        outputStream.write("\r\n".getBytes());
                        outputStream.write("\r\n".getBytes());
                        outputStream.write((value).getBytes());
                        outputStream.write("\r\n".getBytes());
                    }
                }

                if (objects.length != 0) {
                    outputStream.write(("--" + BOUNDARY + "\r\n").getBytes());
                }
            }
            try {
                code = connection.getResponseCode();
                headerFields = connection.getHeaderFields();
            } catch (java.io.IOException e) {
                if (e.getMessage() !=null && e.getMessage().contains("authentication challenge")) {
                    code = HttpURLConnection.HTTP_UNAUTHORIZED;
                } else {
                    throw e;
                }
            }

            if (code >= 400)
                inputStream = connection.getErrorStream();
            else
                inputStream = connection.getInputStream();

            ans = readIt(inputStream);
            Log.d("answer", ans);
            inputStream.close();
        }finally {
            if (outputStream!=null) outputStream.close();
            if (inputStream!=null) inputStream.close();
            if (connection!=null) connection.disconnect();
        }


        return new HTTPResponse(code, ans, headerFields);
    }

    public static String getRequestData(String method, Map<String, Object> data, String contentType) throws JSONException, UnsupportedEncodingException {
        if (contentType.equals(HttpTools.CONTENT_JSON)){
            JSONObject jsonObject = new JSONObject();
/*            Iterator it = data.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> pair = (Map.Entry)it.next();
                String param = pair.getKey();
                Object value = pair.getValue();

                if (value instanceof Map){

                    JSONObject jsonMap = new JSONObject();

                    for (Map.Entry<String, Object> entry:((Map<String, Object>) value).entrySet()){
                        jsonMap.put(entry.getKey(), entry.getValue());
                    }
                    jsonObject.put(param, jsonMap);

                }else if (value instanceof List){

                    JSONArray jsonArray = new JSONArray();

                    for (Object object:(List)value){
                        Map<String, Object> arrayValues = (Map<String, Object>) object;
                        JSONObject arrayItem = new JSONObject();
                        for (Map.Entry<String, Object> arrayValue: arrayValues.entrySet()){
                            arrayItem.put(arrayValue.getKey(), arrayValue.getValue());
                        }
                        jsonArray.put(arrayItem);
                    }

                    jsonObject.put(param, jsonArray);

                }else{
                    jsonObject.put(param, value);
                }

                System.out.println(pair.getKey() + " = " + pair.getValue());

                it.remove();
            }*/
            map2json(jsonObject, data);
            return jsonObject.toString();
        }else if (data!=null && contentType.equals(HttpTools.CONTENT_URLENCODED)){
            String urlParams = "";
            for (Map.Entry<String, Object> entry: data.entrySet()){
                if (urlParams.equals("")){
                    urlParams =  (method.equals(METHOD_POST)?"":"?") + entry.getKey() + "=" + URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8");
                }else{
                    urlParams = urlParams + "&" + entry.getKey() + "=" + URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8");
                }
            }
            return urlParams;
        }
        return null;
    }


    public static void map2json(JSONObject json, Map<String, Object> map ) throws JSONException {
        for (Map.Entry<String, Object> element: map.entrySet()){
            Object value = element.getValue();
            String key = element.getKey();

            if (value instanceof Map){
                JSONObject innerJsonObject = new JSONObject();
                map2json(innerJsonObject, (Map<String, Object>) value);
                json.put(key, innerJsonObject);
            }else if(value instanceof List){

                JSONArray jsonArray = new JSONArray();

                for (Object listElement:(List)value){
                    JSONObject jsonObject = new JSONObject();
                    map2json(jsonObject, (Map<String, Object>) listElement);
                    jsonArray.put(jsonObject);
                }
                json.put(key, jsonArray);

            }else{
                json.put(key, value);
            }
        }
    }

    public HTTPResponse request(String sUrl, String method, Map<String, Object> data, String contentType, String[] headersNames, String[] headersValues) throws IOException, JSONException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        OutputStream outputStream=null;
        InputStream inputStream=null;
        String ans="";
        int code=0;
        Map<String, List<String>> headerFields=null;
        HttpURLConnection connection=null;
        String request="";
        try {
            if (contentType.equals(HttpTools.CONTENT_JSON)){
                request = getRequestData(method, data, contentType);
                connection = getConnection(sUrl, method, contentType, headersNames, headersValues);
                connection.setRequestProperty("Content-Length", String.valueOf(request.getBytes().length));
                outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.write(request.getBytes());
                outputStream.close();
            }else if (data!=null && contentType.equals(HttpTools.CONTENT_URLENCODED) && (method.equals(METHOD_DELETE) || method.equals(METHOD_GET))){
                String urlParams = getRequestData(method, data, contentType);
                connection = getConnection(sUrl + urlParams, method, contentType, headersNames, headersValues);
            }else if (data!=null && contentType.equals(HttpTools.CONTENT_URLENCODED)){
                String bodyParams = getRequestData(method, data, contentType);
                connection = getConnection(sUrl, method, contentType, headersNames, headersValues);
                connection.setRequestProperty("Content-Length", String.valueOf(bodyParams.getBytes().length));
                outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.write(bodyParams.getBytes());
                outputStream.close();
            }else if (data != null && contentType.equals(HttpTools.CONTENT_MULTI_PART)){
                connection = getConnection(sUrl, method, contentType, headersNames, headersValues);
                outputStream = new DataOutputStream(connection.getOutputStream());
                for (Map.Entry<String, Object> element: data.entrySet()) {
                    if (element.getValue() instanceof Bitmap) {
                        Bitmap bitmap = (Bitmap) element.getValue();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();

                        outputStream.write(("--" + BOUNDARY + "\r\n").getBytes());
                        outputStream.write(("Content-Disposition: form-data; name=\"" + element.getKey() + "\"; filename=\"" + "avatar" + "\"\r\n").getBytes());
                        outputStream.write("Content-Type: null\r\n".getBytes());
                        outputStream.write("\r\n".getBytes());
                        outputStream.write(byteArray);
                        outputStream.write("\r\n".getBytes());
                        continue;
                    }
                }
                if (data.entrySet().size()>0){
                    outputStream.write(("--" + BOUNDARY + "\r\n").getBytes());
                }

                outputStream.close();
            }else{
                connection = getConnection(sUrl, method, contentType, headersNames, headersValues);
            }

            connection.connect();

            try {
                code = connection.getResponseCode();
            }
            catch (java.io.IOException e) {
                if ( (e.getMessage() != null) && (e.getMessage().contains("authentication challenge"))) {
                    code = HttpURLConnection.HTTP_UNAUTHORIZED;
                } else { throw e; }
            }

            headerFields = connection.getHeaderFields();

            if(code >= 400)
                inputStream = connection.getErrorStream();
            else
                inputStream = connection.getInputStream();

            ans = readIt(inputStream);
            Log.d("answer", ans);
            inputStream.close();

        }finally {
            if (outputStream!=null) outputStream.close();
            if (inputStream!=null) inputStream.close();
            if (connection!=null) connection.disconnect();
        }

        return new HTTPResponse(code, ans, headerFields);
    }

    public HTTPResponse request(String sUrl, String method, String[] params, String[] values, String contentType, String[] headersNames, String[] headersValues) throws IOException, JSONException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        OutputStream outputStream=null;
        InputStream inputStream=null;
        String ans="";
        int code=0;
        Map<String, List<String>> headerFields=null;
        HttpURLConnection connection=null;

        String request="";

        if (params!=null){
            request = getRequestBody(params, values, contentType);
            if (method.contentEquals(METHOD_GET) & !request.isEmpty())
                sUrl = sUrl + "?" + request;

        }

        try {

            connection = getConnection(sUrl, method, contentType, headersNames, headersValues);

            if (!method.contentEquals(METHOD_GET) & params!=null) {



                switch (contentType){
                    case CONTENT_MULTI_PART:
                        outputStream = new DataOutputStream(connection.getOutputStream());
                        if (params!=null && values!=null && params.length == values.length){
                            for (int i=0; i<params.length; i++){
                                outputStream.write(("--" + BOUNDARY + "\r\n").getBytes());
                                outputStream.write(("Content-Disposition: form-data; name=\"" +params[i]+"\"").getBytes());
                                outputStream.write("\r\n".getBytes());
                                outputStream.write( "Content-Type: text/plain; charset=UTF-8".getBytes());
                                outputStream.write("\r\n".getBytes());
                                outputStream.write("\r\n".getBytes());
                                outputStream.write((values[i]).getBytes());
                                outputStream.write("\r\n".getBytes());
                            }

                            if (params.length!=0){
                                outputStream.write(("--" + BOUNDARY + "\r\n").getBytes());
                            }
                        }

                        break;

                    default:
                        connection.setRequestProperty("Content-Length", String.valueOf(request.getBytes().length));
                        outputStream = new DataOutputStream(connection.getOutputStream());
                        outputStream.write(request.getBytes());
                        break;
                }

                outputStream.close();
            }
            connection.connect();

            try {
                code = connection.getResponseCode();
            }
            catch (java.io.IOException e) {
                if ( (e.getMessage() != null) && (e.getMessage().contains("authentication challenge"))) {
                    code = HttpURLConnection.HTTP_UNAUTHORIZED;
                } else { throw e; }
            }

            headerFields = connection.getHeaderFields();

            if(connection.getResponseCode() >= 400)
                inputStream = connection.getErrorStream();
            else
                inputStream = connection.getInputStream();

            ans = readIt(inputStream);
            Log.d("answer", ans);
            inputStream.close();

        }
        finally {
            if (outputStream!=null) outputStream.close();
            if (inputStream!=null) inputStream.close();
            if (connection!=null) connection.disconnect();
        }

        return new HTTPResponse(code, ans, headerFields);
    }

    private static HttpURLConnection getConnection(String sUrl, String method, String contentType, String[] headersNames, String[] headersValues) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        URL url;
        HttpURLConnection connection;
        url = new URL(sUrl);




        connection = (HttpURLConnection)url.openConnection();
        connection.setUseCaches(false);
        connection.setDefaultUseCaches(false);
        connection.setReadTimeout(60000);
        connection.setConnectTimeout(60000);
        connection.setRequestMethod(method);

        if (contentType.equals(CONTENT_MULTI_PART)){
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        }else {
            connection.setRequestProperty("Content-Type", contentType);
        }


        connection.setRequestProperty("Connection", "close");


        if (headersNames!=null && headersValues!=null && headersNames.length == headersValues.length) {
            for (int i = 0; i < headersNames.length; i++) {
                connection.setRequestProperty(headersNames[i], headersValues[i]);
            }
        }


        return connection;
    }

    private static String getRequestBody(String[] params, String[] values, String contentType) throws UnsupportedEncodingException, JSONException {
        if (params==null) return "";

        if (contentType.contentEquals(CONTENT_JSON)) {
            return createJSONRequest(params, values);
        }

        return createUrlRequest(params, values);
    }

    private static String createUrlRequest(String[] params, String[] values) throws UnsupportedEncodingException {
        String result = "";
        for (int i = 0; i < params.length; i++) {
            if (params[i]==null || values[i]==null) continue;

            if (!result.equals("")) result += "&";

            result += params[i];
            result += "=";
            result += URLEncoder.encode(values[i], "UTF-8");
        }
        return result;
    }

    private static String createJSONRequest(String[] params, String[] values) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        for (int i=0; i<params.length; i++){
            if (values[i].contentEquals("true")){
                jsonObject.put(params[i], true);
            }else if(values[i].contentEquals("false")){
                jsonObject.put(params[i], false);
            }else {
                jsonObject.put(params[i], values[i]);
            }

        }

        return jsonObject.toString();
    }

    private static String readIt(InputStream stream) throws IOException{
        BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
        String line;
        StringBuffer response = new StringBuffer();
        while((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return response.toString();
    }

    public static class HTTPResponse {
        public int code;
        public String text;
        public Map<String, List<String>> headerFields;
        public HTTPResponse(int code, String text, Map<String, List<String>> headerFields){
            super();
            this.code = code;
            this.text = text;
            this.headerFields = headerFields;
        }
        public boolean isError(){
            return code>=400;
        }
    }
}
