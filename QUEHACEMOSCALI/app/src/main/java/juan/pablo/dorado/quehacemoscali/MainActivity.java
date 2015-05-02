package juan.pablo.dorado.quehacemoscali;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


public class MainActivity extends Activity {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {

                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#999999"));
                }

                TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
                tv.setTextColor(Color.parseColor("#999999"));

            }
        });

        //Tab1
        tabHost.setup();
        TabSpec ts1 = tabHost.newTabSpec("Tab1");
        ts1.setIndicator(getString(R.string.categorias));
        ts1.setContent(R.id.tab1);

        tabHost.addTab(ts1);

        //Tab2
        tabHost.setup();
        TabHost.TabSpec ts2 = tabHost.newTabSpec("Tab2");
        ts2.setIndicator(getString(R.string.noticias));
        ts2.setContent(R.id.tab2);

        tabHost.addTab(ts2);

        //Tab3
        tabHost.setup();
        TabHost.TabSpec ts3 = tabHost.newTabSpec("Tab2");
        ts3.setIndicator(getString(R.string.recomiendanos));
        ts3.setContent(R.id.tab3);

        tabHost.addTab(ts3);

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
