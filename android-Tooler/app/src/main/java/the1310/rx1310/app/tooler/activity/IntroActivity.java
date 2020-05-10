/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) the1310, 2020
 * @license     MIT License
 */

package the1310.rx1310.app.tooler.activity;

import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.support.v7.app.*;
import the1310.rx1310.app.tooler.utilities.AppUtils;
import the1310.rx1310.app.tooler.*;

public class IntroActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {

					Intent i = new Intent(IntroActivity.this, MainActivity.class);
					IntroActivity.this.startActivity(i);
					// после того как загрузились в MainActivity нужно
					// убить SplashActivity, иначе при нажатии на кнопку Back
					// юзер снова попадет на сплеш.
					IntroActivity.this.finish();
					overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
				}

			}, 2200);
			
	}

}
