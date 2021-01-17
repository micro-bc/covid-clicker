package cc.micro.clicker.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import cc.micro.clicker.ClickerGameConfig;
import cc.micro.clicker.ClickerGameManager;
import cc.micro.clicker.util.Tools;

/**
 * Settings screen
 */
public class ScreenSettings extends AbstractScreen {

    private Label screenLabel;
    private Label totalClicksLabel;
    private Label totalTimeLabel;
    private Label totalClicks;
    private Label totalTime;

    private TextButton resetButton;

    @Override
    protected void setUp() {
        screenLabel = new Label("Settings", skin);
        totalClicksLabel = new Label("Total clicks:", skin);
        totalTimeLabel = new Label("Total play time:", skin);
        totalClicks = new Label("", skin);
        totalTime = new Label("", skin);
        resetButton = new TextButton("RESET", skin);

        addListeners();
        setLayout();
    }

    private void addListeners() {
        resetButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                showDialog();
                return true;
            }
        });
    }

    private void setLayout() {
        container.add(screenLabel)
                .colspan(2)
                .expandX()
                .height(ClickerGameConfig.HEIGHT * 0.1f)
                .row();
        container.add(totalClicksLabel)
                .left()
                .height(ClickerGameConfig.HEIGHT * 0.05f)
                .padLeft(ClickerGameConfig.WIDTH * 0.1f);
        container.add(totalClicks)
                .left()
                .height(ClickerGameConfig.HEIGHT * 0.05f)
                .row();
        container.add(totalTimeLabel)
                .left()
                .height(ClickerGameConfig.HEIGHT * 0.05f)
                .padLeft(ClickerGameConfig.WIDTH * 0.1f);
        container.add(totalTime)
                .left()
                .height(ClickerGameConfig.HEIGHT * 0.05f)
                .row();
        container.add(resetButton)
                .colspan(2)
                .expandY()
                .bottom()
                .width(ClickerGameConfig.WIDTH * 0.3f)
                .height(ClickerGameConfig.WIDTH * 0.2f)
                .fill()
                .pad(ClickerGameConfig.WIDTH * 0.1f);
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

        final Label message = new Label("Are you sure?", skin);
        final TextButton resetBtn = new TextButton("RESET", skin);
        final TextButton cancelBtn = new TextButton("Cancel", skin);

        resetBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ClickerGameManager.INSTANCE.reset();
                dialog.hide();
                dialog.cancel();
                dialog.remove();
                return true;
            }
        });
        cancelBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dialog.hide();
                dialog.cancel();
                dialog.remove();
                return true;
            }
        });

        dialog.getContentTable().add(message).padTop(40f);
        dialog.getButtonTable().add(cancelBtn).width(300f).height(150f).center().padBottom(80f);
        dialog.getButtonTable().add(resetBtn).width(300f).height(150f).center().padBottom(80f);
        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.setResizable(false);
        dialog.show(stage);

        stage.addActor(dialog);
    }

    @Override
    protected void update(float dt) {
        ClickerGameManager.INSTANCE.updateTotalTime();
        totalClicks.setText(ClickerGameManager.INSTANCE.getTotalClicks().toString());
        totalTime.setText(Tools._prettyTime(ClickerGameManager.INSTANCE.getTotalTime()));
    }
}
