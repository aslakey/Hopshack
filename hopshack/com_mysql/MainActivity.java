package hopshack.com_mysql;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get rid of title in action bar
        getActionBar().setTitle(R.string.EMPTY);
        //a quick animation before going to all beers
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO: Your application init goes here. THIS IS WHERE IT WENT TO DISCOVER
                Intent i = new Intent(MainActivity.this, MainViewPager.class);
                MainActivity.this.startActivity(i);
                MainActivity.this.finish();
            }
        }, 3000);
    }
}


		
		



