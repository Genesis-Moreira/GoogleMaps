package com.example.tema9googlemapsapi;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.slider.Slider;

public class MainActivity
        extends AppCompatActivity
        implements OnMapReadyCallback {
    GoogleMap map;
    Double lat, lng;
    EditText txtLat, txtLong;
    Circle circulo = null;
    Slider sliderRadio;
    float radio = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        txtLat = findViewById(R.id.txtLatitud);
        txtLong = findViewById(R.id.txtLogitud);
        sliderRadio= findViewById(R.id.sliderRadio);
        sliderRadio.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {


        public void onStartTrackingTouch(@NonNull Slider slider) {
            radio = slider.getValue();
            updateInterfaz();
        }
        @Override
        public void onStopTrackingTouch(@NonNull Slider slider) {
        }
    });
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setCompassEnabled(true);


        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(-1.012351, -79.46956), 19);
        map.moveCamera(camUpd1);


        LatLng punto = new LatLng(-1.01292, -79.46898);
        map.addMarker(new
                MarkerOptions().position(punto)
                .title("UTEQ"));


        map.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng center = map.getCameraPosition().target;
                lat = center.latitude;
                lng =center.longitude;
                updateInterfaz();
            }
        });
    }


    private void updateInterfaz(){
        txtLat.setText(String.format("%.4f", lat));
        txtLong.setText(String.format("%.4f", lng));



        PintarCirculo();
    }
    private void PintarCirculo(){
        if(circulo!=null){ circulo.remove(); circulo = null;}



        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(lat, lng))
                .radius(radio*100) //En Metros
                .strokeColor(Color.RED)
                .fillColor(Color.argb(50, 150, 50, 50));

        circulo = map.addCircle(circleOptions);



    }

}
