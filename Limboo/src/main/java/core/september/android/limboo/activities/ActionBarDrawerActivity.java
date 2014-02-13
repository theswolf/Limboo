package core.september.android.limboo.activities;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import core.september.android.limboo.R;
import core.september.android.limboo.abs.BaseActivity;
import core.september.android.limboo.views.DrawingFragment;
import core.september.android.limboo.views.adapters.DrawerActivityMenuAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class ActionBarDrawerActivity extends BaseActivity {

    private DrawingFragment fragment;

    protected Fragment getFragment() {
        return fragment;
    }

    @Override
    protected ArrayAdapter<String> getDrawerListAdapter() {
        if(mAdapter == null) {
            mAdapter = new DrawerActivityMenuAdapter(this);
        }
        return mAdapter;
    }

    @Override
    protected int getMenuInflate() {
        return R.menu.prompt_drawing;
    }

    @Override
    protected void onItemSelected(int position) {
        if(fragment == null) {
            fragment = new DrawingFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }

        ((DrawingFragment)fragment).setColor(DrawerActivityMenuAdapter.parse(DrawerActivityMenuAdapter.colors[position]));

    }

    @Override
    protected void setItemTitle(int position) {

    }
}
