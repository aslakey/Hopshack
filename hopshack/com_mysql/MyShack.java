package hopshack.com_mysql;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.text.TextUtils;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class MyShack extends ListActivity implements SearchView.OnQueryTextListener{
	//Import Classes
	DBTools dbTools = new DBTools(this);
	BeerTypes beerTypes = new BeerTypes();
	
	//HASHMAP for List VIEW
	ArrayList<HashMap<String, String>> beerList;
	
    //list view
    private ListView lv;
    //search
    private SearchView mSearchView;

    
	TextView beerId;

	
	 // JSON Node names
    private static final String TAG_ID = "id";
    private static final String TAG_COMPANY = "company";
    private static final String TAG_NAME = "name";
    private static final String TAG_TYPE = "type";
    private static final String TAG_IMAGE = "image";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_beers);
		//get data from db into array list
		beerList = dbTools.getAllBeer();
		//find searchview
	    mSearchView = (SearchView) findViewById(R.id.searchView);
		//check to make sure there are available beers to display
				if (beerList.size() != 0){
					lv = getListView();
					lv.setTextFilterEnabled(true);
					//search through list
					setupSearchView();
				    //on item click listener
					
					lv.setOnItemClickListener(new OnItemClickListener() {
				        
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							
							beerId = (TextView) view.findViewById(R.id.list_id);
							
							String beerIdValue = beerId.getText().toString();
							
							//have to create BEERINFORMATION class
							//will pass beerId over :)
							Intent theIntent = new Intent(getApplication(), MyShackInfo.class);
							theIntent.putExtra(TAG_ID, beerIdValue);
							startActivity(theIntent);
						}
			        
			        
					});
					//List adapter
				    ListAdapter adapter = new SimpleAdapter(MyShack.this,beerList,
		    				   R.layout.list_item,new String[]{ TAG_ID, TAG_COMPANY,
		                       TAG_NAME, TAG_IMAGE }, new int[] { R.id.list_id,
		                       R.id.list_company, R.id.list_name, R.id.list_image });
		    		   setListAdapter(adapter);
				}
     		   
	}

	private void setupSearchView() {
		 mSearchView.setIconifiedByDefault(false);
	     mSearchView.setOnQueryTextListener(this);
	     mSearchView.setSubmitButtonEnabled(true);
	     mSearchView.setQueryHint("Search Beer");
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
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
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
				goToAllBeer();
				return true;

	        default:
	            return super.onOptionsItemSelected(item);			
		}
		
	}
//go to view beers when menu all beer is pressed
	private void goToAllBeer() {
		Intent i = new Intent(getApplicationContext(), ViewBeers.class);
		startActivity(i);
	}


}
