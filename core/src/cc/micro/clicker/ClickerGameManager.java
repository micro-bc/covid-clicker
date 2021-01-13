package cc.micro.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cc.micro.clicker.util.AutoClicker;

public class ClickerGameManager {
    public static final ClickerGameManager INSTANCE = new ClickerGameManager();
    private static final String CPS_ID = "CPS";
    private static final String CLICKS_ID = "CLICKS";
    private static final String USER_ID = "USER_ID";
    private final Preferences PREFERENCES = Gdx.app.getPreferences(ClickerGame.class.getSimpleName());
    private final Map<String, Integer> items = new HashMap<>();
    private UUID userID;
    private long LAST_UPDATE = TimeUtils.nanoTime();
    private BigInteger cps = BigInteger.valueOf(0);
    private BigInteger clicks = BigInteger.valueOf(0);

    private ClickerGameManager() {
        loadState();
    }

    @NotNull
    public BigInteger getCps() {
        return cps;
    }

    @NotNull
    public BigInteger getClicks() {
        return clicks;
    }

    public void click(final BigInteger clicks) {
        this.clicks = this.clicks.add(clicks);
    }

    public boolean buyItem(@NotNull final String itemId) {
        final AutoClicker item = ClickerGameConfig.AUTO_CLICKERS.get(itemId);
        final BigInteger price = item.getPrice();

        if (this.clicks.compareTo(price) < 0 || (this.items.get(itemId) == null)) {
            return false;
        }

        this.clicks = this.clicks.subtract(price);
        items.put(itemId, items.get(itemId) + 1);
        this.cps = this.cps.add(item.getCps());

        return true;
    }

    private void reset() {
        cps = BigInteger.valueOf(0);
        clicks = BigInteger.valueOf(0);
        for (final String key : ClickerGameConfig.AUTO_CLICKERS.keySet()) {
            items.put(key, 0);
        }
        // TODO: Stats
    }

    public void update(final float dt) {
        final long seconds = TimeUtils.timeSinceNanos(LAST_UPDATE) / 1000000000;
        if (seconds >= 1) {
            if (ClickerGameConfig.BACKGROUND_FARMING) {
                click(cps.multiply(BigInteger.valueOf(seconds))); // Background hack
            } else {
                click(cps); // App must be opened
            }
            LAST_UPDATE = TimeUtils.nanoTime();
        }
    }

    public void saveState() {
        PREFERENCES.putString(USER_ID, userID.toString());
        PREFERENCES.putString(CPS_ID, cps.toString());
        PREFERENCES.putString(CLICKS_ID, clicks.toString());
        for (final String item : items.keySet()) {
            PREFERENCES.putString(item, String.valueOf(items.get(item)));
        }
        // TODO: Stats

        PREFERENCES.flush();
    }

    private void loadState() {
        if (PREFERENCES.contains(USER_ID)) {
            userID = UUID.fromString(PREFERENCES.getString(USER_ID));
            if (PREFERENCES.contains(CPS_ID)) {
                cps = new BigInteger(PREFERENCES.getString(CPS_ID));
            }
            if (PREFERENCES.contains(CLICKS_ID)) {
                clicks = new BigInteger(PREFERENCES.getString(CLICKS_ID));
            }

            for (final String clicker : ClickerGameConfig.AUTO_CLICKERS.keySet()) {
                if (PREFERENCES.contains(clicker)) {
                    items.put(clicker, Integer.valueOf(PREFERENCES.getString(clicker)));
                } else {
                    items.put(clicker, 0);
                }
            }
        } else {
            userID = UUID.randomUUID();
            reset();
        }
        // TODO: Stats
    }
}