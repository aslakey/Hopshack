package hopshack.com_mysql;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class Rate extends Activity {
	//import classes
	DBTools dbTools = new DBTools(this);
	// Creating service handler class that we made for http requests
    ServiceHandler sh = new ServiceHandler();
	
	//VIEWS
	TextView mCompany;
	TextView mName;
	RatingBar mRatingBar;
	TextView mID;
	
	//beer id to send in params!
	String beerId;
	
	// JSON Node names
    private static final String TAG_ID = "id";
    private static final String TAG_COMPANY = "company";
    private static final String TAG_NAME = "name";
    private static final String TAG_RATING = "rating";
    private static final String TAG_RATERS = "raters";
    
    //Strings to store info
    private String id;
    private String name;
    private String company;
    private String rating;
    private String raters;
    
	//Progress Dialog
	private ProgressDialog pDialog;
	
	//URL to rate beer
	private static String url_new_rating = "http://hopshack.com/db_new_rating.php";
	
    //floats for rating bar
	float nRating;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate);
		
		//grab beer id from BeerInformation intent
		Intent intent = getIntent();
		beerId = intent.getStringExtra(TAG_ID);
		name = intent.getStringExtra(TAG_NAME);
		company = intent.getStringExtra(TAG_COMPANY);
		
		//find views
		mCompany = (TextView) findViewById(R.id.rating_Company);
		mName = (TextView) findViewById(R.id.rating_Name);
		mID = (TextView) findViewById(R.id.rating_ID);
		mRatingBar = (RatingBar) findViewById(R.id.rating_ratingBar);
		//set Text for TV's
		mCompany.setText(company);
		mName.setText(name);
		mID.setText(beerId);
		/*
		 *             List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", beerId));
            
            String jsonStr = sh.makeServiceCall(url_beer_details, ServiceHandler.GET, params);
            Log.d("Response: ", "> " + jsonStr);
		*/
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
	
	//ASYNC TASK "AsyncRating"
	class AsyncRating extends AsyncTask<Void, Void, Void>{
     	/*
         * Before starting background thread Show Progress Dialog
         * */
    	 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(Rate.this);
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
            params.add(new BasicNameValuePair("id", beerId));
            params.add(new BasicNameValuePair("rating", rating));
            
            String jsonStr = sh.makeServiceCall(url_new_rating, ServiceHandler.GET, params);
            Log.d("Response: ", "> " + jsonStr);
            
			return null;
		}
		//get rid of Progress Dialog
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
				goToViewBeer();
				return true;

	        default:
	            return super.onOptionsItemSelected(item);			
		}
		
	}
//go to shack when menu icon is clicked

	private void goToViewBeer() {
		Intent i = new Intent(getApplicationContext(), ViewBeers.class);
		startActivity(i);
	}

}
