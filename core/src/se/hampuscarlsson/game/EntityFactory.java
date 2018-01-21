package se.hampuscarlsson.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import se.hampuscarlsson.game.components.PhysicsComponent;
import se.hampuscarlsson.game.components.PlayerInputComponent;
import se.hampuscarlsson.game.components.TextureComponent;
import se.hampuscarlsson.game.components.TransformComponent;

public class EntityFactory {
	public static Entity createPlayer(Texture img, World world) {
		Entity entity = new Entity();

		TransformComponent transformComponent = new TransformComponent();
		transformComponent.position.set(50, 50);
		entity.add(transformComponent);

		TextureComponent textureComponent = new TextureComponent();
		textureComponent.texture = img;
		entity.add(textureComponent);

		PhysicsComponent physicsComponent = new PhysicsComponent();
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(transformComponent.position.cpy());
		physicsComponent.body = world.createBody(bodyDef);
		CircleShape circle = new CircleShape();
		circle.setRadius(6f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 2f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f; // Make it bounce a little bit
//		Fixture fixture = physicsComponent.body.createFixture(fixtureDef);
		circle.dispose();
		entity.add(physicsComponent);

		entity.add(new PlayerInputComponent());

		return entity;
	}

	public static Entity createSolid(Texture img, World world, Vector2 position) {
		Entity entity = new Entity();

		TransformComponent transformComponent = new TransformComponent();
		transformComponent.position.set(position);
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
		rectangle.setAsBox(2000, 10.0f);
		// Create a fixture from our polygon shape and add it to our ground body
		physicsComponent.body.createFixture(rectangle, 0.0f);
		rectangle.dispose();
		entity.add(physicsComponent);

		entity.add(new PlayerInputComponent());

		return entity;
	}
}
