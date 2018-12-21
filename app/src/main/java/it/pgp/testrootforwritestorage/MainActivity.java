package it.pgp.testrootforwritestorage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends Activity {

    public void writeStandard(View unused) {
        // within app
        try (OutputStream o = new FileOutputStream(new File("/sdcard/file.txt"))){
            o.write("abcdefghi".getBytes());
            Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Errors, check logcat", Toast.LENGTH_SHORT).show();
        }
    }

    public void writeInExternalShell(boolean asRoot) {
        // with external shell
        RootHandler.executeCommandSimple("echo", Environment.getExternalStorageDirectory(),asRoot,"ciao > /sdcard/file.txt");
        Toast.makeText(MainActivity.this, "Done, check logcat for any errors", Toast.LENGTH_SHORT).show();
    }

    public void writeInExternalShellAsRoot(View unused) {
        writeInExternalShell(true);
    }

    public void writeInExternalShellAsStdUser(View unused) {
        writeInExternalShell(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
