package o1310.rx1310.app.quickaccess;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.widget.ListView;

public class MainActivity extends PreferenceActivity {

	SharedPreferences s;
	ListView mListView;

	protected void onCreate(Bundle b) {
		super.onCreate(b);

		addPreferencesFromResource(R.layout.activity_settings);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		mListView = findViewById(android.R.id.list);
		mListView.setDivider(null);
		s = PreferenceManager.getDefaultSharedPreferences(this);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	// обработка нажатий на пункты
	public boolean onPreferenceTreeClick(PreferenceScreen s, Preference p) {

		switch (p.getKey()) {

			// переход в меню "Дополнительное"
			case "goToAdditionallyActivity": 
				//startActivity(new Intent(this, AdditionallyActivity.class)); // вызов активности
				break;

		}

		return super.onPreferenceTreeClick(s, p);

	}

}
