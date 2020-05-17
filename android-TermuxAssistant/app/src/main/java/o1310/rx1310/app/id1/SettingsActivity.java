/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.id1;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.ListView;

public class SettingsActivity extends PreferenceActivity {
	
	ListView mListView;
	SharedPreferences mSharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		setTheme(R.style.AppTheme_Preferences);
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.activity_settings);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		mListView = findViewById(android.R.id.list);
		mListView.setDivider(null);
		
	}
	
}
