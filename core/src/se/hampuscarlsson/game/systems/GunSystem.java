package se.hampuscarlsson.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import se.hampuscarlsson.game.EntityFactory;
import se.hampuscarlsson.game.WorldManager;
import se.hampuscarlsson.game.components.GunComponent;
import se.hampuscarlsson.game.components.TransformComponent;

public class GunSystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<GunComponent> gunMapper = ComponentMapper.getFor(GunComponent.class);

    public GunSystem() {
        super(Family.all(TransformComponent.class, GunComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        GunComponent gunComponent = gunMapper.get(entity);
        if(gunComponent.fire) {
            gunComponent.fire = false;
            if(gunComponent.ammo > 0) {
                TransformComponent transformComponent = transformMapper.get(entity);
                WorldManager.engine.addEntity(EntityFactory.createBullet(transformComponent.position.cpy().add(gunComponent.localPosition), new Vector2(0.3f, 0.3f), new Vector2(25, 0).scl(gunComponent.direction)));
            }
        }
    }
}
