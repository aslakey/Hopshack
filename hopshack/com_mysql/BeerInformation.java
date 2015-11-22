package hopshack.com_mysql;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;



public class BeerInformation extends Activity {
	//import classes
	BeerTypes beerTypes = new BeerTypes();
	DBTools dbTools = new DBTools(this);
	// Creating service handler class that we made for http requests
    ServiceHandler sh = new ServiceHandler();
	
	//VIEWS
    ImageView mBannerImage;
	TextView mCompany;
	TextView mName;
	TextView mId;
	ImageView mImage;
	TextView mCity;
	TextView mType;
	TextView mAbv;
	TextView mNotes;
	RatingBar mRatingBar;
	TextView mRaters;
	
	//beer id to send in params!
	String beerId;
	
	// JSON Node names
    @SuppressWarnings("unused")
	private static final String TAG_SUCCESS = "success";
    private static final String TAG_BEER = "beer";
    private static final String TAG_ID = "id";
    private static final String TAG_COMPANY = "company";
    private static final String TAG_NAME = "name";
    private static final String TAG_CITY = "city";
    private static final String TAG_STATE = "state";
    private static final String TAG_TYPE = "type";
    private static final String TAG_ABV = "abv";
    private static final String TAG_RATING = "rating";
    private static final String TAG_RATERS = "raters";
    private static final String TAG_NOTES = "notes";
    private static final String TAG_STATECODE = "stateCode";
    private static final String TAG_URLS = "urls";
    private static final String TAG_IMAGE = "image";
    private static final String TAG_TARGET = "target";
    
	//Progress Dialog
	private ProgressDialog pDialog;
	
	//URL to get all beers
	private static String url_beer_details = "http://hopshack.com/db_get_beer_details.php";
	private String url_banner_image = "http://www.hopshack.com/wp-content/uploads/2014/08/banners-1.png";
	private String url_target = "http://www.hopshack.com";
	
    //Beer JSON Array
    JSONArray beer = null;
    JSONArray urls = null;
    
    //Strings to store info
    private String id;
    private String name;
    private String company;
    private String city;
    private String state;
    private String type;
    private String abv;
    private String rating;
    private String raters;
    private String notes;
    private Bitmap bannerBitmap;
    
    //floats for rating bar
	float nRating;
	float nRaters;
	float nFinalRating;
	//int for type
	int nType;
	String sType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer_information);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);



		//grab beer id from ViewBeers intent
		Intent intent = getIntent();
		beerId = intent.getStringExtra(TAG_ID);
		
		new LoadBeerDetails().execute();
		
		

	}
	
	//AsyncClass to load beer details
	 class LoadBeerDetails extends AsyncTask<Void, Void, Void>{

     	/*
          * Before starting background thread Show Progress Dialog
          * */
     	 @Override
          protected void onPreExecute() {
              super.onPreExecute();
              pDialog = new ProgressDialog(BeerInformation.this);
              pDialog.setMessage("Loading beer details. Please wait...");
              pDialog.setCancelable(true);
              pDialog.show();
          }
     	 
    	 /*
          * getting Beer Details from url
          * */
		@Override
		protected Void doInBackground(Void... arg0) {
    		
            //SH needs: url, int method, List<NameValuePair> params
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", beerId));
            
            String jsonStr = sh.makeServiceCall(url_beer_details, ServiceHandler.GET, params);
            Log.d("Response: ", "> " + jsonStr);
            
            if (jsonStr != null){
         	   try{
         		   //JSON Object!
  
         		   JSONObject jsonObj = new JSONObject(jsonStr);
         		   
         		   //JSON ARRAY nodes
         		   beer = jsonObj.getJSONArray(TAG_BEER);
         		   urls = jsonObj.getJSONArray(TAG_URLS);
         		   
         		   //loop through beer details!
         		   for (int i = 0; i<beer.length(); i++){
         			   JSONObject c = beer.getJSONObject(i);
         			   
                        id = c.getString(TAG_ID);
                        name = c.getString(TAG_NAME);
                        company = c.getString(TAG_COMPANY);
                        state = c.getString(TAG_STATE);
                        city = c.getString(TAG_CITY);
                        type = c.getString(TAG_TYPE);
                        abv = c.getString(TAG_ABV);
                        rating = c.getString(TAG_RATING);
                        raters = c.getString(TAG_RATERS);
                        notes = c.getString(TAG_NOTES);
         		   }
         		   //loop through urls
         		   for (int i = 0; i<urls.length(); i++){
         			   JSONObject c = urls.getJSONObject(i);
                        url_banner_image = c.getString(TAG_IMAGE);
                        url_target = c.getString(TAG_TARGET);
         		   }
         	   }catch (JSONException e){
         		   e.printStackTrace();
         	   }
            }else{
         	   Log.e("ServiceHandler","Couldn't get data from URL");
            }
             bannerBitmap = getBitmapFromURL(url_banner_image);
            return null;
            
		}
 	   @Override
 	   protected void onPostExecute(Void result){
 		   super.onPostExecute(result);
 		   //Dismiss progress dialog
 		   if (pDialog.isShowing())
 			   pDialog.dismiss();
 		    /*
             * Updating parsed JSON data into Views
             * */
   		//grabbing views
 		mBannerImage = (ImageView) findViewById(R.id.info_bannerAd);
 		mBannerImage.setVisibility(View.VISIBLE);
 		mImage = (ImageView) findViewById(R.id.info_Image);
 		mImage.setVisibility(View.VISIBLE);
   		mCompany = (TextView) findViewById(R.id.info_Company);
   		mName = (TextView) findViewById(R.id.info_Name);
   		mId = (TextView) findViewById(R.id.info_id);
   		mCity = (TextView) findViewById(R.id.info_city);
   		mType = (TextView) findViewById(R.id.info_type);
   		mAbv = (TextView) findViewById(R.id.info_abv);
   		mRatingBar= (RatingBar) findViewById(R.id.info_ratingBar);
   		mRaters = (TextView) findViewById(R.id.info_Raters);
   		mNotes = (TextView) findViewById(R.id.info_Notes);
   		
   		//calculate rating
   		nRating = Float.parseFloat(rating);
   		nRaters = Float.parseFloat(raters);
   		nFinalRating = nRating/(nRaters+1);
		//calculating raters for text
   		nRaters= nRaters+1;
   		raters = Float.toString(nRaters);
   		//type to nType and get text from beerTypes.java
   		sType = type;
   		nType = Integer.parseInt(sType);

   		//set text and images
   		mBannerImage.setImageBitmap(bannerBitmap);
   		mImage.setImageResource(beerTypes.idToImage(nType));
   		mCompany.setText(company);
   		mName.setText(name);
   		mId.setText(id);
   		mCity.setText(city + ", " + state);
   		mType.setText(beerTypes.idToType(nType));
   		mAbv.setText(abv);
   		mRatingBar.setRating(nFinalRating);
   		mRaters.setText("(by "+ raters +" hopshackers)");
   		mNotes.setText(notes);

 	   }
 		 //Function to download Bitmap from URL
 		 public Bitmap getBitmapFromURL(String src) {
 			    try {
 			        Log.e("src",src);
 			        URL url = new URL(src);
 			        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
 			        connection.setDoInput(true);
 			        connection.connect();
 			        InputStream input = connection.getInputStream();
 			        Bitmap myBitmap = BitmapFactory.decodeStream(input);
 			        Log.e("Bitmap","returned");
 			        return myBitmap;
 			    } catch (IOException e) {
 			        e.printStackTrace();
 			        Log.e("Exception",e.getMessage());
 			        return null;
 			    }
 			}
}

		 
	 //BUTTON add to shack
	public void addToShack(View view){
		//make sure its not in there already:
		HashMap <String, String> beerList = dbTools.getBeerInfo(id);
		//check to make sure it didn't returned something
		if(beerList.size() == 0){
			HashMap<String, String> queryValuesMap = new HashMap<String, String>();
			
			queryValuesMap.put("id", id);
			queryValuesMap.put("company", company);
			queryValuesMap.put("name", name);
			queryValuesMap.put("city", city);
			queryValuesMap.put("state", state);
			queryValuesMap.put("type", type);
			queryValuesMap.put("abv", abv);
			queryValuesMap.put("rating", rating);
			queryValuesMap.put("raters", raters);
			queryValuesMap.put("notes", notes);
			
			dbTools.insertBeer(queryValuesMap);
			Toast toast = Toast.makeText(getApplicationContext(), "Added to Shack!", Toast.LENGTH_LONG);
			toast.show();
		}else{
			Toast toast = Toast.makeText(getApplicationContext(), "Already in Shack!", Toast.LENGTH_LONG);
			toast.show();	
		}
	}
	
	//Button For ADS!
	public void goToAd(View view){
		Uri uri = Uri.parse(url_target);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
	//Button Rate!
	public void rate(View view){
		Intent i = new Intent(getApplicationContext(), Rate.class);
		i.putExtra(TAG_ID, beerId);
		i.putExtra(TAG_NAME, name);
		i.putExtra(TAG_COMPANY, company);
		startActivity(i);
	}
	//Button Pair!
	public void findPairing(View view){
			Intent i = new Intent(getApplicationContext(), RecipeList.class);
			i.putExtra(TAG_TYPE, sType);
			startActivity(i);	
	}
	//Button Location!
	public void locate(View view){
		 Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
		 intent.putExtra(SearchManager.QUERY, company + " brewery location");
		 startActivity(intent);	
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
			/*case R.id.action_shack:
				goToShack();
				return true;*/
			case R.id.action_beer:
				goToViewBeer();
				return true;
			/*case R.id.action_discover:
				goToDiscover();
				return true;*/
	        default:
	            return super.onOptionsItemSelected(item);			
		}
		
	}
//go to shack when menu icon is clicked
	private void goToShack() {
		Intent i = new Intent(getApplicationContext(), MyShack.class);
		startActivity(i);
	}
	private void goToDiscover() {
		Intent i = new Intent(getApplicationContext(), Discover.class);
		startActivity(i);
	}
	private void goToViewBeer() {
		Intent i = new Intent(getApplicationContext(), ViewBeers.class);
		startActivity(i);
	}
	
}
