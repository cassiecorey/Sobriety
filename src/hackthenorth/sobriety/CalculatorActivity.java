package hackthenorth.sobriety;

import java.text.DecimalFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ViewFlipper;

public class CalculatorActivity extends Activity {
	ViewFlipper viewFlipper;
	private int weightLBS;
	private int weightKgs;
	private int bCount;
	private int wCount;
	private int lCount;
	private double BAC;
	private char gender;
	private String[] levels = {"Feelin' it", "Buzzed", "Tipsy", "Sloppy",
			"Horny", "Reckless", "Hammered", "Blackout", 
			"Definitely dead and somebody stole your phone."};
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

	public void goToWeightM(View v){
		
		gender = 'm';
	
		viewFlipper.showNext();
		NumberPicker weightPicker = (NumberPicker) findViewById(R.id.weight_picker);
		weightPicker.setMinValue(85);
		weightPicker.setMaxValue(400);
		weightPicker.setValue(150);
		weightPicker.setWrapSelectorWheel(false);
		weightPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
	}
	
	public void goToWeightF(View v){
		gender = 'f';
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
		if(button.getText().toString().contains("lbs")){
			button.setText("kgs");
			NumberPicker weightPicker = (NumberPicker) findViewById(R.id.weight_picker);
			weightPicker.setMinValue(40);
			weightPicker.setMaxValue(185);
			weightPicker.setValue(70);
			weightPicker.setWrapSelectorWheel(false);
		}
		else{
			button.setText("lbs");
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
		if(button.getText().toString()=="kgs"){
			weightKgs = weightPicker.getValue();
		}
		else{
			weightLBS = weightPicker.getValue();
		}
		viewFlipper.showNext();
		NumberPicker beerPicker = (NumberPicker) findViewById(R.id.beer_picker);
		beerPicker.setMinValue(0);
		beerPicker.setMaxValue(40);
		beerPicker.setValue(0);
		beerPicker.setWrapSelectorWheel(false);
		beerPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		NumberPicker winePicker = (NumberPicker) findViewById(R.id.wine_picker);
		winePicker.setMinValue(0);
		winePicker.setMaxValue(40);
		winePicker.setValue(0);
		winePicker.setWrapSelectorWheel(false);
		winePicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		NumberPicker liquorPicker = (NumberPicker) findViewById(R.id.liquor_picker);
		liquorPicker.setMinValue(0);
		liquorPicker.setMaxValue(40);
		liquorPicker.setValue(0);
		liquorPicker.setWrapSelectorWheel(false);
		liquorPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
	}

	public void goToTime(View v){
		NumberPicker winePicker = (NumberPicker) findViewById(R.id.wine_picker);
		NumberPicker beerPicker = (NumberPicker) findViewById(R.id.beer_picker);
		NumberPicker liquorPicker = (NumberPicker) findViewById(R.id.liquor_picker);
		wCount = winePicker.getValue();
		bCount = beerPicker.getValue();
		lCount = liquorPicker.getValue();
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
		Calendar c = Calendar.getInstance(); 
		hours.setValue(c.get(Calendar.HOUR));
		minutes.setValue(c.get(Calendar.HOUR));
		ampm.setValue(c.get(Calendar.HOUR));
		
	}

	public void calculate(View v){
		Calendar c = Calendar.getInstance(); 
		int currentHour = c.get(Calendar.HOUR);
		int currentMinute = c.get(Calendar.MINUTE);
		int currentAMPM = c.get(Calendar.AM_PM);
		int elapsedHours;
		int weight;
		
		
		viewFlipper.showNext();
		NumberPicker ampm = (NumberPicker) findViewById(R.id.ampm);
		NumberPicker minutes = (NumberPicker) findViewById(R.id.minutes);
		NumberPicker hours = (NumberPicker) findViewById(R.id.hours);
		String[] hourString = minutes.getDisplayedValues();
		time[0] = hours.getValue();
		time[1] = Integer.parseInt(hourString[minutes.getValue()]);
		time[2] = ampm.getValue();
		if(currentAMPM==time[2]){
				elapsedHours = currentHour-time[0];
				if(currentMinute - time[1] > 30 ){
					elapsedHours++;
				}
		}
		else{
			elapsedHours = currentHour-time[0] + 12;
			if(currentMinute - time[1] > 30 ){
				elapsedHours++;
			}
		}
		
		if(weightLBS == 0){
			weight = weightKgs;
		}
		else{
			weight = weightLBS;
		}
		int SD = 2*bCount + 3*wCount + 1*lCount;
		double MR;
		double DP = elapsedHours;
			
		if(gender == 'm'){
			BAC = ((.806*SD*1.2)/(weight *.58))-(.015*DP);
		}
		if(gender == 'f'){
			BAC = ((.806*SD*1.2)/(weight *.49))-(.017*DP);
		}
		TextView drunkLevel = (TextView) findViewById(R.id.drunklevel);
		String level = "sober.";
		int color = Color.rgb(63, 255, 0);
		for(int i=0; i<9; i++) {
			if((BAC > i*0.0422) && (BAC < ((i+1)*0.0422))) {
				level = levels[i];
				if(i>3) {
					color = Color.rgb(248, 0, 0);
				}
			}
		}
		if(BAC > 1){
			level = "not as funny as you think you are.";
		}
		drunkLevel.setText(level);
		if(level.length()>7) {
			drunkLevel.setTextSize(30);
		}
		if(BAC < 0){
			BAC = 0;
		}
		
		DecimalFormat twoDForm = new DecimalFormat("#.###"); 
	    BAC = Double.valueOf(twoDForm.format(BAC));
		TextView displayBAC = (TextView) findViewById(R.id.bac);
		displayBAC.setText("Blood alcohol level: " + BAC);
		displayBAC.setTextColor(color);
		
	}
	
	
	public void openContacts(View v){
		Intent intent = new Intent(this, ContactsActivity.class);
		startActivity(intent);
    }
	
	public void goHome(View v){
		Intent intent = new Intent(this, GetHomeActivity.class);
		startActivity(intent);
    }

	
}
