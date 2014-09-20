package hackthenorth.sobriety;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.ViewFlipper;

public class CalculatorActivity extends Activity {
	ViewFlipper viewFlipper;
	private int weightLBS;
	private int weightKgs;
	private int count;
	private double BAC;
	private char gender;
	private int[] time = new int[3];
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
		if(((Button)v).getText().toString()=="MALE"){
			gender = 'm';
		}
		else{
			gender = 'f';
		}
		viewFlipper.showNext();
		NumberPicker weightPicker = (NumberPicker) findViewById(R.id.weight_picker);
		weightPicker.setMinValue(85);
		weightPicker.setMaxValue(400);
		weightPicker.setValue(150);
		weightPicker.setWrapSelectorWheel(false);
		weightPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
	}
	
public void LBS(View v){
	
		Button button = (Button) findViewById(R.id.weight_type);
		if(button.getText().toString() == "LBS"){
			button.setText("Kgs");
			NumberPicker weightPicker = (NumberPicker) findViewById(R.id.weight_picker);
			weightPicker.setMinValue(40);
			weightPicker.setMaxValue(185);
			weightPicker.setValue(70);
			weightPicker.setWrapSelectorWheel(false);
		}
		else{
			button.setText("LBS");
			NumberPicker weightPicker = (NumberPicker) findViewById(R.id.weight_picker);
			weightPicker.setMinValue(85);
			weightPicker.setMaxValue(400);
			weightPicker.setValue(150);
			weightPicker.setWrapSelectorWheel(false);
			
		}
		
		
	}

	public void goToCount(View v){
		NumberPicker weightPicker = (NumberPicker) findViewById(R.id.weight_picker);
		Button button = (Button) findViewById(R.id.weight_type);
		if(button.getText().toString()=="Kgs"){
			weightKgs = weightPicker.getValue();
		}
		else{
			weightLBS = weightPicker.getValue();
		}
		viewFlipper.showNext();
		NumberPicker countPicker = (NumberPicker) findViewById(R.id.count_picker);
		countPicker.setMinValue(0);
		countPicker.setMaxValue(40);
		countPicker.setValue(2);
		countPicker.setWrapSelectorWheel(false);
		countPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
	}

	public void goToTime(View v){
		NumberPicker countPicker = (NumberPicker) findViewById(R.id.count_picker);
		count = countPicker.getValue();
		viewFlipper.showNext();
		NumberPicker hours = (NumberPicker) findViewById(R.id.hours);
		NumberPicker minutes = (NumberPicker) findViewById(R.id.minutes);
		NumberPicker ampm = (NumberPicker) findViewById(R.id.ampm);
		
		hours.setMinValue(0);
		hours.setMaxValue(12);
		minutes.setMinValue(0);
		minutes.setMaxValue(59);
		ampm.setMinValue(0);
		ampm.setMaxValue(1);
		ampm.setDisplayedValues(new String[] { "AM", "PM"});
		
		String[] mins = new String[60];
		for(int i=0; i<10; i++) {
			mins[i] = "0" + i;
		}
		for(int i=10; i<60; i++) {
			mins[i] = String.valueOf(i);
		}
		minutes.setDisplayedValues(mins);
		
		hours.setValue(Calendar.HOUR);
		minutes.setValue(Calendar.MINUTE);
		ampm.setValue(Calendar.AM_PM);
		
	}

	public void calculate(View v){
		viewFlipper.showNext();
		NumberPicker ampm = (NumberPicker) findViewById(R.id.ampm);
		NumberPicker minutes = (NumberPicker) findViewById(R.id.minutes);
		NumberPicker hours = (NumberPicker) findViewById(R.id.hours);
		time[0] = hours.getValue();
		time[1] = minutes.getValue();
		time[2] = hours.getValue();
		int SD;
		double MR;
		double DP;
			
		if(gender == 'm'){
			BAC = (.806*2*1.2)/(weightLBS -.58)-(.015*1);
		}
		
		
		
	}
	
	public void callSomebody(View v){
		Intent intent = new Intent(this, ContactsActivity.class);
		startActivity(intent);
    }

	
}
