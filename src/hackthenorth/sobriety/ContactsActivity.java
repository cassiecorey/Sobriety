package hackthenorth.sobriety;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ContactsActivity extends ActionBarActivity {

	private DbHelper dbHelper = new DbHelper(this);
	private ArrayList<Contacts> contacts = new ArrayList<Contacts>();
	private ArrayList<String> names = new ArrayList<String>();
	private ListView list;
	CustomList adapter;
	String[] namesA;
	Bundle savedInstanceState;

	public class CustomList extends ArrayAdapter<String>{
		private final Activity context;
		private final String[] web;
		private final int imageId;
		public CustomList(Activity context, String[] web, int imageId) {
			super(context, R.layout.list_item, web);
			this.context = context;
			this.web = web;
			this.imageId = imageId;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View rowView = inflater.inflate(R.layout.list_item, null, true);
			TextView txtTitle = (TextView) rowView.findViewById(R.id.text1);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
			imageView.setImageResource(R.drawable.ic_launcher);
			imageView.setTag(false);
			txtTitle.setText(web[position]);
			return rowView;
		}

		public void setData(String[] n) {
			namesA = n;
			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() {
					notifyDataSetChanged();
				}
			});
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.savedInstanceState = savedInstanceState;
		setContentView(R.layout.activity_contacts);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		List<Contacts> tempLst = dbHelper.getAllContacts();
		for(Contacts c : tempLst) {
			contacts.add(c);
			names.add(c.getName());
		}
		namesA = new String[names.size()];
		for(int i=0; i<names.size(); i++){
			namesA[i] = names.get(i);
		}

		adapter = new CustomList(this, namesA, R.drawable.ic_launcher);
		list = (ListView)findViewById(R.id.list);
		list.setAdapter(adapter);

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
		LayoutInflater factory = LayoutInflater.from(this);

		//text_entry is an Layout XML file containing two text field to display in alert dialog
		final View textEntryView = factory.inflate(R.layout.contact_popup, null);

		final EditText input1 = (EditText) textEntryView.findViewById(R.id.contact_name);
		final EditText input2 = (EditText) textEntryView.findViewById(R.id.contact_phone);

		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setIcon(R.drawable.ic_launcher).setTitle("Sober person:").setView(textEntryView).setPositiveButton("Save",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,
					int whichButton) {
				saveContact(input1.getText().toString(), input2.getText().toString());
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


	private void deleteContacts() {
		ArrayList<Contacts> deleteContacts = new ArrayList<Contacts>();
		for(int i=0; i<list.getCount(); i++) {
			View v = list.getChildAt(i);
			ImageView iv = (ImageView)v.findViewById(R.id.img);
			if(iv.getTag() != null && iv.getTag().equals(true)) {
				String name = ((TextView)v.findViewById(R.id.text1)).getText().toString();
				for(Contacts c : contacts) {
					if(c.getName().equals(name)) {
						deleteContacts.add(c);
					}
				}
			}
		}
		for(Contacts c : deleteContacts) {
			dbHelper.deleteContact(c);
			contacts.remove(c);
			names.remove(c.getName());
		}
		adapter.notifyDataSetChanged();
		Intent intent = new Intent(this, ContactsActivity.class);
		startActivity(intent);
		finish();
	}

	private void saveContact(String name, String phone) {
		dbHelper.addContacts(new Contacts(name, phone));
		contacts.add(new Contacts(name, phone));
		names.add(name);
		namesA = new String[names.size()];
		for(int i=0; i<names.size(); i++){
			namesA[i] = names.get(i);
		}
		list.setAdapter(adapter);
		adapter.setData(namesA);
		adapter.notifyDataSetChanged();
		Intent intent = new Intent(this, ContactsActivity.class);
		startActivity(intent);
		finish();
	}

	public void selected(View v) {
		if(v.getTag().equals(false)){
			((ImageButton) v).setImageResource(R.drawable.ic_action_accept);
			v.setTag(true);
		} else {
			((ImageButton) v).setImageResource(R.drawable.ic_launcher);
			v.setTag(false);
		}
	}

	public void makeCall(View v) {
		TextView t = (TextView)v.findViewById(R.id.text1);
		String name = t.getText().toString().trim();
		String number = "";
		for(Contacts c: contacts) {
			if(c.getName().equals(name)) {
				number = c.getPhone(); break;
			}
		}
		number = "tel:" + number;
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse(number));
		startActivity(intent);
	}

}