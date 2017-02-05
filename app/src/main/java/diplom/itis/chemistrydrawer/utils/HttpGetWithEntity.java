package diplom.itis.chemistrydrawer.utils;

import cz.msebera.android.httpclient.client.methods.HttpEntityEnclosingRequestBase;

/**
 * Created by Денис on 05.02.2017.
 */

public class HttpGetWithEntity extends HttpEntityEnclosingRequestBase {
    public final static String METHOD_NAME = "GET";

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
}
