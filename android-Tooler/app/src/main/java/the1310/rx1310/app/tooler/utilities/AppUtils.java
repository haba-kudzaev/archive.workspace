/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) the1310, 2020
 * @license     MIT License
 */

package the1310.rx1310.app.tooler.utilities;

import android.content.*;
import android.net.*;
import android.support.design.widget.*;
import android.view.*;
import android.widget.*;

public class AppUtils {
	
	public static void Toast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static void Snackbar(View view, String message) {
		Snackbar.make(view, message, 2000).show();
	}

	public static void CopyTextToClipboard(Context context, String text) {
		ClipboardManager cb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData cd = ClipData.newPlainText("", text);
		cb.setPrimaryClip(cd);
	}

	public static void sendReport(Context context, String subject, String message) {

		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL, new String[]{"codev01.private@gmail.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, subject);
		i.putExtra(Intent.EXTRA_TEXT, message);

		try { 
			context.startActivity(Intent.createChooser(i, "Send with")); 
		} catch (ActivityNotFoundException e) {
			AppUtils.Toast(context, "App not found!");
		}

	}

	public static void jumpLink(Context context, String link) {

		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(link));
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);

	}
	
}
