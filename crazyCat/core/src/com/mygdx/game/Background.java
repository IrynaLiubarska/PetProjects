package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Iryna on 11.12.2016.
 */
public class Background {

    class Bird {                                        // внутренний класс
        private Vector2 position;
        private float speed;
        private Texture birdTexture;

        Bird() {
            this.position = new Vector2((float) Math.random() * 1280, (float) Math.random() * 720);
            this.speed = 1.0f + (float) Math.random() * 7.0f;
            this.birdTexture = new Texture("bird.png");
        }

        public void update() {
            position.x -= speed;
            position.y += (float) Math.sin(position.x);
            if (position.x < -20) {
                position.x = 1280 + (float) Math.random() * 300;
                position.y = (float) Math.random() * 720;
            }
        }
    }

    private Texture backgroundTexture;
    private Bird[] birds;
    private final int BIRDS_COUNT = 10;


    public Background() {
        this.backgroundTexture = new Texture("field.jpg");
        birds = new Bird[BIRDS_COUNT];
        for (int i = 0; i < BIRDS_COUNT; i++) {
            birds[i] = new Bird();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(backgroundTexture, 0, 0);
        for (int i = 0; i < BIRDS_COUNT; i++) {
            batch.draw(birds[i].birdTexture, birds[i].position.x, birds[i].position.y);
            birds[i].update();
        }
    }

    public void update() {
        for (int i = 0; i < BIRDS_COUNT; i++) {
            birds[i].update();
        }
    }

}
