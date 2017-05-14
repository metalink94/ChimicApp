package diplom.itis.chemistrydrawer.utils;

import imangazaliev.scripto.js.JavaScriptFunctionCall;

/**
 * Created by Денис on 09.05.2017.
 */

public interface ScriptoTest {

    JavaScriptFunctionCall<Void> setTest();

    JavaScriptFunctionCall<String> getTest();
}
