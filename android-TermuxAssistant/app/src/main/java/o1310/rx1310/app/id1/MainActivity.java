/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.id1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import o1310.rx1310.app.id1.MainActivity;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {
	
	WebView mWebView;
	ProgressBar mPageLoadProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		mPageLoadProgress = findViewById(R.id.mPageLoadProgress);
		
		initWebView();
		
	}

	void initWebView() {

		mWebView = findViewById(R.id.mWebView);

		WebSettings WebViewSettings = mWebView.getSettings();
		WebViewSettings.setDefaultTextEncodingName("utf-8");
		mWebView.addJavascriptInterface(new JavaScriptInterface(this), "jsFn");
		mWebView.setBackgroundColor(Color.parseColor("#000000"));
		mWebView.setFocusable(true);
		mWebView.setFocusableInTouchMode(true);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setDatabaseEnabled(true);
		mWebView.getSettings().setDatabasePath("/data/data/o1310.rx1310.app.id1/databases/");
		mWebView.getSettings().setAppCacheEnabled(true);
		mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		mWebView.setWebViewClient(new WebViewClient() {

				public boolean shouldOverrideUrlLoading(WebView view, String url) {

					if (Uri.parse(url).getHost().length() == 0) {
						return false;
					}

					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					view.getContext().startActivity(i);
					return true;

				}

				@SuppressWarnings("deprecation")
				@Override
				public void onReceivedError(WebView view, int error, String desc, String failingUrl) {

					final String logError = "code: " + error + "\ndesc: " + desc + "\nurl: " + failingUrl;
					
						AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
						ab.setMessage(logError);
						ab.setTitle(R.string.application_name);
						ab.setCancelable(false);
						ab.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface du, int id) {
									onWebViewPageBack(mWebView);
								}
							});
						AlertDialog ad = ab.create();
						ad.show();
					

				}


			});
			
		mWebView.setWebChromeClient(new WebChromeClient() {

				public void onProgressChanged(WebView view, int progress) {

					if(progress < 100 && mPageLoadProgress.getVisibility() == View.GONE) {
						mPageLoadProgress.setVisibility(View.VISIBLE);
					} else if (progress == 100) {
						mPageLoadProgress.setVisibility(View.GONE);
					} else {
						mPageLoadProgress.setVisibility(View.VISIBLE);
					}

				}

			});

		mWebView.loadUrl("file:///android_asset/" + "index.html");

	}

	public class JavaScriptInterface {
		
		Context mContext;

		JavaScriptInterface(Context c) {
			mContext = c;
		}

		@JavascriptInterface
		public void showToast(String t) {
			Toast.makeText(mContext, t, Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			onWebViewPageBack(mWebView);
			return true;
		} 

		return super.onKeyDown(keyCode, event);
	}
	
	public void onWebViewPageBack(View view) {

		if (mWebView.isFocused() && mWebView.canGoBack()) {
			mWebView.goBack();      
		} else {
			super.onBackPressed();
			finish();
		}

	}

}

