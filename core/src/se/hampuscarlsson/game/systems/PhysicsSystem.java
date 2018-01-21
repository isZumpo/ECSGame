package se.hampuscarlsson.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import se.hampuscarlsson.game.components.PhysicsComponent;
import se.hampuscarlsson.game.components.TransformComponent;

import java.util.ArrayList;

public class PhysicsSystem extends IteratingSystem{
	private ArrayList<Entity> physicsQueue = new ArrayList<>();
	private World world;

	private ComponentMapper<PhysicsComponent> physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);
	private ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);

	public PhysicsSystem(World world) {
		super(Family.all(PhysicsComponent.class, TransformComponent.class).get());
		this.world = world;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		physicsQueue.add(entity);

	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		world.step(1/60f, 6, 2);

		for(Entity entity : physicsQueue) {
			PhysicsComponent physicsComponent = physicsMapper.get(entity);
			TransformComponent transformComponent = transformMapper.get(entity);

			Vector2 position = physicsComponent.body.getPosition();
			transformComponent.position.set(position);
			transformComponent.rotation = physicsComponent.body.getAngle() * MathUtils.radiansToDegrees;
		}
	}
}
