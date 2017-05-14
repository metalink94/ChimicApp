package diplom.itis.chemistrydrawer.screens.tasks;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import diplom.itis.chemistrydrawer.models.api.AdditivesListFields;
import diplom.itis.chemistrydrawer.models.api.ModelListFields;
import diplom.itis.chemistrydrawer.network.NetworkWorker;
import okhttp3.Call;
import okhttp3.Response;

import static diplom.itis.chemistrydrawer.network.IMethods.GET_ADDITIVES;
import static diplom.itis.chemistrydrawer.network.IMethods.GET_MAGIC;
import static diplom.itis.chemistrydrawer.network.IMethods.GET_MODELS;

/**
 * Created by Денис on 04.03.2017.
 */

public class TaskListRepository {

    Callback mCallback;
    NetworkWorker mNetworkWorker;

    public TaskListRepository(NetworkWorker mNetworkWorker) {
        this.mNetworkWorker = mNetworkWorker;
    }

    public void setCallback(Callback aCallback) {
        mCallback = aCallback;
    }


    public void getAdditives() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            mNetworkWorker.getRequest(GET_ADDITIVES, new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(getClass().getSimpleName(), e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseStr = response.body().string();
                        Gson gson = (new GsonBuilder()).setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
                        Type listType = new TypeToken<List<AdditivesListFields>>() {
                        }.getType();
                        List<AdditivesListFields> additivesListFieldses = gson.fromJson(responseStr, listType);
                        mCallback.saveAdditive(additivesListFieldses);
                        Log.d(getClass().getSimpleName(), "Response " + responseStr);
                    }
                }
            });
        }
    }

    public void getMagics() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            mNetworkWorker.getRequest(GET_MAGIC, new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(getClass().getSimpleName(), e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseStr = response.body().string();
                        Log.d(getClass().getSimpleName(), "Response " + responseStr);
                    }
                }
            });
        }
    }

    public void getModels() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            mNetworkWorker.getRequest(GET_MODELS, new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(getClass().getSimpleName(), e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseStr = response.body().string();
                        Gson gson = (new GsonBuilder()).setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
                        Type listType = new TypeToken<List<ModelListFields>>() {
                        }.getType();
                        List<ModelListFields> additivesListFieldses = gson.fromJson(responseStr, listType);
                        mCallback.saveModels(additivesListFieldses);
                        Log.d(getClass().getSimpleName(), "Response " + responseStr);
                    }
                }
            });
        }
    }

    public interface Callback {

        void saveAdditive(List<AdditivesListFields> additivesListFieldses);

        void saveModels(List<ModelListFields> modelListFieldses);
    }
}
