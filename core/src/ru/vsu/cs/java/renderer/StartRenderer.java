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
import ru.vsu.cs.java.model.characters.IPersonToView;
import ru.vsu.cs.java.model.characters.Person;

import java.util.ArrayList;

/**
 * Created by max on 12.12.2014.
 */
public class StartRenderer {

    private float widthRatio;
    private float heightRatio;
    private TextureRegion background;
    private TextureRegion castle;
    private TextureRegion crafthouse;
    private TextureRegion start;
    private TextureRegion exit;
    private TextureRegion warrior_btn_inf;
    private TextureRegion pause;
    private TextureRegion play;
    private TextureRegion stop;
    private TextureRegion doublespeed;
    private TextureRegion info;

    private BitmapFont font;
    private Engine engine;
    private boolean ok;

    ArrayList<IPersonToView> characters;

    public StartRenderer() {

        widthRatio  = Gdx.graphics.getWidth();
        heightRatio = Gdx.graphics.getHeight();
        background = new TextureRegion(new Texture(Gdx.files.internal("gfx/backgroundStart.png")));
        castle = new TextureRegion(new Texture(Gdx.files.internal("gfx/castle.png")));
        crafthouse = new TextureRegion(new Texture(Gdx.files.internal("gfx/crafthouse.png")));
        start = new TextureRegion(new Texture(Gdx.files.internal("gfx/start.jpg")));
        exit = new TextureRegion(new Texture(Gdx.files.internal("gfx/exit.jpg")));
        warrior_btn_inf = new TextureRegion(new Texture(Gdx.files.internal("gfx/warrior_btn_inf.jpg")));
        pause = new TextureRegion(new Texture(Gdx.files.internal("gfx/pause.jpg")));
        play = new TextureRegion(new Texture(Gdx.files.internal("gfx/play.jpg")));
        stop = new TextureRegion(new Texture(Gdx.files.internal("gfx/stop.jpg")));
        doublespeed = new TextureRegion(new Texture(Gdx.files.internal("gfx/doublespeed.jpg")));
        info = new TextureRegion(new Texture(Gdx.files.internal("gfx/info.png")));

        font = new BitmapFont() {{
            setColor(Color.NAVY);
        }};

        //Пример создания движка. 30 - кол-во поселенцев (можно любое число)
        engine = new Engine(30,(int)widthRatio,(int)heightRatio);

//        engine.step();


    }

    public void render(SpriteBatch batch) {
        batch.draw(background,0,0);

        characters = engine.getCharactersData();



//        font.draw(batch, "Start Screen", 50, heightRatio-50);
        batch.draw(castle, widthRatio/2 - 75, heightRatio/2 - 50, 150,300);
        batch.draw(crafthouse, 30 , heightRatio/2 - 75, 100,100);
        batch.draw(start, 0, heightRatio - 40, 60,40);
        batch.draw(exit, widthRatio - 40, heightRatio - 40, 40,40);

        batch.draw(warrior_btn_inf, widthRatio - 40, heightRatio / 2 + 40, 40,40);
        batch.draw(warrior_btn_inf, widthRatio - 40, heightRatio / 2 , 40,40);
        batch.draw(warrior_btn_inf, widthRatio - 40, heightRatio / 2 - 40, 40,40);
        batch.draw(warrior_btn_inf, widthRatio - 40, heightRatio / 2 - 80, 40,40);

        batch.draw(pause, widthRatio/2 - 60, 0 , 30,30);
        batch.draw(play,  widthRatio/2 - 30, 0 , 30,30);
        batch.draw(stop,  widthRatio/2, 0 , 30,30);
        batch.draw(doublespeed, widthRatio/2 + 30, 0 , 30,30);

        for (IPersonToView person : characters){
            batch.draw(warrior_btn_inf, person.getLocation().x - 20, person.getLocation().y - 20, 40,40);
        }

//        engine.step();


        if (ok){
            batch.draw(info,widthRatio - 170, heightRatio / 2 + 60);
        }
    }

    public void moveOnWarrior (int x, int y){

        if ((x >= (widthRatio - 40)) && (x <= widthRatio) && (y < (heightRatio/ 2 + 80) ) && (y >= (heightRatio / 2 + 40))){
            ok = true;
        } else {
            ok = false;
        }
    }

}
