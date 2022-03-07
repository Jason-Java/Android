package net;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zj on 2017/12/30.
 */

public class HeaderInterceptor implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException
    {

        Request.Builder builder = chain.request().newBuilder()
                .addHeader("Authorization"," Bearer "+ NetConfig.currentToken);
        Request build = builder.build();
        return chain.proceed(build);
    }
}
