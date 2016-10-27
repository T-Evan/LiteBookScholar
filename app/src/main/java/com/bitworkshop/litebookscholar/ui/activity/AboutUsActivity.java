package com.bitworkshop.litebookscholar.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends AppCompatActivity {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.activity_about_us)
    RelativeLayout activityAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        initToolbar();

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initToolbar() {
        toolbar.setTitle(getResources().getString(R.string.about_us));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/book.html");
    }
}
