package cc.micro.clicker.screens;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.math.BigInteger;

import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.ClickerGameManager;
import cc.micro.clicker.assets.AssetDescriptors;
import cc.micro.clicker.util.AutoClicker;

import static cc.micro.clicker.screens.ScreenManager.game;
import static cc.micro.clicker.util.Tools._bi2shortStr;

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
        for (final String regionName : AutoClicker.AUTO_CLICKERS.keySet()) {
            final AutoClicker autoClicker = AutoClicker.AUTO_CLICKERS.get(regionName);
            table.row().height(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT);

            final Image icon = new Image(new SpriteDrawable(new Sprite(autoClicker.getAtlasRegion())));
            final Label name = new Label(autoClicker.getName(), skin);
            final Label count = new Label("", skin);
            final Label totalCps = new Label("", skin);
            table.add(icon)
                    .width(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.8f)
                    .height(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.8f)
                    .left()
                    .pad(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.1f);
            table.add(name)
                    .left();
            table.add(count)
                    .right()
                    .expandX();
            table.add(totalCps)
                    .right()
                    .expandX()
                    .padRight(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.2f);
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
        container.add(itemsPane).width(ClickerGameConfig.WIDTH).maxHeight(ClickerGameConfig.HEIGHT);
    }

    @Override
    protected void update(float dt) {
        final Table table = (Table) itemsPane.getActor();
        final Object[] keys = AutoClicker.AUTO_CLICKERS.keySet().toArray();

        AutoClicker autoClicker = null;
        int count = 0;
        for (int i = 0; i < table.getCells().size; i++) {
            final Cell<?> cell = table.getCells().get(i);
            switch (i % 4) {
                case 0: // ICON
                    autoClicker = AutoClicker.AUTO_CLICKERS.get(keys[i / 4]);
                case 1: // TITLE
                    break;
                case 2: // COUNT
                    count = ClickerGameManager.INSTANCE.getItemCount(autoClicker.getRegion());
                    ((Label) cell.getActor()).setText(count);
                    break;
                case 3: // TOTAL_CPS
                    ((Label) cell.getActor()).setText(_bi2shortStr(BigInteger.valueOf(count).multiply(autoClicker.getCps())) + "cps");
                    break;
            }
        }
    }
}
