package se.hampuscarlsson.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import se.hampuscarlsson.game.WorldManager;
import se.hampuscarlsson.game.components.TextureComponent;
import se.hampuscarlsson.game.components.TransformComponent;

import java.util.ArrayList;

public class RenderSystem extends IteratingSystem {
	private ArrayList<Entity> renderQueue = new ArrayList<>();
	private SpriteBatch batch;
	private ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
	private ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);
	private OrthographicCamera camera;

	private final boolean debug = false;
	Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

	public RenderSystem() {
		super(Family.all(TransformComponent.class, TextureComponent.class).get());
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		// Constructs a new OrthographicCamera, using the given viewport width and height
		// Height is multiplied by aspect ratio.
		camera = new OrthographicCamera(30, 30 * (h / w));

		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		this.batch = new SpriteBatch();
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		renderQueue.add(entity);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);



		batch.begin();
		for (Entity entity : renderQueue) {

			TextureComponent textureComponent = textureMapper.get(entity);
			TransformComponent transformComponent = transformMapper.get(entity);
			batch.draw(textureComponent.texture, transformComponent.position.x, transformComponent.position.y, transformComponent.size.x, transformComponent.size.y);
		}
		batch.end();
		if(debug) {
			debugRenderer.render(WorldManager.world, camera.combined);
		}
		renderQueue.clear();
	}
}
