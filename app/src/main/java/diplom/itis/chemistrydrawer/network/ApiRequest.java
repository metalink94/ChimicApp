package diplom.itis.chemistrydrawer.network;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Денис on 11.02.2017.
 */

public interface ApiRequest {

    @POST("/auth")
    Call<ResponseBody> postJson(@Body RequestBody params);
}
