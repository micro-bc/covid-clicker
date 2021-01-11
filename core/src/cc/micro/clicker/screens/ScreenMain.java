package cc.micro.clicker.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.assets.AssetDescriptors;
import cc.micro.clicker.screens.actors.Clicker;
import cc.micro.clicker.screens.actors.MainMenu;
import cc.micro.clicker.screens.actors.ScoreHUD;

import static cc.micro.clicker.screens.ScreenManager.*;

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
public class ScreenMain extends AbstractScreen<Table> {
    public ScreenMain() {
        super(new Table());
    }

    @Override
    protected void setUp() {
        /* Add actors */
        new ScoreHUD(layout);
        new Clicker(layout);
        new MainMenu(layout);

        super.drawTransparentOverlay(BLACK_OVERLAY);

        final Texture backgroundTexture = game.getAssetManager().get(AssetDescriptors.BACKGROUND);
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        final Image background = new Image(backgroundTexture);
        background.setHeight(ClickerGameConfig.HEIGHT);
        stage.addActor(background);
        stage.addActor(layout);
    }
}
