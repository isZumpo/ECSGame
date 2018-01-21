package se.hampuscarlsson.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.hampuscarlsson.game.components.TextureComponent;
import se.hampuscarlsson.game.components.TransformComponent;

import java.util.ArrayList;

public class RenderSystem extends IteratingSystem {
	private ArrayList<Entity> renderQueue = new ArrayList<>();
	private SpriteBatch batch;
	private ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
	private ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);;

	public RenderSystem(SpriteBatch batch) {
		super(Family.all(TransformComponent.class, TextureComponent.class).get());
		this.batch = batch;
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		renderQueue.add(entity);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Entity entity : renderQueue) {
			TextureComponent textureComponent = textureMapper.get(entity);
			TransformComponent transformComponent = transformMapper.get(entity);
			batch.draw(textureComponent.texture, transformComponent.position.x, transformComponent.position.y);
		}
		batch.end();
		renderQueue.clear();
	}
}
