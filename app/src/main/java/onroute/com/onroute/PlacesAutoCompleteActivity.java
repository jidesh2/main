package onroute.com.onroute;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import onroute.com.onroute.adapters.PlacesAutoCompleteAdapter;
import onroute.com.onroute.listeners.RecyclerItemClickListener;


public class PlacesAutoCompleteActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {


    private static final LatLngBounds BOUNDS_INDIA = new LatLngBounds(
            new LatLng(-0, 0), new LatLng(0, 0));
    private GoogleMap mMap;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    public static final String TAG =  MapsActivity.class.getSimpleName();
    private LocationRequest mLocationRequest;
    String lat;
    String lng;
    GPSTracker gps;
    Double dist;
    private EditText mAutocompleteView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private PlacesAutoCompleteAdapter mAutoCompleteAdapter;
   // ImageView delete;
    public static final String EXTRA_ADDRESS = "com.razor.googleplacesautocompletesample.ADDRESS" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGoogleApiClient();
        setContentView(R.layout.activity_search);
        mAutocompleteView = (EditText) findViewById(R.id.autocomplete_places);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
        gps = new GPSTracker(PlacesAutoCompleteActivity.this);
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds


        mAutoCompleteAdapter = new PlacesAutoCompleteAdapter(this, R.layout.searchview_adapter,
                mGoogleApiClient, BOUNDS_INDIA, null);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setAdapter(mAutoCompleteAdapter);
      // delete.setOnClickListener(this);
        mAutocompleteView.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mRecyclerView.setVisibility(View.VISIBLE);
                if (!s.toString().equals("") && mGoogleApiClient.isConnected()) {
                    mAutoCompleteAdapter.getFilter().filter(s.toString());
                } else if (!mGoogleApiClient.isConnected()) {
                    Toast.makeText(getApplicationContext(), Constants.API_NOT_CONNECTED, Toast.LENGTH_SHORT).show();
                    Log.e(Constants.PlacesTag, Constants.API_NOT_CONNECTED);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, int position) {
                        final PlacesAutoCompleteAdapter.PlaceAutocomplete item = mAutoCompleteAdapter.getItem(position);
                        final String placeId = String.valueOf(item.placeId);
                        Log.i("TAG", "Autocomplete item selected: " + item.description);


                        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                                .getPlaceById(mGoogleApiClient, placeId);
                        placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                            @Override
                            public void onResult(PlaceBuffer places) {
                                if (places.getCount() == 1) {

                                    lat = String.valueOf(places.get(0).getLatLng().latitude);

                                    lng = String.valueOf(places.get(0).getLatLng().longitude);
                                  //  Toast.makeText(getApplicationContext(), String.valueOf(places.get(0).getLatLng()), Toast.LENGTH_SHORT).show();
                                    mAutocompleteView.setText(item.description);
                                    AsyncT asyncT = new AsyncT();
                                    asyncT.execute();
                                    mRecyclerView.setVisibility(view.GONE);
                                   double lat2 = Double.parseDouble(lat);
                                  double  lon2 = Double.parseDouble(lng);
                                    // Add a marker in Sydney and move the camera
                                    LatLng destination = new LatLng(lat2, lon2);

                                    mMap.addMarker(new MarkerOptions().position(destination).title("Marker in destination"));
                                    //  mMap.moveCamera(CameraUpdateFactory.newLatLng(destination));
                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination,16));
                                    //Toast.makeText(getApplicationContext(), Constants.SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        Log.i("TAG", "Clicked: " + item.description);
                        Log.i("TAG", "Called getPlaceById to get Place details for " + item.placeId);
                    }
                })
        );


        // check if GPS enabled
        if (gps.canGetLocation()) {

            final double latitude = gps.getLatitude();
            final double longitude = gps.getLongitude();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.v("Google API Callback", "Connection Done");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.v("Google API Callback", "Connection Suspended");
        Log.v("Code", String.valueOf(i));
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.v("Google API Callback","Connection Failed");
        Log.v("Error Code", String.valueOf(connectionResult.getErrorCode()));
       // Toast.makeText(this, Constants.API_NOT_CONNECTED, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
    //    if(v==delete){
      //      mAutocompleteView.setText("");
      //  }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()){
            Log.v("Google API","Connecting");
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mGoogleApiClient.isConnected()){
            Log.v("Google API","Dis-Connecting");
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    class AsyncT extends AsyncTask<Void, Void, Void> {
        String dist1;
        String responseServer ;
        @Override
        protected Void doInBackground(Void... voids) {
            //19.371760 + "," +72.825168 + "&destination=" + 19.048088 + "," + 72.906588



            gps.canGetLocation();
             double latitude = gps.getLatitude();
           double longitude = gps.getLongitude();
           double lat2 = Double.parseDouble(lat);
           double lon2 = Double.parseDouble(lng);
            Log.d("latlong", String.valueOf(lat2));
            Log.d("latlong1", String.valueOf(longitude));
            Log.d("latlong6", String.valueOf(lon2));
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://maps.google.com/maps/api/directions/json?origin=" + latitude + "," +longitude + "&destination=" + lat2 + "," + lon2 + "&sensor=false&units=metric");

            try {


                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                //    HttpResponse publickeys = httpclient.execute(httppost);
                InputStream inputStream = response.getEntity().getContent();
                // InputStream inputStream1 = publickeys.getEntity().getContent();
                InputStreamToStringExample str = new InputStreamToStringExample();
                responseServer = InputStreamToStringExample.getStringFromInputStream(inputStream);

                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject = new JSONObject(responseServer );

                    JSONArray array = jsonObject.getJSONArray("routes");

                    JSONObject routes = array.getJSONObject(0);

                    JSONArray legs = routes.getJSONArray("legs");

                    JSONObject steps = legs.getJSONObject(0);

                    JSONObject distance = steps.getJSONObject("duration");

                    Log.i("Distance", distance.toString());

                    dist1 = distance.getString("text");


                    Log.e("response56", "response -----" + str.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
            @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

Log.d("test",responseServer);
                Toast.makeText(getApplicationContext(),dist1, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
double lat2=19.7062;;
        double lon2=72.7836;
       // double lat2 = Double.parseDouble(lat);
    //    double lon2 = Double.parseDouble(lng);

        gps.canGetLocation();
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();


          // Add a marker in Sydney and move the camera
          LatLng destination = new LatLng(latitude, longitude);

          mMap.addMarker(new MarkerOptions().position(destination).title("Marker in destination"));
          //  mMap.moveCamera(CameraUpdateFactory.newLatLng(destination));
          mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination,16));



    }

}
