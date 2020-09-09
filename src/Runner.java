
import narrator.TTS;
import view.LoginView;

/**
 *
 *
 */
public class Runner {

    public static void main(String[] args) {
        TTS.getInstance().initialize();
        LoginView lv = new LoginView();
        lv.setVisible(true);
    }
}
