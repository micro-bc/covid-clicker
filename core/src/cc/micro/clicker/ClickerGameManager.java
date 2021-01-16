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
    private static final String TOTAL_TIME_ID = "TOTAL_TIME";
    private static final String TOTAL_CLICKS_ID = "TOTAL_CLICKS";
    private final Preferences PREFERENCES = Gdx.app.getPreferences(ClickerGame.class.getSimpleName());
    private final Map<String, Integer> items = new HashMap<>();
    private UUID userID;
    private long LAST_UPDATE = TimeUtils.nanoTime();
    private BigInteger cps = BigInteger.valueOf(0);
    private BigInteger clicks = BigInteger.valueOf(0);
    private BigInteger totalClicks = BigInteger.valueOf(0);
    private BigInteger totalTime = BigInteger.valueOf(0); // milliseconds
    private long instanceStartTime = TimeUtils.millis();


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

    public int getItemCount(@NotNull final String id) {
        return items.get(id);
    }


    public void updateTotalTime() {
        totalTime = totalTime.add(BigInteger.valueOf(TimeUtils.timeSinceMillis(instanceStartTime)));
        instanceStartTime = TimeUtils.millis();
    }

    public void click(final BigInteger clicks) {
        this.clicks = this.clicks.add(clicks);
        totalClicks = totalClicks.add(clicks);
    }

    public void buyItem(@NotNull final String itemId, final int count) {
        final AutoClicker item = ClickerGameConfig.AUTO_CLICKERS.get(itemId);
        final BigInteger price = item.getPrice();

        if (this.clicks.compareTo(price.multiply(BigInteger.valueOf(count))) < 0 || (this.items.get(itemId) == null)) {
            // TODO: popup
            return;
        }

        this.clicks = this.clicks.subtract(price.multiply(BigInteger.valueOf(count)));
        items.put(itemId, items.get(itemId) + count);
        this.cps = this.cps.add(item.getCps().multiply(BigInteger.valueOf(count)));
    }

    private void reset() {
        userID = UUID.randomUUID();
        cps = BigInteger.valueOf(0);
        clicks = BigInteger.valueOf(0);
        totalClicks = BigInteger.valueOf(0);
        totalTime = BigInteger.valueOf(0);
        for (final String key : ClickerGameConfig.AUTO_CLICKERS.keySet()) {
            items.put(key, 0);
        }
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
        PREFERENCES.putString(TOTAL_CLICKS_ID, totalClicks.toString());
        PREFERENCES.putString(TOTAL_TIME_ID, String.valueOf(totalTime));
        for (final String item : items.keySet()) {
            PREFERENCES.putString(item, String.valueOf(items.get(item)));
        }

        PREFERENCES.flush();
    }

    private void loadState() {
        try {
            userID = UUID.fromString(PREFERENCES.getString(USER_ID));
            cps = new BigInteger(PREFERENCES.getString(CPS_ID));
            clicks = new BigInteger(PREFERENCES.getString(CLICKS_ID));
            totalClicks = new BigInteger(PREFERENCES.getString(TOTAL_CLICKS_ID));
            totalTime = new BigInteger(PREFERENCES.getString(TOTAL_TIME_ID));
            for (final String clicker : ClickerGameConfig.AUTO_CLICKERS.keySet()) {
                if (PREFERENCES.contains(clicker)) {
                    items.put(clicker, Integer.valueOf(PREFERENCES.getString(clicker)));
                } else {
                    items.put(clicker, 0); // Enable updates with new clickers without deleting game state
                }
            }
        } catch (final Exception e) {
            reset();
        }
    }
}