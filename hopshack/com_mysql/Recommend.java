package hopshack.com_mysql;

import hopshack.com_mysql.LocalBreweries.getAddress;

import org.json.JSONArray;
import org.json.JSONObject;

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
import android.widget.ImageButton;
import android.widget.Toast;

public class Recommend extends Activity {
	//import class
	GPSTracker gps;
	double latitude;
	double longitude;
	String TAG_RESULTS = "results";
	String TAG_ADDRESSCOMPONENTS = "address_components";
	String TAG_SHORTNAME = "short_name";
	//Progress Dialog
	private ProgressDialog pDialog;
	
	ImageButton btnLocation;
	
	//JSONArray
	JSONArray result = null;
	JSONArray addressComponents;
	JSONObject state;
	//String to store state name
	String stateCode = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);



		
		/*//find popular beers by location
		btnLocation = (ImageButton) findViewById(R.id.recommend_location);
		btnLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				getLocation();
				new getAddress().execute();
			}
		});*/
	}
	//get location
		public void getLocation(){
			gps = new GPSTracker(Recommend.this);
			if(gps.canGetLocation()){
				latitude = gps.getLatitude(); // returns latitude
				longitude = gps.getLongitude(); // returns longitude
				gps.stopUsingGPS();
	            // \n is for new line
	            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
			}else{
				gps.showSettingsAlert();
			}	
		}
		
		//get formatted address
		public class getAddress extends AsyncTask<Void, Void, Void>{
	     	/*
	         * Before starting background thread Show Progress Dialog
	         * */
	    	 @Override
	         protected void onPreExecute() {
	             super.onPreExecute();
	             pDialog = new ProgressDialog(Recommend.this);
	             pDialog.setMessage("Finding location. . .");
	             pDialog.setCancelable(true);
	             pDialog.show();
	         }
	    	 //Log Address
			@Override
			protected Void doInBackground(Void... params) {
				gps = new GPSTracker(Recommend.this);
				if(gps.canGetLocation()){
					String url_google_address ="http://maps.google.com/maps/api/geocode/json?latlng="+latitude+","+longitude+"&sensor=false";
		         // Creating service handler class that we made for http requests
	                ServiceHandler sh = new ServiceHandler();
	                // Making a request to url and getting response
	                String jsonStr = sh.makeServiceCall(url_google_address, ServiceHandler.GET);
	                Log.d("Response: ", "> " + jsonStr);
	                stateCode = gps.reverseGeocode(jsonStr);
				}else{
					//gps.showSettingsAlert(); this doesn't work in async task, but gps settings will show no worries
				}
				return null;
			}
			//get rid of Progress Dialog
		 	   @Override
		 	   protected void onPostExecute(Void result){
		 		   super.onPostExecute(result);
		 		   //Dismiss progress dialog
		 		   if (pDialog.isShowing())
		 			   pDialog.dismiss();
		 		   
		 		   if(stateCode!=null && !stateCode.isEmpty()){
		 			   Intent i = new Intent(getApplicationContext(), RecommendLocation.class);
		 			   i.putExtra("stateCode", stateCode);
		 			   startActivity(i);
		 		   }else{
		 			   Toast.makeText(getApplicationContext(), "Unable to obtain loc from GPS. Please enter yourself", Toast.LENGTH_LONG).show();
		 		   }
		 			   
		 	   }
		}
	

	//Style Button
	public void goToRecommendStyle(View view){
		Intent intent = new Intent(getApplicationContext(), RecommendPopular.class);
		startActivity(intent);
	}
	//Flight Button
	public void goToRecommendFlight(View view){
		Intent intent = new Intent(getApplicationContext(), RecommendFlight.class);
		startActivity(intent);
	}
	//Shack Button
	public void goToRecommendShack(View view){
		Intent intent = new Intent(getApplicationContext(), RecommendShack.class);
		startActivity(intent);
	}
	//Popular Button
	public void goToRecommendPopular(View view){
		getLocation();
		new getAddress().execute();
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
				goToAllBeer();
				return true;

	        default:
	            return super.onOptionsItemSelected(item);			
		}
		
	}

	private void goToAllBeer() {
		Intent i = new Intent(getApplicationContext(), ViewBeers.class);
		startActivity(i);
	}

}
