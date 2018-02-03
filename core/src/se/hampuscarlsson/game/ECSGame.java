package se.hampuscarlsson.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import se.hampuscarlsson.game.systems.PhysicsSystem;
import se.hampuscarlsson.game.systems.PlayerInputSystem;
import se.hampuscarlsson.game.systems.RenderSystem;

public class ECSGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture brick;
	Engine engine;
	World world;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		brick = new Texture("textures/brick.png");
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
		engine.addEntity(EntityFactory.createPlayer(img, world, new Vector2(50, 50), new Vector2(64,64)));

		//Create solid
		engine.addEntity(EntityFactory.createSolid(brick, world, new Vector2(50,10), new Vector2(128, 64)));


	}

	@Override
	public void render () {
		// Update ECS engine
		engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
