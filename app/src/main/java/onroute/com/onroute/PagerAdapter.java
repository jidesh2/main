package onroute.com.onroute;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jidesh on 06-12-2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int arg0) {

        switch (arg0)
        {
            case 0:
                return new fragmentone();
            case 1:
                return new FragmentSimpleLoginButton();
            case 2:
                return new fragmentthree();
            case 3:
                 return new fragmentfour();
            case 4:
                return new fragmentfive();

        }
        return null;
    }
}
