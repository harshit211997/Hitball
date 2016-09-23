package com.sdsmdg.cycle;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.sdsmdg.ball.R;

public class AboutUsActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
        );

        setContentView(R.layout.activity_about_us);

        ImageView gitImage = (ImageView) findViewById(R.id.git_about_us);
        ImageView fbImage = (ImageView) findViewById(R.id.fb_about_us);
        ImageView playImage = (ImageView) findViewById(R.id.play_about_us);

        gitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browser = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://goo.gl/smpcVZ"));
                startActivity(browser);
            }
        });
        fbImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browser = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://goo.gl/6Cznj6"));
                startActivity(browser);
            }
        });
        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browser = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/developer?id=MDG,+SDS"));
                startActivity(browser);
            }
        });
    }
}
