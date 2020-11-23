package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	/*
		===Идеи===
		1. + Движение по пикселям
		2. Преграды
		3. Анимация
		4. Атака
		5. + Хаотичное движение монстра
		6. + Преследование монстром героя
		7. Исцеление, монеты, зелья
		8. +- Параметры героям\монстрам (ХП, крит, скорость)
		9. Система уровней игры
		10. Опыт герою
		11. + Оружие
		12. Финальный босс
		13. + Драка с монстром
		14. + Индикатор здоровья
		15. Привязать логику здоровья
		16. + Перенос на вектора
		17. + Отображение текста
		18. Столкновения монстров
		19. Камера
		 */


	private SpriteBatch batch;
	private GameScreen gameScreen;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.gameScreen = new GameScreen(batch);
		this.gameScreen.create();
	}

	@Override
	public void render () {
		gameScreen.render();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
