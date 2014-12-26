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
        Forest = new Rectangle(0, 0, 350, 350);             //
        Farm = new Rectangle(150, height - 320, 300, 300);            //      Убрать константы.
        CraftHouse = new Rectangle(670, height - 170, 150, 150);     //
        Castle = new Rectangle(wight - 300,70,261,424);      //
        for (int i = 0; i < 20; i++)
            MarketPlace.add(i,new TraderPlace(new Point(Castle.x+i*30,(int)(Castle.y+Castle.getSize().getHeight()+10))));
    }


    public Point findFreeTraderPlace(){
        for(TraderPlace place: MarketPlace){
            if (place.isEmpty)
                return place.getLocation();
        }
        RandomContainer random = new RandomContainer();
        return new Point(random.next(0,wight),random.next(0,height));
    }

    //TODO: может быть косяк с зацикливанием!
    public Point findTrader(){
        RandomContainer random = new RandomContainer();
        TraderPlace p = MarketPlace.get(random.next(0,8));
        if (p.isEmpty)
            p = MarketPlace.get(random.next(0, 8));
        return p.getLocation();
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
        private Point place;
        public boolean isEmpty;

        public TraderPlace(Point place)
        {
            this.place = place;
            isEmpty = true;
        }
        public Point getLocation(){
            return place;
        }
    }
}
