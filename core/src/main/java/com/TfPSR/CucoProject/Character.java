package com.TfPSR.CucoProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Character {
    private static final float HEAD_WIDTH_RATIO = 0.28f;
    private static final float HEAD_HEIGHT_RATIO = 0.13f;

    private static final float CHEST_WIDTH_RATIO = 0.62f;
    private static final float CHEST_HEIGHT_RATIO = 0.37f;

    private static final float ARMS_WIDTH_RATIO = 0.18f;
    private static final float ARMS_HEIGHT_RATIO = 0.45f;

    private static final float LEGS_WIDTH_RATIO = 0.25f;
    private static final float LEGS_HEIGHT_RATIO = 0.5f;

    private final Body head;
    private final Body chest;
    private final FullArm leftArm;
    private final FullArm rightArm;
    private final Body leftLeg;
    private final Body rightLeg;

    public Character(Vector2 position, Vector2 size, World world) {

        Vector2 headSize = new Vector2(size.x * HEAD_WIDTH_RATIO, size.y * HEAD_HEIGHT_RATIO);
        Vector2 chestSize = new Vector2(size.x * CHEST_WIDTH_RATIO, size.y * CHEST_HEIGHT_RATIO);
        Vector2 armsSize = new Vector2(size.x * ARMS_WIDTH_RATIO, size.y * ARMS_HEIGHT_RATIO);
        Vector2 legsSize = new Vector2(size.x * LEGS_WIDTH_RATIO, size.y * LEGS_HEIGHT_RATIO);

        Vector2 headPosition = new Vector2(position.x, position.y + (size.y / 2) - (headSize.y / 2));
        Vector2 chestPosition = new Vector2(position.x, position.y + (size.y / 2) - headSize.y - (chestSize.y / 2));
        Vector2 leftArmPosition = new Vector2(position.x - (chestSize.x / 2) - (armsSize.x / 2), position.y + (size.y / 2) - headSize.y - (armsSize.y / 2));
        Vector2 rightArmPosition = new Vector2(position.x + (chestSize.x / 2) + (armsSize.x / 2), position.y + (size.y / 2) - headSize.y - (armsSize.y / 2));
        Vector2 leftLegPosition = new Vector2(position.x - (chestSize.x / 2) + (legsSize.x / 2), position.y - (size.y / 2) + (legsSize.y / 2));
        Vector2 rightLegPosition = new Vector2(position.x + (chestSize.x / 2) - (legsSize.x / 2), position.y - (size.y / 2) + (legsSize.y / 2));

        this.head = ShapeFactory.createRectangle(headPosition, headSize, 0, BodyDef.BodyType.DynamicBody, world, 10f, 0.1f, 0);
        this.chest = ShapeFactory.createRectangle(chestPosition, chestSize, 0, BodyDef.BodyType.DynamicBody, world, 10f, 0.1f, 0);
        this.leftArm = new FullArm(leftArmPosition, armsSize, 0, BodyDef.BodyType.DynamicBody, world, 0.4f, 0f, 0);
        this.rightArm = new FullArm(rightArmPosition, armsSize, 0, BodyDef.BodyType.DynamicBody, world, 0.4f, 0f, 0);
        this.leftLeg = ShapeFactory.createRectangle(leftLegPosition, legsSize, 0, BodyDef.BodyType.DynamicBody, world, 10f, 0.1f, 0);
        this.rightLeg = ShapeFactory.createRectangle(rightLegPosition, legsSize, 0, BodyDef.BodyType.DynamicBody, world, 10f, 0.1f, 0);

        //createJoints(world);
    }

    /*public void createJoints(World world) {
        RevoluteJointDef revoluteJointDef1 = new RevoluteJointDef();
        revoluteJointDef1.bodyA = chest;
        revoluteJointDef1.bodyB = leftArm.getArm();
        revoluteJointDef1.collideConnected = true;
        revoluteJointDef1.localAnchorA.set(new Vector2(-size.x/2, 0));
        revoluteJointDef1.localAnchorB.set(new Vector2(0, -size.y*0.7f));
        world.createJoint(revoluteJointDef1);

        RevoluteJointDef revoluteJointDef2 = new RevoluteJointDef();
        revoluteJointDef1.bodyA = chest;
        revoluteJointDef1.bodyB = rightArm.getArm();
        revoluteJointDef1.collideConnected = true;
        revoluteJointDef1.localAnchorA.set(new Vector2(size.x/2, 0));
        revoluteJointDef1.localAnchorB.set(new Vector2(0, -size.y*0.7f));
        world.createJoint(revoluteJointDef1);
    }*/

    public void update(Vector2 mousePosition) {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            leftArm.update(mousePosition);
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            rightArm.update(mousePosition);
        }
    }
}
