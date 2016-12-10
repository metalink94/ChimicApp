package diplom.itis.chemistrydrawer.network;

import android.content.Context;

/**
 * Created by denis_000 on 10.12.2016.
 */

public class ApiRequestFactory {
    protected HttpTools mHttpTools;

    public ApiRequestFactory(HttpTools httpTools){
        mHttpTools = httpTools;
    }

    public ApiRequest.Builder createRequest(Context context){
        return new ApiRequest.Builder(context, mHttpTools);
    }
}
