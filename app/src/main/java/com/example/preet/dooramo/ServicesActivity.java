package com.example.preet.dooramo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * purpose: Home page for resident to select the type of request to be raised
 * author: Pahulpreet Singh and team
 * date: Nov 2, 2018
 * ver: 1
 *
 */
public class ServicesActivity extends AppCompatActivity {
    GridView gridViewSA;
    int[] images;
    String[] imageNames;
    @Override
    //serviceActivity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        initComponents();

        gridViewSA.setAdapter(new Adapter(ServicesActivity.this));
        gridViewSA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(ServicesActivity.this, RequestActivity.class)
                        .putExtra("serviceName", imageNames[position]));
            }
        });

    }

    //inner class for gridView adapter
    class Adapter extends BaseAdapter {

        Context context;
        public Adapter(Context _context) {
            context = _context;
        }
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.grid_view_sa_inflator, null);

            ImageView imageView = v.findViewById(R.id.serviceIV);
            TextView textView = v.findViewById(R.id.serviceTV);

            imageView.setImageResource(images[position]);
            textView.setText(imageNames[position]);

            return v;
        }
    }

    //initialize the components
    private void initComponents() {
        gridViewSA = findViewById(R.id.gridViewSA);
        images = new int[]{R.drawable.carpenter, R.drawable.electrician, R.drawable.plumber};
        imageNames = new String[]{"Carpenter", "Electrician", "Plumber"};
    }

    //resident_menu option in menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.resident_menu, menu);
        return true;
    }

    //click listener for menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logoutMenu) {
            //resident_menu code goes here
            SharedPreferences sharedPref = ServicesActivity.this.getSharedPreferences("ForLogin",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear();
            editor.commit();
            editor.apply();
            Intent intent = new Intent(ServicesActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.myJobsMenu) {
            startActivity(new Intent(ServicesActivity.this, ResidentMyJobs.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
