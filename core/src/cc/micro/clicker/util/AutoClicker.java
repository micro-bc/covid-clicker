package cc.micro.clicker.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static cc.micro.clicker.assets.RegionNames.ADVANCED_POSTELJA;
import static cc.micro.clicker.assets.RegionNames.APARAT_OB_POSTELJI;
import static cc.micro.clicker.assets.RegionNames.APARAT_ZA_TESTIRANJE;
import static cc.micro.clicker.assets.RegionNames.BUCKA;
import static cc.micro.clicker.assets.RegionNames.ERLENMAJERICA;
import static cc.micro.clicker.assets.RegionNames.KNJIGE1;
import static cc.micro.clicker.assets.RegionNames.KNJIGE2;
import static cc.micro.clicker.assets.RegionNames.MIKROSKOP;
import static cc.micro.clicker.assets.RegionNames.NAVADNA_MIZA;
import static cc.micro.clicker.assets.RegionNames.OPERACIJSKA_MIZA;
import static cc.micro.clicker.assets.RegionNames.PRIPRAVA;
import static cc.micro.clicker.assets.RegionNames.STOJALO_Z_BUCKAMI;
import static cc.micro.clicker.assets.RegionNames.STOL1;
import static cc.micro.clicker.assets.RegionNames.STOL2;
import static cc.micro.clicker.assets.RegionNames.TABLETE1;
import static cc.micro.clicker.assets.RegionNames.TABLETE2;
import static cc.micro.clicker.assets.RegionNames.TERILNICA;

public class AutoClicker {
    private TextureAtlas.AtlasRegion atlasRegion;

    private final BigInteger price;
    private final BigInteger cps;

    private Map<String, String> map;

    public AutoClicker(BigInteger price, BigInteger cps) {
        this.price = price;
        this.cps = cps;
        this.map = new HashMap<String, String>();
        map.put(ADVANCED_POSTELJA, "Bed");
        map.put(BUCKA, "Flask");
        map.put(ERLENMAJERICA, "Erlenmeyer Flask");
        map.put(KNJIGE1, "Books");
        map.put(KNJIGE2, "Books with Elrenmeyer flask");
        map.put(APARAT_OB_POSTELJI, "Heart monitor");
        map.put(APARAT_ZA_TESTIRANJE, "Testing machine");
        map.put(MIKROSKOP, "Microscope");
        map.put(NAVADNA_MIZA, "Table");
        map.put(OPERACIJSKA_MIZA, "Operating table");
        map.put(PRIPRAVA, "Apparatus");
        map.put(STOJALO_Z_BUCKAMI, "Stand with flasks");
        map.put(STOL1, "White Chair");
        map.put(STOL2, "Blue Chair");
        map.put(TABLETE1, "Bottle of pills");
        map.put(TABLETE2, "Pills");
        map.put(TERILNICA, "Mortar");
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
