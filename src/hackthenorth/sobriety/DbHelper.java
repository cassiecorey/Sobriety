package hackthenorth.sobriety;

/**
 * Source Taken From: http://www.tutorialspoint.com/android/android_sqlite_database.htm
 * Used to implement solution for database
 */

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "ContactsDB";
	private static final int DATABASE_VERSION = 1;
	   
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE contacts "+
		"(id INTEGER PRIMARY KEY, name TEXT, phone TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS contacts");
		this.onCreate(db);
	}
	
	
	private static final String CONTACTS_TABLE_NAME = "contacts";
	
	private static final String CONTACTS_COLUMN_ID = "id";
	private static final String CONTACTS_COLUMN_NAME = "name";	   
	private static final String CONTACTS_COLUMN_PHONE = "phone";
	
	private static final String [] CONTACTS_COLUMNS = 
		{CONTACTS_COLUMN_ID, CONTACTS_COLUMN_NAME, CONTACTS_COLUMN_PHONE};
	
	public void addContacts(Contacts contact){
		Log.d("addContacts", contact.toString());
		
		//get reference
		SQLiteDatabase db = this.getWritableDatabase();
		
		//create content values
		ContentValues values = new ContentValues();
		values.put(CONTACTS_COLUMN_NAME, contact.getName());
		values.put(CONTACTS_COLUMN_PHONE, contact.getPhone());
		
		db.insert(CONTACTS_TABLE_NAME, null, values);
		db.close();
		
	}
	
	public Contacts getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = 
				db.query(CONTACTS_TABLE_NAME, 
						CONTACTS_COLUMNS, 
						" id = ?", 
						new String[] { String.valueOf(id) }, 
						null, 
						null, 
						null, 
						null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Contacts contact = new Contacts();
		contact.setId(Integer.parseInt(cursor.getString(0)));
		contact.setName(cursor.getString(1));
		contact.setPhone(cursor.getString(2));
		
		Log.d("getContact("+id+")", contact.toString());
		
		return contact;
	}
	
	public List<Contacts> getAllContacts() {
		List<Contacts> contacts = new LinkedList<Contacts>();
		
		//build query
		String query = "SELECT * FROM " + CONTACTS_TABLE_NAME;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		Contacts contact = null;
		
		if(cursor.moveToFirst()){
			do{
				contact = new Contacts();
				contact.setId(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setPhone(cursor.getString(2));
				
				contacts.add(contact);
			} while (cursor.moveToNext());
		}
		
		Log.d("getAllBooks()", contacts.toString());
		
		return contacts;
		
	}
	
	public int updateContact(Contacts contact){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("name", contact.getName());
		values.put("phone", contact.getPhone());
		
		int i = db.update(CONTACTS_TABLE_NAME, 
				values, 
				CONTACTS_COLUMN_ID+" = ?", 
				new String[] { String.valueOf(contact.getId()) });
		db.close();
		
		return i;
	}
	
	public void deleteContact(Contacts contact){
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.delete(CONTACTS_TABLE_NAME, 
				CONTACTS_COLUMN_ID+" = ?", 
				new String[] { String.valueOf(contact.getId()) });
		db.close();
		Log.d("deleteContact", contact.toString());
	}
	
}
