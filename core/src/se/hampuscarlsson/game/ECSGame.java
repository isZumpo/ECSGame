package se.hampuscarlsson.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import se.hampuscarlsson.game.systems.*;

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
		engine = WorldManager.engine;
		world = WorldManager.world;

		// Create RenderSystem
		RenderSystem renderSystem = new RenderSystem();
		engine.addSystem(renderSystem);

		// Create PhysicsSystem
		PhysicsSystem physicsSystem = new PhysicsSystem(world);
		engine.addSystem(physicsSystem);

		// Create PlayerInputSystem
		PlayerInputSystem playerInputSystem = new PlayerInputSystem();
		Gdx.input.setInputProcessor(playerInputSystem);
		engine.addSystem(playerInputSystem);

		// Create GunSystem
		GunSystem gunSystem = new GunSystem();
		engine.addSystem(gunSystem);

		// Create DespawnSystem
		DespawnSystem despawnSystem = new DespawnSystem();
		engine.addSystem(despawnSystem);

		// Create entity
		engine.addEntity(EntityFactory.createPlayer(img, world, new Vector2(0, 3), new Vector2(0.8f,2f)));

		//Create solid
		engine.addEntity(EntityFactory.createSolid(brick, world, new Vector2(0,1), new Vector2(40f, 1f)));


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
