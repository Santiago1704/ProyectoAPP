package data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import data.entity.UserEntity;

@Dao
public interface UserDao {
    @Insert
    void insert(UserEntity user);

    @Query("SELECT * FROM user_table WHERE user = :username AND password = :password LIMIT 1")
    UserEntity login(String username, String password);

    @Query("SELECT * FROM user_table")
    List<UserEntity> getAllUsers();

    @Update
    void update(UserEntity user);

    @Delete
    void delete(UserEntity user);

}
