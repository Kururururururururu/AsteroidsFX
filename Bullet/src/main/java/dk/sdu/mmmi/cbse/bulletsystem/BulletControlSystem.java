package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX);
            bullet.setY(bullet.getY() + changeY);

            System.out.println("Bullet X: " + bullet.getX());
            System.out.println("Bullet Y: " + bullet.getY());

            if(bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth() || bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight()){
                world.removeEntity(bullet);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Bullet bullet = new Bullet();

        setShape(bullet);

        bullet.setY(shooter.getY());
        bullet.setX(shooter.getX());

        bullet.setRotation(shooter.getRotation());

        return bullet;
    }

    private void setShape(Entity entity) {
        entity.setPolygonCoordinates(-2, -2, 5, 0, -2, -2);
    }

}
