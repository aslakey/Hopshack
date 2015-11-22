package hopshack.com_mysql;

import hopshack.com_mysql.ViewBeers.LoadBeer;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class FragmentEncyclopedia extends ListFragment {
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
  

	public FragmentEncyclopedia() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_fragment_encyclopedia,
				container, false);
		// Hashmap for ListView
        beerList = new ArrayList<HashMap<String, String>>();
        //find listview for fragment
        lv = (ListView) v.findViewById(android.R.id.list);
      //JSON from URL
        new LoadBeer().execute();
        
		return v;
	}
	
	//clicking items send to myshackinfo.class
	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
	  super.onListItemClick(l, v, pos, id);
		beerId = (TextView) v.findViewById(R.id.list_id);
		
		String beerIdValue = beerId.getText().toString();
		
		//have to create BEERINFORMATION class
		//will pass beerId over :)
		Intent theIntent = new Intent(getActivity(), BeerInformation.class);
		theIntent.putExtra(TAG_ID, beerIdValue);
		startActivity(theIntent);
	}
	//AsyncClass to load beer
	 class LoadBeer extends AsyncTask<Void, Void, Void>{
	        	/*
	             * Before starting background thread Show Progress Dialog
	             * */
	        	 @Override
	             protected void onPreExecute() {
	                 super.onPreExecute();
	                 //pDialog = new ProgressDialog(getActivity());
	                 //pDialog.setMessage("Loading beers. Please wait...");
	                 //pDialog.setCancelable(true);
	                 //pDialog.show();
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
	        		   //if (pDialog.isShowing())
	        			   //pDialog.dismiss();
	        		   
	        		    /*
	        	         * Updating parsed JSON data into ListView
	        	         * */
	        			   ListAdapter adapter = new SimpleAdapter(getActivity(),beerList,
	        					   R.layout.list_item,new String[]{ TAG_ID, TAG_COMPANY,
	        	                TAG_NAME, TAG_IMAGE }, new int[] { R.id.list_id,
	        	                R.id.list_company, R.id.list_name, R.id.list_image });
	        			   lv.setAdapter(adapter);
	        	   }
	 }

}
