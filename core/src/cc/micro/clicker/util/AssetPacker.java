package cc.micro.clicker.util;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {
    private static final boolean DRAW_DEBUG_OUTLINE = false;
    private static final String ATLASNAME="myatlas";
    private static final String RAW_ASSETS_PATH = "raw";
    private static final String ASSETS_PATH = "android/assets";

    public static void main(String[] arg) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.debug = DRAW_DEBUG_OUTLINE;

        TexturePacker.processIfModified(
                settings,
                ASSETS_PATH + "/" + RAW_ASSETS_PATH,
                ASSETS_PATH + "/" + ATLASNAME,
                ATLASNAME
        );
        String file=ASSETS_PATH + ATLASNAME+"/"+ATLASNAME+".atlas";
    }
}
