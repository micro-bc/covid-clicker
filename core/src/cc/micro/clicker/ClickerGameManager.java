package cc.micro.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import cc.micro.clicker.components.AutoClickerItem;

/* TODO: save/load game state
    - CPS
    - CLICKS
    - ITEMS map
 */
public class ClickerGameManager {
    public static final ClickerGameManager INSTANCE = new ClickerGameManager();

    private static final String CPS_ID = "CPS";
    private static final String CLICKS_ID = "CLICKS";
    public final String userID;

    private long LAST_UPDATE = TimeUtils.nanoTime();
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

    public void click(final int clicks) {
        this.clicks += clicks;
    }

    public void update(final float dt) {
        final long seconds = TimeUtils.timeSinceNanos(LAST_UPDATE) / 1000000000;
        if (seconds >= 1) {
            if(ClickerGameConfig.BACKGROUND_FARMING) {
                INSTANCE.click((int) (cps * seconds)); // Background hack
            } else {
                INSTANCE.click(cps); // App must be opened
            }
            LAST_UPDATE = TimeUtils.nanoTime();
        }
    }

    private void reset() {
        this.cps = 1;
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