package za.co.tbt.mydining.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import za.co.tbt.mydining.service.DBStatusListener;
import za.co.tbt.mydining.service.DBStatusService;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.util.Log;

public class MyDiningDbOpenHelper extends SQLiteOpenHelper implements DBStatusListener{
	private static final String DATABASE_PATH = "/data/data/za.co.tbt.mydining/databases/";
	private static final String DATABASE_NAME = "dining.sqlite";
	private static final int SCHEMA_VERSION = 2;
	private static final String DB_VERSION_KEY = "db_version_pref";
	private static boolean dbExist;
	
	private static SQLiteDatabase dbSqlite;	
	private final Context context;
	private ProgressDialog checkDBDialog;
	
	
	public MyDiningDbOpenHelper(Context context){
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
		this.context = context;
	}
	
	
	public SQLiteDatabase getOpenDatabase(){
		if (dbSqlite == null){
			openDataBase();
		}
		return dbSqlite;	
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		copyDBFromResource(null);		
	}
	
	public void createDatabase(){
		dbExist = DBExists();
		
		if (!dbExist){
			//This call creates an empty database in the default.
			//This is required in order to overwrite the database with our new one.
			this.getReadableDatabase();
			
			//now copy our new database
			copyDBFromResource(null);
		}else{						
			checkForDBUpdate();
			//dbExist = false;
		}
	}
	
	/*public boolean isDBAvailable(){
		return dbExist;
	}*/
	
	private boolean DBExists(){
		SQLiteDatabase db = null;
		
		try{
			String databasePath = DATABASE_PATH + DATABASE_NAME;
			db = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
			db.setLocale(Locale.getDefault());
			db.setLockingEnabled(true);
			db.setVersion(SCHEMA_VERSION);
		}catch (SQLiteException sqle){
			Log.e("SQLHelper", "Database not found");
		}
		
		if (db != null){
			db.close();
		}
		
		return db != null ? true : false;
	}
	
	public void checkForDBUpdate(){
		//String version = "";		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		String db_version = sharedPref.getString(DB_VERSION_KEY, "");
		
		checkDBDialog = ProgressDialog.show(context, "Checking for Database Update", "",true);
		DBStatusService dbStatusService = new DBStatusService(this);
		dbStatusService.execute(db_version, DATABASE_PATH + DATABASE_NAME + ".tmp");		
		
		//return version;
	}
	
	private void copyDBFromResource(String path){
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		String databasePath = DATABASE_PATH + DATABASE_NAME;
		try {
			if (path == null){
				inputStream = context.getAssets().open(DATABASE_NAME);
			}else{
				inputStream = new FileInputStream(path);
			}
			
			outputStream = new FileOutputStream(databasePath);
			
			byte[] buffer = new byte[1024];
			int length;
			
			while ((length = inputStream.read(buffer)) > 0){
				outputStream.write(buffer, 0, length);
			}
			
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			
			//dbExist = true;
		} catch (IOException ioe){
			throw new Error("Problem copying database from resource file");
		}
	}
	
	public void openDataBase() throws SQLException{
		String dbPath = DATABASE_PATH + DATABASE_NAME;
		dbSqlite = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);		
	}
	
	public void closeDataBase(){
		if (dbSqlite != null){
			dbSqlite.close();
		}
		super.close();		
	}

	@Override
	public void databaseRetrieved(String version) {
		// TODO Auto-generated method stub
		if (version != null){
			copyDBFromResource(DATABASE_PATH + DATABASE_NAME + ".tmp");
			
			SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
			Editor prefEditor = sharedPref.edit();
			
			prefEditor.putString(DB_VERSION_KEY, version);
			prefEditor.commit();
		}
		checkDBDialog.dismiss();
	}

	@Override
	public void databaseStatusUpdated(String progress) {
		// TODO Auto-generated method stub
		checkDBDialog.setMessage(progress);
	}	
}
