package com.learning.rest.webservices.restful_web_services.userservices.dao;

import com.learning.rest.webservices.restful_web_services.userservices.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Component
public class UserDAOService {
    @Autowired
    private static List<User> users = new ArrayList<>();

    private static Integer usersCount = (Integer) 0;
    static {
        users.add(new User(++usersCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount,"Eve",LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount,"Jim",LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findByID(int userID) {
        Predicate<? super User> predicate = user -> user.getId().equals(userID);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User saveUser(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
