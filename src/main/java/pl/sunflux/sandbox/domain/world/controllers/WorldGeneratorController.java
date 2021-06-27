package pl.sunflux.sandbox.domain.world.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import pl.sunflux.sandbox.Main;
import pl.sunflux.sandbox.domain.game.WorldMap;
import pl.sunflux.sandbox.domain.world.GeneratorOptions;
import pl.sunflux.sandbox.domain.world.WorldMapGenerator;
import pl.sunflux.sandbox.domain.world.renderer.BiomeRenderer;
import pl.sunflux.sandbox.domain.world.renderer.ElevationRenderer;
import pl.sunflux.sandbox.domain.world.renderer.RendererInterface;

public class WorldGeneratorController {
    public Button generateButton;

    public TextField seed;
    public TextField relaxation;

    public AnchorPane mapPane;
    public ScrollPane scrollPane;
    public TextField graphBounds;
    public TextField sitesAmount;
    public AnchorPane tileInfoPane;

    private WorldMapGenerator worldMapGenerator = new WorldMapGenerator();
    private RendererInterface biomeRenderer = new BiomeRenderer();
    private ElevationRenderer elevationRenderer = new ElevationRenderer();

    public void generateMap() {
        generateButton.setDisable(true);
        Main.logger.info("start of generation");

        GeneratorOptions generatorOptions = new GeneratorOptions();

        generatorOptions.graphBounds = Integer.parseInt(graphBounds.getText());
        generatorOptions.sitesAmount = Integer.parseInt(sitesAmount.getText());
        generatorOptions.lloydRelaxations = Integer.parseInt(relaxation.getText());
        generatorOptions.seed = Integer.parseInt(seed.getText());

        WorldMap.mapShapes.forEach(polygon -> {
            polygon.setOnMouseEntered(null);
            polygon.setOnMouseExited(null);
            polygon.setOnMouseClicked(null);
        });

        mapPane.getChildren().removeAll(mapPane.getChildren());

        worldMapGenerator.generate(generatorOptions);
        biomeRenderer.apply(WorldMap.tiles);

        Platform.runLater(() -> {
            Main.logger.debug("shapes1: " + WorldMap.mapShapes.size());

            WorldMap.tiles.parallelStream().filter(tile -> !tile.center.ocean).forEach(tile -> {
                tile.graphic.setOnMouseEntered(event -> {
                    tile.graphic.setFill(Color.CYAN);

                });
                tile.graphic.setOnMouseExited(event -> tile.graphic.setFill(tile.currentColor));
            });

            WorldMap.mapShapes.forEach(polygon -> {
                mapPane.getChildren().add(polygon);
            });

            generateButton.setDisable(false);
            Main.logger.info("generating done");
        });

    }

    public void renderLandMass(ActionEvent actionEvent) {
        biomeRenderer.apply(WorldMap.tiles);
    }

    public void renderElevation(ActionEvent actionEvent) {
        elevationRenderer.apply(WorldMap.tiles);
    }
}
