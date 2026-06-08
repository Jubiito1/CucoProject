package com.TfPSR.CucoProject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class FullArm {
    private final Body hand;
    private final Body forearm;
    private final Body arm;
    private final Vector2 ShoulderAnchor;

    public FullArm(Vector2 position, Vector2 size, float angle, BodyDef.BodyType type, World world, float density, float friction, float restitution, short groupIndex, Sides side) {

        Vector2 armSize = new Vector2(size.x, size.y * 0.47f);
        Vector2 forearmSize = new Vector2(size.x, size.y * 0.4f);
        Vector2 handSize = new Vector2(size.x, size.y * 0.13f);

        Vector2 armPosition = new Vector2(position.x, position.y + (size.y / 2) - (armSize.y / 2));
        Vector2 forearmPosition = new Vector2(position.x, position.y - (size.y / 2) + handSize.y + (forearmSize.y / 2));
        Vector2 handPosition = new Vector2(position.x, position.y - (size.y / 2) + (handSize.y / 2));

        arm = ShapeFactory.createRectangle(armPosition, armSize, angle, type, world, density, friction, restitution, groupIndex);
        forearm = ShapeFactory.createRectangle(forearmPosition, forearmSize, angle, type, world, density, friction, restitution, groupIndex);
        hand = ShapeFactory.createRectangle(handPosition, handSize, angle, type, world, density, friction, restitution, groupIndex);


        Vector2 armElbowAnchor = new Vector2(0, - (armSize.y / 2));
        Vector2 forearmElbowAnchor = new Vector2(0, (forearmSize.y / 2));
        Vector2 forearmWristAnchor = new Vector2(0, - (forearmSize.y / 2));
        Vector2 handWristAnchor = new Vector2(0, (handSize.y / 2));

        if (side == Sides.LEFT) {
            this.ShoulderAnchor = new Vector2((armSize.x / 2), (armSize.y / 2) - (size.x / 2));
            RevoluteJoint elbowJoint = JointFactory.createRevoluteJoint(arm, forearm, false, armElbowAnchor, forearmElbowAnchor, world, -160, 0);
            RevoluteJoint wristAnchor = JointFactory.createRevoluteJoint(forearm, hand, false, forearmWristAnchor, handWristAnchor, world, -45, 45);
        } else {
            this.ShoulderAnchor = new Vector2(-(armSize.x / 2), (armSize.y / 2) - (size.x / 2));
            RevoluteJoint elbowJoint = JointFactory.createRevoluteJoint(arm, forearm, false, armElbowAnchor, forearmElbowAnchor, world, 0, 160);
            RevoluteJoint wristAnchor = JointFactory.createRevoluteJoint(forearm, hand, false, forearmWristAnchor, handWristAnchor, world, -45, 45);
        }


    }



    public void movePlayerToMouse(Vector2 mousePosition) {
        float distanceX = mousePosition.x - hand.getPosition().x;

        float velocityX = hand.getLinearVelocity().x;

        float forceX = distanceX * 5f - velocityX * 2f;

        hand.applyForceToCenter(
            forceX,
            0,
            true
        );

        float distanceY = mousePosition.y - hand.getPosition().y;

        float velocityY = hand.getLinearVelocity().y;

        float forceY = distanceY * 5f - velocityY * 2f;

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

    public Vector2 getShoulderAnchor() {
        return ShoulderAnchor;
    }

}
