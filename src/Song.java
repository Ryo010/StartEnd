//Loading of singular songs class
//Yes Needed
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import com.mpatric.mp3agic.Mp3File;

import java.io.File;

public class Song {
    //A Song specifics
    private String songTitle;
    private String songArtist;
    private String songLength;
    private String filePath;
    private Mp3File mp3File;
    private double frameRatePerMilliseconds;

    //Opens the audio file and finds song length
    public Song(String filePath){
        this.filePath = filePath;
        try {
            mp3File = new Mp3File(filePath);
            frameRatePerMilliseconds = (double) mp3File.getFrameCount() / mp3File.getLengthInMilliseconds();
            songLength = ConvertToSongLengthFormat();


            AudioFile audioFile = AudioFileIO.read(new File(filePath));

            Tag tag = audioFile.getTag();
            if (tag != null){
                songTitle = tag.getFirst(FieldKey.TITLE);
                songArtist = tag.getFirst(FieldKey.ARTIST);

            }else {
                songTitle = "N/A";
                songArtist = "N/A";
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Converting song length to my own format
    private String ConvertToSongLengthFormat() {
        long minutes = mp3File.getLengthInSeconds() / 60;
        long seconds = mp3File.getLengthInSeconds() % 60;

        String formattedTime = String.format("%02d:%02d", minutes, seconds);

        return formattedTime;
    }

    //Returns song title
    public String getSongTitle() { return songTitle; }

    //Returns song artist
    public String getSongArtist() { return songArtist; }

    //Returns song length in my formatted time
    public String getSongLength() { return songLength; }

    //Returns file path of song
    public String getFilePath() { return filePath; }

    //Returns the mp3 file
    public Mp3File getMp3File(){ return mp3File; }

    //Returns frame rate of the song in ms
    public double getFrameRatePerMillisecond(){ return frameRatePerMilliseconds; }
}
