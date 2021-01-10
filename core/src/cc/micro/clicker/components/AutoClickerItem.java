package cc.micro.clicker.components;

import com.badlogic.gdx.graphics.Texture;

import org.jetbrains.annotations.NotNull;

public final class AutoClickerItem {
    private final int cps;
    private final int price;
    private final Texture texture;

    public AutoClickerItem(int cps, int price, final Texture texture) {
        this.cps = cps;
        this.price = price;
        this.texture = texture;
    }

    public final int getCps() {
        return cps;
    }

    public final int getPrice() {
        return price;
    }

    @NotNull
    public final Texture getTexture() {
        return texture;
    }
}