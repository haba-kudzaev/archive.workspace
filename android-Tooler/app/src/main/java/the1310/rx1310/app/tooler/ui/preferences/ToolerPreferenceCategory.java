/*!
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) the1310, 2020
 * @license     MIT License
 */

package the1310.rx1310.app.tooler.ui.preferences;

import android.content.*;
import android.preference.*;
import android.util.*;
import the1310.rx1310.app.tooler.*;

public class ToolerPreferenceCategory extends PreferenceCategory {

	public ToolerPreferenceCategory(Context c, AttributeSet attrs) {
		super(c, attrs);
		setSelectable(false);
		setLayoutResource(R.layout.tlr_ui_preference_category);
	}

}
