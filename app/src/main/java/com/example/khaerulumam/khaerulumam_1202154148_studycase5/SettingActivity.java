package com.example.khaerulumam.khaerulumam_1202154148_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class SettingActivity extends AppCompatActivity {

    TextView a, b;
    AlertDialog.Builder alertDialog1;
    int warna;
    CharSequence[] values = {" Red "," Blue "," Green "};

    // Current background color.
    private int mColor;

    // Key for current color
    private final String COLOR_KEY = "color";

    // Shared preferences object
    private SharedPreferences mPreferences;
    // Name of shared preferences file
    private static final String mSharedPrefFile = "com.example.khaerulumam.khaerulumam_1202154148_studycase5";

    SharedPreferences.Editor preferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        a = (TextView) findViewById(R.id.ubahcolor);
        b = (TextView) findViewById(R.id.color);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Setting");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        alertDialog1 = new AlertDialog.Builder(this);

        //Mendapatkan SharedPreference dan menentukan editor untuk SharedPreference

        SharedPreferences pref = getApplicationContext().getSharedPreferences("pref", 0);

        preferencesEditor = pref.edit();

        warna = pref.getInt("background",R.color.default_background);

        b.setText(getwarna(warna));

        //final int[] warna = {pref.getInt("background", R.color.default_background)};


//        mColor = ContextCompat.getColor(this, R.color.default_background);
//        mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
//
//       preferencesEditor  = mPreferences.edit();
//        a.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
//
//                builder.setTitle("Shape Color");
//
//                builder.setSingleChoiceItems(values, 0, new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int item) {
//
//                        switch(item)
//                        {
//                            case 0:
//                                b.setText("Red");
//                                warna[0] = R.color.colorRed;
////                                int red = ContextCompat.getColor(SettingActivity.this,R.color.colorRed);
////                                red = mPreferences.getInt(COLOR_KEY,red);
////                                preferencesEditor.putInt(COLOR_KEY,red);
////                                preferencesEditor.apply();
//                                break;
//                            case 1:
//                                b.setText("Blue");
//                                warna[1] = R.color.blue_background;
//                                break;
//                            case 2:
//                                b.setText("Green");
//                                warna[2] = R.color.green_background;
//                                break;
//                        }
//
//                        Intent pindah = new Intent(SettingActivity.this,MainActivity.class);
//                        pindah.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(pindah);
//                        preferencesEditor.putInt("background", warna[0]);
//                        preferencesEditor.commit();
//                        alertDialog1.dismiss();
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                       Toast.makeText(SettingActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
//                        mColor = ContextCompat.getColor(SettingActivity.this, R.color.default_background);
//                        // Clear preferences
//                        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
//                        preferencesEditor.clear();
//                        preferencesEditor.apply();
//
//                    }
//                });
//                alertDialog1 = builder.create();
//                alertDialog1.show();
//
//            }
//
//        });


    }

    public void bukadialog(View view) {
        alertDialog1.setTitle("Choose Color");
        View v = getLayoutInflater().inflate(R.layout.colordialog, null);
        alertDialog1.setView(v);

        //Menentukan radiobutton yang dipilih
        final RadioGroup rg = v.findViewById(R.id.rg);
        rg.check(getIntCOlor(warna));

        //Method ketika menekan OK
        alertDialog1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int check = rg.getCheckedRadioButtonId();
                switch (check){
                    case R.id.warnabiru:
                        warna = R.color.biru;
                        break;
                    case R.id.warnahijau:
                        warna = R.color.hijau;
                        break;
                    case R.id.warnamerah:
                        warna = R.color.merah;
                        break;
                    case R.id.warnaputih:
                        warna = R.color.default_background;
                        break;
                }

                //Mengatur SharedPreference dan mengubah text
                b.setText(getwarna(warna));
                preferencesEditor.putInt("background", warna);
                preferencesEditor.commit();
                Intent ha = new Intent(SettingActivity.this,MainActivity.class);
                startActivity(ha);
            }
        });

        //Method ketika menekan Cancel
        alertDialog1.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //Menampilkan dialog
        alertDialog1.create().show();
    }

    //Method untuk mendapatkan String warna
    public String getwarna(int i){
        if(i==R.color.hijau){
            return "Green";
        }else if(i==R.color.biru){
            return "Blue";
        }else if(i==R.color.merah){
            return "Red";
        }else{
            return "White";
        }
    }
    //Method untuk mendapatkan id warna
    public int getIntCOlor(int i){
        if(i==R.color.hijau){
            return R.id.warnahijau;
        }else if(i==R.color.biru){
            return R.id.warnabiru;
        }else if(i==R.color.merah){
            return R.id.warnamerah;
        }else{
            return R.id.warnaputih;
        }
    }
}
