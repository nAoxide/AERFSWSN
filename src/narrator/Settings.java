package narrator;

/**
 *
 * This class contains setting information for TTS API
 */
public class Settings {

    private static final String apiKey = "WzYvcEYRbWOA8lTEk4WHobXjcD8LIOUrEJXSxhGxIggh";
    private static final String serviceURL = "https://api.eu-gb.text-to-speech.watson.cloud.ibm.com/instances/9e146585-bcc1-4f8e-a61b-641f5e7c7555";
    private static final String audioFormat = "audio/mp3";
    private static final String voice = "ar-AR_OmarVoice";
    private static final String fileExtension = ".mp3";
    private static final String path = "repository/";

    public static String getApiKey() {
        return apiKey;
    }

    public static String getServiceURL() {
        return serviceURL;
    }

    public static String getAudioFormat() {
        return audioFormat;
    }

    public static String getVoice() {
        return voice;
    }

    public static String getFileExtension() {
        return fileExtension;
    }

    public static String getPath() {
        return path;
    }

}
