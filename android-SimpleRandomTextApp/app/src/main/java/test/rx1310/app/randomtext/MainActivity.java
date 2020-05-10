package test.rx1310.app.randomtext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	
	public void showToast(View v) {
		
		Random random = new Random();
		
		Toast.makeText(this, Constants.facts[random.nextInt(6)], Toast.LENGTH_SHORT).show();
	}
	
}
