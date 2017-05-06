package com.example.a3rdyearproject.lucanteen.Class.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a3rdyearproject.lucanteen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class UserSignIn extends AppCompatActivity
{

    ImageView imageView;
    private EditText email, pass;
    private Button signin,forgotPass,signup;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in);

        imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(getApplicationContext()).load
                ("https://lh3.googleusercontent.com/ihHvROvOOPfWoPYKaFkz0Yqb6lswILNLR_UYkSQqFwthF3vqZKCUowB5fKXaxRvCBMYaNb8=s140")
                .into(imageView);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        signin = (Button) findViewById(R.id.submit);
        signup = (Button) findViewById(R.id.signup);
        forgotPass = (Button) findViewById(R.id.forgot_password);
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null )
                {
                    String emailText = email.getText().toString().trim();//ommit fore space
                    String passText = pass.getText().toString().trim();
                    if ((emailText.equals("lucanteen4@gmail.com")) && (passText.equals("mnn123456")))
                    {
                        Intent intent = new Intent(UserSignIn.this,Admin.class);
                        // intent.putExtra("email",firebaseAuth.getCurrentUser().getEmail());
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(UserSignIn.this,NormalUser.class);
                        // intent.putExtra("email",firebaseAuth.getCurrentUser().getEmail());
                        startActivity(intent);
                    }

                }

            }
        };

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSignIn.this,UserSignUp.class);
                startActivity(intent);
            }
        });
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSignIn.this,UserForgotPassword.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                progressDialog = new ProgressDialog(UserSignIn.this);
                progressDialog.setMessage("Sign in is on progress");
                progressDialog.show();

                String emailText = email.getText().toString().trim();//ommit fore space
                String passText = pass.getText().toString().trim();

                if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passText)) {
                    progressDialog.hide();
                    Toast.makeText(UserSignIn.this, "Give Data", Toast.LENGTH_SHORT).show();
                } else {

                    firebaseAuth.signInWithEmailAndPassword(emailText, passText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.hide();
                            if (!task.isComplete()) {
                                Toast.makeText(UserSignIn.this, "Problem", Toast.LENGTH_SHORT).show();
                            }
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(UserSignIn.this, "Wrong email and password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });



    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null)
        {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }



}
