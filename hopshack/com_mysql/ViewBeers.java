package hopshack.com_mysql;

import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

//Viewing all beers
//This is a list activity!


public class ViewBeers extends ListActivity implements SearchView.OnQueryTextListener{
	//Progress Dialog
	private ProgressDialog pDialog;
	
	//import classes
	BeerTypes beerTypes = new BeerTypes();
	
	//HASHMAP for List VIEW
	ArrayList<HashMap<String, String>> beerList;
	
	//URL to get all beers
	private static String url_all_beer = "http://hopshack.com/db_get_all_beer.php";
	
	 // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_BEER = "beer";
    private static final String TAG_ID = "id";
    private static final String TAG_COMPANY = "company";
    private static final String TAG_NAME = "name";
    private static final String TAG_TYPE = "type";
    private static final String TAG_IMAGE = "image";
    
    //Beer JSON Array
    JSONArray beer = null;
    
    //list view
    private ListView lv;
    //intent
    private Intent intent;
    //beerID
    private TextView beerId;
    
    //search
    private SearchView mSearchView;

    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_beers);
        ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
        
     // Hashmap for ListView
        beerList = new ArrayList<HashMap<String, String>>();
     
     //Search view
      mSearchView = (SearchView) findViewById(R.id.searchView);
        
     //Get ListView
        lv = getListView();
        lv.setTextFilterEnabled(true);
        setupSearchView();
        //JSON from URL
        new LoadBeer().execute();
        
        //When a single beer is selected set on click listener to go to beer details
        lv.setOnItemClickListener(new OnItemClickListener() {
        
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				beerId = (TextView) view.findViewById(R.id.list_id);
				
				String beerIdValue = beerId.getText().toString();
				
				//have to create BEERINFORMATION class
				//will pass beerId over :)
				Intent theIntent = new Intent(getApplication(), BeerInformation.class);
				theIntent.putExtra(TAG_ID, beerIdValue);
				startActivity(theIntent);
			}
        
        
		});
    }
    
 private void setupSearchView() {
	 mSearchView.setIconifiedByDefault(false);
     mSearchView.setOnQueryTextListener(this);
     mSearchView.setSubmitButtonEnabled(true);
     mSearchView.setQueryHint("Search Beer");
		
	}

//AsyncClass to load beer
 class LoadBeer extends AsyncTask<Void, Void, Void>{
        	/*
             * Before starting background thread Show Progress Dialog
             * */
        	 @Override
             protected void onPreExecute() {
                 super.onPreExecute();
                 pDialog = new ProgressDialog(ViewBeers.this);
                 pDialog.setMessage("Loading beers. Please wait...");
                 pDialog.setCancelable(true);
                 pDialog.show();
             }
        	 /*
              * getting All BEER from url
              * */
        	   protected Void doInBackground(Void... args) {
        		   
        		// Creating service handler class that we made for http requests
                   ServiceHandler sh = new ServiceHandler();
                   // Making a request to url and getting response
                   String jsonStr = sh.makeServiceCall(url_all_beer, ServiceHandler.GET);
                   Log.d("Response: ", "> " + jsonStr);
                   
                   if (jsonStr != null){
                	   try{
                		   //JSON Object!
         
                		   JSONObject jsonObj = new JSONObject(jsonStr);
                		   
                		   //JSON ARRAY node
                		   beer = jsonObj.getJSONArray(TAG_BEER);
                		   
                		   //loop through all beer!
                		   for (int i = 0; i<beer.length(); i++){
                			   JSONObject c = beer.getJSONObject(i);
                			   
                			   //I'm just grabbing name company and id for now!
                				//int for type
                			   
                               String id = c.getString(TAG_ID);
                               String name = c.getString(TAG_NAME);
                               String company = c.getString(TAG_COMPANY);
                               String type = c.getString(TAG_TYPE);
                               
                               int nType = Integer.parseInt(type);
                               int nImage = beerTypes.idToImage(nType);
                               String image = String.valueOf(nImage);
                               //Hashmap for single beer! (called can
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
        			   ListAdapter adapter = new SimpleAdapter(ViewBeers.this,beerList,
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

	@Override
	public boolean onQueryTextChange(String newText) {
		if (TextUtils.isEmpty(newText)) {
            lv.clearTextFilter();
        } else {
            lv.setFilterText(newText.toString());
        }
        return true;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
 
