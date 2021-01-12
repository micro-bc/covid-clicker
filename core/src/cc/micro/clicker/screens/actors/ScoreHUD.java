package cc.micro.clicker.screens.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import org.jetbrains.annotations.NotNull;

import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.ClickerGameManager;
import cc.micro.clicker.assets.AssetDescriptors;
import cc.micro.clicker.screens.ScreenManager;

public class ScoreHUD extends AbstractActorGroup {
    private Label clicksLabel;
    private Label cpsLabel;

    public ScoreHUD(@NotNull final Table table) {
        super(table);
    }

    @Override
    protected void create() {
        final Skin skin = ScreenManager.game.getAssetManager().get(AssetDescriptors.SKIN);
        clicksLabel = new Label("", skin);
        cpsLabel = new Label("", skin);
    }

    @Override
    protected void setLayout() {
        final Table table = (Table) super.layout;
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
    }

    @Override
    public void update(float dt) {
        clicksLabel.setText(String.valueOf(ClickerGameManager.INSTANCE.getClicks()));
        cpsLabel.setText(ClickerGameManager.INSTANCE.getCps() + "cps");
    }

    @Override
    protected void setListeners() {
    }
}
