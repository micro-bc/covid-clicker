package cc.micro.clicker.screens.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import org.jetbrains.annotations.NotNull;

import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.assets.AssetDescriptors;
import cc.micro.clicker.assets.RegionNames;

import static cc.micro.clicker.screens.ScreenManager.ITEMS_SCREEN;
import static cc.micro.clicker.screens.ScreenManager.SETTINGS_SCREEN;
import static cc.micro.clicker.screens.ScreenManager.SHOP_SCREEN;
import static cc.micro.clicker.screens.ScreenManager.game;
import static cc.micro.clicker.screens.ScreenManager.screens;

public class MainMenu extends AbstractActorGroup {
    private static final float MENU_ITEM_WIDTH = ClickerGameConfig.WIDTH / 3f - 2 * ClickerGameConfig.PAD_MENU_ITEM - 2;

    private ImageButton itemsButton;
    private ImageButton shopButton;
    private ImageButton settingsButton;

    public MainMenu(@NotNull final Table table) {
        super(table);
    }

    @Override
    protected void create() {
        final TextureAtlas textureAtlas = game.getAssetManager().get(AssetDescriptors.MY_ATLAS);
        final TextureAtlas.AtlasRegion books = textureAtlas.findRegion(RegionNames.KNJIGE1);
        final TextureAtlas.AtlasRegion plus = textureAtlas.findRegion(RegionNames.KNJIGE2);
        final TextureAtlas.AtlasRegion settings = textureAtlas.findRegion(RegionNames.MIKROSKOP);
        itemsButton = new ImageButton(new SpriteDrawable(new Sprite(books)));
        shopButton = new ImageButton(new SpriteDrawable(new Sprite(plus)));
        settingsButton = new ImageButton(new SpriteDrawable(new Sprite(settings)));
    }

    @Override
    protected void setLayout() {
        final Table table = (Table) super.layout;
        table.add(itemsButton)
                .width(MENU_ITEM_WIDTH)
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM)
                .fill()
                .pad(ClickerGameConfig.PAD_MENU_ITEM);
        table.add(shopButton)
                .width(MENU_ITEM_WIDTH)
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM)
                .fill()
                .pad(ClickerGameConfig.PAD_MENU_ITEM);
        table.add(settingsButton)
                .width(MENU_ITEM_WIDTH)
                .height(ClickerGameConfig.HEIGHT_MENU_ITEM)
                .fill()
                .pad(ClickerGameConfig.PAD_MENU_ITEM);
    }

    @Override
    protected void setListeners() {
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
    public void update(float dt) { }
}
