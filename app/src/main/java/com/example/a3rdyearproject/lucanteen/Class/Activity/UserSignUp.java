package com.example.a3rdyearproject.lucanteen.Class.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a3rdyearproject.lucanteen.Class.JavaClass.CheckNetwork;
import com.example.a3rdyearproject.lucanteen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class UserSignUp extends AppCompatActivity {

    private EditText email, pass;
    private ImageView imageView;
    private Button signin, forgotPass, signup;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);






        /*Get connect with layout by id*/
            /*imageView = (ImageView) findViewById(R.id.imageView);
            Picasso.with(getApplicationContext()).load
                    ("https://lh3.googleusercontent.com/ihHvROvOOPfWoPYKaFkz0Yqb6lswILNLR_UYkSQqFwthF3vqZKCUowB5fKXaxRvCBMYaNb8=s140")
                    .into(imageView);*/
            email = (EditText) findViewById(R.id.email);
            pass = (EditText) findViewById(R.id.pass);
            signin = (Button) findViewById(R.id.signin);
            signup = (Button) findViewById(R.id.signup);
            forgotPass = (Button) findViewById(R.id.forgot_password);
            firebaseAuth = FirebaseAuth.getInstance(); /*Initializing Firebase Authentication*/


        /*Getting user corrent information*/
            authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                /*Getting users value in user variable*/

                /*if(user!=null)
                {
                    Toast.makeText(UserSignUp.this,"Register here!",Toast.LENGTH_SHORT).show();
                    *//*If value is not null*//*
                }
                else
                {
                    Toast.makeText(UserSignUp.this,"signed in",Toast.LENGTH_SHORT).show();
                    *//*If value is null,that mean registration not done yet*//*
                }*/

                }
            };

        /*If sign in button pressed then take me to sign in activity*/
            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if(CheckNetwork.isInternetAvailable(UserSignUp.this)) //returns true if internet available
                    {

                        //do something. loadwebview.
                        Intent intent = new Intent(UserSignUp.this, UserSignIn.class);
                        startActivity(intent);
                    }
                    else
                    {
                        //Toast.makeText(UserSignUp.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserSignUp.this);
                        builder.setMessage("Please enable your internet connection");
                        builder.setTitle("No Internet");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //finish();

                            }
                        });
                        builder.show();
                    }

                }
            });
        /*If you forgot your password and to recover it go to UserForgotPassword activity*/
            forgotPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                    if(CheckNetwork.isInternetAvailable(UserSignUp.this)) //returns true if internet available
                    {

                        Intent intent = new Intent(UserSignUp.this, UserForgotPassword.class);
                        startActivity(intent);
                    }
                    else
                    {
                        //Toast.makeText(UserSignUp.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserSignUp.this);
                        builder.setMessage("Please enable your internet connection");
                        builder.setTitle("No Internet");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //finish();

                            }
                        });
                        builder.show();
                    }
                }
            });
        /*If sign up button pressed*/

            signup.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                /*Show a dialog box while loading*/
                    if(CheckNetwork.isInternetAvailable(UserSignUp.this)) //returns true if internet available
                    {
                        progressDialog = new ProgressDialog(UserSignUp.this);
                        progressDialog.setMessage("Sign up is on progress");
                        progressDialog.show();

                        final String emailText = email.getText().toString().trim();//ommit for space
                        final String passText = pass.getText().toString().trim();

                        if (TextUtils.isEmpty(emailText))
                        {
                            progressDialog.hide();/*hide the dialog box*/
                            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        else if (TextUtils.isEmpty(passText))
                        {
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        else if (passText.length() < 6)
                        {
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                /*Create user by createUserWithEmailAndPassword method*/
                        Task<AuthResult> authResultTask = firebaseAuth.createUserWithEmailAndPassword(emailText, passText).addOnCompleteListener
                                (UserSignUp.this, new OnCompleteListener<AuthResult>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task)
                                    {
                                        progressDialog.hide();


                                        if (task.isSuccessful())
                                        {

                                            Toast.makeText(UserSignUp.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                    /*Login to admin*/
                                            if ((emailText.equals("lucanteen4@gmail.com")) && (passText.equals("mnn123456")))
                                            {
                                                Intent intent = new Intent(UserSignUp.this, Admin.class);
                                                //intent.putExtra("email",firebaseAuth.getCurrentUser().getEmail());
                                                startActivity(intent);
                                            }
                                    /*Normal login*/
                                            else
                                            {
                                                Intent intent = new Intent(UserSignUp.this, NormalUser.class);
                                                //intent.putExtra("email",firebaseAuth.getCurrentUser().getEmail());
                                                startActivity(intent);

                                            }


                                        }
                                /*if user already registered by same email*/
                                        if (!task.isSuccessful())
                                        {
                                            if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                            {
                                                Toast.makeText(UserSignUp.this, "user already exists", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                Toast.makeText(UserSignUp.this, "Invalid email", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                });


                    }
                    else
                    {
                        //Toast.makeText(UserSignUp.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserSignUp.this);
                        builder.setMessage("Please enable your internet connection");
                        builder.setTitle("No Internet");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //finish();

                            }
                        });
                        builder.show();
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
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }

    }


    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(UserSignUp.this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
