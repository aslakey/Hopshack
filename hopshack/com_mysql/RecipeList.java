package hopshack.com_mysql;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

public class RecipeList extends Activity {
	//import classes
	BeerTypes beerTypes = new BeerTypes();
	
	//import views
	private TextView mType;
	private TextView mDescription;
	private ListView lv;
	private TextView mId;
	
	//Progress Dialog
	private ProgressDialog pDialog;
	
	//URL to get all beers
	private static String url_all_recipes = "http://hopshack.com/db_get_recipes_from_type.php";
	//Hashmap for ListView
	ArrayList<HashMap<String, String>> recipesList;
	//JSON Array
	JSONArray recipes = null;
	
	// JSON Node names
    @SuppressWarnings("unused")
	private static final String TAG_SUCCESS = "success";
    private static final String TAG_TITLE = "title";
    private static final String TAG_ID = "id";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_TYPE = "type";

	//int and string for type
	int nType;
	String sType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_list);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);



		//grab type id
		Intent intent = getIntent();
		sType = intent.getStringExtra(TAG_TYPE);
		nType = Integer.parseInt(sType);
		
	     // Hashmap for ListView
        recipesList = new ArrayList<HashMap<String, String>>();
        
		//grab Views
		mType = (TextView) findViewById(R.id.recipe_list_type);
		mDescription = (TextView) findViewById(R.id.recipe_list_description);
		lv = (ListView) findViewById(R.id.recipe_list_listView);
        lv.setTextFilterEnabled(true);
       
        //load recipes from ASYNC TASK
        new LoadRecipes().execute();
		//set text for type and description
		mType.setText(beerTypes.idToType(nType));
		mDescription.setText(beerTypes.idToDescription(nType));
		
        //When a single beer is selected set on click listener to go to beer details
        lv.setOnItemClickListener(new OnItemClickListener() {
        
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				mId = (TextView) view.findViewById(R.id.companies_list_id);
				
				String recipeId = mId.getText().toString();
				
				//have to create Recipe class
				//will pass recipeId over :)
				Intent theIntent = new Intent(getApplication(), Recipe.class);
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
              pDialog = new ProgressDialog(RecipeList.this);
              pDialog.setMessage("Loading recipes. Please wait...");
              pDialog.setCancelable(true);
              pDialog.show();
          }

		@Override
		protected Void doInBackground(Void... arg0) {
    		// Creating service handler class that we made for http requests
            ServiceHandler sh = new ServiceHandler();
            
            //SH needs: url, int method, List<NameValuePair> params
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", sType));
            // Making a request to url and getting response
            
            String jsonStr = sh.makeServiceCall(url_all_recipes, ServiceHandler.GET, params);
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
	   
                        String id = c.getString(TAG_ID);
                        String author = c.getString(TAG_AUTHOR);
                        String title = c.getString(TAG_TITLE);
                       
                        //Hashmap for single recipe! (called can)
                        HashMap<String,String> can = new HashMap<String, String>();
                        
                        //add each child node to hashmap key => value
                        
                        can.put(TAG_ID, id);
                        can.put(TAG_AUTHOR, author);
                        can.put(TAG_TITLE, title);
                        
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
 		   ListAdapter adapter = new SimpleAdapter(RecipeList.this,recipesList,
 				   R.layout.companies_list_item,new String[]{ TAG_ID, TAG_TITLE,
                    TAG_AUTHOR }, new int[] { R.id.companies_list_id,
 				  R.id.companies_list_company,
 	                R.id.companies_list_city });
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

