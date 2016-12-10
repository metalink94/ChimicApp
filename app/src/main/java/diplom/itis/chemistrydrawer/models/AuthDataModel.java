package diplom.itis.chemistrydrawer.models;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import static diplom.itis.chemistrydrawer.utils.Ipreferences.IPreferences.APP_PREFS;
import static diplom.itis.chemistrydrawer.utils.Ipreferences.IPreferences.DEFAULT_PREF_JSON_OBJECT;
import static diplom.itis.chemistrydrawer.utils.Ipreferences.IPreferences.PREF_AUTH;

/**
 * Created by denis_000 on 10.12.2016.
 */

public class AuthDataModel {

    public String login;
    public String password;
    public String sessionId;

    public AuthDataModel() {
    }

    private AuthDataModel(JSONObject object) throws JSONException {

    }

    public static AuthDataModel loadAuthData(Context context) {
        SharedPreferences sp = context.getSharedPreferences(APP_PREFS, 0);
        String strData = sp.getString(PREF_AUTH, DEFAULT_PREF_JSON_OBJECT);
        try {
            JSONObject jsonData = new JSONObject(strData);

            return new AuthDataModel(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new AuthDataModel();
    }

    public static void saveAuthData(Context ctx, AuthDataModel model) {
        SharedPreferences sp = ctx.getSharedPreferences(APP_PREFS, 0);
        SharedPreferences.Editor editor = sp.edit();
        try {
            editor.putString(PREF_AUTH, model.asJson().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor.commit();
    }

    private JSONObject asJson() throws JSONException {
        JSONObject result = new JSONObject();
        return result;
    }

    public boolean isEmpty() {
        return sessionId == null;
    }

    public void clear() {
        sessionId = null;
    }
}
