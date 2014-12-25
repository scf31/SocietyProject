package ru.vsu.cs.java.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.vsu.cs.java.model.Engine;
import ru.vsu.cs.java.model.characters.IPersonToView;

import java.awt.*;
import java.util.*;

/**
 * Created by max on 12.12.2014.
 */
public class StartRenderer {

    private static final int CHARACTERS_HEIGTH = 40;
    private static final int CHARACTERS_WIDTH = 40;
    private static final int CASTLE_HEIGTH = 300;
    private static final int CASTLE_WIDTH = 150;

    private float widthRatio;
    private float heightRatio;

    private TextureRegion backSprite;

    private TextureRegion start;
    private TextureRegion exit;

    private TextureRegion pause;
    private TextureRegion play;
    private TextureRegion stop;
    private TextureRegion doublespeed;

    private TextureRegion castle;
    private TextureRegion crafthouse;

    private TextureRegion warrior;
    private TextureRegion craftman;
    private TextureRegion peasant;

    private TextureRegion info;
    private TextureRegion charInfo;

    private TextureRegion warrior_btn;
    private TextureRegion craftman_btn;
    private TextureRegion peasant_btn;


    private BitmapFont font;
    private Engine engine;
    private boolean onWarBtn = false;
    private boolean onCraftBtn = false;
    private boolean onPeasBtn = false;

    private ArrayList<IPersonToView> characters;
    private Hashtable<String,Rectangle> map;

    private HashSet<Integer> showCharId;

    public StartRenderer() {

        widthRatio  = Gdx.graphics.getWidth();
        heightRatio = Gdx.graphics.getHeight();

        showCharId = new HashSet<Integer>();

        backSprite = new TextureRegion(new Texture(Gdx.files.internal("gfx/backSprite.png")));

        start = new TextureRegion(new Texture(Gdx.files.internal("gfx/start.jpg")));
        exit = new TextureRegion(new Texture(Gdx.files.internal("gfx/exit.jpg")));

        pause = new TextureRegion(new Texture(Gdx.files.internal("gfx/pause.jpg")));
        play = new TextureRegion(new Texture(Gdx.files.internal("gfx/play.jpg")));
        stop = new TextureRegion(new Texture(Gdx.files.internal("gfx/stop.jpg")));
        doublespeed = new TextureRegion(new Texture(Gdx.files.internal("gfx/doublespeed.jpg")));

        castle = new TextureRegion(new Texture(Gdx.files.internal("gfx/castle.png")));
        crafthouse = new TextureRegion(new Texture(Gdx.files.internal("gfx/crafthouse.png")));

        warrior = new TextureRegion(new Texture(Gdx.files.internal("gfx/warrior.png")));
        craftman = new TextureRegion(new Texture(Gdx.files.internal("gfx/craftman.png")));
        peasant = new TextureRegion(new Texture(Gdx.files.internal("gfx/peasant.png")));

        info = new TextureRegion(new Texture(Gdx.files.internal("gfx/info.png")));
        charInfo = new TextureRegion(new Texture(Gdx.files.internal("gfx/charInfo.png")));

        warrior_btn = new TextureRegion(new Texture(Gdx.files.internal("gfx/warrior_btn.png")));
        craftman_btn = new TextureRegion(new Texture(Gdx.files.internal("gfx/craftman_btn.png")));
        peasant_btn = new TextureRegion(new Texture(Gdx.files.internal("gfx/peasant_btn.png")));


        font = new BitmapFont() {{
            setColor(Color.OLIVE);
        }};

        //Пример создания движка. 30 - кол-во поселенцев (можно любое число)
        engine = new Engine(30,(int)widthRatio,(int)heightRatio);


    }

    public void render(SpriteBatch batch) {
//        batch.draw(background,0,0);

        int w = (int) widthRatio / 40;
        int h = (int) heightRatio / 40;

        for (int i = 0; i <= w; i ++){
            for (int j = 0; j <= h; j++){
                batch.draw(backSprite, i * 40, j * 40 );
            }
        }
        characters = engine.getCharactersData();
        map = engine.getMapData();

        for (Map.Entry<String, Rectangle> entry : map.entrySet()){
            String name = entry.getKey();
            Rectangle rect = entry.getValue();

            if (name.equals("castle")){
                batch.draw(castle, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth() , (int)rect.getHeight());
            }
            if (name.equals("crafthouse")){
                batch.draw(crafthouse, (int) rect.getX(), (int) rect.getY() + (int)rect.getHeight() , (int) rect.getWidth() , (int)rect.getHeight());
            }
        }

        for (IPersonToView person : characters){

            if (showCharId != null && !showCharId.isEmpty() && !onWarBtn && !onPeasBtn && !onCraftBtn){
                for (int id : showCharId){
                    if (person.getId() == id){
                        batch.draw(charInfo,person.getLocation().x - CHARACTERS_WIDTH/2 , person.getLocation().y + CHARACTERS_HEIGTH/2 + 3, 50, 50);
                        font.draw(batch, "HP: " + person.getHealth(), person.getLocation().x - CHARACTERS_WIDTH/2 + 2, person.getLocation().y + CHARACTERS_HEIGTH/2 + 20);
                        font.draw(batch, "LvL: " + person.getLevel() , person.getLocation().x - CHARACTERS_WIDTH/2 + 2, person.getLocation().y + CHARACTERS_HEIGTH/2 + 35);
                    }
                }
            }

            switch (person.getProfession()){
                case Warrior:
                    batch.draw(warrior, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
                    break;
                case Craftsman:
                    batch.draw(craftman, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
                    break;
                case Peasant:
                    batch.draw(peasant, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
                    break;
//                case Warrior:
//                    batch.draw(warrior, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
//                    break;
//                case Warrior:
//                    batch.draw(warrior, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
//                    break;
//                case Warrior:
//                    batch.draw(warrior, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
//                    break;

            }
        }



        batch.draw(start, 0, heightRatio - 40, 60,40);
        batch.draw(exit, widthRatio - 40, heightRatio - 40, 40,40);


        batch.draw(pause, widthRatio/2 - 60, 0 , 30,30);
        batch.draw(play,  widthRatio/2 - 30, 0 , 30,30);
        batch.draw(stop,  widthRatio/2, 0 , 30,30);
        batch.draw(doublespeed, widthRatio/2 + 30, 0 , 30,30);


        batch.draw(warrior_btn, widthRatio - 40, heightRatio / 2 + 40, 40,40);
        batch.draw(craftman_btn, widthRatio - 40, heightRatio / 2 , 40,40);
        batch.draw(peasant_btn, widthRatio - 40, heightRatio / 2 - 40, 40,40);

        if (onWarBtn){
            batch.draw(info,widthRatio - 120, heightRatio/ 2 + 60);
        }
        if (onCraftBtn){
            batch.draw(info,widthRatio - 120, heightRatio/ 2 + 20);
        }
        if (onPeasBtn){
            batch.draw(info,widthRatio - 120, heightRatio/ 2 - 20);
        }
    }

    public void moveOn(int x, int y){

        // For War
        if (!onCraftBtn && ! onPeasBtn) {
            if ((x >= (widthRatio - 40)) && (x <= widthRatio) && (y <= (heightRatio / 2 + 80)) && (y >= (heightRatio / 2 + 40))) {
                onWarBtn = true;
            } else {
                if (onWarBtn) {
                    if ((x >= (widthRatio - 120)) && (x <= widthRatio - 20) && (y >= (heightRatio / 2 + 60)) && (y <= (heightRatio / 2 + 120))) {
                        onWarBtn = true;
                    } else {
                        onWarBtn = false;
                    }
                } else {
                    onWarBtn = false;
                }
            }
        }

        // For Craft
        if(!onWarBtn && !onPeasBtn) {
            if ((x >= (widthRatio - 40)) && (x <= widthRatio) && (y <= (heightRatio / 2 + 40)) && (y >= (heightRatio / 2))) {
                onCraftBtn = true;
            } else {
                if (onCraftBtn) {
                    if ((x >= (widthRatio - 120)) && (x <= widthRatio - 20) && (y >= (heightRatio / 2 + 20)) && (y <= (heightRatio / 2 + 80))) {
                        onCraftBtn = true;
                    } else {
                        onCraftBtn = false;
                    }
                } else {
                    onCraftBtn = false;
                }
            }
        }

        // For Peas
        if (!onWarBtn && !onCraftBtn) {
            if ((x >= (widthRatio - 40)) && (x <= widthRatio) && (y <= (heightRatio / 2)) && (y >= (heightRatio / 2 - 40))) {
                onPeasBtn = true;
            } else {
                if (onPeasBtn) {
                    if ((x >= (widthRatio - 120)) && (x <= widthRatio - 20) && (y >= (heightRatio / 2 -20)) && (y <= (heightRatio / 2 + 40))) {
                        onPeasBtn = true;
                    } else {
                        onPeasBtn = false;
                    }
                } else {
                    onPeasBtn = false;
                }
            }
        }

        for (IPersonToView person : characters){
            if (x >= (person.getLocation().getX() - 20) && (x <= person.getLocation().getX() + 20) && (y <= (person.getLocation().getY() + 20)) && (y >= (person.getLocation().getY() - 20))){
                showCharId.add(person.getId());
            } else {
                if (showCharId != null && showCharId.contains(person.getId())){
                    showCharId.remove(person.getId());
                }
            }
        }


    }

    public void mouseTap(int x, int y){
        if (x >= (widthRatio - 40) && (x <= widthRatio) && (y <= (heightRatio)) && (y >= (heightRatio - 40))){
            Gdx.app.exit();
        }
    }
}
