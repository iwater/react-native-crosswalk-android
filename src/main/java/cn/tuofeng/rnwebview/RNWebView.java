package cn.tuofeng.rnwebview;

import org.xwalk.core.XWalkView;

import android.view.ViewGroup;
import android.app.Activity;
import android.content.Context;
import android.widget.FrameLayout;
import android.os.Bundle;

import javax.annotation.Nullable;

/**
 * Created by iwater on 16/1/18.
 */
public class RNWebView extends FrameLayout {
    private XWalkView xWalkWebView;

    public RNWebView(Context context, Activity activity) {
        super(context);

        xWalkWebView = new XWalkView(context, activity);

        addView(
                xWalkWebView,
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

        //xWalkWebView.load("http://54.223.159.104/", null);
    }

    public void loadUrl(@Nullable String url) {
        xWalkWebView.load(url, null);
    }
}
