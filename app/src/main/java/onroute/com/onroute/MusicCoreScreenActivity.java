package onroute.com.onroute;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by STT on 14.4.2016.
 */
public class MusicCoreScreenActivity extends Activity implements SeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener {

    ListView mListView;
    private ImageButton btnPlay;
    private ImageButton btnForward;
    private ImageButton btnBackward;
    private ImageButton btnNext;
    private ImageButton btnPrevious;
    private ImageButton btnPlaylist;
    private ImageButton btnRepeat;
    private ImageButton btnShuffle;
    private ImageButton btnPause;
    private SeekBar songProgressBar;
    private TextView songTitleLabel;
    String impeg;
    private TextView songCurrentDurationLabel;
    private TextView songTotalDurationLabel;
    // Media Player
    int songIndex;
    private  MediaPlayer mp;
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();;
    private SongsManager songManager;
    private Utilities utils;
    private int seekForwardTime = 5000; // 5000 milliseconds
    private int seekBackwardTime = 5000; // 5000 milliseconds
    private int currentSongIndex = 0;
    private boolean isShuffle = false;
    private boolean isRepeat = false;
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
int a=1;
    final String[] songPath=new String[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_music_core);
        final String[] songsListData = new String[8];

        SongsManager plm = new SongsManager();
        // get all songs from sdcard
        this.songsList = plm.getPlayList();

        // looping through playlist
        for (int i = 0; i < songsList.size(); i++) {
            // creating new HashMap
            HashMap<String, String> song = songsList.get(i);
Set keys =song.keySet();
            Iterator itr = keys.iterator();

            String key;
            String value;
            while(itr.hasNext()) {
                key = (String) itr.next();
                value = (String) song.get(key);
                songsListData[i]= value;
                songPath[i]=key;
            }
            // adding HashList to ArrayList

        }
        mListView = (ListView) findViewById(R.id.song_playlist);
        mListView.setAdapter(new ListAdapter(this, R.layout.layout_music_playlist_row, songsListData));
        Log.i("Dan", "Count is: " + mListView.getAdapter().getCount());
mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        Toast.makeText(MusicCoreScreenActivity.this, "Liked This item" + position, Toast.LENGTH_SHORT).show();
        TextView tx = (TextView) findViewById(R.id.texttitle);
        tx.setText(songsListData[position]);
        impeg=songPath[position];
       songIndex = position;
playSong(position);
    }
});
        //btnPlay = (ImageButton) findViewById(R.id.btnPlay);
      //  btnForward = (ImageButton) findViewById(R.id.btnForward);
       // btnBackward = (ImageButton) findViewById(R.id.btnBackward);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnPause = (ImageButton) findViewById(R.id.btnPause);
       // btnRepeat = (ImageButton) findViewById(R.id.btnRepeat);
       // btnShuffle = (ImageButton) findViewById(R.id.btnShuffle);
        songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
        songTitleLabel = (TextView) findViewById(R.id.songTitle);
      //  songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
       // songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);

        // Mediaplayer
        mp = new MediaPlayer();
        songManager = new SongsManager();
        utils = new Utilities();

        // Listeners
        songProgressBar.setOnSeekBarChangeListener(this); // Important
        mp.setOnCompletionListener( this); // Important

        // Getting all songs list
        songsList = songManager.getPlayList();

        // By default play first song
    // playSong(0);

        /**
         * Play button click event
         * plays a song and changes button to pause image
         * pauses a song and changes button to play image
         * */


        /**
         * Forward button click event
         * Forwards song specified seconds
         * */


        /**
         * Backward button click event
         * Backward song to specified seconds
         * */


        /**
         * Next button click event
         * Plays next song by taking currentSongIndex + 1
         * */
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // check if next song is there or not
                if(currentSongIndex < (songsList.size() - 1)){
                    playSong(currentSongIndex + 1);
                    currentSongIndex = currentSongIndex + 1;
                }else{
                    // play first song
                    playSong(0);
                    currentSongIndex = 0;
                }

            }
        });

        /**
         * Back button click event
         * Plays previous song by currentSongIndex - 1
         * */
        btnPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(currentSongIndex > 0){
                    playSong(currentSongIndex - 1);
                    currentSongIndex = currentSongIndex - 1;
                }else{
                    // play last song
                    playSong(songsList.size() - 1);
                    currentSongIndex = songsList.size() - 1;
                }

            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                int a=1;
                if(a==1) {
                    mp.pause();
                    a=0;
                    btnPause.setImageResource(R.drawable.pause_clicked);
                }
                else
                {
                    a=1;
                    btnPlay.setImageResource(R.drawable.play);
                }
            }
        });

        /**
         * Button Click event for Repeat button
         * Enables repeat flag to true
         * */


        /**
         * Button Click event for Shuffle button
         * Enables shuffle flag to true
         * */


    }
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       // if(resultCode == 100){
            currentSongIndex = songIndex;
            // play selected song
            playSong(currentSongIndex);
        //}

    }

    /**
     * Function to play a song
     * @param songIndex - index of song
     * */
    public void  playSong(int songIndex){
        // Play song
        try {
            mp.reset();
            mp.setDataSource(songsList.get(songIndex).get("songPath"));
            mp.prepare();
            mp.start();
            // Displaying Song title
           // String songTitle = songsList.get(songIndex).get("songTitle");
          //  TextView t=(TextView)findViewById(R.id.texttitle);
          //  t.setText(songTitle);
//            songTitleLabel.setText(songTitle);

            // Changing Button Image to pause image
      //      btnPlay.setImageResource(R.drawable.pause_clicked);

            // set Progress bar values
            songProgressBar.setProgress(0);
            songProgressBar.setMax(100);

            // Updating progress bar
            updateProgressBar();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update timer on seekbar
     * */
    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    /**
     * Background Runnable thread
     * */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mp.getDuration();
            long currentDuration = mp.getCurrentPosition();

            // Displaying Total Duration time

            // Updating progress bar
            int progress = (int)(utils.getProgressPercentage(currentDuration, totalDuration));
            //Log.d("Progress", ""+progress);
            songProgressBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };

    /**
     *
     * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }

    /**
     * When user starts moving the progress handler
     * */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // remove message Handler from updating progress bar
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    /**
     * When user stops moving the progress hanlder
     * */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mp.getDuration();
        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mp.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();
    }

    /**
     * On Song Playing completed
     * if repeat is ON play same song again
     * if shuffle is ON play random song
     * */
    @Override
    public void onCompletion(MediaPlayer arg0) {

        // check for repeat is ON or OFF

            // no repeat or shuffle ON - play next song
            if(currentSongIndex < (songsList.size() - 1)){
                playSong(currentSongIndex + 1);
                currentSongIndex = currentSongIndex + 1;
            }else{
                // play first song
                playSong(0);
                currentSongIndex = 0;
            }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mp.release();
    }
    public class ListAdapter extends ArrayAdapter<String> {

        public ListAdapter(Context context, int resource, String[] items) {
            super(context, resource, items);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.layout_music_playlist_row, null);
            }
          //  btnPlay = (ImageButton) convertView.findViewById(R.id.btnPlay);
            final TextView mText = (TextView) convertView.findViewById(R.id.music_row_songname);
            mText.setText(getItem(position));



            return convertView;
        }

    }

}
