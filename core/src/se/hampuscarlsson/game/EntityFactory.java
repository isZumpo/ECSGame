package se.hampuscarlsson.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import se.hampuscarlsson.game.components.*;

public class EntityFactory {
	public static Entity createPlayer(Texture img, World world, Vector2 position, Vector2 size) {
		Entity entity = new Entity();

		TransformComponent transformComponent = new TransformComponent();
		transformComponent.position.set(position);
		transformComponent.size.set(size);
		entity.add(transformComponent);

		TextureComponent textureComponent = new TextureComponent();
		textureComponent.texture = img;
		entity.add(textureComponent);

		GunComponent gunComponent = new GunComponent();
		gunComponent.ammo = 20;
		gunComponent.maxAmmo = 30;
		gunComponent.localPosition.set(size.x * 1.1f, size.y/2);
		entity.add(gunComponent);

		PhysicsComponent physicsComponent = new PhysicsComponent();
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(transformComponent.position.cpy());
		bodyDef.fixedRotation = true;
		physicsComponent.body = world.createBody(bodyDef);
		PolygonShape rectangle = new PolygonShape();
		rectangle.setAsBox(size.x/2, size.y/2, size.scl(0.5f), 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = rectangle;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.5f;
//		fixtureDef.restitution = 0.6f; // Make it bounce a little bit
		physicsComponent.body.createFixture(fixtureDef);
		rectangle.dispose();
		entity.add(physicsComponent);

		entity.add(new PlayerInputComponent());

		return entity;
	}

	public static Entity createSolid(Texture img, World world, Vector2 position, Vector2 size) {
		Entity entity = new Entity();

		TransformComponent transformComponent = new TransformComponent();
		transformComponent.position.set(position);
		transformComponent.size.set(size);
		entity.add(transformComponent);

		TextureComponent textureComponent = new TextureComponent();
		textureComponent.texture = img;
		entity.add(textureComponent);

		PhysicsComponent physicsComponent = new PhysicsComponent();
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(transformComponent.position.cpy());
		physicsComponent.body = world.createBody(bodyDef);
		PolygonShape rectangle = new PolygonShape();
		rectangle.setAsBox(size.x/2, size.y/2, size.scl(0.5f), 0);
		// Create a fixture from our polygon shape and add it to our ground body
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = rectangle;
		fixtureDef.density = 0.1f;
		fixtureDef.friction = 1f;
		physicsComponent.body.createFixture(fixtureDef);
		rectangle.dispose();
		entity.add(physicsComponent);

		entity.add(new PlayerInputComponent());

		return entity;
	}

	private static Texture bulletTexture = new Texture("textures/bullet.png");
	public static Entity createBullet(Vector2 position, Vector2 size, Vector2 velocity) {
		Entity entity = new Entity();

		TransformComponent transformComponent = new TransformComponent();
		transformComponent.position.set(position);
		transformComponent.size.set(size);
		entity.add(transformComponent);

		TextureComponent textureComponent = new TextureComponent();
		textureComponent.texture = bulletTexture;
		entity.add(textureComponent);

		DespawnComponent despawnComponent = new DespawnComponent();
		despawnComponent.timeRemaining = 5;
		entity.add(despawnComponent);

		PhysicsComponent physicsComponent = new PhysicsComponent();
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(transformComponent.position.cpy());
		bodyDef.fixedRotation = true;
		physicsComponent.body = WorldManager.world.createBody(bodyDef);
		PolygonShape rectangle = new PolygonShape();
		rectangle.setAsBox(size.x/2, size.y/2, size.scl(0.5f), 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = rectangle;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.5f;
//		fixtureDef.restitution = 0.6f; // Make it bounce a little bit
		physicsComponent.body.createFixture(fixtureDef);
		rectangle.dispose();
		physicsComponent.body.setLinearVelocity(velocity);
		entity.add(physicsComponent);
		return entity;
	}
}
