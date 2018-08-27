package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Iryna on 11.12.2016.
 */
public class Hero {
   
    private Texture heroTexture;
    private Vector2 position;
    private float speed;
    private int dogCounter;
    private int dogRate;

    public Hero() {
        this.heroTexture = new Texture("cat.png");
        this.position = new Vector2(100, 100);
        this.speed = 4.0f;
        dogRate = 10;
    }

    public void render(SpriteBatch batch) {
        batch.draw(heroTexture, position.x, position.y);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            dogCounter++;
            if (dogCounter > dogRate) {
                dogCounter = 0;
                for (int i = 0; i < CrazyCatGame.balls.length; i++) {
                    if (!CrazyCatGame.balls[i].isActive()) {
                        CrazyCatGame.balls[i].setup(position.x+60, position.y+20);
                        break;
                    }
                }
            }
        }
        getCurrentPosition();
    }

    private void getCurrentPosition() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += speed;
            if (position.y > Gdx.graphics.getHeight() - 57) position.y = Gdx.graphics.getHeight() - 57;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed;
            if (position.y < 0) position.y = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed;
            if (position.x < 0) position.x = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed;
            if (position.x > Gdx.graphics.getWidth() - 57) position.x = Gdx.graphics.getWidth() - 57;
        }
    }

}
