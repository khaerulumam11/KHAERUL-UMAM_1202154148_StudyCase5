package com.example.khaerulumam.khaerulumam_1202154148_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.khaerulumam.khaerulumam_1202154148_studycase5.adapter.TodoAdapter;
import com.example.khaerulumam.khaerulumam_1202154148_studycase5.database.DataHelper;
import com.example.khaerulumam.khaerulumam_1202154148_studycase5.model.Data;
import com.example.khaerulumam.khaerulumam_1202154148_studycase5.utils.SwipeUtilDelete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


//    ListView listView;
//    AlertDialog.Builder dialog;
//    List<Data> itemList = new ArrayList<>();
//    Adapter adapter;
//    DataHelper SQLite = new DataHelper(this);

    private RecyclerView recyclerView;
    private SQLiteDatabase tampil;
    private DataHelper dataHelper;
    private List<Data> userList;
    TodoAdapter userAdapter;
    protected Cursor cursor;
    protected RecyclerView.LayoutManager mLayoutManager;


    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_ADDRESS = "deskripsi";
    public static final String TAG_JUMLAH = "jumlah";

    SharedPreferences.Editor editor;

    SharedPreferences app_preferences;


    // Name of shared preferences file
    private static final String mSharedPrefFile = "com.example.android.hellosharedprefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // listView = (ListView) findViewById(R.id.list_view);

        dataHelper  =  new DataHelper(getApplicationContext());
        tampil      = dataHelper.getReadableDatabase();

        recyclerView                = (RecyclerView) findViewById(R.id.list_view);
        userList                    = new ArrayList<>();


        mLayoutManager              = new LinearLayoutManager(this);



        //Retreive SharedPreference

        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("pref", 0);

        int warna = pref.getInt("background", R.color.default_background);

        userAdapter                 = new TodoAdapter(this,userList,warna);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(userAdapter);

//        app_preferences = getSharedPreferences(mSharedPrefFile,MODE_PRIVATE);
//        int mColor = ContextCompat.getColor(this, R.color.default_background);
//        int warna = app_preferences.getInt("color",0);
//        int ubah = warna;
//
//        if (ubah == 0){
//            recyclerView.setBackgroundColor(warna);
//        }




//        SQLite = new DataHelper(getApplicationContext());
//
//        adapter = new Adapter(MainActivity.this, itemList);
//        listView.setAdapter(adapter);
//
//        // long press listview to show edit and delete
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            @Override
//            public boolean onItemLongClick(final AdapterView<?> parent, View view,
//                                           final int position, long id) {
//                // TODO Auto-generated method stub
//                final String idx = itemList.get(position).getId();
//                final String name = itemList.get(position).getName();
//                final String deskripsi = itemList.get(position).getDeskripsi();
//                final String jumlah = itemList.get(position).getJumlah();
//
//                final CharSequence[] dialogitem = {"Edit", "Delete"};
//                dialog = new AlertDialog.Builder(MainActivity.this);
//                dialog.setCancelable(true);
//                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//                        switch (which) {
//                            case 0:
//                                Intent intent = new Intent(MainActivity.this, Input.class);
//                                intent.putExtra(TAG_ID, idx);
//                                intent.putExtra(TAG_NAME, name);
//                                intent.putExtra(TAG_ADDRESS, deskripsi);
//                                intent.putExtra(TAG_JUMLAH, jumlah);
//                                startActivity(intent);
//                                break;
//                            case 1:
//                                SQLite.delete(Integer.parseInt(idx));
//                                itemList.clear();
//                                dataUser();
//                                break;
//                        }
//                    }
//                }).show();
//                return false;
//            }
//        });

        dataUser();
        setSwipeForRecyclerView();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,Input.class);
                startActivity(a);
            }
        });
    }

    private void setSwipeForRecyclerView() {
        SwipeUtilDelete swipeHelper = new SwipeUtilDelete(0, ItemTouchHelper.LEFT, this) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                TodoAdapter adapter = (TodoAdapter) recyclerView.getAdapter();

                dataHelper.delete(Integer.parseInt(userList.get(position).getId()));

                userList.clear();
                dataUser();
                userAdapter.notifyDataSetChanged();
            }
        };

        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(swipeHelper);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        swipeHelper.setLeftcolorCode(ContextCompat.getColor(this, R.color.colorRed));
    }

    private void dataUser() {

//        cursor     = tampil.rawQuery("SELECT * FROM crud", null);
//
//        for (int i = 0; i < cursor.getCount(); i++) {
//            cursor.moveToNext();
//            Data user = new Data(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
//            userList.add(user);
//        }

      ArrayList<HashMap<String, String>> row = dataHelper.getAllData();
        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String poster = row.get(i).get(TAG_NAME);
            String title = row.get(i).get(TAG_ADDRESS);
            String jumlah = row.get(i).get(TAG_JUMLAH);

            Data data = new Data();

            data.setId(id);
            data.setName(poster);
            data.setDeskripsi(title);
            data.setJumlah(jumlah);

            userList.add(data);
        }

        userAdapter.notifyDataSetChanged();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        userList.clear();
//        dataUser();
//   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           Intent h = new Intent(MainActivity.this,SettingActivity.class);
           startActivity(h);
        }

        return super.onOptionsItemSelected(item);
    }
}
