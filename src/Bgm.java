import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.*;
import java.util.ArrayList;

public class Bgm extends PlaybackListener {
    private static final Object playSignal = new Object();

    private Song currentSong;

    private ArrayList<Song> playlist;

    private int currentPlaylistIndex;

    private AdvancedPlayer advancedPlayer;

    private boolean songFinished;

    public void stopSong(){
        if(advancedPlayer != null){
            advancedPlayer.stop();
            advancedPlayer.close();
            advancedPlayer = null;
        }
    }

    public void nextSong() {
        if(playlist == null) return;

        if (currentPlaylistIndex + 1 > playlist.size() - 1) return;

        currentPlaylistIndex++;

        currentSong = playlist.get(currentPlaylistIndex);

        playCurrentSong();
    }

    public void prevSong(){
        if(playlist == null) return;

        if (currentPlaylistIndex == playlist.size() - 1) {

            currentPlaylistIndex = 0;

            currentSong = playlist.get(currentPlaylistIndex);

            playCurrentSong();
        }
    }

    public void loadPlaylist(File playlistFile){
        playlist = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(playlistFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String songPath;
            while((songPath = bufferedReader.readLine()) != null) {
                Song song = new Song(songPath);

                playlist.add(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(playlist.size() > 0){

            currentSong = playlist.get(0);

            playCurrentSong();
        }
    }

    public void playCurrentSong(){
        if(currentSong == null) return;
        try{
            FileInputStream fileInputStream = new FileInputStream(currentSong.getFilePath());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            advancedPlayer = new AdvancedPlayer(bufferedInputStream);
            advancedPlayer.setPlayBackListener(this);

            startMusicThread();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void startMusicThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    advancedPlayer.play();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void playbackStarted(PlaybackEvent evt) {

        System.out.println("Playback Started");
        songFinished = false;
    }

    @Override
    public void playbackFinished(PlaybackEvent evt){

        System.out.println("Playback Finished");
        if (currentPlaylistIndex != playlist.size() - 1){
            nextSong();
        }else{
            prevSong();
        }

    }
}