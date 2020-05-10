/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) the1310, 2020
 * @license     MIT License
 */

package the1310.rx1310.app.tooler.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import the1310.rx1310.app.tooler.R;
import the1310.rx1310.app.tooler.adapter.ToolerFragmentPagerAdapter;
import the1310.rx1310.app.tooler.fragment.UtilsFragment;

public class MainActivity extends AppCompatActivity
 {

	Toolbar mToolbar;
	ViewPager mViewPager;
	TabLayout mTabLayout;

	SharedPreferences mSharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		mToolbar = findViewById(R.id.ui_view_toolBar);
		mViewPager = findViewById(R.id.ui_view_viewPager);
		mTabLayout = findViewById(R.id.ui_view_tabLayout);

		setSupportActionBar(mToolbar);
		setViewPager(mViewPager);
		mTabLayout.setupWithViewPager(mViewPager);
		
		if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String []{
				Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
        }
		
		
	}

	private void setViewPager(ViewPager vp) {

		ToolerFragmentPagerAdapter a = new ToolerFragmentPagerAdapter(getSupportFragmentManager());

		a.addFragment(new UtilsFragment(), getString(R.string.tab_tools));
		a.addFragment(new UtilsFragment(), getString(R.string.tab_tools_superuser));
		a.addFragment(new UtilsFragment(), getString(R.string.tab_converters));
		a.addFragment(new UtilsFragment(), getString(R.string.tab_aideweb));
		a.addFragment(new UtilsFragment(), getString(R.string.tab_other));
		
		vp.setAdapter(a);

	}

	/*@Override
    public boolean onCreateOptionsMenu(Menu m) {
        super.onCreateOptionsMenu(m);

        m.add(0, 1, 0, R.string.tlr$activity__SettingsActivity);
		m.add(0, 2, 1, R.string.tlr$activity__AboutActivity);

        return true;
		
    }*/
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		//menu.getItem(1).setVisible(false);
		return super.onCreateOptionsMenu(menu);
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {

        int id = mi.getItemId();

        switch(id) {

            case R.id.menu_security_msg :
				securityMessage();
                //startActivity(new Intent(this, SettingsActivity.class));
                return true;

			case R.id.menu_settings_debug : 
				//startActivity(new Intent(this, DebugSettingsActivity.class));
				return true;
				
			case R.id.menu_settings :
				startActivity(new Intent(this, SettingsActivity.class));
                return true;

			case R.id.menu_about : 
				//startActivity(new Intent(this, AboutActivity.class));
				return true;

        }

        return super.onOptionsItemSelected(mi);
    }
	
	void securityMessage() {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setIcon(R.drawable.ic_security);
		b.setTitle(R.string.security_msg);
		b.setMessage(R.string.security_msg_desc);
		AlertDialog d = b.create();
		d.show();
	}


}

