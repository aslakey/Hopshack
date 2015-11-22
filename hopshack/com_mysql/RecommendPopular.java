package hopshack.com_mysql;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;

public class RecommendPopular extends Activity {

	//import classes
	BeerTypes beerTypes = new BeerTypes();
	//typeList to be returned from beerTypes
	ArrayList<HashMap<String, String>> typeList;
	
    //list view
    private ListView lv;
    //search
    private SearchView mSearchView;
    final String TAG_TYPEID = "typeId";
	final String TAG_TYPESTRING = "typeString";
	final String TAG_IMAGE = "image";
	private static final String TAG_TYPE = "type";
	
	private TextView mId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend_popular);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);



		//Hashmap from beerTypes
		typeList = beerTypes.returnTypeList(typeList);
		//grab SearchView
		mSearchView = (SearchView) findViewById(R.id.recommendPopular_searchView);
		//grab lv
		lv = (ListView) findViewById(R.id.recommendPopular_listView);
		lv.setTextFilterEnabled(true);
	    setupSearchView();
	    ListAdapter adapter = new SimpleAdapter(RecommendPopular.this,typeList,
				   R.layout.style_list_item,new String[]{ TAG_TYPEID, TAG_TYPESTRING, TAG_IMAGE
                }, new int[] { R.id.style_list_id,
                R.id.style_list_typeString, R.id.style_list_image });
		   lv.setAdapter(adapter);
		 
	    lv.setOnItemClickListener(new OnItemClickListener() {
	           
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					mId = (TextView) view.findViewById(R.id.style_list_id);
					
					String typeId = mId.getText().toString();
					
					//have to create RecommendPopularBeers class
					//will pass typeId over :)
					Intent theIntent = new Intent(getApplicationContext(), RecommendPopularBeers.class);
					theIntent.putExtra(TAG_TYPE, typeId);
					startActivity(theIntent);
				}     
			});
	}

	 private void setupSearchView() {
		 mSearchView.setIconifiedByDefault(false);
	     mSearchView.setSubmitButtonEnabled(true);
	     mSearchView.setQueryHint("Search Styles. . .");
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
