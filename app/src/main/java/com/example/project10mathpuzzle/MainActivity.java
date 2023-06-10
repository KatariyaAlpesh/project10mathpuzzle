package com.example.project10mathpuzzle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity
{

    Button buttonContinue,buttonPuzzles,buttonBuyPro;

    ImageButton buttonShare,buttonEmail;

    ImageView AD;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

///////////    Permission For creat file in Storage {Temporary Storage}

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{ android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},1);


            buttonContinue = findViewById(R.id.buttonContinue);
            buttonPuzzles = findViewById(R.id.buttonPuzzles);
            buttonBuyPro = findViewById(R.id.buttonBuyPro);
            buttonShare = findViewById(R.id.buttonShare);
            buttonEmail = findViewById(R.id.buttonEmail);
            AD = findViewById(R.id.AD);


            buttonContinue.setOnClickListener(v -> {

                Intent intent = new Intent(MainActivity.this,MainActivity2_continue.class);
                startActivity(intent);

            });


            buttonPuzzles.setOnClickListener(v -> {

                Intent intent = new Intent(MainActivity.this,MainActivity2_puzzles.class);
                startActivity(intent);

            });

        }



    ////////////////////////////  For Sharing - File Creation in Phone Storage  ////////////////////////

        @Override
        public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            switch (requestCode) {

                case 1 : {

                    if ((grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) &&
                            (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED)) {

                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) +
                                                        "/demo1");

                        if (!file.exists()) {

                            if (!file.mkdir()) {

                                System.out.println("folder created");

                            } else {

                                System.out.println("folder not created");
                            }

                        }
                    } else {

                        System.out.println("permission denied");
                        Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
        }



    /////////////////////////  Option Menu - icon visibility ( three-dot on app bar )  /////////////////



        @SuppressLint("RestrictedApi")
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu_items,menu);

            if (menu instanceof MenuBuilder) {

                MenuBuilder m = (MenuBuilder) menu;
                m.setOptionalIconsVisible(true);

            }

            return super.onCreateOptionsMenu(menu);
        }

    /////////////////////////////////// Option Menu - Selection   //////////////////////////////////////

        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            if (item.getItemId() == R.id.menu_download) {

                Toast.makeText(this, "Download", Toast.LENGTH_SHORT).show();

            }

            if (item.getTitle().equals("Share")) {

                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();

            }

            if (item.getItemId() == R.id.menu_exit) {

                Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();

            }

            return super.onOptionsItemSelected(item);
        }

}