/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.novax.ota;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import android.util.Log;

public class MainActivity extends Activity {
     
	public String TAG = "nx-ota";
	
	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		
		setContentView(R.layout.activity_main);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		new Updater().execute(this);
		Log.d(TAG, "Updater execute");
		
	}
	
	public void Update(final Integer lastApkVersion) {
		
		runOnUiThread(new Runnable() {

			Button btnDownloadApk;
			TextView tvInstalledVersion;
			TextView tvOTAVersion;
			
			public void run() {
				
				Log.d(TAG, "run();");
				
				tvInstalledVersion = findViewById(R.id.tvInstalledVersion);
				tvInstalledVersion.setText("dbg-(7.12)");
				
				tvOTAVersion = findViewById(R.id.tvOTAVersion);
				tvOTAVersion.setText("" + lastApkVersion);
				
				btnDownloadApk = findViewById(R.id.btnDownloadApk);
				
				OnClickListener btnDownloadApk$click = new OnClickListener() {
					public void onClick(View v) {
						// Переход по ссылке
						Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_LONG).show();
					}
				};
				
				btnDownloadApk.setOnClickListener(btnDownloadApk$click);
				
			}
			
		});
		
	}
	
}

class Updater extends AsyncTask<MainActivity, Void, Void> {

	protected Void doInBackground(MainActivity... activity) {
		checkUpdates(activity[0]);
		return null;
	}

	Integer getLastApkVersion() {
		
		try {
			URL url = new URL("https://raw.githubusercontent.com/rx1310/novax-ota/master-ota/update.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				int f = str.indexOf("LAST_TEST");
				if (f != -1) {
					str = str.substring(f + ("LAST_TEST").length()).trim();
					return Integer.parseInt(str);
				}
			}
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	void checkUpdates(final MainActivity activity) {
		
		final Integer lastApkVersion = getLastApkVersion();
		
		if (lastApkVersion == null) {
			return;
		} else if (lastApkVersion <= 0) {
			return;
		}
		
		String li = SettingsManager.get(activity, "LastIgnoredUpdateVersion");

		if (li != null) {
			Integer liInt = Integer.parseInt(li);
			if (liInt >= lastApkVersion)
				return;
		}

		activity.Update(lastApkVersion);
		
	}

}
