package pl.sunflux.sandbox.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToolsMain extends Application {
    public static final Logger logger = LogManager.getRootLogger();

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("App started");

        FXMLLoader loader = new FXMLLoader(ToolsMain.class.getResource("../../../sample.fxml"));
        Region root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
