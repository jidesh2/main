package onroute.com.onroute;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


public abstract class DrawerActivity extends InjectableActivity {
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    View drawer;
    Class<? extends Activity> pendingNavigation;
    Bundle pendingNavigationExtras;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mNames;
    private TypedArray mIcons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_drawer);
        mNames = getResources().getStringArray(R.array.navDrawerItems);
        mIcons = getResources().obtainTypedArray(R.array.navDrawerDrawables);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new MyAdapter(this, R.layout.nav_drawer_row_layout, mNames, mIcons));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), mNames[position] + " was clicked"+position, Toast.LENGTH_SHORT).show();
                if(position==1)
                {
                    Intent in =new Intent(getApplicationContext(),Main23Activity.class);
                    startActivity(in);


                }
                if(position==0)
                {
                    Intent in =new Intent(getApplicationContext(),MusicCoreScreenActivity.class);
                    startActivity(in);


                }
                if(position==3)
                {
                    Intent in =new Intent(getApplicationContext(),Newsactivity.class);
                    startActivity(in);


                }
                if(position==4)
                {
                    Intent in =new Intent(getApplicationContext(),Lifestylectivity.class);
                    startActivity(in);


                }

            }
        });
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawer = findViewById(R.id.drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (drawerView.equals(drawer) && pendingNavigation != null) {
                    final Intent intent = new Intent(DrawerActivity.this, pendingNavigation);
                    if (pendingNavigationExtras != null) {
                        intent.putExtras(pendingNavigationExtras);
                        pendingNavigationExtras = null;
                    }
                    // TODO M bug https://code.google.com/p/android/issues/detail?id=193822
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    pendingNavigation = null;
                }
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawer)) closeDrawers();
        else super.onBackPressed();
    }


    @Override
    public void setContentView(int layoutResID) {
        ViewGroup drawerLayout = (ViewGroup) findViewById(R.id.drawer_layout);
        View view = getLayoutInflater().inflate(layoutResID, drawerLayout, false);
        drawerLayout.addView(view, 0);
    }


    public void navigate(Class<? extends Activity> activityClass, @Nullable Bundle extras) {
        pendingNavigation = !getClass().equals(activityClass) ? activityClass : null;
        pendingNavigationExtras = extras;
        closeDrawers();
    }


    void closeDrawers() {
        drawerLayout.closeDrawers();
    }
    public class MyAdapter extends ArrayAdapter<String> {

        TypedArray mIcons;

        public MyAdapter(Context context, int resource, String[] names, TypedArray icons) {
            super(context, resource, names);

            mIcons = icons;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.nav_drawer_row_layout, null);
            }

            android.widget.TextView txt = (android.widget.TextView) convertView.findViewById(R.id.nav_drawer_text_view);
            txt.setText(mNames[position]);

            ImageView image = (ImageView) convertView.findViewById(R.id.nav_drawer_image_view);
            image.setImageResource(mIcons.getResourceId(position, 0));

            return convertView;
        }
    }
}
