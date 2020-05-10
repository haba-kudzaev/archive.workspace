/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) the1310, 2020
 * @license     MIT License
 */

package the1310.rx1310.app.tooler.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import the1310.rx1310.app.tooler.R;

public class UtilsFragment extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		ArrayAdapter<CharSequence> a = ArrayAdapter.createFromResource(getActivity(), R.array.sample, R.layout.tlr_ui_view_list_item);
		setListAdapter(a);

	}

	public void onListItemClick(ListView lv, View v, int p, long lg) {
		super.onListItemClick(lv, v, p, lg);

		switch (p) {

			case 0:
				//startActivity(new Intent(getActivity(), ProjectCreatorActivity.class));
				break;

			case 1:
				Toast.makeText(getActivity(), "Pos" + p, Toast.LENGTH_LONG).show();
				break;

			default: break;

		}

	}

}
