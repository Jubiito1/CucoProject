package com.TfPSR.CucoProject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class GrabPoint {
    private static final float SIZE = 0.1f;

    private final Body body;

    public GrabPoint(Vector2 position, World world) {
        body = ShapeFactory.createCircle(position, SIZE, BodyDef.BodyType.StaticBody, world, 0, 0, 0, true, (short) -2);
        body.setUserData(this);

    }

    public Body getBody() {
        return body;
    }
}
