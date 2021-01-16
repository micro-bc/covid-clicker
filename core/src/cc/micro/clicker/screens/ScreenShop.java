package cc.micro.clicker.screens;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.ClickerGameManager;
import cc.micro.clicker.assets.AssetDescriptors;
import cc.micro.clicker.assets.RegionNames;
import cc.micro.clicker.util.AutoClicker;

import static cc.micro.clicker.screens.ScreenManager.game;
import static cc.micro.clicker.util.Tools._bi2shortStr;

/**
 * List of available items
 */
public class ScreenShop extends AbstractScreen {

    private ScrollPane itemsPane;

    @Override
    protected void setUp() {
        final Table table = new Table();
        itemsPane = new ScrollPane(table, skin);
        setPaneStyle();

        final TextureAtlas textureAtlas = game.getAssetManager().get(AssetDescriptors.MY_ATLAS);
        for (final String regionName : ClickerGameConfig.AUTO_CLICKERS.keySet()) {
            final AutoClicker autoClicker = ClickerGameConfig.AUTO_CLICKERS.get(regionName);
            final TextureAtlas.AtlasRegion books = textureAtlas.findRegion(RegionNames.KNJIGE1);
            autoClicker.setTextureRegion(textureAtlas.findRegion(regionName));
            table.row().height(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT);

            final Image icon = new Image(new SpriteDrawable(new Sprite(autoClicker.getAtlasRegion())));
            final Label price = new Label(_bi2shortStr(autoClicker.getPrice()) + ClickerGameConfig.CURRENCY_SYMBOL, skin);
            final Label cps = new Label('+' + _bi2shortStr(autoClicker.getCps()) + "cps", skin);
            final TextButton oneBtn = new TextButton("1x", skin);
            final TextButton tenBtn = new TextButton("10x", skin);
            table.add(icon)
                    .width(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.8f)
                    .height(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.8f)
                    .left()
                    .pad(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.1f);
            table.add(price)
                    .left();
            table.add(cps)
                    .right()
                    .expandX();
            table.add(oneBtn)
                    .right()
                    .width(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.7f)
                    .height(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.7f)
                    .pad(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.1f);
            table.add(tenBtn)
                    .right()
                    .width(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.7f)
                    .height(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.7f)
                    .pad(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.1f);

            oneBtn.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ClickerGameManager.INSTANCE.buyItem(regionName, 1);
                    return true;
                }
            });
            tenBtn.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ClickerGameManager.INSTANCE.buyItem(regionName, 10);
                    return true;
                }
            });
        }

        setLayout();
    }

    private void setPaneStyle() {
        ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle();

        /* Transparent Background */
        final Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.Alpha);
        pixmap.setColor(0, 0, 0, 0f);
        pixmap.fill();

        style.background = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        itemsPane.setStyle(style);
    }

    private void setLayout() {
        container.add(itemsPane).width(ClickerGameConfig.WIDTH);
    }

    @Override
    protected void update(float dt) {
    }
}
