package cc.micro.clicker.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.assets.AssetDescriptors;
import cc.micro.clicker.screens.actors.Clicker;
import cc.micro.clicker.screens.actors.MainMenu;
import cc.micro.clicker.screens.actors.ScoreHUD;

import static cc.micro.clicker.screens.ScreenManager.game;

/* TODO
        - Virus animation
        - Particles [ Optional ]
 */

/**
 * Game screen
 */
public class ScreenMain extends AbstractScreen {

    private ScoreHUD hud;
    private Clicker clicker;
    private MainMenu menu;

    @Override
    protected void setUp() {
        /* Add actors */
        hud = new ScoreHUD(container);
        clicker = new Clicker(container);
        menu = new MainMenu(container);

        super.drawTransparentOverlay(BLACK_OVERLAY);

        final Texture backgroundTexture = game.getAssetManager().get(AssetDescriptors.BACKGROUND);
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        final Image background = new Image(backgroundTexture);
        background.setHeight(ClickerGameConfig.HEIGHT);
        stage.addActor(background);
        stage.addActor(container);
    }

    @Override
    protected void update(float dt) {
        hud.update(dt);
    }
}
