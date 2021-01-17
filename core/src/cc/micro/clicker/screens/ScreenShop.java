package cc.micro.clicker.screens;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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
        for (final String regionName : AutoClicker.AUTO_CLICKERS.keySet()) {
            final AutoClicker autoClicker = AutoClicker.AUTO_CLICKERS.get(regionName);
            final TextureAtlas.AtlasRegion books = textureAtlas.findRegion(RegionNames.KNJIGE1);
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
                    .width(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.6f)
                    .height(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.6f)
                    .pad(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.1f);
            table.add(tenBtn)
                    .right()
                    .width(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.6f)
                    .height(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.6f)
                    .pad(ClickerGameConfig.SCROLL_PANE_ROW_HEIGHT * 0.1f);

            oneBtn.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (ClickerGameManager.INSTANCE.buyItem(regionName, 1)) {
                        showDialog();
                    }
                    return true;
                }
            });
            tenBtn.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (ClickerGameManager.INSTANCE.buyItem(regionName, 10)) {
                        showDialog();
                    }
                    return true;
                }
            });
        }
        setLayout();
    }

    private void showDialog() {
        final Dialog dialog = new Dialog("", skin) {
            @Override
            public float getPrefWidth() {
                return ClickerGameConfig.WIDTH * 0.8f;
            }

            @Override
            public float getPrefHeight() {
                return ClickerGameConfig.WIDTH * 0.5f;
            }
        };

        final Label message = new Label("Insufficient clicks!", skin);
        final TextButton dismissBtn = new TextButton("Dismiss", skin);
        dismissBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dialog.hide();
                dialog.cancel();
                dialog.remove();
                return true;
            }
        });

        dialog.getContentTable().add(message).padTop(40f);
        dialog.getButtonTable().add(dismissBtn).width(300f).height(150f).center().padBottom(80f);
        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.setResizable(false);
        dialog.show(stage);

        stage.addActor(dialog);
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
