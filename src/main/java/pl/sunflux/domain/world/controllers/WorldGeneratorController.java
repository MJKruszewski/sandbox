package pl.sunflux.domain.world.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import pl.sunflux.domain.game.State;
import pl.sunflux.domain.world.GeneratorOptions;
import pl.sunflux.domain.world.WorldMapGenerator;
import pl.sunflux.domain.world.renderer.ElevationRenderer;
import pl.sunflux.domain.world.renderer.LandMassRenderer;
import pl.sunflux.domain.world.renderer.RendererInterface;

import java.util.ArrayList;

public class WorldGeneratorController {
    public Button generateButton;

    public TextField maxLand;
    public TextField nVertices;
    public TextField seed;
    public TextField relaxation;

    public AnchorPane mapPane;
    public ScrollPane scrollPane;

    private WorldMapGenerator worldMapGenerator = new WorldMapGenerator();
    private RendererInterface landMassRenderer = new LandMassRenderer();
    private ElevationRenderer elevationRenderer = new ElevationRenderer();

    public void generateMap() {
        GeneratorOptions generatorOptions = new GeneratorOptions();

        generatorOptions.nVertices = Integer.parseInt(nVertices.getText());
        generatorOptions.relaxation = Integer.parseInt(relaxation.getText());
        generatorOptions.maxLand = Double.parseDouble(maxLand.getText());
        generatorOptions.seed = Integer.parseInt(seed.getText());

        mapPane.getChildren().removeAll(mapPane.getChildren());

        State.worldMap = worldMapGenerator.generate(generatorOptions);

        ArrayList<Polygon> polygons = new ArrayList<>(State.worldMap.getTiles().size());
        State.worldMap.getTiles().stream().forEach(tile -> {
            polygons.add(tile.getGraphic());
        });

        landMassRenderer.apply(State.worldMap);

        polygons.forEach(polygon -> {
            mapPane.getChildren().add(polygon);
            polygon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                polygon.setFill(Color.CYAN);
            });
        });
    }

    public void renderLandMass(ActionEvent actionEvent) {
        landMassRenderer.apply(State.worldMap);
    }

    public void renderElevation(ActionEvent actionEvent) {
        elevationRenderer.apply(State.worldMap);
    }
}
