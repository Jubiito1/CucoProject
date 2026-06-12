package com.TfPSR.CucoProject;

import com.badlogic.gdx.physics.box2d.*;

public class GameContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        FullArm arm = getArm(contact);
        GrabPoint grabPoint = getGrabPoint(contact);

        if(arm != null && grabPoint != null) {
            arm.setCurrentGrabPoint(grabPoint);
        }
    }

    @Override
    public void endContact(Contact contact) {
        FullArm arm = getArm(contact);
        GrabPoint grabPoint = getGrabPoint(contact);

        if(arm != null && grabPoint != null) {
            arm.setCurrentGrabPoint(null);
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

    private GrabPoint getGrabPoint(Contact contact) {
        Object dataA = contact.getFixtureA().getBody().getUserData();
        Object dataB = contact.getFixtureB().getBody().getUserData();

        if(dataA instanceof GrabPoint) {
            return (GrabPoint) dataA;
        }
        if(dataB instanceof GrabPoint) {
            return (GrabPoint) dataB;
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
