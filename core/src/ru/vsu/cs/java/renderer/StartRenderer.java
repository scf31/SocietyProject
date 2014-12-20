package ru.vsu.cs.java.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.PauseableThread;
import ru.vsu.cs.java.model.Engine;
import ru.vsu.cs.java.model.Kingdom;
import ru.vsu.cs.java.model.characters.Person;

/**
 * Created by max on 12.12.2014.
 */
public class StartRenderer {

    private float widthRatio;
    private float heightRatio;

    private BitmapFont font;
    private Engine engine;

    public StartRenderer() {
        widthRatio  = Gdx.graphics.getWidth();
        heightRatio = Gdx.graphics.getHeight();

        font = new BitmapFont() {{
            setColor(Color.NAVY);
        }};

        //Пример создания движка. 30 - кол-во поселенцев (можно любое число)
        engine = new Engine(30,(int)widthRatio,(int)heightRatio);

        engine.step();


    }

    public void render(SpriteBatch batch) {
        batch.draw(new Texture(Gdx.files.internal("gfx/backgroundStart.png")),0,0);
        font.draw(batch, "Start Screen", 50, heightRatio-50);
    }

}
