package cc.micro.clicker.screens.actors;

import com.badlogic.gdx.scenes.scene2d.utils.Layout;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractActorGroup {

    @NotNull
    protected final Layout layout;

    public AbstractActorGroup(@NotNull final Layout layout) {
        this.layout = layout;
        create();
        setLayout();
        setListeners();
    }

    protected abstract void create();

    protected abstract void setLayout();

    protected abstract void setListeners();
}
