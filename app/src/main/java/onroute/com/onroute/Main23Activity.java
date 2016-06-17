package onroute.com.onroute;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class Main23Activity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);

    }
    public void FragmentOneClick(View view) {
        Fragment myfragment;
        myfragment = new MyFragment();

        FragmentManager fm = getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, myfragment);
        fragmentTransaction.commit();


    }
    public void FragmentFourClick(View view) {
        Fragment mysecondfragment;
        mysecondfragment = new Mysecondfragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, mysecondfragment);
        fragmentTransaction.commit();

    }
    public void FragmentTwoClick(View view) {
        Fragment mysecondfragment;
        mysecondfragment = new Mythirdfragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, mysecondfragment);
        fragmentTransaction.commit();

    }
    public void FragmentThreeClick(View view) {
        Fragment mysecondfragment;
        mysecondfragment = new Myfourthfragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, mysecondfragment);
        fragmentTransaction.commit();

    }

}


