package cc.micro.clicker.screens;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.assets.AssetDescriptors;
import cc.micro.clicker.assets.RegionNames;
import cc.micro.clicker.util.AutoClicker;

import static cc.micro.clicker.screens.ScreenManager.game;

/**
 * List of purchased items
 */
public class ScreenItems extends AbstractScreen {

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

            // TODO add extra info, format row
            // | image | Title | count | totalCps
            // Transparent bg possible?

            final Image icon = new Image(new SpriteDrawable(new Sprite(autoClicker.getAtlasRegion())));
            table.add(icon)
                    .width(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.8f)
                    .height(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.8f)
                    .left()
                    .expandX()
                    .pad(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.1f);
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
//        container.pad(10).defaults().expandX().space(4);
        container.add(itemsPane).width(ClickerGameConfig.WIDTH);
    }

    @Override
    protected void update(float dt) {
    }
}
