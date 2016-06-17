package onroute.com.onroute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class Splash extends FragmentActivity {
ViewPager pager;
    private AutoScrollViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

               // pager= (ViewPager) findViewById(R.id.viewpager);

        viewPager = (AutoScrollViewPager)findViewById(R.id.view_pager);
PagerAdapter padapter=new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(padapter);
        viewPager.setInterval(4000);

        viewPager.startAutoScroll();



    }
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    }

