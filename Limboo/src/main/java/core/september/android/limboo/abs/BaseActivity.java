package core.september.android.limboo.abs;

/**
 * Created by christian on 13/02/14.
 */

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import core.september.android.limboo.R;

public abstract class BaseActivity<T> extends ActionBarActivity {

    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected CharSequence mDrawerTitle;
    protected CharSequence mTitle;
    protected ListView mDrawerList;
    protected ArrayAdapter<T> mAdapter;

    ////PRIVATE
    private class ItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.septemberredtheme_ic_navigation_drawer, R.string.ic_drawer_open, R.string.ic_drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu();
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(getDrawerListAdapter());
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new ItemClickListener());

        if (savedInstanceState == null) {
            selectItem(0);
        }

    }

    protected abstract <T> ArrayAdapter<T> getDrawerListAdapter();
    protected abstract int getMenuInflate();
    protected abstract void onItemSelected(int position);
    protected abstract void setItemTitle(int position);
    protected abstract Fragment getFragment();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(getMenuInflate(),menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }



    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        //Fragment fragment = null;
        onItemSelected(position);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, getFragment())
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setItemTitle(position);
        mDrawerLayout.closeDrawer(mDrawerList);


//	        Fragment fragment = new PlanetFragment();
//	        Bundle args = new Bundle();
//	        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//	        fragment.setArguments(args);
//
//	        // Insert the fragment by replacing any existing fragment
//	        FragmentManager fragmentManager = getFragmentManager();
//	        fragmentManager.beginTransaction()
//	                       .replace(R.id.content_frame, fragment)
//	                       .commit();
//
//	        // Highlight the selected item, update the title, and close the drawer
//	        mDrawerList.setItemChecked(position, true);
//	        setTitle(mPlanetTitles[position]);
//	        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


}
