package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Iryna on 11.12.2016.
 */
public class Bone {

    private static Texture boneTexture;
    private Vector2 position;
    private float speed;
    private Rectangle rectangle;
    private int health;
    private float angel;


    public Bone() {
        if (boneTexture == null) {
            this.boneTexture = new Texture("bone.png");
        }
        this.position = new Vector2(1280 + (float) Math.random() * 600.0f, (float) Math.random() * 720);
        this.speed = 6.0f + (float) Math.random() * 3.0f;
        rectangle = new Rectangle(position.x, position.y, 160, 160);
        health = 3;
        angel = (float) Math.random() * 360.0f;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(1, (3 - health) * 0.3f, (3 - health * 0.3f), 1);
        batch.draw(boneTexture, position.x, position.y, 20, 20, 160, 160, 1.0f, 1.0f, angel, 0, 0, 160, 160, false, false);
        batch.setColor(1, 1, 1, 1);
    }

    public void update() {
        position.x -= speed;
        angel += speed;
        rectangle.x = position.x;
        rectangle.y = position.y;
        if (position.x < -57) {
            recreate();
        }
    }

    public void getDamage() {
        health--;
        if (health <= 0) {
            recreate();
        }
    }

    private void recreate() {
        position.x = 1280 + (float) Math.random() * 600;
        position.y = (float) Math.random() * 720;
        speed = 2.0f + (float) Math.random() * 5.0f;
    }
    
}
