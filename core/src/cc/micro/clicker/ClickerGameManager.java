package cc.micro.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import cc.micro.clicker.components.AutoClickerItem;

public class ClickerGameManager {
    public static final ClickerGameManager INSTANCE = new ClickerGameManager();

    // TODO: save game state
    private static final String CPS_ID = "CPS";
    private static final String CLICKS_ID = "CLICKS";
    public final String userID;

    private int cps;
    private int clicks;
    private final Map<String, Integer> items = new HashMap<>();

    private ClickerGameManager() {
        Preferences PREFERENCES = Gdx.app.getPreferences(ClickerGame.class.getSimpleName());
        userID = "xx";
        reset();
    }

    public int getCps() {
        return cps;
    }

    public int getClicks() {
        return clicks;
    }

    public void update() {
        this.clicks += cps;
    }

    private void reset() {
        this.cps = 0;
        this.clicks = 0;
        for (final String key : ClickerGameConfig.AUTO_CLICKERS.keySet()) {
            this.items.put(key, 0);
        }
    }

    public boolean buyItem(@NotNull final String itemId) {
        final AutoClickerItem item = ClickerGameConfig.AUTO_CLICKERS.get(itemId);
        final int price = item.getPrice();

        if(this.clicks < price || this.items.get(itemId) == null) {
            return false;
        }

        this.clicks -= price;
        items.put(itemId, items.get(itemId) + 1);
        this.cps += item.getCps();

        return true;
    }
}