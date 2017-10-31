package com.ruslanlyalko.agency.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ruslan Lyalko
 * on 31.10.2017.
 */

class BaseRepository {

    FirebaseDatabase getDatabase() {
        return FirebaseDatabase.getInstance();
    }

    FirebaseAuth getAuth() {
        return FirebaseAuth.getInstance();
    }
}
