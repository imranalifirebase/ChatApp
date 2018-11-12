package com.example.aaa.chatapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;


import com.example.aaa.chatapp.activites.LoginActivity;
import com.example.aaa.chatapp.activites.NewMessageActivity;
import com.example.aaa.chatapp.activites.ProfileActivity;
import com.example.aaa.chatapp.adapter.ChatAdapter;
import com.example.aaa.chatapp.fragments.ChatFragment;
import com.example.aaa.chatapp.fragments.ContactFragment;
import com.wafflecopter.multicontactpicker.ContactResult;
import com.wafflecopter.multicontactpicker.LimitColumn;
import com.wafflecopter.multicontactpicker.MultiContactPicker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DrawerLayout mDrawerLayout;

    private static final int CONTACT_PICKER_REQUEST = 991;
    private static final String TAG = "HTAG";
    private ArrayList<ContactResult> results = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ChatAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_my_content_main);
        setSupportActionBar(toolbar);

//        String []mData = {
//                "I'm Coming, Where are U",
//                "I'm Coming, Where are U",
//                "I'm Coming, Where are U",
//                "I'm Coming, Where are U",
//                "I'm Coming, Where are U",
//                "I'm Coming, Where are U",
//                "I'm Coming, Where are U",
//                "I'm Coming, Where are U",
//                "I'm Coming, Where are U",
//                "I'm Coming, Where are U",
//                "I'm Coming, Where are U",
//                "I'm Coming, Where are U",
//        };

        String[] mData = {"User 1", "User 2", "User 3", "User 4", "User 5", "User 6", "User 7", "User 8", "User 9",};

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_my_content_main);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ChatAdapter(mData, this);
        mRecyclerView.setAdapter(mAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, NewMessageActivity.class));
            }
        });

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.nav_profile) {
                            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        } else if (id == R.id.nav_contact) {
                            pickContactMethod();
                        } else if (id == R.id.nav_media) {
                            pickImageFile();
                        } else if (id == R.id.nav_logout) {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }

                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;

                    }
                });

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
//        ChatFragment fragment = new ChatFragment();
//        fragmentTransaction.add(R.id.frameLayout_main_activity, fragment);
//        fragmentTransaction.commit();

//        bottomNavigationView = findViewById(R.id.bottom_navigation_activity_main);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.action_favorites:{
////                        Toast.makeText(MainActivity.this, "FIRST", Toast.LENGTH_SHORT).show();
////                        ChatFragment fragment = new ChatFragment();
////                        fragmentManager = getSupportFragmentManager();
////                        fragmentTransaction = fragmentManager.beginTransaction();
////                        fragmentTransaction.replace(R.id.frameLayout_main_activity, fragment);
////                        fragmentTransaction.addToBackStack(null);
////                        fragmentTransaction.commit();
//
//                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//
//                        break;
//                    }
//                    case R.id.action_schedules:{
////                        fragmentManager = getSupportFragmentManager();
////                        fragmentTransaction = fragmentManager.beginTransaction();
////                        ContactFragment fragment = new ContactFragment();
////                        fragmentTransaction.replace(R.id.frameLayout_main_activity, fragment);
////                        fragmentTransaction.addToBackStack(null);
////                        fragmentTransaction.commit();
//
//                        getMyContact();
//
//                        break;
//                    }
//                    case R.id.action_music:{
////                        Toast.makeText(MainActivity.this, "Thired", Toast.LENGTH_SHORT).show();
////                        break;
//                        mDrawerLayout.openDrawer(GravityCompat.END);
//                        return true;
//                    }
//                }
//                return true;
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            moveTaskToBack(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        return true;
    }




    ////////////////////////////////

    private void getMyContact() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            new MultiContactPicker.Builder(MainActivity.this) //Activity/fragment context
                    .theme(R.style.MyCustomPickerTheme) //Optional - default: MultiContactPicker.Azure
                    .hideScrollbar(false) //Optional - default: false
                    .showTrack(true) //Optional - default: true
                    .searchIconColor(Color.WHITE) //Optional - default: White
                    .setChoiceMode(MultiContactPicker.CHOICE_MODE_MULTIPLE) //Optional - default: CHOICE_MODE_MULTIPLE
                    .handleColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                    .bubbleColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                    .bubbleTextColor(Color.WHITE) //Optional - default: White
                    .setTitleText("Select Contacts") //Optional - only use if required
                    .setSelectedContacts(results) //Optional - will pre-select contacts of your choice. String... or List<ContactResult>
                    .setLoadingType(MultiContactPicker.LOAD_ASYNC) //Optional - default LOAD_ASYNC (wait till all loaded vs stream results)
                    .limitToColumn(LimitColumn.NONE) //Optional - default NONE (Include phone + email, limiting to one can improve loading time)
                    .setActivityAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                            android.R.anim.fade_in,
                            android.R.anim.fade_out) //Optional - default: No animation overrides
                    .showPickerForResult(CONTACT_PICKER_REQUEST);
        } else {
            Toast.makeText(MainActivity.this, "Go into settings and enable the contacts permission.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTACT_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                results.addAll(MultiContactPicker.obtainResult(data));
                if (results.size() > 0) {
                    Log.d(TAG, results.get(0).getDisplayName());
                }
            } else if (resultCode == RESULT_CANCELED) {
                System.out.println("User closed the picker without selecting items.");
            }
        }

        else if (requestCode == 89){

                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        // TODO Fetch other Contact details as you want to use


                    }
                }
        }
    }


    /*@Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_profile) {
            Log.d(TAG, "onNavigationItemSelected: PROFILE");
            Toast.makeText(this, "PROFILE", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_contact) {
            Log.d(TAG, "onNavigationItemSelected: Contact");
        } else if (id == R.id.nav_media) {
            Log.d(TAG, "onNavigationItemSelected: MEDIA");

        } else if (id == R.id.nav_logout) {
            Log.d(TAG, "onNavigationItemSelected: LOGOUT");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
    ///////////////////////////////////////////////////////////////


    private void pickContactMethod() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, 89);
    }

    public void pickImageFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 55);
    }


}
