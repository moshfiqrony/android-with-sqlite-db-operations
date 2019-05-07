package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    DbHelper mydb;

    Button btnInsert;
    TextView t;


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DbHelper(this);
        btnInsert = findViewById(R.id.button);
        t = findViewById(R.id.textView);


        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        addData();
    }

    public void addData(){
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MR", "onClick: ");
                SQLiteDatabase db = SQLiteDatabase.openDatabase("storage/emulated/0/d2_brd/metadata/forms.db", null, 0);
//                db.delete("forms", "jrFormId=?", new String[]{"rzm"});
//                Cursor res = db.rawQuery("select * from "+"forms where jrFormId='rzm'", null);
                db.delete("forms", null, null);

//                File file = new File("storage/emulated/0/d2_brd/forms");
//                boolean deleted = file.delete();
                File dir = new File("storage/emulated/0/d2_brd/forms");
                if (dir.isDirectory())
                {
                    String[] children = dir.list();
                    for (int i = 0; i < children.length; i++)
                    {
                        new File(dir, children[i]).delete();
                    }
                }



//                if (ress.getCount() != 0) {
//                    String jrFormId ="";
//                    String formFilePath="";
//                    while (ress.moveToNext()){
//                        Log.i("MR1", ress.getString(4));
//                        if(ress.getString(4).equals("rtf2")){
//                            jrFormId = ress.getString(4);
//                            formFilePath= ress.getString(9);
//
//
//                            Log.i("MRFormId", jrFormId);
//                            Log.i("MRFormFilePath", formFilePath);
//                            File file = new File(formFilePath);
//                            boolean deleted = file.delete();
//                            db.delete("forms", "jrFormId=?", new String[]{jrFormId});
//                        }
//                    }
//
//
//                }
            }
        });
    }
}
