package pl.sunflux.sandbox.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.sunflux.sandbox.state.ApplicationSession;
import pl.sunflux.sandbox.ui.views.menu.StartMenuView;

import java.net.URL;

public class GameMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationSession.logger.info("App started");

        URL resource = ApplicationSession.classLoader.getResource("ui/fx/game/game.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        GridPane root = loader.load();

        ApplicationSession.gameScene = new Scene(root);
        ApplicationSession.gameController = loader.getController();

        primaryStage.setTitle(ApplicationSession.GAME_NAME + " " + ApplicationSession.VERSION_NUMBER + " " + ApplicationSession.VERSION_DESCRIPTION);
        loadFonts();

        ApplicationSession.gameScene.getStylesheets().add(ApplicationSession.classLoader.getResource("ui/css/stylesheet.css").toExternalForm());

        primaryStage.setScene(ApplicationSession.gameScene);
        root.setMinSize(600, 800);

        primaryStage.show();

        ApplicationSession.gameController.setView(StartMenuView.getInstance());
    }

    protected void loadFonts() {
        if (Font.loadFont(ApplicationSession.classLoader.getResourceAsStream("ui/fonts/Carlito/Carlito-Regular.ttf"), 11) != null) {
            Font.loadFont(ApplicationSession.classLoader.getResourceAsStream("ui/fonts/Carlito/Carlito-Bold.ttf"), 11);
            Font.loadFont(ApplicationSession.classLoader.getResourceAsStream("ui/fonts/Carlito/Carlito-BoldItalic.ttf"), 11);
            Font.loadFont(ApplicationSession.classLoader.getResourceAsStream("ui/fonts/Carlito/Carlito-Italic.ttf"), 11);
        } else {
            ApplicationSession.logger.error("Carlito font could not be loaded.");
        }

        if (Font.loadFont(ApplicationSession.classLoader.getResourceAsStream("ui/fonts/DejaVu Sans/DejaVuSans.ttf"), 12) != null) {
            Font.loadFont(ApplicationSession.classLoader.getResourceAsStream("ui/fonts/DejaVu Sans/DejaVuSans-Bold.ttf"), 12);
            Font.loadFont(ApplicationSession.classLoader.getResourceAsStream("ui/fonts/DejaVu Sans/DejaVuSans-BoldOblique.ttf"), 12);
            Font.loadFont(ApplicationSession.classLoader.getResourceAsStream("ui/fonts/DejaVu Sans/DejaVuSans-ExtraLight.ttf"), 12);
            Font.loadFont(ApplicationSession.classLoader.getResourceAsStream("ui/fonts/DejaVu Sans/DejaVuSans-Oblique.ttf"), 12);
        } else {
            ApplicationSession.logger.error("DejaVu Sans font could not be loaded.");
        }

        ApplicationSession.logger.debug("Fonts loaded");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
