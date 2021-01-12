package cc.micro.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import cc.micro.clicker.util.AutoClicker;

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
    private BigInteger cps;
    private BigInteger clicks;
    private final Map<String, Integer> items = new HashMap<>();

    private ClickerGameManager() {
        Preferences PREFERENCES = Gdx.app.getPreferences(ClickerGame.class.getSimpleName());
        userID = "xx";
        reset();
    }

    public BigInteger getCps() {
        return cps;
    }

    public BigInteger getClicks() {
        return clicks;
    }

    public void click(final BigInteger clicks) {
        this.clicks = this.clicks.add(clicks);
    }

    public void update(final float dt) {
        final long seconds = TimeUtils.timeSinceNanos(LAST_UPDATE) / 1000000000;
        if (seconds >= 1) {
            if(ClickerGameConfig.BACKGROUND_FARMING) {
                INSTANCE.click(cps.multiply(BigInteger.valueOf(seconds))); // Background hack
            } else {
                INSTANCE.click(cps); // App must be opened
            }
            LAST_UPDATE = TimeUtils.nanoTime();
        }
    }

    private void reset() {
        this.cps = BigInteger.valueOf(123456789);
        this.clicks = BigInteger.valueOf(0);
        for (final String key : ClickerGameConfig.AUTO_CLICKERS.keySet()) {
            this.items.put(key, 0);
        }
    }

    public boolean buyItem(@NotNull final String itemId) {
        final AutoClicker item = ClickerGameConfig.AUTO_CLICKERS.get(itemId);
        final BigInteger price = item.getPrice();

        if(this.clicks.compareTo(price) < 0 || (this.items.get(itemId) == null)) {
            return false;
        }

        this.clicks = this.clicks.subtract(price);
        items.put(itemId, items.get(itemId) + 1);
        this.cps = this.cps.add(item.getCps());

        return true;
    }
}