package hackthenorth.sobriety;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class GetHomeActivity extends Activity {

	String address = "100 University Ave W, Waterloo, ON";
	SharedPreferences sharedpreferences;
	public static final String PREFS_NAME = "MyApp_Settings";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_home);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		sharedpreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		if(sharedpreferences.contains("address")){
			address = sharedpreferences.getString("address", "");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if(item.getItemId()==R.id.action_settings) {
			editAddress();
			return true;
		} else if(item.getItemId()==R.id.home) {
			NavUtils.navigateUpFromSameTask(this);
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void walk(View v) {
		showMap();
	}
	
	public void catchABus(View v) {
		showMap();
	}
	
	public void callACab(View v) {
		
	}
	
	public void editAddress() {
		LayoutInflater factory = LayoutInflater.from(this);

		//text_entry is an Layout XML file containing two text field to display in alert dialog
		final View textEntryView = factory.inflate(R.layout.address_popup, null);

		final EditText input1 = (EditText) textEntryView.findViewById(R.id.textA);
		input1.setText(address);

		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setIcon(R.drawable.ic_launcher).setTitle("Edit home address:").setView(textEntryView).setPositiveButton("Save",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,
					int whichButton) {
				address = input1.getText().toString();
				Editor editor = sharedpreferences.edit();
				editor.putString("address", address);
				editor.commit();
			}
		}).setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,
					int whichButton) {
				/*
				 * User clicked cancel so don't do anything
				 */
			}
		});
		alert.show();
	}
	
	public void showMap() {
		String uri = "google.navigation:q="+address;
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
	}
}
