package it.pgp.testrootforwritestorage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button111);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RootHandler.executeCommandSimple("echo", Environment.getExternalStorageDirectory(),true,"ciao > file.txt");
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
