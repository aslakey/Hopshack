package hopshack.com_mysql;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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

public class Recipe extends Activity {
	//import classes
	ServiceHandler sh = new ServiceHandler();
	BeerTypes beerTypes = new BeerTypes();
	
	//Views
	ImageView mBannerImage;
	TextView mTitle;
	TextView mAuthor;
	ImageView mImage;
	RatingBar mRatingBar;
	TextView mRaters;
	TextView mType;
	TextView mTime;
	TextView mIngredients;
	TextView mDirections;
	
	//String id to send params!
	String recipeId;
	
	// JSON Node names
    @SuppressWarnings("unused")
	private static final String TAG_SUCCESS = "success";
    private static final String TAG_RECIPE = "recipe";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_TYPE = "type";
    private static final String TAG_RATING = "rating";
    private static final String TAG_RATERS = "raters";
    private static final String TAG_TIME = "time";
    private static final String TAG_INGREDIENTS = "ingredients";
    private static final String TAG_DIRECTIONS = "directions";
    private static final String TAG_URLS = "urls";
    private static final String TAG_IMAGE = "image";
    private static final String TAG_TARGET = "target";
    
	//Progress Dialog
	private ProgressDialog pDialog;
	
	//URL to get all beers
	private static String url_recipe_details = "http://hopshack.com/db_get_recipe_details.php";
	private String url_banner_image = "http://www.hopshack.com/wp-content/uploads/2014/08/banners-1.png";
	private String url_target = "http://www.hopshack.com";
	
    //Beer JSON Array
    JSONArray recipe = null;
    JSONArray urls = null;
    
    //Strings to store info
    private String id;
    private String title;
    private String author;
    private String type;
    private String rating;
    private String raters;
    private String time;
    private String ingredients;
    private String directions;
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
		setContentView(R.layout.activity_recipe);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);



		
		//grab recipe id from RecipeList intent
		Intent intent = getIntent();
		recipeId = intent.getStringExtra(TAG_ID);
		
		new LoadRecipeDetails().execute();
	}
	
	//AsyncTask to load recipe details
	class LoadRecipeDetails extends AsyncTask<Void, Void, Void>{
     	/*
         * Before starting background thread Show Progress Dialog
         * */
    	 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(Recipe.this);
             pDialog.setMessage("Loading recipe. Please wait...");
             pDialog.setCancelable(true);
             pDialog.show();
         }
    	 /*
          * getting Recipe Details from url
          * */
		@Override
		protected Void doInBackground(Void... arg0) {
            //SH needs: url, int method, List<NameValuePair> params
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_ID, recipeId));
            
            String jsonStr = sh.makeServiceCall(url_recipe_details, ServiceHandler.GET, params);
            Log.d("Response: ", "> " + jsonStr);
            if(jsonStr != null){
            	try{
          		   //JSON Object!
          		   JSONObject jsonObj = new JSONObject(jsonStr);
          		   //Json Array node
          		   recipe = jsonObj.getJSONArray(TAG_RECIPE);
          		   urls = jsonObj.getJSONArray(TAG_URLS);
          		   
          		   //For loop going through array and saving data in string variables
         		   for (int i = 0; i<recipe.length(); i++){
         			   JSONObject c = recipe.getJSONObject(i);
         			   
         			   
                        id = c.getString(TAG_ID);
                        type = c.getString(TAG_TYPE);
                        title = c.getString(TAG_TITLE);
                        author = c.getString(TAG_AUTHOR);
                        rating = c.getString(TAG_RATING);
                        raters = c.getString(TAG_RATERS);
                        time = c.getString(TAG_TIME);
                        ingredients = c.getString(TAG_INGREDIENTS); 
                        directions = c.getString(TAG_DIRECTIONS);
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
   	 /*
         * filling views on Post Execute
         * */
		@Override
		protected void onPostExecute(Void result){
	 		   super.onPostExecute(result);
	 		   //Dismiss progress dialog
	 		   if (pDialog.isShowing())
	 			   pDialog.dismiss();
	 		    //grab views
	 		    mBannerImage = (ImageView) findViewById(R.id.recipe_bannerAd);
	 	 		mBannerImage.setVisibility(View.VISIBLE);
	 			mTitle = (TextView) findViewById(R.id.recipe_Title);
	 			mAuthor = (TextView) findViewById(R.id.recipe_Author);
	 			mImage = (ImageView) findViewById(R.id.recipe_Image);
	 	 		mImage.setVisibility(View.VISIBLE);
	 			mRatingBar = (RatingBar) findViewById(R.id.recipe_ratingBar);
	 			mRaters = (TextView) findViewById(R.id.recipe_Raters);
	 			mType = (TextView) findViewById(R.id.recipe_Type);
	 			mTime = (TextView) findViewById(R.id.recipe_Time);
	 			mIngredients = (TextView) findViewById(R.id.recipe_Ingredients);
	 			mDirections= (TextView) findViewById(R.id.recipe_Directions);
	 			
	 			Log.d("Response: ", "> " + ingredients);
	 			Log.d("Response: ", "> " + time);
	 			Log.d("Response: ", "> " + directions);
	 	   		//calculate rating
	 	   		nRating = Float.parseFloat(rating) + 5;
	 	   		nRaters = Float.parseFloat(raters);
	 	   		nFinalRating = nRating/(nRaters+1);
	 			//calculating raters for text
	 	   		nRaters= nRaters+1;
	 	   		raters = Float.toString(nRaters);
	 	   		//string for type
	 	   		nType = Integer.parseInt(type);
	 	   		sType = beerTypes.idToType(nType);
	 	   		
	 	      	
	 	   		//set text/image resources
	 	     	mBannerImage.setImageBitmap(bannerBitmap);
	 	   		mTitle.setText(title);
	 	   		mAuthor.setText(author);
	 	   		mImage.setImageResource(beerTypes.idToImage(nType));
	 	   		mRatingBar.setRating(nFinalRating);
	 	   		mRaters.setText("(by " + raters + " hopshackers)");
	 	   		mType.setText(sType);
	 	   		mTime.setText(time);
	 	   		mIngredients.setText(ingredients);
	 	   		mDirections.setText(directions);   
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
	//Button Rate!
	public void rate(View view){
		Intent i = new Intent(getApplicationContext(), RateRecipe.class);
		i.putExtra(TAG_ID, recipeId);
		i.putExtra(TAG_TITLE, title);
		i.putExtra(TAG_AUTHOR, author);
		startActivity(i);
	}

	//Button For ADS!
	public void goToAd(View view){
		Uri uri = Uri.parse(url_target);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
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
