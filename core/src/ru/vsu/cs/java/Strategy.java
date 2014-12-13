package ru.vsu.cs.java;

import com.badlogic.gdx.Game;
import ru.vsu.cs.java.screens.StartScreen;

public class Strategy extends Game {

    @Override
    public void create () {
        setScreen(new StartScreen(this));
    }

}