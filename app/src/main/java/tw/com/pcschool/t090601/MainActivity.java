package tw.com.pcschool.t090601;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import android.support.v4.app.ActivityCompat;
import static android.Manifest.permission.*;
import static android.Manifest.*;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // 無權限，向使用者請求
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{WRITE_EXTERNAL_STORAGE,
                            READ_EXTERNAL_STORAGE},
                    REQUEST_EXTERNAL_STORAGE
            );
        } else {
            writeFile();
        }



    }

    private void writeFile()
    {
        File[] f = new File[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            f = getExternalFilesDirs(null);
            for (File ff : f) {
                Log.d("FILE", ff.toString());
            }

        }
        File f2 = Environment.getExternalStorageDirectory();
        Log.d("FILE", f2.toString());

        File path = getFilesDir();
        // File f = new File(path.toString(), "mydata.txt");
        // String fName = f.toString();
        String fName = f2.toString() + "/" + "mydata.txt";
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(fName);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);  // 寫入資料
            osw.write("She sell sea shells on the sea shore .");
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            writeFile();
        }
        else
        {

        }
    }
}
