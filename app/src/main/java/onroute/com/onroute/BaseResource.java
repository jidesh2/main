package onroute.com.onroute;


import com.google.gson.Gson;


import javax.inject.Inject;


public abstract class BaseResource implements ResponseManager.Listener {
    @Inject Gson gson;
    @Inject ResponseManager responseManager;


    public BaseResource() {
        App.getApplicationGraph().inject(this);
        responseManager.addListener(this, this.getClass().getName());
    }
}