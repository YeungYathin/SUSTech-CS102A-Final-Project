package minesweeper;

import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;

public class PlayMusic {

    AudioClip christmas;

    public PlayMusic(){
        this.christmas = loadSound("src\\Yellow.wav");
    }

    public static AudioClip loadSound(String filename) {
        URL url = null;
        try {
            url = new URL("file:" + filename);
        }
        catch (MalformedURLException e) {;}
        return JApplet.newAudioClip(url);
    }
    public void play() {
        christmas.play();
    }

    public void stop(){
        christmas.stop();
    }

    public void loop(){
        christmas.loop();
    }
}
