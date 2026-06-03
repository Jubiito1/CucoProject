package com.TfPSR.CucoProject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class ShapeFactory {
    private ShapeFactory() {}

    public static Body createRectangle(final Vector2 position, final Vector2 size, final BodyDef.BodyType type, final World world, float density) {

        //define body
        final BodyDef bDef = new BodyDef();
        bDef.position.set(position);
        bDef.type = type;
        final Body body = world.createBody(bDef);

        //define fixture
        final PolygonShape shape = new PolygonShape();
        shape.setAsBox((size.x / 2), (size.y / 2));
        final FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.density = density;

        body.createFixture(fDef);
        shape.dispose();

        return body;
    }
}
