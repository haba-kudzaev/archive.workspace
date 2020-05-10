/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) the1310, 2020
 * @license     MIT License
 */

package the1310.rx1310.app.tooler.adapter;

import android.preference.*;
import java.util.*;
import android.support.v4.app.*;
import android.graphics.drawable.*;

public class ToolerFragmentPagerAdapter extends FragmentPagerAdapter {

	private final List<Fragment> mFragment = new ArrayList<>();
	private final List<String> mTitle = new ArrayList<>();

	public ToolerFragmentPagerAdapter(FragmentManager m) {
		super(m);
	}


	public Fragment getItem(int p) {
		return mFragment.get(p);
	}

	@Override
	public int getCount() {
		return mFragment.size();
	}

	public void addFragment(Fragment f, String t) {
		mFragment.add(f);
		mTitle.add(t);
	}
	@Override

	public CharSequence getPageTitle(int p) {
		return mTitle.get(p);
	}

}
