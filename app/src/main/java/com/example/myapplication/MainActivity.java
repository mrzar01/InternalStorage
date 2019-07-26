package com.example.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FILENAME = "namafile.txt";

    EditText input;
    Button add,tampil,edit,hapus;
    TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input=findViewById(R.id.input);
        add=findViewById(R.id.add);
        tampil=findViewById(R.id.tampil);
        edit=findViewById(R.id.ubah);
        hapus=findViewById(R.id.hapus);
        hasil=findViewById(R.id.hasil);

        add.setOnClickListener(this);
        tampil.setOnClickListener(this);
        edit.setOnClickListener(this);
        hapus.setOnClickListener(this);
    }

    void add(){
        String isiFile = input.getText().toString();
        File file= new File(getFilesDir(),FILENAME);

        if (isiFile.matches("")){
            Toast toast = Toast.makeText(MainActivity.this,"Masukkan data yang valid",Toast.LENGTH_SHORT);
            toast.show();
        }else{

            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file,true);
                outputStream.write(isiFile.getBytes());
                outputStream.flush();
                outputStream.close();
                Toast toast = Toast.makeText(MainActivity.this,"Data berhasil ditambahkan",Toast.LENGTH_SHORT);
                toast.show();

                InputMethodManager inputMgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                EditText editText = (EditText)findViewById(R.id.input);
                inputMgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);

            }catch (Exception e){
                e.printStackTrace();
            }
        }



    }


    void tampil(){
        File sdcard = getFilesDir();
        File file =  new File(sdcard, FILENAME);

        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
                Toast toast = Toast.makeText(MainActivity.this,"Data berhasil ditampilkan",Toast.LENGTH_SHORT);
                toast.show();

            }catch (IOException e){
                System.out.println("Error"+e.getMessage());

            }
            hasil.setText(text.toString());
        }else {
            Toast toast = Toast.makeText(MainActivity.this,"Belum ada data",Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    void ubah(){
        String ubah = input.getText().toString();
        File file = new File(getFilesDir(),FILENAME);

        if (ubah.matches("")){
            Toast toast = Toast.makeText(MainActivity.this,"Masukkan data yang valid",Toast.LENGTH_SHORT);
            toast.show();
        }else{
            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file,false);
                outputStream.write(ubah.getBytes());
                outputStream.flush();
                outputStream.close();
                Toast toast = Toast.makeText(MainActivity.this,"data berhasil diubah",Toast.LENGTH_SHORT);
                toast.show();

                InputMethodManager inputMgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                EditText editText = (EditText)findViewById(R.id.input);
                inputMgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);

            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    void hapus(){
        File file = new File(getFilesDir(),FILENAME);
        if (file.exists()){
            file.delete();
            hasil.setText("");
            Toast toast = Toast.makeText(MainActivity.this,"Data berhasil dihapus",Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    @Override
    public void onClick(View v) {
        jalankanPerintah(v.getId());
    }

    public void jalankanPerintah(int id){
        switch (id){
            case R.id.add:
                add();
                break;
            case R.id.tampil:
                tampil();
                break;
            case R.id.ubah:
                ubah();
                break;
            case R.id.hapus:
                hapus();
                break;
        }
    }

}
