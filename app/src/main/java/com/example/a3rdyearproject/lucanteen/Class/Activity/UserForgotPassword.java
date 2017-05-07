package com.example.a3rdyearproject.lucanteen.Class.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a3rdyearproject.lucanteen.Class.JavaClass.CheckNetwork;
import com.example.a3rdyearproject.lucanteen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UserForgotPassword extends AppCompatActivity {

    private EditText inputEmail;
    private Button btnReset, btnBack;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forgot_password);

        inputEmail = (EditText) findViewById(R.id.email);
        btnReset = (Button) findViewById(R.id.forgot_password);
        btnBack = (Button) findViewById(R.id.back);


        auth = FirebaseAuth.getInstance();

        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(CheckNetwork.isInternetAvailable(UserForgotPassword.this)) //returns true if internet available
                {

                    String email = inputEmail.getText().toString().trim();

                    if (TextUtils.isEmpty(email))
                    {
                        Toast.makeText(getApplication(), "Enter your registered email id",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }


                    auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(UserForgotPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(UserForgotPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                }
                else
                {
                    //Toast.makeText(UserSignUp.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserForgotPassword.this);
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
}
