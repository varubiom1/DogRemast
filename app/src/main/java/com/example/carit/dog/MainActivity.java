package com.example.carit.dog;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_registro);
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onStart(){
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser=mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {

    }

    public void createAccount (String email, String password){
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sing in success, update UI with the signed-in user´s information
                        //Log.d(TAG, "CreateUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    }else{
                        //If sign in fails, display a message to the user.
                        //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }

            });
    }

    public void signIn (String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                 @Override
                 public void onComplete (@NonNull Task<AuthResult> task){
                     if (task.isSuccessful()) {
                         //Sign in success, update UIWITH THE SIGNED-IN USER´S INFORMATION
                         //Log.d(TAG, "signInWithEmail:success");
                         FirebaseUser user = mAuth.getCurrentUser();
                         updateUI(user);
                     }else{
                         //If sign in fails,display a message to the user.
                         //Log.w(TAG, "signInWothEmail:failure", task.getException());
                         Toast.makeText(MainActivity.this, "Authenticationfailed.",
                                 Toast.LENGTH_SHORT).show();
                         updateUI(null);

                     }
                 }
                });
    }

    public void getCurrentUser (){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            //Name, email, adress, and Profile photo URL
            String name=user.getDisplayName();
            String email=user.getEmail();
            Uri photoUrl=user.getPhotoUrl();

            //Check if user´s email is verified
            boolean emailVerified=user.isEmailVerified();

            String uid=user.getUid();

        }
    }

    //hola tu

}