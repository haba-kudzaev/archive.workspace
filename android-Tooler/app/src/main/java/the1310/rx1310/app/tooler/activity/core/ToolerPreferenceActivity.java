/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) the1310, 2020
 * @license     MIT License
 */

package the1310.rx1310.app.tooler.activity.core;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.ListView;

public class ToolerPreferenceActivity extends PreferenceActivity
 {

	ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		mListView = findViewById(android.R.id.list);
		mListView.setDivider(null);

	}

}
