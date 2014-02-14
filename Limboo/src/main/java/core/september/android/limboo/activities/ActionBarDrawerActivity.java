package core.september.android.limboo.activities;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.ArrayAdapter;

import core.september.android.limboo.R;
import core.september.android.limboo.abs.BaseActivity;
import core.september.android.limboo.views.DrawingFragment;
import core.september.android.limboo.views.adapters.DrawerActivityMenuAdapter;



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
