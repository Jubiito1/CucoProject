package com.TfPSR.CucoProject;

import com.badlogic.gdx.physics.box2d.*;

public class GameContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        FullArm arm = getArm(contact);
        GripPoint gripPoint = getGripPoint(contact);

        if(arm != null && gripPoint != null) {
            arm.setCurrentGripPoint(gripPoint);
        }
    }

    @Override
    public void endContact(Contact contact) {
        FullArm arm = getArm(contact);
        GripPoint gripPoint = getGripPoint(contact);

        if(arm != null && gripPoint != null) {
            arm.setCurrentGripPoint(null);
        }

    }

    private FullArm getArm(Contact contact) {
        Object dataA = contact.getFixtureA().getBody().getUserData();
        Object dataB = contact.getFixtureB().getBody().getUserData();

        if(dataA instanceof FullArm) {
            return (FullArm) dataA;
        }

        if(dataB instanceof FullArm) {
            return (FullArm) dataB;
        }

        return null;
    }

    private GripPoint getGripPoint(Contact contact) {
        Object dataA = contact.getFixtureA().getBody().getUserData();
        Object dataB = contact.getFixtureB().getBody().getUserData();

        if(dataA instanceof GripPoint) {
            return (GripPoint) dataA;
        }
        if(dataB instanceof GripPoint) {
            return (GripPoint) dataB;
        }

        return null;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
