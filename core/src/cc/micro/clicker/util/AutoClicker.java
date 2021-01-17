package cc.micro.clicker.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.LinkedHashMap;
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
import static cc.micro.clicker.assets.RegionNames.STOL1;
import static cc.micro.clicker.assets.RegionNames.STOL2;
import static cc.micro.clicker.assets.RegionNames.TABLETE1;
import static cc.micro.clicker.assets.RegionNames.TABLETE2;

public class AutoClicker {
    /* AutoClickers */
    @NotNull
    public static final Map<String, AutoClicker> AUTO_CLICKERS = new LinkedHashMap<String, AutoClicker>() {
        {
            /* Region                                 Region                       Name                  price                                            cps                   */
            put(STOL2,                new AutoClicker(STOL2,                "Chair",              BigInteger.valueOf(50),                          BigInteger.valueOf(1)));
            put(ADVANCED_POSTELJA,    new AutoClicker(ADVANCED_POSTELJA,    "Bed",                BigInteger.valueOf(120),                         BigInteger.valueOf(3)));
            put(ERLENMAJERICA,        new AutoClicker(ERLENMAJERICA,        "Flask",              BigInteger.valueOf(250),                         BigInteger.valueOf(5)));
            put(KNJIGE2,              new AutoClicker(KNJIGE2,              "Science",            BigInteger.valueOf(480),                         BigInteger.valueOf(10)));
            put(TABLETE2,             new AutoClicker(TABLETE2,             "Drugs",              BigInteger.valueOf(1500),                        BigInteger.valueOf(30)));
            put(APARAT_OB_POSTELJI,   new AutoClicker(APARAT_OB_POSTELJI,   "Respirator",         BigInteger.valueOf(3000),                        BigInteger.valueOf(50)));
            put(MIKROSKOP,            new AutoClicker(MIKROSKOP,            "Microscope",         BigInteger.valueOf(10000),                       BigInteger.valueOf(100)));
            put(STOL1,                new AutoClicker(STOL1,                "Super chair",        BigInteger.valueOf(100000),                      BigInteger.valueOf(1000)));
            put(BUCKA,                new AutoClicker(BUCKA,                "Mysterious liquid",  BigInteger.valueOf(1000000),                     BigInteger.valueOf(10000)));
            put(TABLETE1,             new AutoClicker(TABLETE1,             "Expired drugs",      BigInteger.valueOf(10000000),                    BigInteger.valueOf(100000)));
            put(OPERACIJSKA_MIZA,     new AutoClicker(OPERACIJSKA_MIZA,     "Autopsy time",       BigInteger.valueOf(100000000),                   BigInteger.valueOf(1000000)));
            put(PRIPRAVA,             new AutoClicker(PRIPRAVA,             "Definitely poison",  BigInteger.valueOf(10_000_000_000L),             BigInteger.valueOf(10000000)));
            put(NAVADNA_MIZA,         new AutoClicker(NAVADNA_MIZA,         "Race car",           BigInteger.valueOf(1_000_000_000_000L),          BigInteger.valueOf(100000000)));
            put(KNJIGE1,              new AutoClicker(KNJIGE1,              "Knowledge",          BigInteger.valueOf(100_000_000_000_000L),        BigInteger.valueOf(1000000000)));
            put(APARAT_ZA_TESTIRANJE, new AutoClicker(APARAT_ZA_TESTIRANJE, "Time machine",       BigInteger.valueOf(10_000_000_000_000_000L),     BigInteger.valueOf(10_000_000_000L)));
//            put(TERILNICA, new AutoClicker(TERILNICA, "Grinder", BigInteger.valueOf(0), BigInteger.valueOf(0)));
//            put(STOJALO_Z_BUCKAMI, new AutoClicker(STOJALO_Z_BUCKAMI, "Stand with flasks", BigInteger.valueOf(0), BigInteger.valueOf(0)));
        }
    };

    @NotNull
    private final String region;
    @NotNull
    private final String name;
    @NotNull
    private final BigInteger price;
    @NotNull
    private final BigInteger cps;

    private TextureAtlas.AtlasRegion atlasRegion;

    public AutoClicker(@NotNull final String region, @NotNull final String name, @NotNull final BigInteger price, @NotNull final BigInteger cps) {
        this.region = region;
        this.name = name;
        this.price = price;
        this.cps = cps;
    }

    public void setTextureRegion(@NotNull final TextureAtlas atlas) {
        this.atlasRegion = atlas.findRegion(region);
    }

    public TextureAtlas.AtlasRegion getAtlasRegion() {
        return atlasRegion;
    }

    @NotNull
    public final String getRegion() {
        return region;
    }

    @NotNull
    public final String getName() {
        return name;
    }

    @NotNull
    public final BigInteger getPrice() {
        return price;
    }

    @NotNull
    public final BigInteger getCps() {
        return cps;
    }
}
