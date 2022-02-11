package com.aston.basketballapp.pages.home.stadiums;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aston.basketballapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
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
    MaterialButton ticketButton;

    String currentURL;


    public StadiumsBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
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

        mapView = (MapView) v.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        mapView = (MapView) v.findViewById(R.id.map_view);

        stadiumTextView =  v.findViewById(R.id.stadium_name);
        capacityTextView =  v.findViewById(R.id.stadium_capacity);
        stadiumImageView =  v.findViewById(R.id.stadium_image);
        ticketButton =  v.findViewById(R.id.stadium_ticket_button);

        ticketButton.setEnabled(false);

        ticketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(currentURL)));
            }
        });

        return v;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);

        ArrayList<StadiumInformation> bob = StadiumRepo.getStadiumsInfo();
        for(int i = 0; i < bob.size(); i++) {
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


                map.moveCamera(CameraUpdateFactory.newLatLngZoom(map.getCameraPosition().target, 15));

                currentURL = stadiumInformation.ticketsURL;
                ticketButton.setEnabled(true);
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