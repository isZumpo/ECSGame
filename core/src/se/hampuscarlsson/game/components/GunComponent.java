package se.hampuscarlsson.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class GunComponent implements Component{
    public int ammo;
    public int maxAmmo;
    public boolean fire = false;
    public final Vector2 localPosition = new Vector2();
}
