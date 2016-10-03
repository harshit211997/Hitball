package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.cycle.chelpers.AssetLoader;

public class JumpingTextView {

    Vector2 position;
    String text;
    BitmapFont font;
    float scale = 0.5f;
    float theta = 0;
    boolean show = false;

    public JumpingTextView(Vector2 position, String text) {
        this.position = position;
        this.text = text;
        font = AssetLoader.font80;
    }

    public void render(SpriteBatch batcher) {

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, text);
        float w = glyphLayout.width;
        float h = glyphLayout.height;
        font.getData().setScale(scale);
        font.draw(batcher, text, position.x - w / 2, position.y - h / 2);

    }

    public void update(float delta) {
        theta += 4 * delta;
        scale = 0.50f + (float) Math.sin(theta) * 0.05f;
    }

    public void show() {
        show = true;
    }

    public boolean isHidden() {
        return !show;
    }

    public void hide() {
        show = false;
    }

}
