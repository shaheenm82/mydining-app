package za.co.tbt.mydining.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import za.co.tbt.mydining.service.DBDownloadListener;
import za.co.tbt.mydining.service.DBDownloadService;
import za.co.tbt.mydining.service.DBVersionCheckListener;
import za.co.tbt.mydining.service.DBVersionChecker;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.util.Log;

public class MyDiningDbOpenHelper extends SQLiteOpenHelper {
	private static final String DATABASE_PATH = "/data/data/za.co.tbt.mydining/databases/";
	private static final String DATABASE_NAME = "dining.sqlite";
	private static final int SCHEMA_VERSION = 2;
	private static final String DB_VERSION_KEY = "db_version_pref";
	private static boolean dbExist;
	
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
		copyDBFromResource("copy");		
	}
	
	public void createDatabase(){
		dbExist = DBExists();
		
		if (!dbExist){
			//This call creates an empty database in the default.
			//This is required in order to overwrite the database with our new one.
			this.getReadableDatabase();
			
			//now copy our new database
			copyDBFromResource(null);
			DBVersionCheckListener dbVersionCheckListener = ((DBVersionCheckListener)context);
			dbVersionCheckListener.databaseUpToDate();
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
		}catch (Exception e){
		//catch (SQLiteException sqle){
			Log.e("SQLHelper", "Database not found");
		}
		
		if (db != null){
			db.close();
		}
		
		return db != null ? true : false;
	}
	
	public void checkForDBUpdate(){		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		String db_version = sharedPref.getString(DB_VERSION_KEY, "");
		
		DBVersionChecker dbVersionChecker = new DBVersionChecker((DBVersionCheckListener)context);
		dbVersionChecker.execute(db_version);
		
	}
	
	public void downloadDB(){		
		DBDownloadService dbDownloader = new DBDownloadService((DBDownloadListener)context);
		dbDownloader.execute(DATABASE_PATH + DATABASE_NAME + ".tmp");
		
	}
	
	public boolean updateDBFromResource(){
		return copyDBFromResource(DATABASE_PATH + DATABASE_NAME + ".tmp");
	}
	
	public boolean copyDBFromResource(String path){
		boolean success = false;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		FavouriteDataSource favDataSource = new FavouriteDataSource(context);
		List<Favourite> favourites = new ArrayList<Favourite>();
		
		if (path != null){
			//Copy favourites to memory so that they can be re-inserted later
			favDataSource.open();
			favourites = favDataSource.getAllFavourites();
			favDataSource.close();
		}
		
		String databasePath = DATABASE_PATH + DATABASE_NAME;
		try {
			//closeDataBase();
			if (path == null || path.equals("copy")){
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
			
			success = true;
		} catch (IOException ioe){
			throw new Error("Problem copying database from resource file");
		}
		
		if (favourites.size() > 0){
			//openDataBase();
			favDataSource.open();
			favDataSource.insertAllFavourites(favourites);
		}
		
		return success;
	}
	
	public void openDataBase() throws SQLException{
		String dbPath = DATABASE_PATH + DATABASE_NAME;
		dbSqlite = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);	
	}
	
	public void closeDataBase(){
		if (dbSqlite != null){
			dbSqlite.close();
			dbSqlite = null;			
		}
		super.close();		
	}	
}
