package hopshack.com_mysql;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link FragmentMyShack.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link FragmentMyShack#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class FragmentMyShack extends ListFragment {
	//Import Classes
	
	BeerTypes beerTypes = new BeerTypes();
	
	//HASHMAP for List VIEW
	ArrayList<HashMap<String, String>> beerList;
	
    //list view
    private ListView lv;

    ListAdapter adapter;    
	TextView beerId;

	
	 // JSON Node names
    private static final String TAG_ID = "id";
    private static final String TAG_COMPANY = "company";
    private static final String TAG_NAME = "name";
    private static final String TAG_TYPE = "type";
    private static final String TAG_IMAGE = "image";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_fragment_my_shack, container,
				false);
		//get data from db into array list
		DBTools dbTools = new DBTools(getActivity());
		beerList = dbTools.getAllBeer();
		
		//check to make sure there are available beers to display
	    
				if (beerList.size() != 0){
					lv = (ListView) v.findViewById(android.R.id.list);
					lv.setTextFilterEnabled(true);
					//search view doesn't work in fragment, so deleted   
					//List adapter 
				    ListAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(),beerList,
		    				   R.layout.list_item,new String[]{ TAG_ID, TAG_COMPANY,
		                       TAG_NAME, TAG_IMAGE}, new int[] { R.id.list_id,
		                       R.id.list_company, R.id.list_name, R.id.list_image});
		    		setListAdapter(adapter);

				}
		
		return v;
	}
	public void onResume()
	{
	    super.onResume();
	    DBTools dbTools = new DBTools(getActivity());
	    beerList = dbTools.getAllBeer();
	    
	    ListAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(),beerList,
				   R.layout.list_item,new String[]{ TAG_ID, TAG_COMPANY,
                TAG_NAME, TAG_IMAGE }, new int[] { R.id.list_id,
                R.id.list_company, R.id.list_name, R.id.list_image });
		setListAdapter(adapter);  // refresh adapter    
	}
	
	//clicking items send to myshackinfo.class
	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
	  super.onListItemClick(l, v, pos, id);
		beerId = (TextView) v.findViewById(R.id.list_id);
		
		String beerIdValue = beerId.getText().toString();
		
		//have to create BEERINFORMATION class
		//will pass beerId over :)
		Intent theIntent = new Intent(getActivity(), MyShackInfo.class);
		theIntent.putExtra(TAG_ID, beerIdValue);
		startActivity(theIntent);
	}
}
