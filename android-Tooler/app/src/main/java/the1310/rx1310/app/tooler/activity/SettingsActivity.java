/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) the1310, 2020
 * @license     MIT License
 */

package the1310.rx1310.app.tooler.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import the1310.rx1310.app.tooler.R;
import the1310.rx1310.app.tooler.activity.core.ToolerPreferenceActivity;

public class SettingsActivity extends ToolerPreferenceActivity
 {

	SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.layout.activity_settings);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		sp = PreferenceManager.getDefaultSharedPreferences(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen prefScr, Preference pref) {

		String mKey = pref.getKey();

		//

		return true;
	}

}
