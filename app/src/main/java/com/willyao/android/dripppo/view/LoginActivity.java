package com.willyao.android.dripppo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.willyao.android.dripppo.R;
import com.willyao.android.dripppo.dripppo.Dribbble;
import com.willyao.android.dripppo.dripppo.DribbbleException;
import com.willyao.android.dripppo.dripppo.auth.Auth;
import com.willyao.android.dripppo.dripppo.auth.AuthActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mitya on 2/1/2017.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.activity_login_btn) TextView loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // load access token from shared preference
        Dribbble.init(this);

        if (!Dribbble.isLoggedIn()) {
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Auth.openAuthActivity(LoginActivity.this);
                }
            });
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Auth.REQ_CODE && resultCode == RESULT_OK) {
            final String authCode = data.getStringExtra(AuthActivity.KEY_CODE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String token = Auth.fetchAccessToken(authCode);
                        Dribbble.login(LoginActivity.this, token);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (IOException | DribbbleException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
