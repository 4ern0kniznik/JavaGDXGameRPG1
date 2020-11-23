package com.mygdx.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Weapon;

public class Monster extends GameCharacter{
    private float moveTimer;
    private float activityRadius;


    public Monster(GameScreen gameScreen) {
        this.texture = new Texture("Skeleton.png");
        this.textureHP = new Texture("Bar.png");
        this.position = new Vector2(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        this.direction = new Vector2(0, 0);
        this.temp = new Vector2(0, 0);
        this.speed = 40.0f;
        this.activityRadius = 200.0f;
        this.gameScreen = gameScreen;
        this.hpMax = 20;
        this.hp = this.hpMax;
        this.weapon = new Weapon("Rusty Sword", 50.0f, 0.8f, 5.0f);

        }

    @Override
    public void update(float dt) {
        damageEffectTimer -= dt;
        if (damageEffectTimer < 0.0f) {
            damageEffectTimer = 0.0f;
        }

        float dst = gameScreen.getHero().getPosition().dst(this.position);
        if (dst < activityRadius){
                temp.set(gameScreen.getHero().getPosition()).sub(this.position).nor();
                position.mulAdd(temp, speed *dt);
        } else {
                position.mulAdd(direction, speed * dt);
                moveTimer -= dt;
                if (moveTimer < 0.0f) {
                    moveTimer = MathUtils.random(1.0f, 4.0f);
                    direction.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f));
                    direction.nor();
                }
        }
        if (dst < weapon.getAttackRadius()){
            attackTimer += dt;
            if (attackTimer >= weapon.getAttackPeriod()) {
                attackTimer = 0.0f;
                gameScreen.getHero().takeDamage(weapon.getDamage());
            }
        }
        checkScreenBounds();
    }
}
