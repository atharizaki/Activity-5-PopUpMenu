package com.example.popupmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    // Deklarasi variable dengan jenis data listview
    private ListView list;

    // memanggil class ListviewAdapter dan diinisiasi menjadi variabel adapter
    private  ListViewAdapter adapter;

    // Deklarasi array untuk menyimpan nama
    String [] listNama;

    // memanggil class cmassNama
    public  static ArrayList<ClassNama> classNamaArrayList = new ArrayList<ClassNama>();

    // Membuat objek bundle
    Bundle bundle = new Bundle();

    // Membuat objek Intent
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Menyimpan nama didalam array listnama
        listNama = new String[]{"Inayah", "Ilham", "Eris", "Fikri",
                "Maul", "Intan", "Vina", "Gita", "Vian", "Lutfi"};

        list = findViewById(R.id.listkontak);

        // membuat objek dari class ClassNama menjadi arraylist
        classNamaArrayList = new  ArrayList<>();

        // membaca seluruh data pada aray listNama
        for (int i = 0; i < listNama.length; i++)
        {
            // membuat objek class nama
            ClassNama classNama = new ClassNama(listNama[i]);

            // binds string ke array
            classNamaArrayList.add(classNama);
        }

        adapter = new ListViewAdapter(this);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Deklarasi variable nama yang berisi item yang diklik
                String nama = classNamaArrayList.get(position).getName();

                // Memasukkan value dari variable nama dengan kunci "a"
                // dan dimasukkan kedalam bundle
                bundle.putString("a", nama.trim());

                // membuat objek popup menu
                PopupMenu pm = new PopupMenu(getApplicationContext(), view);

                // membuat event untuk popup menu ketika dipilih
                pm.setOnMenuItemClickListener(Home_Activity.this);

                // menampilkan popup menu dari layout menu
                pm.inflate(R.menu.popup_menu);

                // menampulkan popup menu
                pm.show();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.mnview:
                intent = new Intent(getApplicationContext(), ActivityLihatData.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.mnedit:
                Toast.makeText(getApplicationContext(), "ini untuk edit kontak", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}