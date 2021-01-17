package cc.micro.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

public final class ClickerGameConfig {
    public static final int DEBUG = Logger.DEBUG;

    /* Style */
    public static final String CURRENCY_SYMBOL = "c"; // ₡ font has to support it

    /* Behaviour */
    public static final boolean BACKGROUND_FARMING = true;

    /* Screen */
    public static final int WIDTH = Gdx.graphics.getWidth();
    public static final int HEIGHT = Gdx.graphics.getHeight();
    public static final float SCALE = Gdx.graphics.getDensity();

    /* Sizes */
    public static final float HEIGHT_HUD_ITEM = HEIGHT / 15f;
    public static final float HEIGHT_MENU_ITEM = HEIGHT / 12f;
    public static final float VIRUS_SCALE = 0.8f;
    public static final float SCROLL_PANE_ROW_HEIGHT = HEIGHT / 10f;
}
