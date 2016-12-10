package diplom.itis.chemistrydrawer.network;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import diplom.itis.chemistrydrawer.models.AuthDataModel;

/**
 * Created by denis_000 on 06.12.2016.
 */

public class DataProvider implements IDataProvider, IMessages {

    public static final int ANSWER_TYPE_NORMAL = 0;
    public static final int ANSWER_TYPE_ERROR = 1;
    public static final int ANSWER_TYPE_CACHED_DATA = 2;

    private final Context mContext;
    private final ApiRequestFactory mApiRequestFactory;

    public DataProvider(Context context, ApiRequestFactory apiRequestFactory){
        mContext = context;
        mApiRequestFactory = apiRequestFactory;
    }

    @Override
    public void setAuthorization() {
        ApiRequest apiRequest = createGetRequest(ApiHttpClient.METHOD_USER_INFO);
        apiRequest.run(callback, null, null, UI_USER_INFO, new ApiRequest.ResponseProcessor() {
            @Override
            public Object process(HttpTools.HTTPResponse response) throws JSONException {
                Log.d("Profile", response.text);
                return new ProfileModel(new JSONObject(response.text));
            }
        }, forceUpdate);
    }

    @Override
    public void getTasks() {

    }

    @Override
    public void getExperiments() {

    }

    public ApiRequest createGetRequest(String method){
        AuthDataModel auth = AuthDataModel.loadAuthData(mContext);
        ApiRequest.Builder builder = mApiRequestFactory.createRequest(mContext);
        builder.setUrl(ApiHttpClient.SERVER_API_URL + method)
                .setHeaders(new String[]{HTTP_HEADER_SESSION, HTTP_HEADER_AGENT, HTTP_HEADER_LANGUAGE})
                .setHeadersValues(new String[]{auth.sessionId, HTTP_HEADER_AGENT_VALUE, HTTP_HEADER_LANGUAGE_VALUE})
                .setContentType(HttpTools.CONTENT_URLENCODED)
                .setRequestMethod(HttpTools.METHOD_GET)
                .setCallbacks(mCallbacks);

        return builder.build();
    }
}
