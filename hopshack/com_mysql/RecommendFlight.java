package hopshack.com_mysql;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class RecommendFlight extends Activity {
	
	//import classes
	BeerTypes beerTypes = new BeerTypes();
	
	//import views
	private ListView lv;
	private TextView mId;
	
	//Progress Dialog
	private ProgressDialog pDialog;
	
	//URL to get all beers
	private static String url_flight = "http://hopshack.com/db_recommend_flight.php";
	//Hashmap for ListView
	ArrayList<HashMap<String, String>> beerList;
	// JSON Node names
    @SuppressWarnings("unused")
	private static final String TAG_SUCCESS = "success";
    private static final String TAG_ID = "id";
    private static final String TAG_BEER = "beer";
    private static final String TAG_COMPANY = "company";
    private static final String TAG_NAME = "name";
    private static final String TAG_TYPE = "type";
    private static final String TAG_STATE = "state";
    private static final String TAG_IMAGE = "image";
    
    
	//int and string for type
	int nType;
	String sType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend_flight);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);



		//get LV
		lv = (ListView) findViewById(R.id.recommendFlight_listView);
		//Fill LV
		new LoadBeers().execute();
		//Set on item click listener
        lv.setOnItemClickListener(new OnItemClickListener() {
	        
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				mId = (TextView) view.findViewById(R.id.flight_list_id);
				
				String beerId = mId.getText().toString();
				
				//have to create Recipe class
				//will pass recipeId over :)
				Intent theIntent = new Intent(getApplication(), BeerInformation.class);
				theIntent.putExtra(TAG_ID, beerId);
				startActivity(theIntent);
			}   
		});
		
	}
	
	//ASYNC Task to load beers
	class LoadBeers extends AsyncTask<Void, Void, Void>{
     	/*
         * Before starting background thread Show Progress Dialog
         * */
    	 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(RecommendFlight.this);
             pDialog.setMessage("Loading beers. Please wait...");
             pDialog.setCancelable(true);
             pDialog.show();
         }
    	 
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class that we made for http requests
            ServiceHandler sh = new ServiceHandler();
            String jsonStr = sh.makeServiceCall(url_flight, ServiceHandler.GET);
            Log.d("Response: ", "> " + jsonStr);
            
			beerList = beerTypes.randomFlight(jsonStr);
			return null;
		}
	 	   @Override
	 	   protected void onPostExecute(Void result){
	 		   super.onPostExecute(result);
	 		   //Dismiss progress dialog
	 		   if (pDialog.isShowing())
	 			   pDialog.dismiss();
	 		    /*
	             * Updating parsed JSON data into ListView
	             * */
	 		   ListAdapter adapter = new SimpleAdapter(RecommendFlight.this,beerList,
	 				   R.layout.flight_list_item,new String[]{ TAG_ID, TAG_COMPANY,
	                    TAG_NAME, TAG_IMAGE }, new int[] { R.id.flight_list_id,
	                    R.id.flight_list_company, R.id.flight_list_name, R.id.flight_list_image });
	 		   lv.setAdapter(adapter);
	 	   }
	}
	
	
	

	// Initiating Menu XML file (menu.xml)
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		//inflate menu items for use in action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		//if an item is selected we are showing a toast, but should open up a new action
		switch (item.getItemId()){
			case android.R.id.home:
				finish();
				return true;

			case R.id.action_beer:
				goToViewBeers();
				return true;
	        default:
	            return super.onOptionsItemSelected(item);			
		}
		
	}
	private void goToViewBeers(){
		Intent i = new Intent(getApplicationContext(), ViewBeers.class);
		startActivity(i);
	}

}
