/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.id1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity
 {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {

					Intent i = new Intent(SplashActivity.this, MainActivity.class);
					SplashActivity.this.startActivity(i);
					SplashActivity.this.finish();
					overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
					
				}

			}, 200);
	}

}
