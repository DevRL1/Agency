package com.ruslanlyalko.agency.data;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.ruslanlyalko.agency.data.listeners.UserListener;
import com.ruslanlyalko.agency.data.listeners.UsersListener;
import com.ruslanlyalko.agency.data.models.UserItem;

/**
 * Created by Ruslan Lyalko
 * on 30.10.2017.
 */

public interface AgencyRepository {

    void getUsers(UsersListener listener);

    Task<AuthResult> createUser(UserItem user, String password);

    Task<Void> updateUser(UserItem user);

    void logout();

    void getCurrentUserData(UserListener listener);
}