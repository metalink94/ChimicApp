package diplom.itis.chemistrydrawer.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import org.json.JSONException;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Set;

/**
 * Created by denis_000 on 10.12.2016.
 */

public class ApiRequest {

    protected String mUrl;
    protected String requestMethod;
    protected String contentType;
    protected String[] headers;
    protected String[] headersValues;
    private Set<Handler.Callback> callbacks;
    private Context mContext;

    private String[] mUrlParams;
    private String[] mUrlParamsValues;

    public HttpTools mHttpTools;


    public void setUrlParams(String[] urlParams, String[] urlParamsValues){
        mUrlParams = urlParams;
        mUrlParamsValues = urlParamsValues;
    }

    public void run(final Handler.Callback callback, final String[] params, final String[] values, final int uiCallbackId, final ResponseProcessor responseProcessor, final boolean forceUpdate){
        if (callbacks!=null) {
            callbacks.add(callback);
        }

        final Handler h = new Handler(callback);
        Runnable r = new  Runnable(){
            @Override
            public void run() {

                Message msg = new Message();
                msg.what = uiCallbackId;
                try {
                    String urlParams = getUrlParams();// for MultiPart request mainly;
                    if (!forceUpdate) {
                        String data = null;

                        if (data != null) {
                            msg.arg1 = DataProvider.ANSWER_TYPE_CACHED_DATA;
                            msg.obj = responseProcessor.process(new HttpTools.HTTPResponse(200, data, null) );
                            h.sendMessage(msg);
                            return;
                        }
                    }else{
                    }

                    HttpTools.HTTPResponse response = mHttpTools.request(mUrl + urlParams, requestMethod,
                            params,
                            values,
                            contentType, headers, headersValues);


                    if (response.code>=400){
//                        throw new ApiException(response.code, response.text);
                    }

                    msg.arg1 = DataProvider.ANSWER_TYPE_NORMAL;
                    if (responseProcessor!=null){
                        msg.obj = responseProcessor.process(response);
                    }

                } catch (IOException | JSONException | CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
                    e.printStackTrace();
                    msg.arg1 = DataProvider.ANSWER_TYPE_ERROR;
                    msg.obj = e;
                }
                if (callbacks == null || !callbacks.contains(callback)) return;
                h.sendMessage(msg);
            }
        };
    }

    private String getUrlParams(){
        String result = "";
        if (mUrlParams!=null && mUrlParamsValues!=null && mUrlParams.length == mUrlParamsValues.length && mUrlParams.length!=0){
            result = "?";
            for (int i=0; i<mUrlParams.length; i++){
                if (i==0){
                    result = result + mUrlParams[i] + "=" + mUrlParamsValues[i];
                }else{
                    result = result + "&" + mUrlParams[i] + "=" + mUrlParamsValues[i];
                }
            }

        }
        return result;
    }

    public interface ResponseProcessor{
        Object process(HttpTools.HTTPResponse response) throws JSONException;
    }

    public void setCallbacks(Set<Handler.Callback> callbacks) {
        this.callbacks = callbacks;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public void setHeadersValues(String[] headersValues) {
        this.headersValues = headersValues;
    }

    public void setContext(Context context){
        mContext = context;
    }

    public void setHttpTools(HttpTools httpTools){
        mHttpTools = httpTools;
    }

    public static class Builder{


        public ApiRequest mApiRequest;

        public Builder(Context context, HttpTools httpTools){
            mApiRequest = new ApiRequest();
            mApiRequest.setContext(context);
            mApiRequest.setHttpTools(httpTools);
        }

        public ApiRequest build(){
            return mApiRequest;
        }

        public Builder setRequestMethod(String requestMethod) {
            mApiRequest.setRequestMethod(requestMethod);
            return this;
        }

        public Builder setUrl(String mUrl) {
            mApiRequest.setUrl(mUrl);
            return this;
        }

        public Builder setContentType(String contentType) {
            mApiRequest.setContentType(contentType);
            return this;
        }


        public Builder setHeaders(String[] headers) {
            mApiRequest.setHeaders(headers);
            return this;
        }


        public Builder setHeadersValues(String[] headersValues) {
            mApiRequest.setHeadersValues(headersValues);
            return this;
        }

        public Builder setCallbacks(Set<Handler.Callback> callbacks){
            mApiRequest.setCallbacks(callbacks);
            return this;
        }

        public Builder setUrlParams(String[] urlParams, String[] urlParamsValues){
            mApiRequest.setUrlParams(urlParams, urlParamsValues);
            return this;
        }

    }
}
