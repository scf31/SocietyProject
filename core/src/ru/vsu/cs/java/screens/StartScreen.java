package ru.vsu.cs.java.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import ru.vsu.cs.java.renderer.StartRenderer;

/**
 * Created by max on 12.12.2014.
 */
public class StartScreen extends AbstractScreen {

    StartRenderer renderer;

    public StartScreen(Game game) {
        super(game);
        renderer = new StartRenderer();
    }


    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                screenY = Gdx.graphics.getHeight() - screenY;
                renderer.moveOnWarrior(screenX,screenY);
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        renderer.render(batch);
        batch.end();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                switch (button) {
                    case Input.Buttons.LEFT:

                        break;
                }
                return true;
            }
        });
    }
}
