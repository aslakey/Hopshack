package hopshack.com_mysql;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class FoodPairing extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_pairing);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	//FoodRecipes Button
	public void goToFoodPairingRecipes(View view){
		Intent intent = new Intent(getApplicationContext(), FoodPairingRecipes.class);
		startActivity(intent);
	}
	//FoodStyle Button
	public void goToFoodPairingStyles(View view){
		Intent intent = new Intent(getApplicationContext(), FoodPairingStyles.class);
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
