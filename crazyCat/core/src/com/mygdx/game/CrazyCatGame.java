package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CrazyCatGame extends ApplicationAdapter {
    
    private SpriteBatch batch;
    private Background background;
    private Hero hero;
    private Bone[] bones;
    private final int COUNT_BONE = 10;
    public static Ball[] balls;
    private final int COUNT_BALL = 20;
    private Texture ballTexture;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
        hero = new Hero();
        bones = new Bone[COUNT_BONE];
        for (int i = 0; i < COUNT_BONE; i++) {
            bones[i] = new Bone();
        }
        ballTexture = new Texture("ball.png");
        balls = new Ball[COUNT_BALL];
        for (int i = 0; i < COUNT_BALL; i++) {
            balls[i] = new Ball();
        }

    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);
        hero.render(batch);
        for (int i = 0; i < COUNT_BONE; i++) {
            bones[i].render(batch);
        }
        for (int i = 0; i < COUNT_BALL; i++) {
            if (balls[i].isActive()) {
                batch.draw(ballTexture, balls[i].getPosition().x, balls[i].getPosition().y);
            }
        }
        changingState();
        batch.end();
    }

    private void changingState() {
        for (int i = 0; i < COUNT_BALL; i++) {
            if (balls[i].isActive()) {
                for (int j = 0; j < COUNT_BONE; j++) {
                    if (bones[j].getRectangle().contains(balls[i].getPosition())) {
                        bones[j].getDamage();
                        balls[i].destroy();
                        break;
                    }
                }
            }
        }
    }

    public void update() {
        background.update();
        hero.update();
        for (int i = 0; i < COUNT_BONE; i++) {
            bones[i].update();
        }
        for (int i = 0; i < COUNT_BALL; i++) {
            if (balls[i].isActive()) {
                balls[i].update();
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
    
}
