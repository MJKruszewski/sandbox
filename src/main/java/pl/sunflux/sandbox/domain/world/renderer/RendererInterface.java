package pl.sunflux.sandbox.domain.world.renderer;

import pl.sunflux.sandbox.domain.world.components.Tile;

import java.util.Collection;

public interface RendererInterface {
    public void apply(Collection<Tile> tiles);
}
