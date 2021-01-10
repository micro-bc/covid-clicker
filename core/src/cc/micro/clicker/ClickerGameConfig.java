package cc.micro.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

import java.util.HashMap;
import java.util.Map;

import cc.micro.clicker.components.AutoClickerItem;

public final class ClickerGameConfig {
    public static final int DEBUG = Logger.DEBUG;

    /* Screen */
    public static final int WIDTH = Gdx.graphics.getWidth();
    public static final int HEIGHT = Gdx.graphics.getHeight();
    public static final float HEIGHT_HUD_ITEM = HEIGHT / 15f;
    public static final float HEIGHT_MENU_ITEM = HEIGHT / 12f;
    public static final float PAD_MENU_ITEM = WIDTH / 50f;
    public static final float VIRUS_SCALE = 0.8f;
    public static final float SCALE = Gdx.graphics.getDensity();

    /* AutoClickers */
    public static final Map<String, AutoClickerItem> AUTO_CLICKERS = new HashMap<String, AutoClickerItem>() {
        {
            put("Bed",        new AutoClickerItem(0, 0, null));
            put("Chemist",    new AutoClickerItem(0, 0, null));
            put("Doctor",     new AutoClickerItem(0, 0, null));
            put("Drugs",      new AutoClickerItem(0, 0, null));
            put("Knowledge",  new AutoClickerItem(0, 0, null));
            put("Microscope", new AutoClickerItem(0, 0, null));
            put("Syrup",      new AutoClickerItem(0, 0, null));
            put("SuperBed",   new AutoClickerItem(0, 0, null));
            put("Respirator", new AutoClickerItem(0, 0, null));
        }
    };
}
