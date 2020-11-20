package com.example.app_tambalanqu.ui.tambal;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_tambalanqu.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TambalFragment extends Fragment implements OnMapReadyCallback{


    private TambalViewModel dashboardViewModel;
    Button tombolTemukan;
    ImageButton tombolPetunjuk;
    GoogleMap mMap;
    MapView mMapView;
    View mView;
    private RequestQueue mQueue;
    ArrayList<LatLng>arrayList= new ArrayList<LatLng>();

    public static final String url = "http://yanayastore.com/api-location/location.php";

    ArrayList<String> title = new ArrayList<>();

    ArrayList<String> alamat= new ArrayList<>();

    public TambalFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(TambalViewModel.class);
        mView = inflater.inflate(R.layout.fragment_tambal, container, false);

        mQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("FL");

                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject isiLocation = jsonArray.getJSONObject(i);
//                                String LatLng = isiLocation.getString("latlng");

                                String latitude = isiLocation.getString("latitude");
                                String longitude = isiLocation.getString("longitude");
                                String judul = isiLocation.getString("title");
                                String address = isiLocation.getString("address");

                                LatLng LatLng = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
                                arrayList.add(LatLng);
                                title.add(judul);
                                alamat.add(address);

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tombolPetunjuk = getView().findViewById(R.id.petunjuk);
        mMapView = (MapView) mView.findViewById(R.id.map);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }



        tombolPetunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "https://www.google.com/maps/search/tambal+ban+terdekat+jagakarsa/@-6.3508217,106.8059962,15z/data=!3m1!4b1";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);

            }
        });




    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        tombolTemukan = getView().findViewById(R.id.temukan);





        tombolTemukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<arrayList.size();i++){
//                    mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(title.get(i)));

                        mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(title.get(i)).snippet(alamat.get(i)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker)));
//                    mMap.animateCamera(CameraUpdateFactory.zoomTo(26));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
//                    mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arrayList.get(i), 14));
                        mMap.getUiSettings().setZoomControlsEnabled(true);
                        mMap.getUiSettings().setCompassEnabled(true);


                }

            }
        });




    }





}