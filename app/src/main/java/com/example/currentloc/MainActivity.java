package com.example.currentloc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText lat,lon;
Button btn;
TextView tv;
private void checkloc() {
    // Check if the user has granted location permissions
    int permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);

    if (permission != PackageManager.PERMISSION_GRANTED) {
        // Request location permissions
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }
}
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    lat=findViewById(R.id.lati);
    lon=findViewById(R.id.longi);
    btn=findViewById(R.id.button);
    tv=findViewById(R.id.textView2);
    checkloc();
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String lati=lat.getText().toString();
            String longi=lon.getText().toString();
            if(lati.isEmpty() || longi.isEmpty())
                tv.setText("Fill in the fields");
            else{
                double la=Double.parseDouble(lati);
                double lo=Double.parseDouble(longi);
                Geocoder gc=new Geocoder(MainActivity.this);
                String adr="";
                List<Address> addresses = null;
                try {
                    addresses = gc.getFromLocation(la, lo, 1);
                    if (addresses != null && addresses.size() > 0) {
                        adr = addresses.get(0).getAddressLine(0);
                } }
                catch (IOException e) {
                    e.printStackTrace();
                }
tv.setText(adr);
                }

            }


    });



    }
}