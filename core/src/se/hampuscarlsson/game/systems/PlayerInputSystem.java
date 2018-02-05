package se.hampuscarlsson.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import se.hampuscarlsson.game.components.GunComponent;
import se.hampuscarlsson.game.components.PhysicsComponent;
import se.hampuscarlsson.game.components.PlayerInputComponent;

public class PlayerInputSystem extends IteratingSystem implements InputProcessor{
	private boolean leftIsPressed = false;
	private boolean rightIsPressed = false;
	private boolean upIsPressed = false;
	private boolean spaceIsPressed = false;

	private ComponentMapper<PhysicsComponent> physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);
	private ComponentMapper<GunComponent> gunMapper = ComponentMapper.getFor(GunComponent.class);
	public PlayerInputSystem() {
		super(Family.all(PhysicsComponent.class, PlayerInputComponent.class, GunComponent.class).get());
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		float forceX = 0;
		float forceY = 0;
		Body physicsBody = physicsMapper.get(entity).body;
		if(leftIsPressed) {
			forceX -= 10;
		}
		if(rightIsPressed) {
			forceX += 10;
		}
		if(upIsPressed) {
			forceY = 10;
		}
		if(spaceIsPressed) {
			GunComponent gunComponent = gunMapper.get(entity);
			gunComponent.fire = true;
			spaceIsPressed = false;
		}

		if(physicsBody.getLinearVelocity().y == 0) {
			physicsBody.setLinearVelocity(forceX, forceY);
		} else {
			physicsBody.setLinearVelocity(forceX, physicsBody.getLinearVelocity().y);
		}
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
			case(Input.Keys.UP):
				upIsPressed = true;
				break;
			case(Input.Keys.SPACE):
				spaceIsPressed = true;
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
			case(Input.Keys.UP):
				upIsPressed = false;
				break;
			case(Input.Keys.SPACE):
				spaceIsPressed = false;
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
