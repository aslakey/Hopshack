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
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;

public class CompaniesFromCity extends Activity {
	// Creating service handler class that we made for http requests
    ServiceHandler sh = new ServiceHandler();
	//Progress Dialog
	private ProgressDialog pDialog;
	
	//HASHMAP for List VIEW
	ArrayList<HashMap<String, String>> companyList;
	
	//URL to get breweries from city **it is country right now!!!!!
	private static String url_comanies_fromGPS = "http://hopshack.com/db_companies_from_city.php";
	
	 // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_COMPANY = "company";
    private static final String TAG_COMPANIES = "companies";
    private static final String TAG_CITY = "city";
    private static final String TAG_CITYCODE = "cityCode";
    //string to store stateCode
    String cityCode = "";
    
    //Beer JSON Array
    JSONArray companies = null;
    
    //list view
    private ListView lv;

    //search
    private SearchView mSearchView;
    //To send to beers from company class
    TextView companyView;
    String company = "";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_companies_from_gps);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);



		//grab Intent
		Intent intent = getIntent();
		cityCode = intent.getStringExtra(TAG_CITYCODE);
		Log.d(TAG_CITYCODE, cityCode);
	     // Hashmap for ListView
        companyList = new ArrayList<HashMap<String, String>>();
     
     //Search view
      mSearchView = (SearchView) findViewById(R.id.companiesFromGPS_searchView);
        
     //Get ListView
        lv = (ListView) findViewById(R.id.companiesFromGPS_list);
        lv.setTextFilterEnabled(true);
        setupSearchView();
		new LoadCompanies().execute();

        //When a single Company is selected set on click listener to go to beers from that company
        lv.setOnItemClickListener(new OnItemClickListener() {
        
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				companyView = (TextView) view.findViewById(R.id.companies_list_company);
				
				String company = companyView.getText().toString();
				
				//have to create BEERINFORMATION class
				//will pass beerId over :)
				Intent theIntent = new Intent(getApplication(), BeersFromCompany.class);
				theIntent.putExtra(TAG_COMPANY, company);
				startActivity(theIntent);
			}
        
        
		});
	}
	
//Async Class to load companies
class LoadCompanies extends AsyncTask<Void,Void,Void>{
 	/*
     * Before starting background thread Show Progress Dialog
     * */
	 @Override
     protected void onPreExecute() {
         super.onPreExecute();
         pDialog = new ProgressDialog(CompaniesFromCity.this);
         pDialog.setMessage("Loading Breweries. Please wait...");
         pDialog.setCancelable(true);
         pDialog.show();
     }
//load companies from url
	@Override
	protected Void doInBackground(Void... arg0) {
        //SH needs: url, int method, List<NameValuePair> params
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(TAG_CITYCODE, cityCode));
        
        String jsonStr = sh.makeServiceCall(url_comanies_fromGPS, ServiceHandler.GET, params);
        Log.d("Response: ", "> " + jsonStr);
        if (jsonStr != null){
     	   try{
     		   //JSON Object!

     		   JSONObject jsonObj = new JSONObject(jsonStr);
     		   
     		   //JSON ARRAY node
     		   companies = jsonObj.getJSONArray(TAG_COMPANIES);
     		   
     		   //loop through all beer!
     		   for (int i = 0; i<companies.length(); i++){
     			   JSONObject c = companies.getJSONObject(i);
     			   
     			   //I'm just grabbing name company and id for now!          
                    String company = c.getString(TAG_COMPANY);
                    String city = c.getString(TAG_CITY);
                    
                    //Hashmap for single beer! (called can
                    HashMap<String,String> can = new HashMap<String, String>();
                    
                    //add each child node to hashmap key => value                  
                    can.put(TAG_COMPANY, company);
                    can.put(TAG_CITY, city);
                    
                    //add can to beerList
                    companyList.add(can);
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
		   ListAdapter adapter = new SimpleAdapter(CompaniesFromCity.this,companyList,
				   R.layout.companies_list_item,new String[]{TAG_COMPANY,
                TAG_CITY }, new int[] { R.id.companies_list_company,
                R.id.companies_list_city });
		   lv.setAdapter(adapter);
	   }

}
private void setupSearchView() {
	 mSearchView.setIconifiedByDefault(false);
    mSearchView.setSubmitButtonEnabled(true);
    mSearchView.setQueryHint("Search Brewery/City");
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
//go to shack when menu icon is clicked
private void goToShack() {
	Intent i = new Intent(getApplicationContext(), MyShack.class);
	startActivity(i);
}
private void goToDiscover() {
	Intent i = new Intent(getApplicationContext(), Discover.class);
	startActivity(i);
	
}
private void goToViewBeers(){
	Intent i = new Intent(getApplicationContext(), ViewBeers.class);
	startActivity(i);
}

}
