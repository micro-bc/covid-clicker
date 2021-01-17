package cc.micro.clicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

import cc.micro.clicker.assets.AssetDescriptors;
import cc.micro.clicker.screens.ScreenItems;
import cc.micro.clicker.screens.ScreenMain;
import cc.micro.clicker.screens.ScreenManager;
import cc.micro.clicker.screens.ScreenSettings;
import cc.micro.clicker.screens.ScreenShop;
import cc.micro.clicker.util.AutoClicker;

public class ClickerGame extends Game {
    @NotNull
    public final AssetManager assetManager = new AssetManager();

    private SpriteBatch batch;

    @NotNull
    public final SpriteBatch getBatch() {
        return batch;
    }

    @NotNull
    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        Gdx.app.setLogLevel(ClickerGameConfig.DEBUG);
        ScreenManager.game = this;

        loadAssets();
        initClickers();
        ClickerGameManager.INSTANCE = new ClickerGameManager();
        initScreens();

        setScreen(ScreenManager.screens.get(ScreenManager.MAIN_SCREEN));
    }

    @Override
    public void setScreen(Screen screen) {
        ScreenManager.screenStack.add(screen);
        super.setScreen(screen);
    }

    private void loadAssets() {
        assetManager.getLogger().setLevel(ClickerGameConfig.DEBUG);
        for (final Field field : AssetDescriptors.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                final AssetDescriptor<?> value = (AssetDescriptor<?>) field.get(AssetDescriptor.class);
                assetManager.load(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        assetManager.finishLoading();
    }

    private void initScreens() {
        ScreenManager.screens.put(ScreenManager.MAIN_SCREEN, new ScreenMain());
        ScreenManager.screens.put(ScreenManager.ITEMS_SCREEN, new ScreenItems());
        ScreenManager.screens.put(ScreenManager.SHOP_SCREEN, new ScreenShop());
        ScreenManager.screens.put(ScreenManager.SETTINGS_SCREEN, new ScreenSettings());
    }

    private void initClickers() {
        final TextureAtlas textureAtlas = assetManager.get(AssetDescriptors.MY_ATLAS);
        for (final String region : AutoClicker.AUTO_CLICKERS.keySet()) {
            AutoClicker.AUTO_CLICKERS.get(region).setTextureRegion(textureAtlas);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        assetManager.dispose();
    }
}
