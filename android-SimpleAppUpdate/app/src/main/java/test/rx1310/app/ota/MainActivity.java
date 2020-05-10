package test.rx1310.app.ota;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
				@Override
				public void run() {
					AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
					builder.setMessage("Доступно обновление приложения rx1310•OTA до версии " +
									   lastAppVersion + " - желаете обновиться? " +
									   "Если вы согласны - вы будете перенаправлены к скачиванию APK файла,"
									   +" который затем нужно будет открыть.")
                        .setCancelable(true)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                String apkUrl = "https://github.com/rx1310/test-SimpleAppUpdate/releases/download/" +
									lastAppVersion + "/app-release.apk";
                                //intent.setDataAndType(Uri.parse(apkUrl), "application/vnd.android.package-archive");
                                intent.setData(Uri.parse(apkUrl));

                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SettingsManager.put(MainActivity.this, "LastIgnoredUpdateVersion", lastAppVersion.toString());
                                dialog.cancel();
                            }
                        });
					AlertDialog alert = builder.create();
					alert.show();
				}
			});
    }
	
}


class Updater extends AsyncTask<MainActivity, Void, Void> {

    private Exception exception;

    protected Void doInBackground(MainActivity... activity) {
        checkUpdates(activity[0]);
        return null;
    }

    Integer getLastAppVersion() {
        try {
            // Create a URL for the desired page
            URL url = new URL("https://raw.githubusercontent.com/rx1310/test-SimpleAppUpdate/master/app/release.txt");
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                int f = str.indexOf("versionRelease");
                if (f != -1) {
                    str = str.substring(f + ("versionRelease").length()).trim();
                    Log.d("rx1310•OTA", "Last release version: " + str);
                    return Integer.parseInt(str);
                }
            }
            in.close();
            Log.d("rx1310•OTA", "Failed to get last release version! 1");
        } catch (Exception e) {
            Log.d("rx1310•OTA", "Failed to get last release version:");
            e.printStackTrace();
        }
        return null;
    }

    void checkUpdates(final MainActivity activity) {
        final Integer lastAppVersion = getLastAppVersion();
        if (lastAppVersion == null)
            return;
        if (lastAppVersion <= 0) {
            Log.d("rx1310•OTA", "App version is okay, skipping update");
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
