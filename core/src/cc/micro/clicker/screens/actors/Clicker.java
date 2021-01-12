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
import cc.micro.clicker.ClickerGameManager;
import cc.micro.clicker.assets.AssetDescriptors;
import cc.micro.clicker.assets.RegionNames;

import static cc.micro.clicker.screens.ScreenManager.game;

public class Clicker extends AbstractActorGroup {
    private static final float MAX_HEIGHT = ClickerGameConfig.HEIGHT
            - 2 * ClickerGameConfig.HEIGHT_HUD_ITEM
            - ClickerGameConfig.HEIGHT_MENU_ITEM
            - 2 * ClickerGameConfig.PAD_MENU_ITEM;

    private ImageButton virusButton;

    public Clicker(@NotNull final Table table) {
        super(table);
    }

    @Override
    protected void create() {
        final TextureAtlas textureAtlas = game.getAssetManager().get(AssetDescriptors.MY_ATLAS);
        final TextureAtlas.AtlasRegion virus = textureAtlas.findRegion(RegionNames.ZELENI_VIRUS1);
        virusButton = new ImageButton(new SpriteDrawable(new Sprite(virus)));
    }

    @Override
    protected void setLayout() {
        final Table table = (Table) super.layout;
        table.add(virusButton)
                .colspan(3)
                .maxWidth(ClickerGameConfig.WIDTH * ClickerGameConfig.VIRUS_SCALE)
                .maxHeight(MAX_HEIGHT)
                .expandY()
                .row();
    }

    @Override
    protected void setListeners() {
        virusButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ClickerGameManager.INSTANCE.click(1);
                return true;
            }
        });
    }

    @Override
    public void update(float dt) {
    }
}
