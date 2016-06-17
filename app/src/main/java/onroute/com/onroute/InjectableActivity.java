package onroute.com.onroute;


import android.os.Bundle;



import javax.inject.Inject;

import dagger.ObjectGraph;


public abstract class InjectableActivity extends BaseActivity implements Injectable {
    ObjectGraph activityGraph;
    boolean isDestroyed;

    protected @Inject Websocket websocket;

    protected @Inject PassengerResource passengerResource;
    protected @Inject MediaResource mediaResource;
    protected @Inject JourneyResource journeyResource;
    protected @Inject TabletResource tabletResource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGraph = ((App) getApplication()).getApplicationGraph();
        activityGraph.inject(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
        activityGraph = null;
    }


    @Override
    public void onBackPressed() {
        // TODO http://b.android.com/176265
        try {
            super.onBackPressed();
        } catch (IllegalStateException e) {
            supportFinishAfterTransition();
        }
    }


    @Override
    public void inject(Object object) {
        activityGraph.inject(object);
    }


    public boolean isActivityDestroyed() {
        return isDestroyed;
    }
}
