package com.aston.basicarchitecture.pages.home.stadiums;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aston.basicarchitecture.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StadiumsBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StadiumsBaseFragment extends Fragment implements OnMapReadyCallback {



    MapView mapView;
    GoogleMap map;

    TextView stadiumTextView;
    TextView capacityTextView;
    ImageView stadiumImageView;



    public StadiumsBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Stadiums.
     */
    // TODO: Rename and change types and number of parameters
    public static StadiumsBaseFragment newInstance() {
        StadiumsBaseFragment fragment = new StadiumsBaseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stadiums, container, false);

        mapView = (MapView) v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        mapView = (MapView) v.findViewById(R.id.mapView);

        stadiumTextView =  v.findViewById(R.id.StadiumName);
        capacityTextView =  v.findViewById(R.id.StadiumCapacity);
        stadiumImageView =  v.findViewById(R.id.stadiumImage);

        return v;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);




        ArrayList<StadiumInformation> bob = StadiumRepo.getStadiumsInfo();
        for(int i = 0; i < bob.size(); i++) {
            Log.d("HELP", String.valueOf(bob.size()));
            Marker marker = map.addMarker(new MarkerOptions()
                    .position(bob.get(i).getMapPosition())
                    .icon(bitmapDescriptorFromVector(getActivity(), bob.get(i).getVectorPointer())));
            marker.setTag(i);

        }


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.4634, -97.5151), 3));
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int position = (int)(marker.getTag());
                StadiumInformation stadiumInformation = StadiumRepo.getStadiumsInfo().get(position);

                stadiumTextView.setText(new StringBuilder().append("Stadium: ").append(stadiumInformation.stadiumName).toString());
                capacityTextView.setText(new StringBuilder().append("Capacity: ").append(stadiumInformation.capacity).toString());
                Picasso.get()
                        .load(stadiumInformation.getStadiumURL())
                        .fit()
                        .into(stadiumImageView);


                Log.d("MARKER CLICKED", String.valueOf(position));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(map.getCameraPosition().target, 15));
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


}