package cc.micro.clicker.screens;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.math.BigInteger;

import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.ClickerGameManager;
import cc.micro.clicker.assets.AssetDescriptors;
import cc.micro.clicker.assets.RegionNames;

import static cc.micro.clicker.screens.ScreenManager.ITEMS_SCREEN;
import static cc.micro.clicker.screens.ScreenManager.SETTINGS_SCREEN;
import static cc.micro.clicker.screens.ScreenManager.SHOP_SCREEN;
import static cc.micro.clicker.screens.ScreenManager.game;
import static cc.micro.clicker.screens.ScreenManager.screens;
import static cc.micro.clicker.util.Tools._bi2shortStr;

/**
 * Game screen
 */
public class ScreenMain extends AbstractScreen {
    private static final float MAX_CLICKER_HEIGHT = ClickerGameConfig.HEIGHT
            - 2 * ClickerGameConfig.HEIGHT_HUD_ITEM
            - ClickerGameConfig.HEIGHT_MENU_ITEM;
    private static final float MENU_ITEM_WIDTH = ClickerGameConfig.WIDTH / 3f;

    private Label clicksLabel;
    private Label cpsLabel;

    private ImageButton clickerButton;

    private Label shopLabel;
    private Label itemsLabel;
    private Label settingsLabel;
    private ImageButton itemsButton;
    private ImageButton shopButton;
    private ImageButton settingsButton;

    @Override
    protected void setUp() {
        final TextureAtlas textureAtlas = game.getAssetManager().get(AssetDescriptors.MY_ATLAS);
        final TextureAtlas.AtlasRegion books = textureAtlas.findRegion(RegionNames.KNJIGE1);
        final TextureAtlas.AtlasRegion virus = textureAtlas.findRegion(RegionNames.ZELENI_VIRUS1);
        final TextureAtlas.AtlasRegion plus = textureAtlas.findRegion(RegionNames.KNJIGE2);
        final TextureAtlas.AtlasRegion settings = textureAtlas.findRegion(RegionNames.MIKROSKOP);

        clicksLabel = new Label("", skin);
        cpsLabel = new Label("", skin);
        clickerButton = new ImageButton(new SpriteDrawable(new Sprite(virus)));
        itemsButton = new ImageButton(new SpriteDrawable(new Sprite(books)));
        shopButton = new ImageButton(new SpriteDrawable(new Sprite(plus)));
        settingsButton = new ImageButton(new SpriteDrawable(new Sprite(settings)));
        itemsLabel = new Label("Items", skin);
        shopLabel = new Label("Shop", skin);
        settingsLabel = new Label("Settings", skin);

        addListeners();
        setLayout();
    }

    private void setLayout() {
        container.add(clicksLabel)
                .colspan(3)
                .expandX()
                .height(ClickerGameConfig.HEIGHT_HUD_ITEM)
                .row();
        container.add(cpsLabel)
                .colspan(3)
                .expandX()
                .height(ClickerGameConfig.HEIGHT_HUD_ITEM)
                .row();
        container.add(clickerButton)
                .colspan(3)
                .maxWidth(ClickerGameConfig.WIDTH * ClickerGameConfig.VIRUS_SCALE)
                .maxHeight(MAX_CLICKER_HEIGHT)
                .expandY()
                .row();
        container.add(itemsButton)
                .width(MENU_ITEM_WIDTH)
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM)
                .fill();
        container.add(shopButton)
                .width(MENU_ITEM_WIDTH)
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM)
                .fill();
        container.add(settingsButton)
                .width(MENU_ITEM_WIDTH)
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM)
                .fill()
                .row();
        container.add(itemsLabel)
                .center()
                .expandX()
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM * 0.5f);
        container.add(shopLabel)
                .expandX()
                .center()
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM * 0.5f);
        container.add(settingsLabel)
                .center()
                .expandX()
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM * 0.5f);
    }

    private void addListeners() {
        clickerButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ClickerGameManager.INSTANCE.click(BigInteger.valueOf(1));
                return true;
            }
        });
        itemsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(screens.get(ITEMS_SCREEN));
                return true;
            }
        });
        shopButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(screens.get(SHOP_SCREEN));
                return true;
            }
        });
        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(screens.get(SETTINGS_SCREEN));
                return true;
            }
        });
    }

    @Override
    protected void update(float dt) {
        clicksLabel.setText(_bi2shortStr(ClickerGameManager.INSTANCE.getClicks()) + ClickerGameConfig.CURRENCY_SYMBOL);
        cpsLabel.setText(_bi2shortStr(ClickerGameManager.INSTANCE.getCps()) + "cps");
        // TODO: Virus animation [ OPTIONAL ]
        // TODO: Item icon particles [ OPTIONAL ]
    }
}
