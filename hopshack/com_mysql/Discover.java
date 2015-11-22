package hopshack.com_mysql;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Discover extends Activity {
	//Buttons and onClick's are defined in XML. Functions are listed below!

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discover);

	}
	//Recommend Button
	public void goToRecommend(View view){
		Intent intent = new Intent(getApplicationContext(), Recommend.class);
		startActivity(intent);
	}
	//Pairing Button
	public void goToFoodPairing(View view){
		Intent intent = new Intent(getApplicationContext(), FoodPairing.class);
		startActivity(intent);
	}
	//Recommend Button
	public void goToLocalBreweries(View view){
		Intent intent = new Intent(getApplicationContext(), LocalBreweries.class);
		startActivity(intent);
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

	private void goToShack() {
		Intent i = new Intent(getApplicationContext(), MyShack.class);
		startActivity(i);
		
	}
	private void goToAllBeer() {
		Intent i = new Intent(getApplicationContext(), ViewBeers.class);
		startActivity(i);
	}

}
