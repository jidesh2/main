package onroute.com.onroute;

import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener {

    private GoogleMap mMap;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    public static final String TAG =  MapsActivity.class.getSimpleName();
    private LocationRequest mLocationRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
      //  SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.map);
       // mapFragment.getMapAsync(this);

        //Getting current location
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds



    }
    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()){
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
       mMap = googleMap;
        Intent intent = getIntent();
        String dstAddrss = intent.getStringExtra(PlacesAutoCompleteActivity.EXTRA_ADDRESS);
        mMap.setMyLocationEnabled(true);
        LatLng dst = getLocationFromAddress(dstAddrss);
    }

    /*-- Getting Location From Address */
    private LatLng getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress,1);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            double lat =  location.getLatitude();
            double lng =  location.getLongitude();

            p1 = new LatLng(lat,lng);

            return p1;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG , "Location Serive Connected.");
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null){
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
        }
        else{
            handleNewLocation(location);
        }
    }

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());
        double current_lat = location.getLatitude();
        double current_long = location.getLongitude();
        LatLng latLng = new LatLng(current_lat,current_long);


        Intent intent = getIntent();
        String dstAddrss = intent.getStringExtra(PlacesAutoCompleteActivity.EXTRA_ADDRESS);
        LatLng dst = getLocationFromAddress(dstAddrss);



        final Intent mintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" + "saddr=" + current_lat + "," + current_long + "&daddr=" + dst.latitude + "," + dst.longitude+"&dirflg=d&nav=1"));

       // mintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mintent.setClassName("com.google.android.apps.maps", "com.google.android.maps.driveabout.app.NavigationActivity");
        startActivity(mintent);


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location Services Suspended , Please Connect.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if(connectionResult.hasResolution()){
            try {
                connectionResult.startResolutionForResult(this,CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }
        else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /*public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }*/

}
