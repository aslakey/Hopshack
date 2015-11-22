package hopshack.com_mysql;

import hopshack.com_mysql.RecommendPopularBeers.LoadBeers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
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
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class RecommendLocation extends Activity {
	//import classes
		BeerTypes beerTypes = new BeerTypes();
		
		//import views
		private ListView lv;
		private TextView mId;
		
		//Progress Dialog
		private ProgressDialog pDialog;
		
		//URL to get all beers
		private static String url_recommend_from_location = "http://hopshack.com/db_recommend_from_location.php";
		//Hashmap for ListView
		ArrayList<HashMap<String, String>> beerList;
		//JSON Array
		JSONArray beers = null;
		
		// JSON Node names
	    @SuppressWarnings("unused")
		private static final String TAG_SUCCESS = "success";
	    private static final String TAG_ID = "id";
	    private static final String TAG_BEER = "beer";
	    private static final String TAG_COMPANY = "company";
	    private static final String TAG_NAME = "name";
	    private static final String TAG_TYPE = "type";
	    private static final String TAG_IMAGE = "image";
	    private static final String TAG_STATECODE = "stateCode";

		//int and string for type
		int nType;
		String sType;
		
		String stateCode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend_location);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);



			//grab stateCode
			Intent intent = getIntent();
			stateCode = intent.getStringExtra(TAG_STATECODE);
			
		     // Hashmap for ListView
	        beerList = new ArrayList<HashMap<String, String>>();
	        
			//grab Views
			lv = (ListView) findViewById(R.id.recommendLocation_listView);
	        
	        //load beers from ASYNC TASK
	        new LoadBeers().execute();
			
	        //When a single beer is selected set on click listener to go to beer details
	        lv.setOnItemClickListener(new OnItemClickListener() {
	        
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					mId = (TextView) view.findViewById(R.id.list_id);
					
					String beerId = mId.getText().toString();
					
					//have to create Recipe class
					//will pass recipeId over :)
					Intent theIntent = new Intent(getApplication(), BeerInformation.class);
					theIntent.putExtra(TAG_ID, beerId);
					startActivity(theIntent);
				}
	        
	        
			});
	}
	//ASYNC Task to load recipes
	 class LoadBeers extends AsyncTask<Void, Void, Void>{
    	/*
         * Before starting background thread Show Progress Dialog
         * */
    	 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(RecommendLocation.this);
             pDialog.setMessage("Loading beers. Please wait...");
             pDialog.setCancelable(true);
             pDialog.show();
         }

		@Override
		protected Void doInBackground(Void... arg0) {
   		// Creating service handler class that we made for http requests
           ServiceHandler sh = new ServiceHandler();
           
           //SH needs: url, int method, List<NameValuePair> params
           List<NameValuePair> params = new ArrayList<NameValuePair>();
           params.add(new BasicNameValuePair(TAG_STATECODE, stateCode));
           // Making a request to url and getting response
           
           String jsonStr = sh.makeServiceCall(url_recommend_from_location, ServiceHandler.GET, params);
           Log.d("Response: ", "> " + jsonStr);
           
           if (jsonStr != null){
        	   try{
        		   //JSON Object!
 
        		   JSONObject jsonObj = new JSONObject(jsonStr);
        		   
        		   //JSON ARRAY node
        		   beers = jsonObj.getJSONArray(TAG_BEER);
        		   
        		   //loop through all beer!
        		   for (int i = 0; i<beers.length(); i++){
        			   JSONObject c = beers.getJSONObject(i);
        			   
        			   //I'm just grabbing Name, Company and id for now!
        			   
                       String id = c.getString(TAG_ID);
                       String company = c.getString(TAG_COMPANY);
                       String name = c.getString(TAG_NAME);
                       String type = c.getString(TAG_TYPE);
                       
                       int nType = Integer.parseInt(type);
                       int nImage = beerTypes.idToImage(nType);
                       String image = String.valueOf(nImage);
                       
                       //Hashmap for single recipe! (called can)
                       HashMap<String,String> can = new HashMap<String, String>();
                       
                       //add each child node to hashmap key => value
                       
                       can.put(TAG_ID, id);
                       can.put(TAG_COMPANY, company);
                       can.put(TAG_NAME, name);
                       can.put(TAG_IMAGE, image);
                       
                       //add can to beerList
                       beerList.add(can);
        		   }
        	   }catch (JSONException e){
        		   e.printStackTrace();
        	   }
           }else{
        	   Log.e("ServiceHandler","Couldn't get data from URL");
           }
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
		   ListAdapter adapter = new SimpleAdapter(RecommendLocation.this,beerList,
				   R.layout.list_item,new String[]{ TAG_ID, TAG_COMPANY,
                   TAG_NAME, TAG_IMAGE }, new int[] { R.id.list_id,
                   R.id.list_company, R.id.list_name, R.id.list_image });
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
