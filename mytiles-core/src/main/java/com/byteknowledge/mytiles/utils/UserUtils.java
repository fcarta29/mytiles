package com.byteknowledge.mytiles.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.byteknowledge.mytiles.model.User;

public final class UserUtils {

    private static final ConcurrentHashMap<UUID,User> USER_MAP = new ConcurrentHashMap<UUID,User>();
    static {
        final User frank = generateUser("Frank");
        final User andrew = generateUser("Andrew");
        USER_MAP.put(frank.getId(), frank);
        USER_MAP.put(andrew.getId(), andrew);
    }
    
    // DO NOT INSTANTIATE
    private UserUtils() {};
    
    @Deprecated // TODO[fcarta] remove when db layer is added
    public static Collection<User> getUsers() {
        return new ArrayList<User>(USER_MAP.values());
    }
    
    public User getUser(final UUID id) {
        return USER_MAP.get(id);
    }
    
    public static User generateUser(final String username) {
        final User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserName(username);
        return user;
    }
}
