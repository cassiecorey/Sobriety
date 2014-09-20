package hackthenorth.sobriety;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class CalculatorActivity extends Activity {
	ViewFlipper viewFlipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void goToWeight(View v){
		
		viewFlipper.showNext();	
	}
	public void goToCount(View v){
		viewFlipper.showNext();
	}
	
	public void goToTime(View v){
		
		viewFlipper.showNext();
	}
	
	public void calculate(View v){
		viewFlipper.showNext();
	}
}
