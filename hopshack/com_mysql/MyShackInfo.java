package hopshack.com_mysql;

import java.util.HashMap;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class MyShackInfo extends Activity {
	//import classes
	DBTools dbTools = new DBTools(this);
	BeerTypes beerTypes = new BeerTypes();
	
	//VIEWS
	TextView mCompany;
	TextView mName;
	TextView mId;
	ImageView mImage;
	ImageView bannerAd;
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
    
    //floats for rating bar
	float nRating;
	float nRaters;
	float nFinalRating;
	//int and string for type
	int nType;
	String sType;
	String state = "";
    int nImage;
    
    //string to send company to google search
    private String company;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer_information);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);



		//GET RID OF THOSE DAMN BUTTONS and Banner Ads!
		ImageView bannerAd = (ImageView) findViewById(R.id.info_bannerAd);
		bannerAd.setVisibility(View.GONE);
		ImageButton buttonADD = (ImageButton) findViewById(R.id.info_btnAdd);
		buttonADD.setImageResource(R.drawable.delete_shack);
		ImageButton buttonRATE = (ImageButton) findViewById(R.id.info_btnRate);
		buttonRATE.setVisibility(View.GONE);
		
		//grab beer id from ViewBeers intent
		Intent intent = getIntent();
		beerId = intent.getStringExtra(TAG_ID);
		
   		//grabbing views
		mImage = (ImageView) findViewById(R.id.info_Image);
		mImage.setVisibility(View.VISIBLE);
   		mCompany = (TextView) findViewById(R.id.info_Company);
   		mName = (TextView) findViewById(R.id.info_Name);
   		mId = (TextView) findViewById(R.id.info_id);
   		mCity = (TextView) findViewById(R.id.info_city);
   		mType = (TextView) findViewById(R.id.info_type);
   		mAbv = (TextView) findViewById(R.id.info_abv);
   		mRatingBar = (RatingBar) findViewById(R.id.info_ratingBar);
   		mRaters=(TextView) findViewById(R.id.info_Raters);
   		mNotes = (TextView) findViewById(R.id.info_Notes);
   		
   		//grabbing data from sqlite!
		HashMap <String, String> beerList = dbTools.getBeerInfo(beerId);
		//check to make sure it returned something
		if(beerList.size() != 0){
			//calculate rating
	   		nRating = Float.parseFloat(beerList.get(TAG_RATING));
	   		nRaters = Float.parseFloat(beerList.get(TAG_RATERS));
	   		nFinalRating = nRating/(nRaters);
			//calculating raters for text
	   		nRaters= nRaters;
	   		String raters = Float.toString(nRaters);
	   		//grabbing type from BeerTypes.java
	   		sType = beerList.get(TAG_TYPE);
	   		nType = Integer.parseInt(sType);
	   		nImage = beerTypes.idToImage(nType);
	   		state = beerList.get(TAG_STATE);
	   		

	   		
	   		//set text
	   		company = beerList.get(TAG_COMPANY);
	   		mImage.setImageResource(nImage);
	   		mCompany.setText(company);
	   		mName.setText(beerList.get(TAG_NAME));
	   		mId.setText(beerList.get(TAG_ID));
	   		mCity.setText(beerList.get(TAG_CITY) + ", " + state);
	   		mType.setText(beerTypes.idToType(nType));
	   		mAbv.setText(beerList.get(TAG_ABV));
	   		mRatingBar.setRating(nFinalRating);
	   		mRaters.setText("(by "+ raters +" hopshackers)");
	   		mNotes.setText(beerList.get(TAG_NOTES));
	   		//mImage.setImageResource...
		}

   		
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
	 //BUTTON DELETE FROM SHACK shack
	public void addToShack(View view){
			dbTools.deleteBeer(beerId);
			Toast toast = Toast.makeText(getApplicationContext(), "Deleted From Shack!", Toast.LENGTH_LONG);
			toast.show();
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
private void goToViewBeers() {
	Intent i = new Intent(getApplicationContext(), ViewBeers.class);
	startActivity(i);
		
	}

}
