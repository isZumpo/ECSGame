package se.hampuscarlsson.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import se.hampuscarlsson.game.components.PhysicsComponent;
import se.hampuscarlsson.game.components.PlayerInputComponent;
import se.hampuscarlsson.game.components.TextureComponent;
import se.hampuscarlsson.game.components.TransformComponent;
import se.hampuscarlsson.game.systems.PhysicsSystem;
import se.hampuscarlsson.game.systems.PlayerInputSystem;
import se.hampuscarlsson.game.systems.RenderSystem;

public class ECSGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Engine engine;
	World world;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		engine = new Engine();
		world = new World(new Vector2(0, -10), true);

		// Create RenderSystem
		RenderSystem renderSystem = new RenderSystem(batch);
		engine.addSystem(renderSystem);

		// Create PhysicsSystem
		PhysicsSystem physicsSystem = new PhysicsSystem(world);
		engine.addSystem(physicsSystem);

		// Create PlayerInputSystem
		PlayerInputSystem playerInputSystem = new PlayerInputSystem();
		Gdx.input.setInputProcessor(playerInputSystem);
		engine.addSystem(playerInputSystem);


		// Create entity
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

		engine.addEntity(entity);


	}

	@Override
	public void render () {
		// Update ECS engine
		engine.update(Gdx.graphics.getDeltaTime());


//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
