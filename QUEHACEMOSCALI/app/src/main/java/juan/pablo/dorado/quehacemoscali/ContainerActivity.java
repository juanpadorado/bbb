package juan.pablo.dorado.quehacemoscali;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Juan Pablo on 08/06/2015.
 */
public class ContainerActivity extends ActionBarActivity {

    Fragment rumbaFrag, gastroFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setLogo(R.mipmap.ic_launcher);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.primary_dark));
        }

        rumbaFrag = new RumbaFragment();
        gastroFrag = new GastroFragment();

        Bundle bundle = getIntent().getExtras();

        switch (bundle.getInt("boton")) {
            case 0:
                getSupportFragmentManager().beginTransaction().add(R.id.containerFrag, rumbaFrag, "listaRumba").commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().add(R.id.containerFrag, gastroFrag, "listaGastro").commit();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            Toast.makeText(this, "SETTINGS", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
