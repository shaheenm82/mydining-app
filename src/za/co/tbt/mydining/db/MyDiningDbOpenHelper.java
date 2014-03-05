package za.co.tbt.mydining.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.OpenableColumns;
import android.util.Log;

public class MyDiningDbOpenHelper extends SQLiteOpenHelper {
	private static final String DATABASE_PATH = "/data/data/za.co.tbt.mydining/databases/";
	private static final String DATABASE_NAME = "dining.sqlite";
	private static final int SCHEMA_VERSION = 2;
	
	private static SQLiteDatabase dbSqlite;
	
	private final Context context;
	
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
		if ( oldVersion != SCHEMA_VERSION){
			copyDBFromResource();
		}
	}
	
	public void createDatabase(){
		boolean dbExist = DBExists();
		
		if (!dbExist){
			//This call creates and empty database in the default.
			//This is required in order to overwrite the database with our new one.
			this.getReadableDatabase();
			
			//now copy our new database
			copyDBFromResource();
		}
	}
	
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
	
	private void copyDBFromResource(){
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		String databasePath = DATABASE_PATH + DATABASE_NAME;
		try {
			inputStream = context.getAssets().open(DATABASE_NAME);
			
			outputStream = new FileOutputStream(databasePath);
			
			byte[] buffer = new byte[1024];
			int length;
			
			while ((length = inputStream.read(buffer)) > 0){
				outputStream.write(buffer, 0, length);
			}
			
			outputStream.flush();
			outputStream.close();
			inputStream.close();
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
}
