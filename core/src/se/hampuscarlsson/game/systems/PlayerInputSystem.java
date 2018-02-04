package se.hampuscarlsson.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import se.hampuscarlsson.game.components.PhysicsComponent;
import se.hampuscarlsson.game.components.PlayerInputComponent;

public class PlayerInputSystem extends IteratingSystem implements InputProcessor{
	private boolean leftIsPressed = false;
	private boolean rightIsPressed = false;

	private ComponentMapper<PhysicsComponent> physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);
	public PlayerInputSystem() {
		super(Family.all(PhysicsComponent.class, PlayerInputComponent.class).get());
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		float force = 0;
		if(leftIsPressed) {
			force -= 20;
		}
		if(rightIsPressed) {
			force += 20;
		}

		physicsMapper.get(entity).body.applyForceToCenter(force,0,true);
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case(Input.Keys.LEFT):
				leftIsPressed = true;
				break;
			case(Input.Keys.RIGHT):
				rightIsPressed = true;
				break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case(Input.Keys.LEFT):
				leftIsPressed = false;
				break;
			case(Input.Keys.RIGHT):
				rightIsPressed = false;
				break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
