package cc.micro.clicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

import cc.micro.clicker.assets.AssetDescriptors;
import cc.micro.clicker.debug.ViewportUtils;
import cc.micro.clicker.screens.ScreenMain;

public class ClickerGame extends Game {
    @NotNull
    public final AssetManager assetManager = new AssetManager();

    private SpriteBatch batch;

    @NotNull
    public final SpriteBatch getBatch() {
        return batch;
    }

    @NotNull
    public AssetManager getAssetManager() { return assetManager; }

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        Gdx.app.setLogLevel(ClickerGameConfig.DEBUG);
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        batch.enableBlending();

        ViewportUtils.DEFAULT_CELL_SIZE = 32;

        assetManager.getLogger().setLevel(ClickerGameConfig.DEBUG);
        for (final Field field : AssetDescriptors.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                final AssetDescriptor value = (AssetDescriptor) field.get(AssetDescriptor.class);
                assetManager.load(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        assetManager.finishLoading();

        super.setScreen(new ScreenMain(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        assetManager.dispose();
    }
}
