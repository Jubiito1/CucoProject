package com.TfPSR.CucoProject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter {
    private final Vector2 GRAVEDAD = new Vector2(0, -9.8f);

    private final Main game;
    private final Viewport viewport;
    private final OrthographicCamera camera;
    private final Batch batch;

    private final World world;
    private final Box2DDebugRenderer B2dr;
    private final Body playerBody;
    private final Body groundBody;

    public GameScreen(Main game) {
        this.game = game;
        this.viewport = game.getViewport();
        this.camera = game.getCamera();
        this.batch = game.getBatch();
        this.B2dr = new Box2DDebugRenderer();

        world = new World(GRAVEDAD, true);
        groundBody = ShapeFactory.createRectangle(new Vector2(0f, 0f), new Vector2(4f, 1f), BodyDef.BodyType.StaticBody, world, 0.4f);
        playerBody = ShapeFactory.createRectangle(new Vector2(2.5f, 5f), new Vector2(1f, 1f), BodyDef.BodyType.DynamicBody, world, 0.4f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        draw();
    }

    private void draw() {
        batch.setProjectionMatrix(camera.combined);
        B2dr.render(world, camera.combined);
    }

    private void update(float delta) {
        camera.position.set(playerBody.getPosition(), 0);
        camera.zoom = 1f;
        camera.update();

        world.step(delta, 6, 2);
    }
}
