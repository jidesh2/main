package onroute.com.onroute;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by daniel on 24.3.16.
 */
public class DrawerScreenActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mNames;
    private TypedArray mIcons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_drawer);

        mNames = getResources().getStringArray(R.array.navDrawerItems);
        mIcons = getResources().obtainTypedArray(R.array.navDrawerDrawables);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new MyAdapter(this, R.layout.nav_drawer_row_layout, mNames, mIcons));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
               // Toast.makeText(DrawerScreenActivity.this, mNames[position] + " was clicked"+position, Toast.LENGTH_SHORT).show();
            }
        });

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

            TextView txt = (TextView) convertView.findViewById(R.id.nav_drawer_text_view);
            txt.setText(mNames[position]);

            ImageView image = (ImageView) convertView.findViewById(R.id.nav_drawer_image_view);
            image.setImageResource(mIcons.getResourceId(position, 0));

            return convertView;
        }
    }
}
