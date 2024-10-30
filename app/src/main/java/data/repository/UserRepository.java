package data.repository;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import data.db.AppDatabase;
import data.db.UserDao;
import data.entity.UserEntity;

public class UserRepository {
    private UserDao userDao;
    private static UserRepository instance;

    private UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
    }

    public static synchronized UserRepository getInstance(Application application) {
        if (instance == null) {
            instance = new UserRepository(application);
        }
        return instance;
    }

    public void insertUser(UserEntity user) {
        new Thread(() -> userDao.insert(user)).start();
    }

    public UserEntity loginUser(String username, String password) {
        return userDao.login(username, password);
    }
}
