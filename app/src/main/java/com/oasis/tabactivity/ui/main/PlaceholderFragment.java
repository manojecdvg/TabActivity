package com.oasis.tabactivity.ui.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.oasis.tabactivity.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    private WebView wv;
    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
       // alertView("tab"+index);
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {




if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
    View root = inflater.inflate(R.layout.fragment_sub_page01, container, false);
    wv = (WebView) root.findViewById(R.id.webView1);
    WebSettings settings = wv.getSettings();
    settings.setJavaScriptEnabled(true);
    wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
    wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    wv.getSettings().setAppCacheEnabled(true);
    wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

    settings.setDomStorageEnabled(true);
    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    settings.setUseWideViewPort(true);
    settings.setSavePassword(true);
    settings.setSaveFormData(true);
    this.wv.loadUrl("http://oasiscrescent.net/eap/stores/txnview/Allocated");
    wv.setWebViewClient(new MywebViewClient());
    return root;

}
if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
            View root = inflater.inflate(R.layout.fragment_sub_page02, container, false);
    wv = (WebView) root.findViewById(R.id.webView2);
    WebSettings settings = wv.getSettings();
    settings.setJavaScriptEnabled(true);
    wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
    wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    wv.getSettings().setAppCacheEnabled(true);
    wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

    settings.setDomStorageEnabled(true);
    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    settings.setUseWideViewPort(true);
    settings.setSavePassword(true);
    settings.setSaveFormData(true);
    this.wv.loadUrl("http://oasiscrescent.net/eap/stores/txnview/NotAllocated/");
    wv.setWebViewClient(new MywebViewClient());
            return root;

        }
        else {
    View root = inflater.inflate(R.layout.fragment_main, container, false);
    final TextView textView = root.findViewById(R.id.section_label);
    pageViewModel.getText().observe(this, new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            textView.setText(s);
        }
    });
    return root;
}
    }


    private class MywebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("www.uandblog.com")){
                return false;
            }

            else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        }
        ProgressDialog pd = null;
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            pd = new ProgressDialog(getContext());
            pd.setTitle("please wait...");
            pd.setMessage("System is loading Txn..");
            pd.show();
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            pd.dismiss();
            super.onPageFinished(view, url);
        }
    }




/*    private void alertView( String message ) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle( "Hello" )
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage(message)
//     .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//      public void onClick(DialogInterface dialoginterface, int i) {
//          dialoginterface.cancel();
//          }})
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                }).show();
    }*/
}