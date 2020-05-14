package test.rx1310.shellexec;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;

public class MainActivity extends Activity {
	
	private Button btnExec;
	private EditText fieldCommand;
	private CheckBox boxSu;
	private TextView textOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		fieldCommand = findViewById(R.id.field_exec);
		boxSu = findViewById(R.id.checbox_su);
		btnExec = findViewById(R.id.btn_execute);
		textOut = findViewById(R.id.command_output);

		btnExec.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View p1) {
					String out = CommandExecutor.execute(boxSu.isChecked(), fieldCommand.getText().toString()).getResult();
					textOut.setText(out);
				}


		});
		
	}
	
}
