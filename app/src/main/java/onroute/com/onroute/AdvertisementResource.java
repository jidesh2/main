package onroute.com.onroute;


import javax.inject.Inject;


public class AdvertisementResource extends BaseResource {
    @Inject Websocket websocket;


    @Override
    public void onServerResponse(SessionModel session) {

    }


    public void getAd() {
        Request request = new Request("advertisement", "get");
        websocket.send(request);
    }


    public void onAdWatched(AdvertisementModel advertisement) {
        Request request = new Request("advertisement", "watched", advertisement);
        websocket.send(request);
    }
}
