package tgs.com.junglecookapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;

public class Attachment extends Fragment {
    RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private WebView webView;
    String Vt_url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.webview_activity, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Bundle bundle = getArguments();
        Vt_url = bundle.getString("url");

        webView = (WebView) view.findViewById(R.id.webView1);
        webView.loadUrl(Vt_url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        // webView.getSettings().setSupportZoom(true);
        // webView.getSettings().setLoadsImagesAutomatically(true);
        //webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setUseWideViewPort(true);
        //webView.getSettings().setDisplayZoomControls(true);
        // webView.getSettings().setBuiltInZoomControls(true);
        initview();
        setHasOptionsMenu(true);
        return view;
    }

    private void initview() {
        initProgressDialog();
        stopProgressDialog();
    }
    private void initProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
        pDialog.setCancelable(true);
    }
    private void stopProgressDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }


}
