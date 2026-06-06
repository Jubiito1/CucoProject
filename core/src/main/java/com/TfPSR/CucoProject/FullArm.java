package com.TfPSR.CucoProject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class FullArm {
    private final Body hand;
    private final Body forearm;
    private final Body arm;

    public FullArm(Vector2 position, Vector2 size, float angle, BodyDef.BodyType type, World world, float density, float friction, float restitution) {
        hand = ShapeFactory.createRectangle(position, size, angle, type, world, density, friction, restitution);
        forearm = ShapeFactory.createRectangle(position, new Vector2(0.5f, 2f), angle, type, world, density, friction, restitution);
        arm = ShapeFactory.createRectangle(position, new Vector2(0.5f, 2f), angle, type, world, density, friction, restitution);

        RevoluteJointDef revoluteJointDef1 = new RevoluteJointDef();
        revoluteJointDef1.bodyA = hand;
        revoluteJointDef1.bodyB = forearm;
        revoluteJointDef1.collideConnected = true;
        revoluteJointDef1.localAnchorA.set(new Vector2(0, size.y/1.5f));
        revoluteJointDef1.localAnchorB.set(new Vector2(0, size.y));
        world.createJoint(revoluteJointDef1);

        RevoluteJointDef revoluteJointDef2 = new RevoluteJointDef();
        revoluteJointDef2.bodyA = forearm;
        revoluteJointDef2.bodyB = arm;
        revoluteJointDef2.collideConnected = true;
        revoluteJointDef2.localAnchorA.set(new Vector2(0, size.y*-1.3f));
        revoluteJointDef2.localAnchorB.set(new Vector2(0, size.y));
        world.createJoint(revoluteJointDef2);

    }

    public void movePlayerToMouse(Vector2 mousePosition) {
        float distanceX = mousePosition.x - hand.getPosition().x;

        float velocityX = hand.getLinearVelocity().x;

        float forceX = distanceX * 20f - velocityX * 10f;

        hand.applyForceToCenter(
            forceX,
            0,
            true
        );

        float distanceY = mousePosition.y - hand.getPosition().y;

        float velocityY = hand.getLinearVelocity().y;

        float forceY = distanceY * 20f - velocityY * 10f;

        hand.applyForceToCenter(
            0,
            forceY,
            true
        );
    }

    public void update(Vector2 mousePosition) {
        movePlayerToMouse(mousePosition);
    }

    public Body getArm() {
        return arm;
    }
}
