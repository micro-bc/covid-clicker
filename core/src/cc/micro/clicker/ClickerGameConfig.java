package cc.micro.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import cc.micro.clicker.assets.RegionNames;
import cc.micro.clicker.util.AutoClicker;

public final class ClickerGameConfig {
    public static final int DEBUG = Logger.DEBUG;

    /* Behaviour */
    public static final boolean BACKGROUND_FARMING = true;

    /* Screen */
    public static final int WIDTH = Gdx.graphics.getWidth();
    public static final int HEIGHT = Gdx.graphics.getHeight();
    public static final float SCALE = Gdx.graphics.getDensity();

    /* Sizes */
    public static final float HEIGHT_HUD_ITEM = HEIGHT / 15f;
    public static final float HEIGHT_MENU_ITEM = HEIGHT / 12f;
    public static final float PAD_MENU_ITEM = WIDTH / 50f;
    public static final float VIRUS_SCALE = 0.8f;
    public static final float SCROLL_PANE_ROW_HEIGHT = HEIGHT / 10f;


    /* AutoClickers */
    public static final Map<String, AutoClicker> AUTO_CLICKERS = new HashMap<String, AutoClicker>() {
        {
            put(RegionNames.ADVANCED_POSTELJA, new AutoClicker(BigInteger.valueOf(100), BigInteger.valueOf(20)));
            put(RegionNames.BUCKA, new AutoClicker(BigInteger.valueOf(100), BigInteger.valueOf(20)));
        }
    };
}
