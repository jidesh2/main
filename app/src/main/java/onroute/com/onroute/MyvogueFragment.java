package onroute.com.onroute;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyvogueFragment extends Fragment {
    private static RecyclerView recyclerView;
    private static RecyclerView recyclerView2;
    private static RecyclerView recyclerView3;
    private static RecyclerView recyclerView4;
    public  String[] TITLESS = new String[200];
    public  String[] URLS = new String[200];
    public static final String[] TITLES = new String[200];
    public static final String[] URL = new String[200];
    private static String url = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8&channelId=UCRXiA3h1no_PFkb1JCP0yMA&part=snippet,id&order=date&maxResults=50";
    private static String url3 = "https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId=UCRXiA3h1no_PFkb1JCP0yMA&key=AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8";
    ArrayList<String> TITLESS3 = new ArrayList<String>();
    public  String[] URLS3 = new String[200];
    ArrayList<String> TITLESS4 = new ArrayList<String>();
    public  String[] URLS4 = new String[500];
    ArrayList<String> TITLESS5 = new ArrayList<String>();
    public  String[] URLS5 = new String[200];
    public  String[] playlists = new String[200];
   // public  String[] playlists2 = new String[9];
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragmentvogue, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        //Set RecyclerView type according to intent value
        recyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        //
                        // V  view.findViewById(R.id.recycler_view);
                        TextView x=(TextView)view.findViewById(R.id.title);
                        String a=x.getText().toString();
                        Toast.makeText(getActivity(),a,Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(getActivity(),Youtube_activity.class);
                        in.putExtra("hi",a);
                        startActivity(in);
                    }
                })
        );
        recyclerView2 = (RecyclerView)view.findViewById(R.id.recycler2);
        recyclerView2.setHasFixedSize(true);
        //Set RecyclerView type according to intent value
        recyclerView2
                .setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        //
                        // V  view.findViewById(R.id.recycler_view);
                        TextView x=(TextView)view.findViewById(R.id.title);
                        String a=x.getText().toString();
                        Toast.makeText(getActivity(),a,Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(getActivity(),Youtube_activity.class);
                        in.putExtra("hi",a);
                        startActivity(in);
                    }
                })
        );
        recyclerView3 = (RecyclerView)view.findViewById(R.id.recycler3);
        recyclerView3.setHasFixedSize(true);
        //Set RecyclerView type according to intent value
        recyclerView3
                .setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView3.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        //
                        // V  view.findViewById(R.id.recycler_view);
                        TextView x=(TextView)view.findViewById(R.id.title);
                        String a=x.getText().toString();
                        Toast.makeText(getActivity(),a,Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(getActivity(),Youtube_activity.class);
                        in.putExtra("hi",a);
                        startActivity(in);
                    }
                })
        );
        recyclerView4 = (RecyclerView)view.findViewById(R.id.recycler4);
        recyclerView4.setHasFixedSize(true);
        //Set RecyclerView type according to intent value
        recyclerView4
                .setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView4.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        //
                        // V  view.findViewById(R.id.recycler_view);
                        TextView x=(TextView)view.findViewById(R.id.title);
                        String a=x.getText().toString();
                        Toast.makeText(getActivity(),a,Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(getActivity(),Youtube_activity.class);
                        in.putExtra("hi",a);
                        startActivity(in);
                    }
                })
        );


      //  initViews();
        new GetContacts().execute();
        new Getplaylists().execute();
        ImageButton im= (ImageButton) view.findViewById(R.id.ima);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=TITLES[1];
                Intent in=new Intent(getActivity(),Youtube_activity.class);
                in.putExtra("hi",a);
                Toast.makeText(getActivity(),a,Toast.LENGTH_SHORT).show();
                getActivity().startActivity(in);
                //getActivity().finish();
            }
        });
        ImageButton im2= (ImageButton) view.findViewById(R.id.imageButton6);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=TITLES[2];
                Intent in=new Intent(getActivity(),Youtube_activity.class);
                in.putExtra("hi",a);
                Toast.makeText(getActivity(),a,Toast.LENGTH_SHORT).show();
                getActivity().startActivity(in);
                //getActivity().finish();
            }
        });
return view;
    }
    private void populatRecyclerView() {
        ArrayList<ItemModel> arrayList = new ArrayList<>();
        for (int i = 0; i < TITLESS.length; i++) {
            arrayList.add(new ItemModel(TITLESS[i], URLS[i]));
        }
        RvAdapter adapter = new RvAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview
        adapter.notifyDataSetChanged();// Notify the adapter
    }
    private void populatRecyclerView2(){
        ArrayList<ItemModel> arrayList2 = new ArrayList<>();
        for (int i = 0; i <TITLESS3.size(); i++) {
            arrayList2.add(new ItemModel(TITLESS3.get(i),URLS3[i]));
        }
        RvAdapter adapter2 = new RvAdapter(getActivity(), arrayList2);
        recyclerView2.setAdapter(adapter2);// set adapter on recyclerview
        adapter2.notifyDataSetChanged();}// Notify the adapter
    private void populatRecyclerView3(){
        ArrayList<ItemModel> arrayList3 = new ArrayList<>();
        for (int i = 0; i <TITLESS4.size(); i++) {
            arrayList3.add(new ItemModel(TITLESS4.get(i),URLS4[i]));
        }
        RvAdapter adapter3 = new RvAdapter(getActivity(), arrayList3);
        recyclerView3.setAdapter(adapter3);// set adapter on recyclerview
        adapter3.notifyDataSetChanged();}
    // Notify the adapter
    private void populatRecyclerView4() {
        ArrayList<ItemModel> arrayList4 = new ArrayList<>();
        for (int i = 0; i < TITLESS5.size(); i++) {
            arrayList4.add(new ItemModel(TITLESS5.get(i), URLS5[i]));
        }
        RvAdapter adapter4 = new RvAdapter(getActivity(), arrayList4);
        recyclerView4.setAdapter(adapter4);// set adapter on recyclerview
        adapter4.notifyDataSetChanged();// Notify the adapter
    }



   /* private void initViews() {
       // getView().getSupportActionBar().setDisplayHomeAsUpEnabled(true);//Set Back Icon on Activity
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

    }*/
    private class Getplaylists extends AsyncTask<Void, Void, Void> {
        String thumb;
        //https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId="+playlist[i]+"&key=AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8"



        // ImageView im=(ImageView)findViewById(R.id.imageView);
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = null;
            try {
                jsonStr = sh.makeServiceCall(url3, ServiceHandler.GET);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(String.valueOf(jsonStr));
                    JSONArray con=jsonObj.getJSONArray("items");
                    Log.d("Hero",">"+con);
                    int length = con .length();
                    Log.d("pradsss",String.valueOf(length));
                    for(int i=0; i<50; i++) {
                        JSONObject jObj = con.getJSONObject(i);
                        String id = jObj.getString("id");
                        JSONObject snip = jObj.getJSONObject("snippet");
                        //   String idx=id.getString("videoId");
                        //   Log.d("prad", idx);
                        JSONObject jobj2 = snip.getJSONObject("thumbnails");
                        JSONObject jobj3= jobj2.getJSONObject("medium");
                        thumb= jobj3.getString("url");
                        // JSONObject jobj4 = snip.getJSONObject("resourceId");


                        //  String playlistid=jobj4.getString("videoId");
                        // a[i]=idx;
                        playlists[i]=id;
                        Log.d("chu",id);
                        // TITLES[i]=playlistid;
                        //  URL[i]=thumb;
                        // Log.d("pradsss",a[2]);
                    }
                    // Getting JSON Array node
                    // Log.d("chu",jobj2.toString());

                    // looping through All Contacts

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            new GetVideos().execute();
            new GetVideostwo().execute();
            new GetVideosthree().execute();
            new GetVideosfour().execute();
        }


    }
    private class GetVideostwo extends AsyncTask<Void, Void, Void> {
        String thumb;
        //https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId="+playlist[i]+"&key=AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8"
        // ImageView im=(ImageView)findViewById(R.id.imageView);
        private  String url1 = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=PLztAHXmlMZFRf1K4I7ut2ZvHMfrPW9md1&key=AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8";
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = null;
            try {
                jsonStr = sh.makeServiceCall(url1, ServiceHandler.GET);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(String.valueOf(jsonStr));
                    JSONArray con=jsonObj.getJSONArray("items");
                    Log.d("Hero",">"+con);
                    int length = con .length();
                    Log.d("pradsss",String.valueOf(length));
                    for(int i=0; i<50; i++) {
                        JSONObject jObj = con.getJSONObject(i);
                        String id = jObj.getString("id");
                        JSONObject snip = jObj.getJSONObject("snippet");
                        //   String idx=id.getString("videoId");
                        //   Log.d("prad", idx);
                        JSONObject jobj2 = snip.getJSONObject("thumbnails");
                        JSONObject jobj3= jobj2.getJSONObject("medium");
                        thumb= jobj3.getString("url");
                        JSONObject jobj4 = snip.getJSONObject("resourceId");


                        String playlistid=jobj4.getString("videoId");
                        // a[i]=idx;
                        // playlists[i]=id;
                        Log.d("chu",playlistid);
                        TITLESS3.add(playlistid);
                        URLS3[i]=thumb;
                        // Log.d("pradsss",a[2]);
                    }
                    // Getting JSON Array node
                    // Log.d("chu",jobj2.toString());

                    // looping through All Contacts

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog

            populatRecyclerView2();



        }


    }
    private class GetVideosthree extends AsyncTask<Void, Void, Void> {
        String thumb;
        //https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId="+playlist[i]+"&key=AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8"
        // ImageView im=(ImageView)findViewById(R.id.imageView);
        private  String url1 = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=PLztAHXmlMZFRkmTZ7VXb7_gALcLJfWVDf&key=AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8";
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = null;
            try {
                jsonStr = sh.makeServiceCall(url1, ServiceHandler.GET);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(String.valueOf(jsonStr));
                    JSONArray con=jsonObj.getJSONArray("items");
                    Log.d("Hero",">"+con);
                    int length = con .length();
                    Log.d("pradsss",String.valueOf(length));
                    for(int i=0; i<50; i++) {
                        JSONObject jObj = con.getJSONObject(i);
                        String id = jObj.getString("id");
                        JSONObject snip = jObj.getJSONObject("snippet");
                        //   String idx=id.getString("videoId");
                        //   Log.d("prad", idx);
                        JSONObject jobj2 = snip.getJSONObject("thumbnails");
                        JSONObject jobj3= jobj2.getJSONObject("medium");
                        thumb= jobj3.getString("url");
                        JSONObject jobj4 = snip.getJSONObject("resourceId");


                        String playlistid=jobj4.getString("videoId");
                        // a[i]=idx;
                        // playlists[i]=id;
                        Log.d("chu",playlistid);
                        TITLESS4.add(playlistid);
                        URLS4[i]=thumb;
                        // Log.d("pradsss",a[2]);
                    }
                    // Getting JSON Array node
                    // Log.d("chu",jobj2.toString());

                    // looping through All Contacts

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            populatRecyclerView3();
        }


    }
    private class GetVideosfour extends AsyncTask<Void, Void, Void> {
        String thumb;
        //https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId="+playlist[i]+"&key=AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8"
        // ImageView im=(ImageView)findViewById(R.id.imageView);
        private  String url1 = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=PLztAHXmlMZFSBZDRPfcLxzSw0kmrWaQJ_&key=AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8";
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = null;
            try {
                jsonStr = sh.makeServiceCall(url1, ServiceHandler.GET);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(String.valueOf(jsonStr));
                    JSONArray con=jsonObj.getJSONArray("items");
                    Log.d("Hero",">"+con);
                    int length = con .length();
                    Log.d("pradsss",String.valueOf(length));
                    for(int i=0; i<50; i++) {
                        JSONObject jObj = con.getJSONObject(i);
                        String id = jObj.getString("id");
                        JSONObject snip = jObj.getJSONObject("snippet");
                        //   String idx=id.getString("videoId");
                        //   Log.d("prad", idx);
                        JSONObject jobj2 = snip.getJSONObject("thumbnails");
                        JSONObject jobj3= jobj2.getJSONObject("medium");
                        thumb= jobj3.getString("url");
                        JSONObject jobj4 = snip.getJSONObject("resourceId");


                        String playlistid=jobj4.getString("videoId");
                        // a[i]=idx;
                        // playlists[i]=id;
                        Log.d("chu",playlistid);
                        TITLESS5.add(playlistid);
                        URLS5[i]=thumb;
                        // Log.d("pradsss",a[2]);
                    }
                    // Getting JSON Array node
                    // Log.d("chu",jobj2.toString());

                    // looping through All Contacts

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            populatRecyclerView4();

        }


    }
    private class GetVideos extends AsyncTask<Void, Void, Void> {
        String thumb;
        //https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId="+playlist[i]+"&key=AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8"
        // ImageView im=(ImageView)findViewById(R.id.imageView);
        private  String url1 = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=PLztAHXmlMZFQqnEL2hPOHFDyROa3h--xl&key=AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8";
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = null;
            try {
                jsonStr = sh.makeServiceCall(url1, ServiceHandler.GET);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(String.valueOf(jsonStr));
                    JSONArray con=jsonObj.getJSONArray("items");
                    Log.d("Hero",">"+con);
                    int length = con .length();
                    Log.d("pradsss",String.valueOf(length));
                    for(int i=0; i<50; i++) {
                        JSONObject jObj = con.getJSONObject(i);
                        String id = jObj.getString("id");
                        JSONObject snip = jObj.getJSONObject("snippet");
                        //   String idx=id.getString("videoId");
                        //   Log.d("prad", idx);
                        JSONObject jobj2 = snip.getJSONObject("thumbnails");
                        JSONObject jobj3= jobj2.getJSONObject("medium");
                        thumb= jobj3.getString("url");
                        JSONObject jobj4 = snip.getJSONObject("resourceId");


                        String playlistid=jobj4.getString("videoId");
                        // a[i]=idx;
                        // playlists[i]=id;
                        Log.d("chu",playlistid);
                        TITLESS[i]=playlistid;
                        URLS[i]=thumb;
                        // Log.d("pradsss",a[2]);
                    }
                    // Getting JSON Array node
                    // Log.d("chu",jobj2.toString());

                    // looping through All Contacts

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            populatRecyclerView();

        }


    }
    private class GetContacts extends AsyncTask<Void, Void, Void> {
        String thumb;

        // ImageView im=(ImageView)findViewById(R.id.imageView);
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = null;
            try {
                jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(String.valueOf(jsonStr));
                    JSONArray con=jsonObj.getJSONArray("items");
                    Log.d("Hero",">"+con);
                    int length = con .length();
                    Log.d("pradsss",String.valueOf(length));
                    for(int i=0; i<50; i++) {
                        JSONObject jObj = con.getJSONObject(i);
                        JSONObject id = jObj.getJSONObject("id");


                        for(int k=1;k<=3;k++){
                            JSONObject snip = jObj.getJSONObject("snippet");
                            String idx=id.getString("videoId");
                            Log.d("prad", idx);
                            JSONObject jobj2 = snip.getJSONObject("thumbnails");
                            JSONObject jobj3= jobj2.getJSONObject("medium");
                            thumb= jobj3.getString("url");
                            TITLES[i]=idx;
                            Log.d("chu",thumb);
                            URL[i]=thumb;
                        }

                        // a[i]=idx;

                        // Log.d("pradsss",a[2]);
                    }
                    // Getting JSON Array node
                    // Log.d("chu",jobj2.toString());

                    // looping through All Contacts

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog

            ImageView im= (ImageView)getView().findViewById(R.id.imageView5);
            Glide.with(getActivity()).load(URL[1]).into(im);
            ImageView im2= (ImageView)getView().findViewById(R.id.imageView6);
            Glide.with(getActivity()).load(URL[2]).into(im2);
        }


    }
}
