package cc.micro.clicker.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;

import org.jetbrains.annotations.NotNull;

import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.ClickerGameManager;
import cc.micro.clicker.assets.AssetDescriptors;

import static cc.micro.clicker.screens.ScreenManager.camera;
import static cc.micro.clicker.screens.ScreenManager.game;

public abstract class AbstractScreen extends ScreenAdapter {

    @NotNull
    protected static final Color OVERLAY = new Color().set(0, 0, 0, 0.7f);

    @NotNull
    protected final Stage stage = new Stage(new FitViewport(camera.viewportWidth, camera.viewportHeight, camera), ScreenManager.game.getBatch());

    @NotNull
    protected final Table container = new Table();

    @NotNull
    protected final Skin skin;

    protected boolean setDefaultBackground = true;

    public AbstractScreen() {
        this.skin = game.getAssetManager().get(AssetDescriptors.SKIN);
        init();
    }

    protected abstract void setUp();

    protected abstract void update(final float dt);

    private void init() {
        skin.setScale(ClickerGameConfig.SCALE);
        skin.getFont("default-font").getData().setScale(ClickerGameConfig.SCALE * 1.3f);
        skin.addRegions(game.getAssetManager().get(AssetDescriptors.SKIN_ATLAS));

        container.setSkin(skin);
        container.setFillParent(true);
        container.setDebug(ClickerGameConfig.DEBUG != Logger.NONE);

        setUp();
        setGenericListeners();
        if(setDefaultBackground) drawBackground();
        stage.addActor(container);
    }

    private void setGenericListeners() {
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.BACK:
                        if (ScreenManager.screenStack.size() < 2) return true;
                        ScreenManager.screenStack.pop();
                        game.setScreen(ScreenManager.screenStack.pop());
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void drawTransparentOverlay() {
        final Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.Alpha);
        pixmap.setColor(AbstractScreen.OVERLAY.r, AbstractScreen.OVERLAY.g, AbstractScreen.OVERLAY.b, AbstractScreen.OVERLAY.a);
        pixmap.fill();
        final TextureRegionDrawable bg = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        container.setBackground(bg);
    }

    private void drawBackground() {
        drawTransparentOverlay();

        final Texture backgroundTexture = game.getAssetManager().get(AssetDescriptors.BACKGROUND);
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        final Image background = new Image(backgroundTexture);
        background.setHeight(ClickerGameConfig.HEIGHT);
        stage.addActor(background);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        ClickerGameManager.INSTANCE.update(delta);
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
}
