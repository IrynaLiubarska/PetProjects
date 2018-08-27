package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Iryna on 11.12.2016.
 */
public class Ball {
    private float speed;
    private Vector2 position;
    private boolean active;

    public Ball() {
        this.speed = 20.0f;
        this.position = new Vector2(0.0f, 0.0f);
        this.active = false;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update() {
        position.x += speed;
        if (position.x > 1280) {
            destroy();
        }
    }

    public void setup(float x, float y) {
        position.x = x;
        position.y = y;
        active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void destroy() {
        active = false;
    }
    
}
