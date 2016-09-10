package cn.tuofeng.rnwebview;

import android.app.Activity;
import javax.annotation.Nullable;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.common.annotations.VisibleForTesting;

public class RNWebViewManager extends SimpleViewManager<RNWebView> {

    private RNWebView view;

    public static final int GO_BACK = 1;
    public static final int GO_FORWARD = 2;
    public static final int RELOAD = 3;

    ReactApplicationContext rContext;

    public RNWebViewManager(ReactApplicationContext reactApplicationContext) {
        rContext = reactApplicationContext;
    }

    @VisibleForTesting
    public static final String REACT_CLASS = "RNWebViewAndroid";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public RNWebView createViewInstance(ThemedReactContext context) {
        Activity mActivity = rContext.getCurrentActivity();
        return new RNWebView(context, mActivity);
        //xWalkWebView.setX(0);
        //xWalkWebView.setY(0);
        //xWalkWebView.load("http://crosswalk-project.org/", null);

        //mActivity.addView(xWalkWebView, 200, 200);

        //return xWalkWebView;
    }

    @ReactProp(name = "url")
    public void setUrl(RNWebView view, @Nullable String url) {
        view.loadUrl(url);
    }


    @ReactProp(name = "disableCookies", defaultBoolean = false)
    public void setDisableCookies(RNWebView view, boolean disableCookies) {
    }

    @ReactProp(name = "disablePlugins", defaultBoolean = false)
    public void setDisablePlugins(RNWebView view, boolean disablePlugins) {
    }

    @ReactProp(name = "builtInZoomControls", defaultBoolean = false)
    public void setBuiltInZoomControls(RNWebView view, boolean builtInZoomControls) {
    }

    @ReactProp(name = "geolocationEnabled", defaultBoolean = false)
    public void setGeolocationEnabled(RNWebView view, boolean geolocationEnabled) {
    }

    @ReactProp(name = "javaScriptEnabled", defaultBoolean = true)
    public void setJavaScriptEnabled(RNWebView view, boolean javaScriptEnabled) {
    }

}
