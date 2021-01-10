package cc.micro.clicker.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;

import org.jetbrains.annotations.NotNull;

import cc.micro.clicker.ClickerGame;
import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.assets.AssetDescriptors;

/* TODO
        - Update hud
        - Add menu item click actions
        - Virus animation
        - Virus click action
        - Particles (Optional)
 */

/**
 * Game screen
 */
public class ScreenMain extends ScreenAdapter {
    @NotNull
    private static final Logger log = new Logger(ScreenMain.class.getSimpleName(), ClickerGameConfig.DEBUG);

    @NotNull
    private final AssetManager assetManager;

    @NotNull
    private final OrthographicCamera camera = new OrthographicCamera(ClickerGameConfig.WIDTH, ClickerGameConfig.HEIGHT);
    @NotNull
    private final Stage stage;

    public ScreenMain(@NotNull final ClickerGame game) {
        this.stage = new Stage(new FitViewport(camera.viewportWidth, camera.viewportHeight, camera), game.getBatch());
        this.assetManager = game.getAssetManager();
        this.create();
    }

    public void create() {
        final Skin skin = assetManager.get(AssetDescriptors.SKIN);
        skin.setScale(ClickerGameConfig.SCALE);
        skin.getFont("default-font").getData().setScale(ClickerGameConfig.SCALE * 1.3f);
        skin.addRegions(assetManager.get(AssetDescriptors.SKIN_ATLAS));

        final Table table = new Table();
        table.setSkin(skin);
        table.setFillParent(true);
        table.setDebug(ClickerGameConfig.DEBUG != Logger.NONE);

        final TextureAtlas textureAtlas = assetManager.get(AssetDescriptors.MY_ATLAS);

        /* HUD */
        final Label clicksLabel = new Label("12512312311", skin); // TODO fix font
        final Label cpsLabel = new Label("111.4k cps", skin);
        table.add(clicksLabel)
                .colspan(3)
                .expandX()
                .height(ClickerGameConfig.HEIGHT_HUD_ITEM)
                .row();
        table.add(cpsLabel)
                .colspan(3)
                .expandX()
                .height(ClickerGameConfig.HEIGHT_HUD_ITEM)
                .row();

        /* Game */
        final TextureAtlas.AtlasRegion virus = textureAtlas.findRegion("zeleni_virus");
        final ImageButton virusButton = new ImageButton(new SpriteDrawable(new Sprite(virus)));
        final float maxVirusHeight = ClickerGameConfig.HEIGHT
                - 2 * ClickerGameConfig.HEIGHT_HUD_ITEM
                - ClickerGameConfig.HEIGHT_MENU_ITEM
                - 2 * ClickerGameConfig.PAD_MENU_ITEM;
        table.add(virusButton).colspan(3).maxWidth(ClickerGameConfig.WIDTH * ClickerGameConfig.VIRUS_SCALE).expandY().row();

        /* Menu */
        final float menuItemWidth = ClickerGameConfig.WIDTH / 3f - 2 * ClickerGameConfig.PAD_MENU_ITEM - 2;
        final TextureAtlas.AtlasRegion knjige = textureAtlas.findRegion("knjige1");
        final TextureAtlas.AtlasRegion plus = textureAtlas.findRegion("knjige2");
        final TextureAtlas.AtlasRegion settings = textureAtlas.findRegion("mikroskop");
        final ImageButton itemListButton = new ImageButton(new SpriteDrawable(new Sprite(knjige)));
        final ImageButton buyItemButton = new ImageButton(new SpriteDrawable(new Sprite(plus)));
        final ImageButton settingsButton = new ImageButton(new SpriteDrawable(new Sprite(settings)));

        table.add(itemListButton)
                .width(menuItemWidth)
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM)
                .fill()
                .pad(ClickerGameConfig.PAD_MENU_ITEM);
        table.add(buyItemButton)
                .width(menuItemWidth)
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM)
                .fill()
                .pad(ClickerGameConfig.PAD_MENU_ITEM);
        table.add(settingsButton)
                .width(menuItemWidth)
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM)
                .fill()
                .pad(ClickerGameConfig.PAD_MENU_ITEM);

        /* Background transparency */
        final Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.Alpha);
        bgPixmap.setColor(0, 0, 0, 0.7f);
        bgPixmap.fill();
        final TextureRegionDrawable bg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        table.setBackground(bg);

        final Texture backgroundTexture = assetManager.get(AssetDescriptors.BACKGROUND);
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        final Image background = new Image(backgroundTexture);
        background.setHeight(ClickerGameConfig.HEIGHT);
        stage.addActor(background);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
//        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
