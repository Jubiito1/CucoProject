package com.TfPSR.CucoProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private final Box2DDebugRenderer debugRenderer;
    private final Body groundBody;
    private final Player player1;
    private final Player player2;

    public GameScreen(Main game) {
        this.game = game;
        this.viewport = game.getViewport();
        this.camera = game.getCamera();
        this.batch = game.getBatch();
        this.debugRenderer = new Box2DDebugRenderer();

        world = new World(GRAVEDAD, true);
        groundBody = ShapeFactory.createRectangle(new Vector2(0f, 0f), new Vector2(10f, 1f), 0, BodyDef.BodyType.StaticBody, world, 0.4f, 0f, 0);
        player1 = new Player(new Vector2(0f, 5f), new Vector2(1f, 1f), 0, BodyDef.BodyType.DynamicBody, world, 0.4f, 0f, 0);
        player2 = new Player(new Vector2(0f, 5f), new Vector2(1f, 1f), 0, BodyDef.BodyType.DynamicBody, world, 0.4f, 0f, 0);
    }

    public Vector2 findMousePosition() {
        Vector2 mousePosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());

        viewport.unproject(mousePosition);

        return mousePosition;
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
        debugRenderer.render(world, camera.combined);
    }

    private void update(float delta) {
        camera.position.set(0, 2, 0);
        camera.zoom = 3f;
        camera.update();

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            player1.update(findMousePosition());
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            player2.update(findMousePosition());
        }

        world.step(delta, 6, 2);
    }
}
