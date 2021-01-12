package cc.micro.clicker.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public class AutoClicker {
    private TextureAtlas.AtlasRegion atlasRegion;

    private final BigInteger price;
    private final BigInteger cps;

    public AutoClicker(BigInteger price, BigInteger cps) {
        this.price = price;
        this.cps = cps;
    }

    public void setTextureRegion(@NotNull final TextureAtlas.AtlasRegion atlasRegion) {
        this.atlasRegion = atlasRegion;
    }

    public TextureAtlas.AtlasRegion getAtlasRegion() {
        return atlasRegion;
    }

    public BigInteger getPrice() {
        return price;
    }

    public BigInteger getCps() {
        return cps;
    }
}
