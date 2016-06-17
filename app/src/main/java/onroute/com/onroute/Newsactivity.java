package onroute.com.onroute;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.google.android.youtube.player.YouTubeBaseActivity;

public class Newsactivity extends YouTubeBaseActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

    }
    public void FragmentOneClick(View view) {
        Fragment myfragment;
        myfragment = new MynewsaajtakFragment();

        FragmentManager fm = getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, myfragment);
        fragmentTransaction.commit();


    }
    public void FragmentFourClick(View view) {
        Fragment mysecondfragment;
        mysecondfragment = new MynewsnmtvFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, mysecondfragment);
        fragmentTransaction.commit();

    }
    public void FragmentTwoClick(View view) {
        Fragment mysecondfragment;
        mysecondfragment = new MynewsbbcFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, mysecondfragment);
        fragmentTransaction.commit();

    }
    public void FragmentThreeClick(View view) {
        Fragment mysecondfragment;
        mysecondfragment = new MynewsddFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, mysecondfragment);
        fragmentTransaction.commit();

    }

}


