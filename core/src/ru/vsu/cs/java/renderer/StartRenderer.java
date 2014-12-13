package ru.vsu.cs.java.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.vsu.cs.java.model.Kingdom;
import ru.vsu.cs.java.model.characters.Person;

/**
 * Created by max on 12.12.2014.
 */
public class StartRenderer {

    private float widthRatio;
    private float heightRatio;

    private BitmapFont font;

    public StartRenderer() {
        widthRatio  = Gdx.graphics.getWidth();
        heightRatio = Gdx.graphics.getHeight();

        Kingdom kingdom = new Kingdom(20,(int)widthRatio,(int)heightRatio);

        font = new BitmapFont() {{
            setColor(Color.NAVY);
        }};

    }

    public void render(SpriteBatch batch) {
        Kingdom kingdom = new Kingdom(10,500,500);

        batch.draw(new Texture(Gdx.files.internal("gfx/backgroundStart.png")),0,0);
        font.draw(batch, "Start Screen", 50, heightRatio-50);
    }

}
