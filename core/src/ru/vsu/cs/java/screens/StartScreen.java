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
    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        renderer = new StartRenderer();
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
