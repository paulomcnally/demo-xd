package xd.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tumblr.remember.Remember;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xd.myapplication.MainActivity;
import xd.myapplication.R;
import xd.myapplication.api.Api;
import xd.myapplication.models.AccessToken;
import xd.myapplication.models.User;

public class SignInActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signIn;
    private Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initViews();
    }

    private void initViews() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.sign_in);
        signUp = (Button) findViewById(R.id.sign_up);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

    }

    /**
     * Esto sirve para iniar sesion.
     */
    private void signIn() {
        if (email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Necesito un email xD", Toast.LENGTH_LONG).show();
        } else if(password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Necesito la contraseña chatel", Toast.LENGTH_LONG).show();
        } else {
           loginRequest(email.getText().toString(), password.getText().toString());
        }
    }

    /**
     * To make http request
     * @param email
     * @param password
     */
    private void loginRequest(String email, String password) {
        // instance a user
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        // create call
        Call<AccessToken> call = Api.instance().login(user);
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.body() != null) {
                    Log.i("access_token", response.body().getId());
                    Remember.putString("access_token", response.body().getId(), new Remember.Callback() {
                        @Override
                        public void apply(Boolean success) {
                            if (success) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {

            }
        });
    }

    private void signUp() {
        if (email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Necesito un email xD", Toast.LENGTH_LONG).show();
        } else if(password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Necesito la contraseña chatel", Toast.LENGTH_LONG).show();
        } else {
            // instance a user
            User user = new User();
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());

            // create call
            Call<User> call = Api.instance().signUp(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() != null) {
                        loginRequest(email.getText().toString(), password.getText().toString());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }

}
