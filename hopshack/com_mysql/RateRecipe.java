package hopshack.com_mysql;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import hopshack.com_mysql.Rate.AsyncRating;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class RateRecipe extends Activity {
	//Import classes
	ServiceHandler sh = new ServiceHandler();
	
	//Views
	TextView mTitle;
	TextView mAuthor;
	RatingBar mRatingBar;
	
	//recipeId to send in params
	String recipeId;
	
	// JSON Node names
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_RATING = "rating";
    private static final String TAG_RATERS = "raters";
    
    //Strings to store info
    private String id;
    private String title;
    private String author;
    private String rating;
    private String raters;
    
	//Progress Dialog
	private ProgressDialog pDialog;
	
	//URL to rate recipe
	private static String url_recipe_rating = "http://hopshack.com/db_recipe_rating.php";
	
    //floats for rating bar
	float nRating;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate_recipe);
		
		//grab intent from recipe.java
		Intent intent = getIntent();
		recipeId = intent.getStringExtra(TAG_ID);
		title = intent.getStringExtra(TAG_TITLE);
		author = intent.getStringExtra(TAG_AUTHOR);
		
		//find views and set text
		mTitle = (TextView) findViewById(R.id.recipe_rating_Title);
		mAuthor = (TextView) findViewById(R.id.recipe_rating_Author);
		mRatingBar = (RatingBar) findViewById(R.id.recipe_rating_ratingBar);
		//set text
		mTitle.setText(title);
		mAuthor.setText(author);
	     // Set ChangeListener to Rating Bar
	    mRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
	        public void onRatingChanged(RatingBar ratingBar, float rating,
	        boolean fromUser) {
	        		nRating = rating;
	        //Toast.makeText(getApplicationContext(),"Your Selected Ratings  : " + String.valueOf(rating),Toast.LENGTH_LONG).show();
	        }
	        });
		
	}
	//Submit Button This is where ASYNC TASK needs to go and all that jazz!
	public void submit(View view){
		if (nRating != 0){
			new AsyncRating().execute();	
		}else{
			Toast.makeText(getApplicationContext(),"Please rate or hit the back button!",Toast.LENGTH_LONG).show();
		}
	}
	//AsyncTask to rate recipe
	class AsyncRating extends AsyncTask<Void, Void, Void>{
    	/*
         * Before starting background thread Show Progress Dialog
         * */
    	 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(RateRecipe.this);
             pDialog.setMessage("Rating!");
             pDialog.setCancelable(true);
             pDialog.show();
         }
       	/*
          * Sending over rating to URL
          * */
		@Override
		protected Void doInBackground(Void... arg0) {
			//SH needs: url, int method, List<NameValuePair> params
			//make nRating a string
			rating = Float.toString(nRating);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", recipeId));
            params.add(new BasicNameValuePair("rating", rating));
            //send params
            String jsonStr = sh.makeServiceCall(url_recipe_rating, ServiceHandler.GET, params);
            Log.d("Response: ", "> " + jsonStr);
			return null;
		}
	 	   @Override
	 	   protected void onPostExecute(Void result){
	 		   super.onPostExecute(result);
	 		   //Dismiss progress dialog
	 		   if (pDialog.isShowing())
	 			   pDialog.dismiss();
	 		   //send toast then finish
	 		  Toast.makeText(getApplicationContext(),"Thank You!",Toast.LENGTH_LONG).show();
	 		 finish();
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
