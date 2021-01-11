package cc.micro.clicker.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Logger;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import cc.micro.clicker.ClickerGame;
import cc.micro.clicker.ClickerGameConfig;

public final class ScreenManager {
    @NotNull
    public static final Logger log = new Logger(ScreenMain.class.getSimpleName(), ClickerGameConfig.DEBUG);
    @NotNull
    public static final OrthographicCamera camera = new OrthographicCamera(ClickerGameConfig.WIDTH, ClickerGameConfig.HEIGHT);

    @NotNull
    public static final String MAIN_SCREEN = "screenMain";
    @NotNull
    public static final String ITEMS_SCREEN = "screenItems";
    @NotNull
    public static final String SHOP_SCREEN = "screenShop";
    @NotNull
    public static final String SETTINGS_SCREEN = "screenSettings";

    @NotNull
    public static final Map<String, ScreenAdapter> screens = new HashMap<>();

    public static ClickerGame game;

    @NotNull
    public static final Stack<Screen> screenStack = new Stack<>();
}
