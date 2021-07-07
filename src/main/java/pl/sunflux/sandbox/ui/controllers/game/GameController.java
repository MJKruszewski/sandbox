package pl.sunflux.sandbox.ui.controllers.game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import pl.sunflux.sandbox.state.ApplicationSession;
import pl.sunflux.sandbox.ui.views.ViewInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private WebView webViewMain, webViewControl, webViewLeft, webViewRight, webViewButtonsLeft, webViewButtonsRight;

    private WebEngine webEngineMain;
    private WebEngine webEngineControl;
    private WebEngine webEngineLeft;
    private WebEngine webEngineRight;
    private WebEngine webEngineButtonsRight;
    private WebEngine webEngineButtonsLeft;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngineMain = webViewMain.getEngine();
        webEngineLeft = webViewLeft.getEngine();
        webEngineControl = webViewControl.getEngine();
        webEngineRight = webViewRight.getEngine();
        webEngineButtonsRight = webViewButtonsRight.getEngine();
        webEngineButtonsLeft = webViewButtonsLeft.getEngine();

        loadCss();
    }

    private void loadCss() {
        webEngineMain.setUserStyleSheetLocation(ApplicationSession.classLoader.getResource("ui/css/webView_stylesheet.css").toExternalForm());

        webEngineControl.setUserStyleSheetLocation(ApplicationSession.classLoader.getResource("ui/css/webView_stylesheet.css").toExternalForm());

        webEngineButtonsRight.setUserStyleSheetLocation(ApplicationSession.classLoader.getResource("ui/css/webViewButtons_stylesheet.css").toExternalForm());
        webEngineRight.setUserStyleSheetLocation(ApplicationSession.classLoader.getResource("ui/css/webViewAttributes_stylesheet.css").toExternalForm());

        webEngineLeft.setUserStyleSheetLocation(ApplicationSession.classLoader.getResource("ui/css/webViewAttributes_stylesheet.css").toExternalForm());
        webEngineButtonsLeft.setUserStyleSheetLocation(ApplicationSession.classLoader.getResource("ui/css/webViewButtons_stylesheet.css").toExternalForm());

        ApplicationSession.logger.debug("Styles loaded");
    }

    public void setView(ViewInterface instance) {
        webEngineMain.loadContent(instance.getMainContent());
        webEngineControl.loadContent(instance.getButtonMenu());
    }


}
