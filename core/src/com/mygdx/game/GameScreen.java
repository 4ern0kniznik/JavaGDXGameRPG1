package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.characters.GameCharacter;
import com.mygdx.game.characters.Hero;
import com.mygdx.game.characters.Monster;

import java.util.*;

public class GameScreen {
    private SpriteBatch batch;
    private BitmapFont font24;
    private Map map;
    private Hero hero;


    private List<GameCharacter> allCharacters;
    private List<Monster> allMonsters;

    private Comparator<GameCharacter> drawOrderComparator;

    public List<Monster> getAllMonsters() {
        return allMonsters;
    }

    public Map getMap() {
        return map;
    }

    public Hero getHero() {
        return hero;
    }


    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    public void create() {
        map = new Map();
        allCharacters = new ArrayList<>();
        allMonsters = new ArrayList<>();
        hero = new Hero(this);
        allCharacters.addAll(Arrays.asList(
                hero,
                new Monster(this),
                new Monster(this)
        ));
        for (int i =0; i< allCharacters.size(); i++){
            if (allCharacters.get(i) instanceof Monster) {
                allMonsters.add((Monster)allCharacters.get(i));
            }
        }
        font24 = new BitmapFont(Gdx.files.internal("font24.fnt"));
        drawOrderComparator = new Comparator<GameCharacter>() {
            @Override
            public int compare(GameCharacter o1, GameCharacter o2) {
                return (int) (o2.getPosition().y - o1.getPosition().y);
            }
        };

    }
    public  void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        map.render(batch);

        Collections.sort(allCharacters, drawOrderComparator);
        for (int i = 0; i < allCharacters.size(); i++) {
            allCharacters.get(i).render(batch, font24);

        }
        batch.end();

    }
    public void update(float dt) {
        for (int i = 0; i < allCharacters.size(); i++) {
            allCharacters.get(i).update(dt);
        }
        for (int i = 0; i < allMonsters.size(); i++) {
            Monster curretnsMonster = allMonsters.get(i);
            if (!curretnsMonster.isAlive()) {
                allMonsters.remove(curretnsMonster);
                allCharacters.remove(curretnsMonster);
            }
        }
    }
}


