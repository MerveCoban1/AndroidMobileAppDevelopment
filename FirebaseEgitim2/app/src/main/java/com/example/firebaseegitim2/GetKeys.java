package com.example.firebaseegitim2;

import com.google.firebase.database.DatabaseReference;

public class GetKeys {
    public static String getKey(DatabaseReference ref1){
        DatabaseReference push = ref1.child("message").push();
        String key = push.getKey();
        return key;
    }
}
