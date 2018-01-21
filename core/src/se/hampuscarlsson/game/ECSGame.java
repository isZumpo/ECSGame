package se.hampuscarlsson.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.hampuscarlsson.game.components.TextureComponent;
import se.hampuscarlsson.game.components.TransformComponent;
import se.hampuscarlsson.game.systems.RenderSystem;

public class ECSGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Engine engine;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		engine = new Engine();

		// Create RenderSystem
		RenderSystem renderSystem = new RenderSystem(batch);
		engine.addSystem(renderSystem);


		// Create entity
		Entity entity = new Entity();

		TransformComponent transformComponent = new TransformComponent();
		transformComponent.position.set(50, 50);
		entity.add(transformComponent);

		TextureComponent textureComponent = new TextureComponent();
		textureComponent.texture = img;
		entity.add(textureComponent);

		engine.addEntity(entity);
//		System.out.println(entity.getComponents().toString());
//		System.out.println(engine.getEntities().get(0).getComponents().toString());
//		System.out.println(engine.getEntitiesFor(Family.all(TransformComponent.class, TextureComponent.class).get()).get(0).getComponents().toString());

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
