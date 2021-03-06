package onroute.com.onroute;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


@EActivity(R.layout.activity_dashboard)
public class DashboardActivity extends BaseDashboardActivity implements OnGridItemClickListener,
        BottomListAdapter.OnBottomItemClickListener {
    private ScrollableGrid mScrollableGrid;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    View drawer;
    Class<? extends Activity> pendingNavigation;
    Bundle pendingNavigationExtras;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mNames;
    private TypedArray mIcons;

    private static final String COLUMN_ONE_ID = "column one id";
    private static final String COLUMN_TWO_ID = "column two id";
    private static final String COLUMN_THREE_ID = "column three id";
    private static final String COLUMN_FOUR_ID = "column four id";
    private static final String COLUMN_FIVE_ID = "column five id";

    //    @ViewById Toolbar toolbar;
    @ViewById CompoundView compoundView;
    RecyclerView recyclerView;

    MediaModel mediaModel;


    @AfterViews
    protected void afterViews() {
//        initializeToolbar(R.id.toolbar);

        assert compoundView != null;
        SharedPreferences sp = getSharedPreferences("ETApref", Activity.MODE_PRIVATE);
        String marcus = sp.getString("ETA", null);
        TextView tx = (TextView) findViewById(R.id.textView3);
        if(marcus!=null) {

            assert tx != null;
            tx.setText(" "+marcus);
        }
        else
        {
            assert tx != null;
            tx.setText(" N.A");
        }
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ina=new Intent(getBaseContext(),PlacesAutoCompleteActivity.class);
                startActivity(ina);
                finish();
            }
        });
        drawer = findViewById(R.id.drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ImageButton im=(ImageButton)findViewById(R.id.open_drawer);
        assert im != null;
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
                drawerToggle = new ActionBarDrawerToggle( DashboardActivity.this, drawerLayout, R.string.open_drawer,
                        R.string.close_drawer) {
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        if (drawerView.equals(drawer) && pendingNavigation != null) {
                            final Intent intent = new Intent(DashboardActivity.this, pendingNavigation);
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
                mNames = getResources().getStringArray(R.array.navDrawerItems);
                mIcons = getResources().obtainTypedArray(R.array.navDrawerDrawables);


                mDrawerList = (ListView) findViewById(R.id.left_drawer);

                mDrawerList.setAdapter(new MyAdapter(getApplicationContext(), R.layout.nav_drawer_row_layout, mNames, mIcons));
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
                        // Toast.makeText(DrawerScreenActivity.this, mNames[position] + " was clicked"+position, Toast.LENGTH_SHORT).show();
                    }
                });
                drawerLayout.setDrawerListener(drawerToggle);
                drawerToggle.syncState();

                mDrawerList.setAdapter(new MyAdapter(getApplicationContext(), R.layout.nav_drawer_row_layout, mNames, mIcons));
            }
        });
        // obtain a link to the ScrollableGrid
        mScrollableGrid = CompoundView.getScrollableGrid();

        // obtain a link to the RecyclerView
        recyclerView = CompoundView.getRecyclerView();

        // create an adapter instance and set it to the recycler view
        BottomListAdapter adapter = new BottomListAdapter(this, createTestObject());
        adapter.setOnBottomItemClickListener(this);
        recyclerView.setAdapter(adapter);

        if (mScrollableGrid != null) {
            // this is how you can add how many column you want
            mScrollableGrid.addNewColumn(COLUMN_ONE_ID, 2);
            mScrollableGrid.addNewColumn(COLUMN_TWO_ID, 3);
            mScrollableGrid.addNewColumn(COLUMN_THREE_ID, 2);
            mScrollableGrid.addNewColumn(COLUMN_FOUR_ID, 2);
            mScrollableGrid.addNewColumn(COLUMN_FIVE_ID, 2);

            // this is a listener which will be called when an item is clicked
            mScrollableGrid.setOnGridItemClickListener(this);

             /* this is how you can add items to any column you've added you can add a layout
             resource last parameter is layout_weight, you can specify 1 and then all items will be
             with same size the item with more then one will be bigger then the others
             */

            // TODO: remove the hardcoded data and work with the server

            // Everybody loves Raymond
            MediaModel raymondMedia = new MediaModel();
            raymondMedia.setTitle("Everybody loves raymond");
            raymondMedia.setDescription("The comical everyday life of a " +
                    "successful sports columnist and his dysfunctional family.");
            raymondMedia.setVideoPath("/sdcard/raymond-s1e1.avi");
            raymondMedia.setImagePath("file:///android_asset/images/raymond.jpg");

            // Mind your language
            MediaModel ninenineMedia = new MediaModel();
            ninenineMedia.setTitle("Mind your language");
            ninenineMedia.setDescription("Mind Your Language is a British comedy television series that premiered on ITV in 1977. Produced by London Weekend Television and directed by Stuart Allen.");
            ninenineMedia.setVideoPath("/sdcard/mind.mp4");
            ninenineMedia.setImagePath("file:///android_asset/images/mind.jpg");

            // Friends
            MediaModel friendsMedia = new MediaModel();
            friendsMedia.setTitle("Friends S1:E5");
            friendsMedia.setDescription("Eager to spend time with Rachel, Ross pretends his building's washroom is rat-infested so he can join her at the laundromat. Chandler points out this could be a 'date' and the first time she'll see his underwear so it shouldn't be dirty! Rachel, the spoiled 'laundry virgin' feels managing this domestic chore is a real step to independence, but despite Ross's good advice she leaves a red sock in the machine. The real accomplishment comes where she has to stand up as no-nonsense-New Yorker against a rude, aggressive woman who invents rules to pretend it's not Rachel turn to do her laundry. Joey realizes he regrets dumping foxy Angela when he learns she is dating Bob. He proposes a double-date, then needs a girl stat. Monica agrees to be Joey's date but when she sees hunky Bob and realizes he's not Angela's brother she starts to enjoy the evening. Chandler drinks too much espresso while desperately trying to break up with neurotic Janice.");
            friendsMedia.setVideoPath("/sdcard/friends.mp4");
            friendsMedia.setImagePath("file:///android_asset/images/friends.jpg");

            // Wizards of Waverly place
            MediaModel wizardsMedia = new MediaModel();
            wizardsMedia.setTitle("Wizards of Waverly place");
            wizardsMedia.setDescription("The Russo family own a restaurant and live in New York. However, the father is a wizard while the children are in training. The child who masters the powers shall be given the family wand.\n");
            wizardsMedia.setVideoPath("/sdcard/wizards.mp4");
            wizardsMedia.setImagePath("file:///android_asset/images/wizards.jpg");

            // Mad Men
            MediaModel madMenMedia = new MediaModel();
            madMenMedia.setTitle("Mad Men");
            madMenMedia.setDescription("Donald Draper, the Creative Director at the Sterling Cooper ad agency in New York, tries to maintain a balance between his exceptional professional life and wavering personal life in the 1960s.");
            madMenMedia.setVideoPath("/sdcard/mad.mp4");
            madMenMedia.setImagePath("file:///android_asset/images/mad.jpg");

            // Key & Peele
            MediaModel keypeeleMedia = new MediaModel();
            keypeeleMedia.setTitle("Key & Peele");
            keypeeleMedia.setDescription("Keegan-Michael Key and Jordan Peele, two comics, use sketches and live segments to perform their jokes.");
            keypeeleMedia.setVideoPath("/sdcard/key.mp4");
            keypeeleMedia.setImagePath("file:///android_asset/images/key.jpg");

            // Maps
            DashboardTileModel googleMapsTile = new DashboardTileModel();
            googleMapsTile.setLocalBackgroundImagePath("file:///android_asset/images/maps.png");
            googleMapsTile.setTitle("Route & Maps");
            googleMapsTile.setDescription("Set your destination and see traffic details");
            googleMapsTile.setOnClickListener(new DashboardTileModel.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
                    startActivity(launchIntent);
                }
            });


            // Food - top places to eat
            DashboardTileModel foodTile = new DashboardTileModel();
            foodTile.setLocalBackgroundImagePath("file:///android_asset/images/food.jpg");
            foodTile.setTitle("Top Places to eat");
            foodTile.setDescription("See our best picks for the month. Explore Mumbai and the wide variety of restaurants");
            foodTile.setOnClickListener(new DashboardTileModel.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://www.zomato.com/mumbai/top-restaurants"));
                    startActivity(i);
                }
            });

            // News - top places to eat
            DashboardTileModel newsTile = new DashboardTileModel();
            newsTile.setLocalBackgroundImagePath("file:///android_asset/images/news.jpg");
            newsTile.setTitle("Watch NDTV live");
            newsTile.setDescription("Get the latest news from NDTV");
            newsTile.setOnClickListener(new DashboardTileModel.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://www.ndtv.com/video/live/channel/ndtv24x7"));
                    startActivity(i);
                }
            });

            mScrollableGrid.addViewToColumn(COLUMN_ONE_ID, "map item",
                    new ImageGridItem(this, googleMapsTile), 1);
            mScrollableGrid.addViewToColumn(COLUMN_ONE_ID, "map item two",
                    new ImageGridItem(this, friendsMedia.getDashboardTile()), 1);

            mScrollableGrid.addViewToColumn(COLUMN_TWO_ID, "map item",
                    new ImageGridItem(this, foodTile), 1);
            mScrollableGrid.addViewToColumn(COLUMN_TWO_ID, "map item three",
                    new ImageGridItem(this, newsTile), 1);
            mScrollableGrid.addViewToColumn(COLUMN_TWO_ID, "map item two",
                    new ImageGridItem(this, madMenMedia.getDashboardTile()), 1);

            // or you can add a view to the column
            mScrollableGrid.addViewToColumn(COLUMN_THREE_ID, "image item",
                    new ImageGridItem(this, raymondMedia.getDashboardTile()), 1);

            /*// Create a new buttons layout
            ButtonsGridItem buttonsGridItem = new ButtonsGridItem(this);

            // Customize left button
            buttonsGridItem.setLeftButtonImage(R.drawable.ic_favorite_white_24dp);
            buttonsGridItem.setLeftButtonTitle("Explore Movies");
            buttonsGridItem.setLeftButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DashboardActivity.this, "On left button click", Toast.LENGTH_SHORT).show();
                }
            });

            // customize right button
            buttonsGridItem.setRightButtonImage(R.drawable.ic_favorite_white_24dp);
            buttonsGridItem.setRightButtonTitle("Explore Movies");
            buttonsGridItem.setRightButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DashboardActivity.this, "On right button click", Toast.LENGTH_SHORT).show();
                }
            });*/

            mScrollableGrid.addViewToColumn(COLUMN_THREE_ID, "button item",
                    new ImageGridItem(this, ninenineMedia.getDashboardTile()), 1);
//            mScrollableGrid.addViewToColumn(COLUMN_TWO_ID, "image item two",
//                    new ImageGridItem(this, balikaMedia), 1);

            mScrollableGrid.addViewToColumn(COLUMN_FOUR_ID, "image item three",
                    new ImageGridItem(this, wizardsMedia.getDashboardTile()), 1);
            mScrollableGrid.addViewToColumn(COLUMN_FOUR_ID, "image item four",
                    new ImageGridItem(this, keypeeleMedia.getDashboardTile()), 1);


            String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
            TextView txr=(TextView)findViewById(R.id.date);
            assert txr != null;
            txr.setText(currentDateTimeString);
//            Button button = new Button(this);
//            button.setText("View more...");
//            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            mScrollableGrid.addViewToColumn(COLUMN_THREE_ID, "button item in column", button, 1);
        }
    }


    private ArrayList<DashboardTileModel> createTestObject() {
        ArrayList<DashboardTileModel> objects = new ArrayList<>();

        objects.add(getBottomTile("netflix.png", "English shows", "com.netflix.mediaclient"));
        objects.add(getBottomTile("colors.png", "Hindi shows", "com.viacom18.colorstv"));
        objects.add(getBottomTile("gaana.png", "Music", "com.gaana"));
        objects.add(getBottomTile("espn.png", "Sports update", "com.espn.score_center"));
        objects.add(getBottomTile("cnbc.png", "Business News", "com.zumobi.msnbc"));
        objects.add(getBottomTile("tvf.jpg", "TVF videos", "com.tvf.tvfplay"));

        return objects;
    }


    public DashboardTileModel getBottomTile(String imageFile, String name, final String pkgName) {
        DashboardTileModel dashboardTile = new DashboardTileModel();
        dashboardTile.setTitle(name);
        dashboardTile.setLocalBackgroundImagePath("file:///android_asset/images/bottom/" + imageFile);
        dashboardTile.setOnClickListener(new DashboardTileModel.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(pkgName);
                startActivity(launchIntent);
            }
        });
        return dashboardTile;
    }


    @Override
    public void onGridItemClick(View item, String itemId, String columnId) {
        if (item instanceof ImageGridItem) {
            ImageGridItem imageGridItem = (ImageGridItem) item;

            if (imageGridItem.getTileModel() != null &&
                    imageGridItem.getTileModel().getOnClickListener() != null) {
                imageGridItem.getTileModel().getOnClickListener().onClick(imageGridItem);
//                Intent intent = new Intent(this, AdcolonyVideoActivity_.class);
//                intent.putExtra(AdcolonyVideoActivity.EXTRA_MEDIA_MODEL,
//                        imageGridItem.getTileModel());
//                startActivity(intent);
            }
        }
    }


    @Override
    public void onBottomItemClickListener(DashboardTile object) {
        Toast.makeText(this, "On bottom item clicked", Toast.LENGTH_SHORT).show();
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
