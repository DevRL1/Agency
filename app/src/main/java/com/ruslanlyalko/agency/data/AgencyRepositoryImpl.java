package com.ruslanlyalko.agency.data;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ruslanlyalko.agency.data.listeners.OrdersListener;
import com.ruslanlyalko.agency.data.listeners.UserListener;
import com.ruslanlyalko.agency.data.listeners.UsersListener;
import com.ruslanlyalko.agency.data.models.OrderItem;
import com.ruslanlyalko.agency.data.models.UserItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 30.10.2017.
 */

public class AgencyRepositoryImpl extends BaseRepository implements AgencyRepository {
    @Override
    public void getUsers(final UsersListener listener) {
        getDatabase().getReference(DefaultConfiguration.DB_USERS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<UserItem> users = new ArrayList<>();
                for (DataSnapshot userData : dataSnapshot.getChildren()) {
                    UserItem item = userData.getValue(UserItem.class);
                    if (item != null)
                        users.add(item);
                }
                listener.updateUsers(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public Task<AuthResult> createUser(UserItem user, String password) {

        Task<AuthResult> result = getAuth().createUserWithEmailAndPassword(user.getEmail(), password);
        result.addOnCompleteListener(authResult -> {
            if (!authResult.isSuccessful()) return;
            String id = authResult.getResult().getUser().getUid();
            user.setId(id);
            getDatabase().getReference(DefaultConfiguration.DB_USERS).child(id).setValue(user);
        });
        return result;
    }

    @Override
    public Task<Void> updateUser(UserItem user) {
        return getDatabase().getReference(DefaultConfiguration.DB_USERS).child(user.getId()).setValue(user);
    }

    @Override
    public void logout() {
        getAuth().signOut();
    }

    @Override
    public void getCurrentUserData(final UserListener listener) {
        if (getAuth().getCurrentUser() == null)
            throw new RuntimeException("No user logged in");

        String id = getAuth().getCurrentUser().getUid();
        getDatabase().getReference(DefaultConfiguration.DB_USERS).child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserItem user = dataSnapshot.getValue(UserItem.class);
                listener.updateUsers(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void getOrders(OrdersListener listener) {
        getDatabase().getReference(DefaultConfiguration.DB_ORDERS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<OrderItem> orders = new ArrayList<>();
                for (DataSnapshot orderData : dataSnapshot.getChildren()) {
                    OrderItem item = orderData.getValue(OrderItem.class);
                    if (item != null)
                        orders.add(item);
                }
                listener.updateOrders(orders);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public Task<Void> createOrder(OrderItem order) {
        order.setCreatedDate(new Date());
        order.setUpdatedDate(new Date());
        order.setCreatedBy(getAuth().getUid());
        order.setId(getDatabase().getReference(DefaultConfiguration.DB_ORDERS).push().getKey());
        return getDatabase().getReference(DefaultConfiguration.DB_ORDERS).child(order.getId()).setValue(order);
    }
}
