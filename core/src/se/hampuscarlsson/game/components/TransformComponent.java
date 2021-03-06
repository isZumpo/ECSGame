package se.hampuscarlsson.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {
	public final Vector2 position = new Vector2();
	public final Vector2 scale = new Vector2(1.0f, 1.0f);
	public final Vector2 size = new Vector2(128f,64f);
	public float rotation = 0.0f;
	public boolean isHidden = false;
}