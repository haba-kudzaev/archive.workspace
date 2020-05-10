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

public class SettingsActivity extends PreferenceActivity
 {
	
	SharedPreferences mSharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		setTheme(R.style.AppTheme_Preferences);
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.layout.activity_settings);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
	}
	
}
