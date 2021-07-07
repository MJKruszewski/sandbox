package pl.sunflux.sandbox.state;

import javafx.scene.Scene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.sunflux.sandbox.ui.controllers.game.GameController;

public class ApplicationSession {
    public static final String AUTHOR = "MJKruszewski";
    public static final String GAME_NAME = "Sandbox";
    public static final String VERSION_NUMBER = "0.0.1";
    public static final String VERSION_DESCRIPTION = "Alpha";

    public static final Logger logger = LogManager.getRootLogger();
    public static final ClassLoader classLoader = ApplicationSession.class.getClassLoader();

    public static Scene gameScene;
    public static GameController gameController;

}
