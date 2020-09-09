package narrator;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * This class is responsible for reading the text
 */
public class Narrator {

    final JFXPanel fxPanel;
    private MediaPlayer mediaPlayer;
    private static final Narrator narrator = new Narrator();
    private Thread narratorThread = new Thread();
    private TTS tts;
    private boolean isPlaying;

    private Narrator() {//initialize the Narrator object
        fxPanel = new JFXPanel();
        this.tts = TTS.getInstance();
    }

    private void play(String fileName) throws Exception {//pleay an audio file
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        Media hit = new Media(new File(Settings.getPath() + fileName + Settings.getFileExtension()).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.dispose();
                isPlaying = false;
            }
        });
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                isPlaying = true;
            }
        });

    }

    public void narrate(String text, String elementFullID) {//check if audio file for the text exists and run it. If audio file does not exist convert the text into one and run it.
        stop();
        narratorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (exists(elementFullID)) {
                        play(elementFullID);
                    } else {
                        tts.convertTextToSpeech(text, elementFullID);
                        play(elementFullID);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Narrator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        narratorThread.start();
    }

    private boolean exists(String fileName) {//check existance of a file
        File tempFile = new File(Settings.getPath() + fileName + Settings.getFileExtension());
        return tempFile.exists();
    }

    public void stop() {//stop the audio file currently running
        this.narratorThread.stop();
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
    }

    public static Narrator getNarrator() {//return a singleton Narrator object
        return narrator;
    }

}
