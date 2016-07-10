package hackathon.a3ds.com.glo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class OutingViewActivity extends Activity implements OnMapReadyCallback{

    private ArrayList<Marker> mPosseMarkers;
    private LocationManager mLocationManager;
    private ArrayList<LatLng> mPosseLocations;

    public static int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outing_view);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Acquire a reference to the system Location Manager
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mPosseLocations.set(0, new LatLng(location.getLatitude(), location.getLongitude()));
                Toast.makeText(getApplicationContext(), "location changed", Toast.LENGTH_SHORT).show();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        mPosseMarkers = new ArrayList<Marker>();
        mPosseLocations = new ArrayList<LatLng>();

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
        }

        // Register the listener with the Location Manager to receive location updates
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        Location lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        mPosseLocations.add(new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()));
    }

    /***
     * Callback when the google map is ready
     * Create a new marker for user's current location
     *
     * TODO: Create markers for every user in the outing
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Draw marker at last known location
        Marker self = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mPosseMarkers.add(self);
        LatLng loc = mPosseLocations.get(0);
        if(loc != null){
            self.setPosition(loc);

        }else{
            Toast.makeText(getApplicationContext(), "No Previous Known Location", Toast.LENGTH_SHORT).show();
        }

        // Pan and zoom map to current location
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(loc, 15));
        googleMap.animateCamera(cameraUpdate);
    }
}
