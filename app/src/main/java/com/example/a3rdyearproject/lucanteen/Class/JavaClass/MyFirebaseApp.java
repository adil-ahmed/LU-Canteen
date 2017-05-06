package com.example.a3rdyearproject.lucanteen.Class.JavaClass;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Adil on 5/2/2017.
 */

public class MyFirebaseApp extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

       /* if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }*/

    }
}
