package diplom.itis.chemistrydrawer.utils;

import java.lang.ref.WeakReference;

/**
 * Created by Денис on 21.04.2017.
 */

public class Presenter <V extends BaseView>{

    private WeakReference<V> mViewRef;

    public void setView(V view) {
        mViewRef = new WeakReference<V>(view);
    }


    protected V getView() {
        return mViewRef == null ? null : mViewRef.get();
    }

    public void onViewClosed() {
        mViewRef = null;
    }
}