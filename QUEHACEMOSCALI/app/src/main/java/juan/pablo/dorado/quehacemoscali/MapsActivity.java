package juan.pablo.dorado.quehacemoscali;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends ActionBarActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleApiClient mLocationClient;

    LatLng ubicacion;

    private List<Address> addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMapa);
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

        Bundle bundle = getIntent().getExtras();

        ubicacion = new LatLng(bundle.getDouble("lat"), bundle.getDouble("lng"));

        setUpMapIfNeeded();
        onUAO(ubicacion, bundle.getString("titulo"), bundle.getString("info"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    public void onUAO(LatLng ubi, String titulo, String info) {
        CameraPosition INIT;
        INIT = new CameraPosition.Builder()
                .target(ubi)
                .zoom(17.0F)
                .bearing(300F) // orientation
                .tilt(50F) // viewing angle
                .build();


        // use GooggleMap mMap to move camera into position
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(INIT));
        setMarket(ubi, titulo, info);

        // LatLng latitud_longitud=new LatLng(3.353941,-76.521716);
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latitud_longitud, 17));
    }

    private void setMarket(LatLng position, String titulo, String info) {
        // Agregamos marcadores para indicar sitios de interéses.
        Marker myMaker = mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)  //Agrega un titulo al marcador
                .snippet(info)   //Agrega información detalle relacionada con el marcador
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))); //Color del marcador
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mLocationClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        Toast.makeText(getApplicationContext(), "Conectado", Toast.LENGTH_SHORT).show();
                        Location location = LocationServices.FusedLocationApi.getLastLocation(mLocationClient);
                        String direccion;

                        if (addresses == null) {
                            //locationManager.requestLocationUpdates(bestProvider, 20000, 1, this);
                            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                            try {
                                if (location != null)
                                    addresses = geocoder.getFromLocation(
                                            location.getLatitude(),
                                            location.getLongitude(), 1);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                //direccion = "Sin dirección";

                            }
                        }
                        if (addresses != null) {
                            Address returnedAddress = addresses.get(0);
                            StringBuilder strReturnedAddress = new StringBuilder("location: ");
                            for (int i = 0; i < returnedAddress
                                    .getMaxAddressLineIndex(); i++) {
                                strReturnedAddress.append(
                                        returnedAddress.getAddressLine(i)).append("\n");
                            }
                            direccion = strReturnedAddress.toString();
                        } else {
                            direccion = "Sin dirección";
                        }
                        if (location != null)
                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(location.getLatitude(), location.getLongitude()))
                                    .title("Posicion")
                                    .snippet(direccion));

                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Toast.makeText(getApplicationContext(), "Suspendido", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(), "Fallido", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        mLocationClient.connect();


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker").snippet("SNIPPET"));
            }
        });
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                //Toast.makeText(getApplicationContext(), "Cámara cambió", Toast.LENGTH_SHORT).show();
            }
        });
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                return false;
            }
        });

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
