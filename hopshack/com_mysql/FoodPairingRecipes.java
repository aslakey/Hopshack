package hopshack.com_mysql;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;

public class FoodPairingRecipes extends Activity {
	//import classes
	BeerTypes beerTypes = new BeerTypes();
	//HASHMAP for List VIEW
	ArrayList<HashMap<String, String>> recipesList;
	
	//URL to get all beers
	private static String url_all_recipes = "http://hopshack.com/db_get_all_recipes.php";
	//Progress Dialog
	private ProgressDialog pDialog;
	
	 // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_TITLE = "title";
    private static final String TAG_ID = "id";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_TYPE = "type";
    private static final String TAG_IMAGE = "image";
    
    //Beer JSON Array
    JSONArray recipes = null;
    
    //list view
    private ListView lv;
    //beerID
    private TextView mId;
    
    //search
    private SearchView mSearchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_pairing_recipes);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);



		// Hashmap for ListView
        recipesList = new ArrayList<HashMap<String, String>>();
        mSearchView = (SearchView) findViewById(R.id.foodPairingRecipes_searchView);
        
      //Get ListView
       lv = (ListView) findViewById(R.id.foodPairingRecipes_listView);
       lv.setTextFilterEnabled(true);
       setupSearchView();
        
      //load recipes from ASYNC TASK
       new LoadRecipes().execute();
       
       //When a single beer is selected set on click listener to go to beer details
       lv.setOnItemClickListener(new OnItemClickListener() {
       
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				mId = (TextView) view.findViewById(R.id.list_id);
				
				String recipeId = mId.getText().toString();
				
				//have to create Recipe class
				//will pass recipeId over :)
				Intent theIntent = new Intent(getApplicationContext(), Recipe.class);
				theIntent.putExtra(TAG_ID, recipeId);
				startActivity(theIntent);
			}     
		});
        
	}
	//ASYNC Task to load recipes
	 class LoadRecipes extends AsyncTask<Void, Void, Void>{
    	/*
         * Before starting background thread Show Progress Dialog
         * */
    	 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(FoodPairingRecipes.this);
             pDialog.setMessage("Loading recipes. Please wait...");
             pDialog.setCancelable(true);
             pDialog.show();
         }

		@Override
		protected Void doInBackground(Void... arg0) {
   		// Creating service handler class that we made for http requests
           ServiceHandler sh = new ServiceHandler();
           
           //SH needs: url
           // Making a request to url and getting response
           
           String jsonStr = sh.makeServiceCall(url_all_recipes, ServiceHandler.GET);
           Log.d("Response: ", "> " + jsonStr);
           
           if (jsonStr != null){
        	   try{
        		   //JSON Object!
 
        		   JSONObject jsonObj = new JSONObject(jsonStr);
        		   
        		   //JSON ARRAY node
        		   recipes = jsonObj.getJSONArray("recipes");
        		   
        		   //loop through all beer!
        		   for (int i = 0; i<recipes.length(); i++){
        			   JSONObject c = recipes.getJSONObject(i);
        			   
        			   //I'm just grabbing Title, Author and id for now!
        			   
                       String id = c.getString(TAG_ID);
                       String author = c.getString(TAG_AUTHOR);
                       String title = c.getString(TAG_TITLE);
                       String type = c.getString(TAG_TYPE);
                       
                       int nType = Integer.parseInt(type);
                       int nImage = beerTypes.idToImage(nType);
                       String image = String.valueOf(nImage);
                       
                       //Hashmap for single recipe! (called can)
                       HashMap<String,String> can = new HashMap<String, String>();
                       
                       //add each child node to hashmap key => value
                       
                       can.put(TAG_ID, id);
                       can.put(TAG_AUTHOR, author);
                       can.put(TAG_TITLE, title);
                       can.put(TAG_IMAGE, image);
                       
                       //add can to beerList
                       recipesList.add(can);
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
		   ListAdapter adapter = new SimpleAdapter(FoodPairingRecipes.this,recipesList,
				   R.layout.list_item,new String[]{ TAG_ID, TAG_TITLE,
                   TAG_AUTHOR, TAG_IMAGE }, new int[] { R.id.list_id,
                   R.id.list_company, R.id.list_name, R.id.list_image });
		   lv.setAdapter(adapter);
	   }
}
	 private void setupSearchView() {
		 mSearchView.setIconifiedByDefault(false);
	     mSearchView.setSubmitButtonEnabled(true);
	     mSearchView.setQueryHint("Search Recipes and Pairings");
	     mSearchView.setOnQueryTextListener(new OnQueryTextListener() {
	         @Override
	         public boolean onQueryTextSubmit(String arg0) {

	             return true;
	         }

	         @Override
	         public boolean onQueryTextChange(String newText) {
	        	 	if (TextUtils.isEmpty(newText)) {
	                    lv.clearTextFilter();
	                } else {
	                    lv.setFilterText(newText.toString());
	                }
	                return false;
	            }
	        });	
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
