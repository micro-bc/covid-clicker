package cc.micro.clicker.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetDescriptors {
    public static final AssetDescriptor<TextureAtlas> MY_ATLAS = new AssetDescriptor<>(AssetPath.MY_ATLAS, TextureAtlas.class);

    public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<>(AssetPath.SKIN, Skin.class);
    public static final AssetDescriptor<TextureAtlas> SKIN_ATLAS = new AssetDescriptor<>(AssetPath.SKIN_ATLAS, TextureAtlas.class);

    public static final AssetDescriptor<Texture> BACKGROUND = new AssetDescriptor<>(AssetPath.BACKGROUND, Texture.class);

    public static final AssetDescriptor<Sound> SOUND_BOOK_FLIPPING = new AssetDescriptor<>(AssetPath.SOUND_BOOK_FLIPPING, Sound.class);

    public static final AssetDescriptor<Sound> SOUND_COUGH_1 = new AssetDescriptor<>(AssetPath.SOUND_COUGH_1, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_COUGH_2 = new AssetDescriptor<>(AssetPath.SOUND_COUGH_2, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_COUGH_3 = new AssetDescriptor<>(AssetPath.SOUND_COUGH_3, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_COUGH_4 = new AssetDescriptor<>(AssetPath.SOUND_COUGH_4, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_COUGH_5 = new AssetDescriptor<>(AssetPath.SOUND_COUGH_5, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_COUGH_6 = new AssetDescriptor<>(AssetPath.SOUND_COUGH_6, Sound.class);

    public static final AssetDescriptor<Sound> SOUND_SNEEZE_1 = new AssetDescriptor<>(AssetPath.SOUND_SNEEZE_1, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_SNEEZE_2 = new AssetDescriptor<>(AssetPath.SOUND_SNEEZE_2, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_SNEEZE_3 = new AssetDescriptor<>(AssetPath.SOUND_SNEEZE_3, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_SNEEZE_4 = new AssetDescriptor<>(AssetPath.SOUND_SNEEZE_4, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_SNEEZE_5 = new AssetDescriptor<>(AssetPath.SOUND_SNEEZE_5, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_SNEEZE_6 = new AssetDescriptor<>(AssetPath.SOUND_SNEEZE_6, Sound.class);

    public static final AssetDescriptor<Sound> SOUND_TEST_TUBE_1 = new AssetDescriptor<>(AssetPath.SOUND_TEST_TUBE_1, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_TEST_TUBE_2 = new AssetDescriptor<>(AssetPath.SOUND_TEST_TUBE_2, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_TEST_TUBE_3 = new AssetDescriptor<>(AssetPath.SOUND_TEST_TUBE_3, Sound.class);

    public static final AssetDescriptor<Sound> SOUND_BUBBLING_1 = new AssetDescriptor<>(AssetPath.SOUND_BUBBLING_1, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_BUBBLING_2 = new AssetDescriptor<>(AssetPath.SOUND_BUBBLING_2, Sound.class);

    public static final AssetDescriptor<Sound> SOUND_TABLETS_1 = new AssetDescriptor<>(AssetPath.SOUND_TABLETS_1, Sound.class);
}
