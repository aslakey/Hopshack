package hopshack.com_mysql;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link FragmentDiscover.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link FragmentDiscover#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class FragmentDiscover extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_fragment_discover, container, false);
		//Recommend Button
		ImageButton recommendButt = (ImageButton)v.findViewById(R.id.discover_recommend);
        recommendButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Recommend.class);
                startActivity(intent);
            }
        });
		//Pairing Button
        ImageButton pairButt = (ImageButton)v.findViewById(R.id.discover_pairing);
        pairButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FoodPairing.class);
                startActivity(intent);
            }
        });
		//Local Breweries Button
        ImageButton localButt = (ImageButton)v.findViewById(R.id.discover_local);
        localButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocalBreweries.class);
                startActivity(intent);
            }
        });
		
		
		return v;
	
	}


}
