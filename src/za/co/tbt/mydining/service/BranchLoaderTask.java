package za.co.tbt.mydining.service;

import java.util.List;

import za.co.tbt.mydining.adapter.BranchListAdapter;
import za.co.tbt.mydining.db.Branch;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class BranchLoaderTask extends AsyncTask<Void, Void, Void> {
	private Activity activity = null;
	private BranchListAdapter branchAdapter = null;
	private List<Branch> restaurant_branches = null;
	private ListLoadingListener loadingListener;
	
	ProgressDialog loaderDialog;
	
	public BranchLoaderTask(Activity activity, List<Branch> branches, BranchListAdapter adapter, ListLoadingListener listener) {
		// TODO Auto-generated constructor stub
		loaderDialog = ProgressDialog.show(activity, "", "", true);
		this.activity = activity;
		loadingListener = listener;
		branchAdapter = adapter;
		restaurant_branches = branches;
	}
	
	@Override
	protected Void doInBackground(Void...params) {
		// TODO Auto-generated method stub		
		Log.d("ssm", "set branches");
		branchAdapter.setBranches(restaurant_branches);
		Log.d("ssm", "finished setting branches");
		return null;
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Log.d("ssm", "load completed");
		loadingListener.loadCompleted();
		loaderDialog.dismiss();
	}
	
	

}
