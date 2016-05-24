package younggeeks.com.methystake2.MethysApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by Samjunior on 23/05/16.
 * this interface(Service) which will be used by Retrofit
 * simply defines our path and parameters or fields if it is
 * a post request
 */
public interface MethysApi {

    @FormUrlEncoded
    @POST("index.php")
    Call<ResponseBody> login(
            @Field("login") String username,
            @Field("password") String password
    );

}
