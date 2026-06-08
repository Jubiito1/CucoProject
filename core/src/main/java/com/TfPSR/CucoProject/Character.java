package com.TfPSR.CucoProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;

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
    private final Body torso;
    private final FullArm leftArm;
    private final FullArm rightArm;
    private final Body leftLeg;
    private final Body rightLeg;

    private final short groupIndex = -1;

    public Character(Vector2 position, Vector2 size, World world) {

        Vector2 headSize = new Vector2(size.x * HEAD_WIDTH_RATIO, size.y * HEAD_HEIGHT_RATIO);
        Vector2 torsoSize = new Vector2(size.x * CHEST_WIDTH_RATIO, size.y * CHEST_HEIGHT_RATIO);
        Vector2 armsSize = new Vector2(size.x * ARMS_WIDTH_RATIO, size.y * ARMS_HEIGHT_RATIO);
        Vector2 legsSize = new Vector2(size.x * LEGS_WIDTH_RATIO, size.y * LEGS_HEIGHT_RATIO);

        Vector2 headPosition = new Vector2(position.x, position.y + (size.y / 2) - (headSize.y / 2));
        Vector2 torsoPosition = new Vector2(position.x, position.y + (size.y / 2) - headSize.y - (torsoSize.y / 2));
        Vector2 leftArmPosition = new Vector2(position.x - (torsoSize.x / 2) - (armsSize.x / 2), position.y + (size.y / 2) - headSize.y - (armsSize.y / 2));
        Vector2 rightArmPosition = new Vector2(position.x + (torsoSize.x / 2) + (armsSize.x / 2), position.y + (size.y / 2) - headSize.y - (armsSize.y / 2));
        Vector2 leftLegPosition = new Vector2(position.x - (torsoSize.x / 2) + (legsSize.x / 2), position.y - (size.y / 2) + (legsSize.y / 2));
        Vector2 rightLegPosition = new Vector2(position.x + (torsoSize.x / 2) - (legsSize.x / 2), position.y - (size.y / 2) + (legsSize.y / 2));

        this.head = ShapeFactory.createRectangle(headPosition, headSize, 0, BodyDef.BodyType.DynamicBody, world, 1f, 0f, 0, groupIndex);
        this.torso = ShapeFactory.createRectangle(torsoPosition, torsoSize, 0, BodyDef.BodyType.DynamicBody, world, 1f, 0f, 0, groupIndex);
        this.leftArm = new FullArm(leftArmPosition, armsSize, 0, BodyDef.BodyType.DynamicBody, world, 1f, 0f, 0, groupIndex, Sides.LEFT);
        this.rightArm = new FullArm(rightArmPosition, armsSize, 0, BodyDef.BodyType.DynamicBody, world, 1f, 0f, 0, groupIndex, Sides.RIGHT);
        this.leftLeg = ShapeFactory.createRectangle(leftLegPosition, legsSize, 0, BodyDef.BodyType.DynamicBody, world, 1f, 0f, 0, groupIndex);
        this.rightLeg = ShapeFactory.createRectangle(rightLegPosition, legsSize, 0, BodyDef.BodyType.DynamicBody, world, 1f, 0f, 0, groupIndex);

        Vector2 torsoNeckAnchor = new Vector2(0, (torsoSize.y / 2));
        Vector2 headNeckAnchor = new Vector2(0, - (headSize.y / 2));
        Vector2 leftShoulderAnchor = new Vector2( - (torsoSize.x / 2), (torsoSize.y / 2) - (armsSize.x / 2));
        Vector2 rightShoulderAnchor = new Vector2((torsoSize.x / 2), (torsoSize.y / 2) - (armsSize.x / 2));
        Vector2 leftTorsoHipAnchor = new Vector2( - (torsoSize.x / 2) + (legsSize.x / 2), - (torsoSize.y / 2));
        Vector2 leftLegHipAnchor = new Vector2(0, (legsSize.y / 2));
        Vector2 rightTorsoHipAnchor = new Vector2((torsoSize.x / 2) - (legsSize.x / 2), - (torsoSize.y / 2));
        Vector2 rightLegHipAnchor = new Vector2(0, (legsSize.y / 2));

        RevoluteJoint neckJoint = JointFactory.createRevoluteJoint(torso, head, false, torsoNeckAnchor, headNeckAnchor, world, -45, 45);
        RevoluteJoint leftShoulderJoint = JointFactory.createRevoluteJoint(torso, leftArm.getArm(), false, leftShoulderAnchor, leftArm.getShoulderAnchor(), world, -180f, 0f);
        RevoluteJoint rightShoulderJoint = JointFactory.createRevoluteJoint(torso, rightArm.getArm(), false, rightShoulderAnchor, rightArm.getShoulderAnchor(), world, 0f, 180f);
        RevoluteJoint leftLegJoint = JointFactory.createRevoluteJoint(torso, leftLeg, false, leftTorsoHipAnchor, leftLegHipAnchor, world, -90, 90);
        RevoluteJoint rightLegJoint = JointFactory.createRevoluteJoint(torso, rightLeg, false, rightTorsoHipAnchor, rightLegHipAnchor, world, -90, 90);
    }

    public void update(Vector2 mousePosition) {

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            leftArm.update(mousePosition);
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            rightArm.update(mousePosition);
        }
    }
}
