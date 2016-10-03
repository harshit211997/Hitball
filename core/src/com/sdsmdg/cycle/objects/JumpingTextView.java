package com.sdsmdg.cycle.objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sdsmdg.cycle.chelpers.AssetLoader;

public class JumpingTextView {

    String text;
    BitmapFont font;
    float scale = 0.5f;
    float theta = 0;

    public JumpingTextView(String text) {
        this.text = text;
        font = AssetLoader.font80;
    }

    public void render(SpriteBatch batcher, float x, float y) {

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, text);
        float w = glyphLayout.width;
        float h = glyphLayout.height;
        font.getData().setScale(scale);
        font.draw(batcher, text, x - w / 2, y - h / 2);

    }

    public void update(float delta) {
        theta += 4 * delta;
        scale = 0.50f + (float) Math.sin(theta) * 0.05f;
    }

}
