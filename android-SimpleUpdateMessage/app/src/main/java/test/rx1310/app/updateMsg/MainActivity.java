package test.rx1310.app.updateMsg;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		new Updater().execute(this);
    }
	
	public void Update(final Integer lastAppVersion) {
		runOnUiThread(new Runnable() {
			public void run() {
				TextView tv1 = findViewById(R.id.tv1);
				TextView tv2 = findViewById(R.id.tv2);
				Button btn1 = findViewById(R.id.btn1);
				
				tv1.setText("Доступна версия: " + lastAppVersion);
				tv2.setText("https://github.com/rx1310/test-SimpleUpdateMessage/releases/tag/" + lastAppVersion);
				
				OnClickListener btn1cl = new OnClickListener() {
					@Override
					public void onClick(View v) {
						SettingsManager.put(MainActivity.this, "LastIgnoredUpdateVersion", lastAppVersion.toString());
					}
				};
				
				btn1.setOnClickListener(btn1cl);
				
			}
		});
	}
	
	public void ignoreBuild(View v) {
		
	}
	
}

class Updater extends AsyncTask<MainActivity, Void, Void> {

	private Exception ex;

	protected Void doInBackground(MainActivity... activity) {
		checkUpdates(activity[0]);
		return null;
	}

	Integer getLastAppVersion() {
		try {
			URL url = new URL("https://raw.githubusercontent.com/rx1310/test-SimpleUpdateMessage/master/app/build.gradle");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				int f = str.indexOf("versionRelease");
				if (f != -1) {
					str = str.substring(f + ("versionRelease").length()).trim();
					return Integer.parseInt(str);
				}
			}
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	void checkUpdates( final MainActivity activity) {
		final Integer lastAppVersion = getLastAppVersion();
		if (lastAppVersion == null) {
			return;
		} else if (lastAppVersion <= 0) {
			return;
		}
		
		String li = SettingsManager.get(activity, "LastIgnoredUpdateVersion");

		if (li != null) {
			Integer liInt = Integer.parseInt(li);
			if (liInt >= lastAppVersion)
				return;
		}
		activity.Update(lastAppVersion);
	}

}
