package onroute.com.onroute;


import javax.inject.Inject;


public class JourneyResource extends BaseResource {
    @Inject Websocket websocket;


    @Override
    public void onServerResponse(SessionModel session) {

    }


    public void beginJourney() {
        Request request = new Request("journey", "begin");
        websocket.send(request);
    }


    public void endJourney() {
        Request request = new Request("journey", "end");
        websocket.send(request);
    }
}
