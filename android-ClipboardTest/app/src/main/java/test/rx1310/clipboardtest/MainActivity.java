package test.rx1310.clipboardtest;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import test.rx1310.clipboardtest.R;

public class MainActivity extends Activity implements View.OnClickListener
{
	
	EditText et;
	Button btnCopy, btnPaste;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		et = findViewById(R.id.editText);
		et.setOnClickListener(this);
		
		btnCopy = findViewById(R.id.btnCopy);
		btnCopy.setOnClickListener(this);
		
		btnPaste = findViewById(R.id.btnPaste);
		btnPaste.setOnClickListener(this);
		
    }
	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {

			case R.id.btnCopy:
				String str = et.getText().toString();
				ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
				ClipData clip = ClipData.newPlainText(null, str);
				clipboard.setPrimaryClip(clip);
				Toast.makeText(this, "Copy! " + str, Toast.LENGTH_LONG).show();
				break;

			case R.id.btnPaste:
				ClipboardManager clipboard2 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				
				et.setText(clipboard2.getPrimaryClip().getItemAt(0).getText());
				break;
				
			default: break;

		}

	}
}
