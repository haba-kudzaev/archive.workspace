/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) the1310, 2020
 * @license     MIT License
 */

package the1310.rx1310.app.tooler.utilities;

import android.content.*;
import android.webkit.*;
import android.os.*;

public class SysInfoUtils {

	public static String getSystemUserAgent(Context context) {

		WebView wv = new WebView(context);
        String ua = wv.getSettings().getUserAgentString();
		return ua;

	}

}
