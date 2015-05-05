package juan.pablo.dorado.quehacemoscali;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
//import android.widget.TabHost;
//import android.widget.TabHost.TabSpec;


public class MainActivity extends FragmentActivity {

    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        tabHost.addTab(tabHost.newTabSpec("Tab1").setIndicator("", ContextCompat.getDrawable(this, R.drawable.archive)),
                Tab1Fragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("Tab2").setIndicator("", ContextCompat.getDrawable(this, R.drawable.news)),
                Tab2Fragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("Tab3").setIndicator("", ContextCompat.getDrawable(this, R.drawable.square)),
                Tab3Fragment.class, null);

        //Tab1
        //pestania = tabHost.newTabSpec("Tab1");
        //  pestania.setContent(R.id.tab1);
        // pestania.setIndicator("", ContextCompat.getDrawable(this, R.drawable.drawer));

        //tabHost.addTab(pestania);

        //Tab2
        // pestania = tabHost.newTabSpec("Tab2");
        // pestania.setContent(R.id.tab2);
        // pestania.setIndicator("", ContextCompat.getDrawable(this, R.drawable.news));
//
        // tabHost.addTab(pestania);
//
        // //Tab3
        // pestania = tabHost.newTabSpec("Tab2");
        // pestania.setContent(R.id.tab3);
        // pestania.setIndicator("", ContextCompat.getDrawable(this, R.drawable.paperplane));
//
        // tabHost.addTab(pestania);
//
        // tabHost.setCurrentTab(0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
