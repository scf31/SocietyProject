package ru.vsu.cs.java.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.vsu.cs.java.model.Engine;
import ru.vsu.cs.java.model.characters.IPersonToView;
import ru.vsu.cs.java.model.characters.PersonState;
import ru.vsu.cs.java.model.characters.Profession;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by max on 12.12.2014.
 */
public class StartRenderer {

    private static final int CHARACTERS_BTN_HEIGTH      = 40;
    private static final int CHARACTERS_BTN_WIDTH       = 40;
    private static final int CHARACTERS_HEIGTH          = 70;
    private static final int CHARACTERS_WIDTH           = 70;
    private static final int INFO_ON_BTNS_WIDTH         = 150;
    private static final int INFO_ON_BTNS_HEIGTH        = 100;

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
    private TextureRegion forest;
    private TextureRegion farm;

    private TextureRegion warrior;
    private TextureRegion craftman;
    private TextureRegion peasant;
    private TextureRegion robber;
    private TextureRegion trader;

    private TextureRegion info;
    private TextureRegion charInfo;
    private TextureRegion fight;

    private TextureRegion warrior_btn;
    private TextureRegion craftman_btn;
    private TextureRegion peasant_btn;
    private TextureRegion trader_btn;


    private BitmapFont font;
    private Engine engine;

    private boolean onWarriorBtn = false;
    private boolean onCraftmanBtn = false;
    private boolean onPeasantBtn = false;
    private boolean onTraderBtn = false;

    private ArrayList<IPersonToView> characters;
    private Hashtable<String,Rectangle> map;

    private HashSet<Integer> showCharId;

    private List<Integer> fightingPersonsId;

    public StartRenderer() {

        widthRatio  = Gdx.graphics.getWidth();
        heightRatio = Gdx.graphics.getHeight();

        showCharId = new HashSet<Integer>();

        backSprite = new TextureRegion(new Texture(Gdx.files.internal("gfx/grass.png")));

        start = new TextureRegion(new Texture(Gdx.files.internal("gfx/start1.png")));
        exit = new TextureRegion(new Texture(Gdx.files.internal("gfx/exit.jpg")));

        pause = new TextureRegion(new Texture(Gdx.files.internal("gfx/pause.jpg")));
        play = new TextureRegion(new Texture(Gdx.files.internal("gfx/play.jpg")));
        stop = new TextureRegion(new Texture(Gdx.files.internal("gfx/stop.jpg")));
        doublespeed = new TextureRegion(new Texture(Gdx.files.internal("gfx/doublespeed.jpg")));

        castle = new TextureRegion(new Texture(Gdx.files.internal("gfx/castle.png")));
        crafthouse = new TextureRegion(new Texture(Gdx.files.internal("gfx/crafthouse.png")));
        forest = new TextureRegion(new Texture(Gdx.files.internal("gfx/forest.png")));
        farm = new TextureRegion(new Texture(Gdx.files.internal("gfx/farm.png")));

        warrior = new TextureRegion(new Texture(Gdx.files.internal("gfx/warrior.png")));
        craftman = new TextureRegion(new Texture(Gdx.files.internal("gfx/craftman.png")));
        peasant = new TextureRegion(new Texture(Gdx.files.internal("gfx/peasant.png")));
        robber = new TextureRegion(new Texture(Gdx.files.internal("gfx/robber.png")));
        trader = new TextureRegion(new Texture(Gdx.files.internal("gfx/trader.png")));

        info = new TextureRegion(new Texture(Gdx.files.internal("gfx/info.png")));
        charInfo = new TextureRegion(new Texture(Gdx.files.internal("gfx/char_info.png")));
        fight = new TextureRegion(new Texture(Gdx.files.internal("gfx/fighting.png")));

        warrior_btn = new TextureRegion(new Texture(Gdx.files.internal("gfx/warrior_btn.png")));
        craftman_btn = new TextureRegion(new Texture(Gdx.files.internal("gfx/craftman_btn.png")));
        peasant_btn = new TextureRegion(new Texture(Gdx.files.internal("gfx/peasant_btn.png")));
        trader_btn = new TextureRegion(new Texture(Gdx.files.internal("gfx/trader_btn.png")));


        font = new BitmapFont() {{
            setColor(Color.OLIVE);
        }};

        //Пример создания движка. 30 - кол-во поселенцев (можно любое число)
        engine = new Engine(30,(int)widthRatio,(int)heightRatio);


    }

    public void render(SpriteBatch batch) {
//        batch.draw(background,0,0);

        int w = (int) widthRatio / 50;
        int h = (int) heightRatio / 50;

        for (int i = 0; i <= w; i ++){
            for (int j = 0; j <= h; j++){
                batch.draw(backSprite, i * 50, j * 50 );
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
                batch.draw(crafthouse, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth() , (int)rect.getHeight());
            }
            if (name.equals("forest")){
                batch.draw(forest, (int) rect.getX(), (int) rect.getY() , (int) rect.getWidth() , (int)rect.getHeight());
            }
            if (name.equals("farm")){
                batch.draw(farm, (int) rect.getX(), (int) rect.getY() , (int) rect.getWidth() , (int)rect.getHeight());
            }

        }

        fightingPersonsId = new ArrayList<Integer>();
        for (IPersonToView person : characters){


            if (person.getStatus().equals(PersonState.Fighting)){
                batch.draw(fight,person.getLocation().x - CHARACTERS_WIDTH/2 + 13 , person.getLocation().y + CHARACTERS_HEIGTH/2 + 3 );
            }

            if (showCharId != null && !showCharId.isEmpty() && !onWarriorBtn && !onPeasantBtn && !onCraftmanBtn && !onTraderBtn){
                for (int id : showCharId){
                    if (person.getId() == id){
                        batch.draw(charInfo,person.getLocation().x - CHARACTERS_WIDTH/2 , person.getLocation().y + CHARACTERS_HEIGTH/2 + 3, 80, 60);
                        font.draw(batch, "" + person.getLevel(), person.getLocation().x - CHARACTERS_WIDTH/2 + 35, person.getLocation().y + CHARACTERS_HEIGTH/2 + 56);
                        font.draw(batch, "     " + person.getHealth(), person.getLocation().x - CHARACTERS_WIDTH/2 + 8, person.getLocation().y + CHARACTERS_HEIGTH/2 + 26);
                        font.draw(batch, "       " + person.getEquipment().getDamage() , person.getLocation().x - CHARACTERS_WIDTH/2 + 8, person.getLocation().y + CHARACTERS_HEIGTH/2 + 41);
                    }
                }
            }

            switch (person.getProfession()){
                case Warrior:
                    if (!person.getStatus().equals(PersonState.Fighting)) {
                        batch.draw(warrior, person.getLocation().x - CHARACTERS_WIDTH / 2, person.getLocation().y - CHARACTERS_HEIGTH / 2, CHARACTERS_WIDTH, CHARACTERS_HEIGTH);
                    } else {
                        batch.draw(warrior, person.getLocation().x - CHARACTERS_WIDTH, person.getLocation().y - CHARACTERS_HEIGTH / 2, CHARACTERS_WIDTH, CHARACTERS_HEIGTH);

                    }
                    break;
                case Craftsman:
                    batch.draw(craftman, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
                    break;
                case Peasant:
                    batch.draw(peasant, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
                    break;
                case Robber:
                    batch.draw(robber, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
                    break;
                case Trader:
                    batch.draw(trader, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
                    break;
            }
        }



        batch.draw(start, 0, heightRatio - 40, 60,40);
        batch.draw(exit, widthRatio - 40, heightRatio - 40, 40,40);


//        batch.draw(pause, widthRatio/2 - 60, 0 , 30,30);
//        batch.draw(play,  widthRatio/2 - 30, 0 , 30,30);
//        batch.draw(stop,  widthRatio/2, 0 , 30,30);
//        batch.draw(doublespeed, widthRatio/2 + 30, 0 , 30,30);


        batch.draw(warrior_btn, widthRatio - CHARACTERS_BTN_WIDTH, heightRatio / 2 + CHARACTERS_BTN_HEIGTH, CHARACTERS_BTN_WIDTH,CHARACTERS_BTN_HEIGTH);
        batch.draw(craftman_btn, widthRatio - CHARACTERS_BTN_WIDTH, heightRatio / 2 , CHARACTERS_BTN_WIDTH,CHARACTERS_BTN_HEIGTH);
        batch.draw(peasant_btn, widthRatio - CHARACTERS_BTN_WIDTH, heightRatio / 2 - CHARACTERS_BTN_HEIGTH, CHARACTERS_BTN_WIDTH,CHARACTERS_BTN_HEIGTH);
        batch.draw(trader_btn, widthRatio - CHARACTERS_BTN_WIDTH, heightRatio / 2 - 2 * CHARACTERS_BTN_HEIGTH, CHARACTERS_BTN_WIDTH,CHARACTERS_BTN_HEIGTH);

        if (onWarriorBtn){
            batch.draw(info,widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2, heightRatio/ 2 + 3 * CHARACTERS_BTN_HEIGTH / 2);
        }
        if (onCraftmanBtn){
            batch.draw(info,widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2, heightRatio/ 2 + CHARACTERS_BTN_HEIGTH/2);
        }
        if (onPeasantBtn){
            batch.draw(info,widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2, heightRatio/ 2 - CHARACTERS_BTN_HEIGTH/2);
        }
        if (onTraderBtn){
            batch.draw(info,widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2, heightRatio/ 2 - 3 * CHARACTERS_BTN_HEIGTH / 2);
        }
        engine.step();
        try {
            Thread.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveOn(int x, int y){

        // For War
        if (!onCraftmanBtn && !onPeasantBtn && !onTraderBtn) {
            if ((x >= (widthRatio - CHARACTERS_BTN_WIDTH)) &&
                    (x <= widthRatio) &&
                    (y <= (heightRatio / 2 + 2 * CHARACTERS_BTN_HEIGTH)) &&
                    (y >= (heightRatio / 2 + CHARACTERS_BTN_HEIGTH))) {
                onWarriorBtn = true;
            } else {
                if (onWarriorBtn) {
                    if ((x >= (widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2)) &&
                            (x <= widthRatio - CHARACTERS_BTN_WIDTH / 2) &&
                            (y >= (heightRatio / 2 + 3 * CHARACTERS_BTN_HEIGTH / 2)) &&
                            (y <= (heightRatio / 2 + 3 * CHARACTERS_BTN_HEIGTH / 2 + INFO_ON_BTNS_HEIGTH))) {
                        onWarriorBtn = true;
                    } else {
                        onWarriorBtn = false;
                    }
                } else {
                    onWarriorBtn = false;
                }
            }
        }

        // For Craft
        if(!onWarriorBtn && !onPeasantBtn && !onTraderBtn) {
            if ((x >= (widthRatio - CHARACTERS_BTN_WIDTH)) &&
                    (x <= widthRatio) &&
                    (y <= (heightRatio / 2 + CHARACTERS_BTN_HEIGTH)) &&
                    (y >= (heightRatio / 2))) {
                onCraftmanBtn = true;
            } else {
                if (onCraftmanBtn) {
                    if ((x >= (widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2)) &&
                            (x <= widthRatio - CHARACTERS_BTN_WIDTH / 2) &&
                            (y >= (heightRatio / 2 + CHARACTERS_BTN_HEIGTH/2)) &&
                            (y <= (heightRatio / 2 + CHARACTERS_BTN_HEIGTH / 2 + INFO_ON_BTNS_HEIGTH))) {
                        onCraftmanBtn = true;
                    } else {
                        onCraftmanBtn = false;
                    }
                } else {
                    onCraftmanBtn = false;
                }
            }
        }

        // For Peas
        if (!onWarriorBtn && !onCraftmanBtn && !onTraderBtn) {
            if ((x >= (widthRatio - CHARACTERS_BTN_WIDTH)) &&
                    (x <= widthRatio) &&
                    (y <= (heightRatio / 2)) &&
                    (y >= (heightRatio / 2 - CHARACTERS_BTN_HEIGTH))) {
                onPeasantBtn = true;
            } else {
                if (onPeasantBtn) {
                    if ((x >= (widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2)) &&
                            (x <= widthRatio - CHARACTERS_BTN_WIDTH/2) &&
                            (y >= (heightRatio / 2 -CHARACTERS_BTN_HEIGTH/2)) &&
                            (y <= (heightRatio / 2 - CHARACTERS_BTN_HEIGTH / 2 + INFO_ON_BTNS_HEIGTH))) {
                        onPeasantBtn = true;
                    } else {
                        onPeasantBtn = false;
                    }
                } else {
                    onPeasantBtn = false;
                }
            }
        }

        // For Trader
        if (!onWarriorBtn && !onCraftmanBtn && !onPeasantBtn) {
            if ((x >= (widthRatio - CHARACTERS_BTN_WIDTH)) &&
                    (x <= widthRatio) &&
                    (y <= (heightRatio / 2 - CHARACTERS_BTN_HEIGTH)) &&
                    (y >= (heightRatio / 2 - 2 * CHARACTERS_BTN_HEIGTH))) {
                onTraderBtn = true;
            } else {
                if (onTraderBtn) {
                    if ((x >= (widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2)) &&
                            (x <= widthRatio - CHARACTERS_BTN_WIDTH/2) &&
                            (y >= (heightRatio / 2 - 3 * CHARACTERS_BTN_HEIGTH / 2)) &&
                            (y <= (heightRatio / 2 - 3 * CHARACTERS_BTN_HEIGTH / 2 + INFO_ON_BTNS_HEIGTH))) {
                        onTraderBtn = true;
                    } else {
                        onTraderBtn = false;
                    }
                } else {
                    onTraderBtn = false;
                }
            }
        }

        for (IPersonToView person : characters){
            if (x >= (person.getLocation().getX() - CHARACTERS_WIDTH/2) &&
                    (x <= person.getLocation().getX() + CHARACTERS_WIDTH/2) &&
                    (y <= (person.getLocation().getY() + CHARACTERS_HEIGTH / 2)) &&
                    (y >= (person.getLocation().getY() - CHARACTERS_HEIGTH / 2))){
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

        if (onWarriorBtn){
            if ((x >= widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2 + 15) &&
                    (x <= widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2 + 131) &&
                    (y >= (heightRatio / 2 + 3 * CHARACTERS_BTN_HEIGTH / 2 + 20)) &&
                    (y <= (heightRatio / 2 + 3 * CHARACTERS_BTN_HEIGTH / 2 + 55))) {
                engine.addCharacter(Profession.Warrior);
            }
        }

        if (onCraftmanBtn){
            if ((x >= widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2 + 15) &&
                    (x <= widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2 + 131) &&
                    (y >= (heightRatio / 2 + CHARACTERS_BTN_HEIGTH / 2 + 20)) &&
                    (y <= (heightRatio / 2 + CHARACTERS_BTN_HEIGTH / 2 + 55))) {
                engine.addCharacter(Profession.Craftsman);
            }
        }

        if (onPeasantBtn){
            if ((x >= widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2 + 15) &&
                    (x <= widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2 + 131) &&
                    (y >= (heightRatio / 2 -CHARACTERS_BTN_HEIGTH/2 + 20)) &&
                    (y <= (heightRatio / 2 -CHARACTERS_BTN_HEIGTH/2 + 55))) {
                engine.addCharacter(Profession.Peasant);
            }
        }

        if (onTraderBtn){
            if ((x >= widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2 + 15) &&
                    (x <= widthRatio - INFO_ON_BTNS_WIDTH - CHARACTERS_BTN_WIDTH / 2 + 131) &&
                    (y >= (heightRatio / 2 - 3 * CHARACTERS_BTN_HEIGTH / 2 + 20)) &&
                    (y <= (heightRatio / 2 - 3 * CHARACTERS_BTN_HEIGTH / 2 + 55))) {
                engine.addCharacter(Profession.Trader);
            }
        }
    }

    private void drawFight (SpriteBatch batch, IPersonToView person){

//        IPersonToView opponent = person.get
        
        switch (person.getProfession()){
            case Warrior:
                if (!person.getStatus().equals(PersonState.Fighting)) {
                    batch.draw(warrior, person.getLocation().x - CHARACTERS_WIDTH / 2, person.getLocation().y - CHARACTERS_HEIGTH / 2, CHARACTERS_WIDTH, CHARACTERS_HEIGTH);
                } else {
                    batch.draw(warrior, person.getLocation().x - CHARACTERS_WIDTH, person.getLocation().y - CHARACTERS_HEIGTH / 2, CHARACTERS_WIDTH, CHARACTERS_HEIGTH);

                }
                break;
            case Craftsman:
                batch.draw(craftman, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
                break;
            case Peasant:
                batch.draw(peasant, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
                break;
            case Robber:
                batch.draw(robber, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
                break;
            case Trader:
                batch.draw(trader, person.getLocation().x - CHARACTERS_WIDTH/2, person.getLocation().y - CHARACTERS_HEIGTH/2, CHARACTERS_WIDTH,CHARACTERS_HEIGTH);
                break;
        }
    }
}