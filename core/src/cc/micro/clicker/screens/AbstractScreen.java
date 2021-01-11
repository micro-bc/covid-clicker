package cc.micro.clicker.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;

import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.assets.AssetDescriptors;

import static cc.micro.clicker.screens.ScreenManager.camera;
import static cc.micro.clicker.screens.ScreenManager.game;

public abstract class AbstractScreen<T> extends ScreenAdapter {
    @NotNull
    protected static final Color BLACK_OVERLAY = new Color().set(0, 0, 0, 0.3f);

    @NotNull
    protected final Stage stage = new Stage(new FitViewport(camera.viewportWidth, camera.viewportHeight, camera), ScreenManager.game.getBatch());

    @NotNull
    protected final T layout;

    @NotNull
    protected final Skin skin;

    public AbstractScreen(@NotNull final T layout) {
        this.layout = layout;
        this.skin = game.getAssetManager().get(AssetDescriptors.SKIN);
        skin.setScale(ClickerGameConfig.SCALE);
        skin.getFont("default-font").getData().setScale(ClickerGameConfig.SCALE * 1.3f);
        skin.addRegions(game.getAssetManager().get(AssetDescriptors.SKIN_ATLAS));

        if (layout instanceof Table) {
            ((Table) layout).setSkin(skin);
            ((Table) layout).setFillParent(true);
            ((Table) layout).setDebug(ClickerGameConfig.DEBUG != Logger.NONE);
        }

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.BACK:
                        if (ScreenManager.screenStack.size() < 2) return true;
                        final Stack<Screen> screenStack = ScreenManager.screenStack;
                        ScreenManager.screenStack.pop();
                        game.setScreen(ScreenManager.screenStack.pop());
                        return true;
                    default:
                        return false;
                }
            }
        });

        setUp();
    }

    protected abstract void setUp();

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void drawTransparentOverlay(@NotNull final Color color) {
        if (!(layout instanceof Table)) return;
        final Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.Alpha);
        bgPixmap.setColor(color.r, color.g, color.b, color.a);
        bgPixmap.fill();
        final TextureRegionDrawable bg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        ((Table) layout).setBackground(bg);
    }
}
