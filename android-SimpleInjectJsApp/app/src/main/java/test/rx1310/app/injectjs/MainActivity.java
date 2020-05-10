package test.rx1310.app.injectjs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		WebView webView = (WebView) findViewById(R.id.webBrowser);
		webView.loadUrl("file:///android_res/raw/test.html");
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new JavaScriptInterface(this), "AndroidFunction");
    }
	public class JavaScriptInterface {
		Context mContext;

		JavaScriptInterface(Context c) {
			mContext = c;
		}

		@JavascriptInterface
		public void showToast(int toast) {
			if (toast >= 190405) {
				Toast.makeText(mContext, "55", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(mContext, "Fuck", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
