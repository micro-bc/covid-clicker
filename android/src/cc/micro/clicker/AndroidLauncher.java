package cc.micro.clicker;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {

    final ClickerGame game = new ClickerGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(game, config);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ClickerGameManager.INSTANCE.updateTotalTime();
        ClickerGameManager.INSTANCE.saveState();
    }
}
