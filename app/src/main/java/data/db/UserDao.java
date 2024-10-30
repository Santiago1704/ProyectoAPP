package data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import data.entity.UserEntity;

@Dao
public interface UserDao {
    @Insert
    void insert(UserEntity user);

    @Query("SELECT * FROM user_table WHERE user = :username AND password = :password LIMIT 1")
    UserEntity login(String username, String password);

}
