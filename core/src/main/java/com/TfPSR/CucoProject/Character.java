package com.TfPSR.CucoProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Character {
    private final FullArm leftArm;
    private final FullArm rightArm;
    private final Body torso;

    public Character(Vector2 size, World world) {
        this.leftArm = new FullArm(new Vector2(0f, 5f), new Vector2(1f, 1f), 0, BodyDef.BodyType.DynamicBody, world, 0.4f, 0f, 0);
        this.rightArm = new FullArm(new Vector2(0f, 5f), new Vector2(1f, 1f), 0, BodyDef.BodyType.DynamicBody, world, 0.4f, 0f, 0);
        this.torso = ShapeFactory.createRectangle(new Vector2(0f, 2f), size, 0, BodyDef.BodyType.DynamicBody, world, 10f, 0.1f, 0);

        RevoluteJointDef revoluteJointDef1 = new RevoluteJointDef();
        revoluteJointDef1.bodyA = torso;
        revoluteJointDef1.bodyB = leftArm.getArm();
        revoluteJointDef1.collideConnected = true;
        revoluteJointDef1.localAnchorA.set(new Vector2(-size.x/2, 0));
        revoluteJointDef1.localAnchorB.set(new Vector2(0, -size.y*0.7f));
        world.createJoint(revoluteJointDef1);

        RevoluteJointDef revoluteJointDef2 = new RevoluteJointDef();
        revoluteJointDef1.bodyA = torso;
        revoluteJointDef1.bodyB = rightArm.getArm();
        revoluteJointDef1.collideConnected = true;
        revoluteJointDef1.localAnchorA.set(new Vector2(size.x/2, 0));
        revoluteJointDef1.localAnchorB.set(new Vector2(0, -size.y*0.7f));
        world.createJoint(revoluteJointDef1);

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
