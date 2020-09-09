package narrator;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * This class is responsible for converting texts into audio materials
 */
public class TTS {

    private IamAuthenticator authenticator;
    private TextToSpeech textToSpeech;
    private static final TTS tts = new TTS();

    private TTS() {
        authenticator = new IamAuthenticator(Settings.getApiKey());
        textToSpeech = new TextToSpeech(authenticator);
        textToSpeech.setServiceUrl(Settings.getServiceURL());
        new File(Settings.getPath()).mkdir();
    }

    public boolean convertTextToSpeech(String text, String fileName) {//convert a text into audio file stored in the repository folder
        try {
            SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
            .text(text)
            .accept(Settings.getAudioFormat())
            .voice(Settings.getVoice())
            .build();

            InputStream inputStream = textToSpeech.synthesize(synthesizeOptions).execute().getResult();
            InputStream in = WaveUtils.reWriteWaveHeader(inputStream);
            FileOutputStream out = new FileOutputStream(Settings.getPath() + fileName + Settings.getFileExtension());
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            out.close();
            in.close();
            inputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static TTS getInstance() {//return a singleton TTS object
        return tts;
    }
    public void initialize(){//simple initialization code
        convertTextToSpeech("temp", "temp");
    }
}
