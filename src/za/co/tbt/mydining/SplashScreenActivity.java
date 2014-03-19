package za.co.tbt.mydining;

import za.co.tbt.mydining.db.MyDiningDbOpenHelper;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SplashScreenActivity extends Activity {
	MyDiningDbOpenHelper diningHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		//create database helper
		diningHelper = new MyDiningDbOpenHelper(this);
		diningHelper.createDatabase();
		//diningHelper.openDataBase();
	}
}
