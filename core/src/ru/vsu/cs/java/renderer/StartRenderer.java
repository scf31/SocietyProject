package ru.vsu.cs.java.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

        //Пример создания движка. 10 - кол-во поселенцев (можно любое число)
        engine = new Engine(10,(int)widthRatio,(int)heightRatio);

        //Дальше нужно создать поток, в нем вызывать метод engine.step()
        //и паузить поток на 30 милиссекунд (примерное число,выясним наилучшее значение экспириментально)

        // Получить данные о всех персонажах можно через engine.getCharactersData()
        // Получить данные об объектах на карте можно через engine.getMapData()

    }

    public void render(SpriteBatch batch) {
        batch.draw(new Texture(Gdx.files.internal("gfx/backgroundStart.png")),0,0);
        font.draw(batch, "Start Screen", 50, heightRatio-50);
    }

}
