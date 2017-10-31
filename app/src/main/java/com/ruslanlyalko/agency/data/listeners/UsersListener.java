package com.ruslanlyalko.agency.data.listeners;

import com.ruslanlyalko.agency.data.models.UserItem;

import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 31.10.2017.
 */

public interface UsersListener {

    void updateUsers(List<UserItem> users);
}
