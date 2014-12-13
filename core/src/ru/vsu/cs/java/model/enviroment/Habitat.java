package ru.vsu.cs.java.model.enviroment;

import ru.vsu.cs.java.model.RandomContainer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Среда обитания.
 */
public class Habitat {

    private int wight;
    private int height;
    public Rectangle Forest;
    public Rectangle Farm;
    public Rectangle CraftHouse;
    public Rectangle Castle;
    public ArrayList<TraderPlace> MarketPlace;

    public Habitat(int wight, int height )
    {
        this.wight = wight;
        this.height = height;
        MarketPlace = new ArrayList<TraderPlace>(20);
        Forest = new Rectangle(0, 0, 170, 170);             //
        Farm = new Rectangle(200, 30, 100, 100);            //      Убрать константы.
        CraftHouse = new Rectangle(300, 500, 100, 100);     //
        Castle = new Rectangle(wight - 324,0,324,285);       //
        for (int i = 0; i < 20; i++)
            MarketPlace.add(i,new TraderPlace(new Rectangle(Castle.x+i*30,(int)(Castle.y+Castle.getSize().getHeight()+10),30,30)));
    }



    public Point findTrader(){
        RandomContainer random = new RandomContainer();
        TraderPlace p = MarketPlace.get(random.next(0,8));
        while (p.isEmpty)
            p = MarketPlace.get(random.next(0, 8));
        return new Point(p.place.x+p.place.getSize().width/2,p.place.y+p.place.getSize().height);
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public class TraderPlace
    {
        public Rectangle place;
        public boolean isEmpty;

        public TraderPlace(Rectangle place)
        {
            this.place = place;
            isEmpty = true;
        }
    }
}
