package com.mygdx.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Weapon;

public class Hero extends GameCharacter {


    public Hero(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.texture = new Texture("Knight.png");
        this.textureHP = new Texture("Bar.png");
        this.position = new Vector2(200, 200);
        this.temp = new Vector2(0, 0);
        this.direction = new Vector2(0,0);
        this.hpMax = 100.0f;
        this.hp = this.hpMax;
        this.speed = 100.0f;
        this.weapon = new Weapon( "Sword", 150.0f, 0.5f,4.0f);   ///урон от персонажа
    }


    @Override
    public void update(float dt) {
        damageEffectTimer -= dt;
        if (damageEffectTimer < 0.0f) {
            damageEffectTimer = 0.0f;
        }

        Monster naerestMonster = null;
        float minDist = Float.MAX_VALUE;
        for (int i = 0; i < gameScreen.getAllMonsters().size(); i++){
            float dst = gameScreen.getAllMonsters().get(i).getPosition().dst(this.position);
            if (dst < minDist) {
                minDist = dst;
                naerestMonster = gameScreen.getAllMonsters().get(i);
            }
        }


        if (naerestMonster != null && minDist < weapon.getAttackRadius()) {
            attackTimer +=dt;
            if (attackTimer > weapon.getAttackPeriod()) {
                attackTimer = 0.0f;
                naerestMonster.takeDamage(weapon.getDamage());
            }
        }
        direction.set(0,0);

     /*   if (damageEffectTimer < 0.0f) {
            damageEffectTimer = 0.0f;
            }
            */


        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
           direction.x = 1.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction.x = -1.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction.y = 1.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction.y = -1.0f;
        }


        temp.set(position).mulAdd(direction, speed * dt);   ///прохождение препятствия
        if (gameScreen.getMap().isCellPassable(temp)) {
            position.mulAdd(direction, speed * dt);
        }

        checkScreenBounds();
    }
}
