package hackthenorth.sobriety;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.InputType;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ContactsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		ListView listview = (ListView) findViewById(R.id.list);

		//Creating a fake list of stuff
		String[] values = new String[] { "Cassie", "Brian", "Greg",
				"Becca", "Sarah", "Emily", "Ryan", "Meghan",
				"Laura", "Tim", "Kevin", "Mark"};
		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}

		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//Here is where you open the phone app and call someone
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contacts, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()) {
		case R.id.action_settings: 
			return true;
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_new:
			addContact();
			return true;
		case R.id.action_delete:
			deleteContacts();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void addContact() {
		/*AlertDialog.Builder alert = new AlertDialog.Builder(this);
		LayoutInflater inflater = getLayoutInflater();

		alert.setTitle("New sober person");

		alert.setView(inflater.inflate(R.layout.contact_popup, null));
		final EditText nameIn = (EditText) findViewById(R.id.contact_name);
		final EditText phoneIn = (EditText) findViewById(R.id.contact_phone);

		alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String contactName = nameIn.getText().toString();
				String contactPhone = phoneIn.getText().toString();
				// Do something with value!
			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});

		alert.show();*/
		
		final View view = findViewById(R.id.contact_popup);
		final EditText nameIn = (EditText) findViewById(R.id.contact_name);
		final EditText phoneIn = (EditText) findViewById(R.id.contact_phone);

		new AlertDialog.Builder(this)
		.setTitle("You got a new highscore!")
		.setView(view)
		.setPositiveButton("Save", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				saveContact(nameIn.getText().toString(), phoneIn.getText().toString());
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Do nothing.
			}
		}).show();
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

	private void deleteContacts() {

	}
	
	private void saveContact(String name, String phone) {
		
	}
}