package ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import data.entity.UserEntity;
import data.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private MutableLiveData<List<UserEntity>> allUsers = new MutableLiveData<>();
    private MutableLiveData<UserEntity> loginUserData = new MutableLiveData<>();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
    }

    public LiveData<List<UserEntity>> getAllUsersLiveData() {
        return allUsers;
    }

    public LiveData<UserEntity> getLoginUserLiveData() {
        return loginUserData;
    }

    public void insertUser(UserEntity user) {
        executorService.execute(() -> userRepository.insertUser(user));
    }

    public void loginUser(String username, String password) {
        executorService.execute(() -> {
            UserEntity user = userRepository.loginUser(username, password);
            loginUserData.postValue(user);
        });
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        executorService.execute(() -> {
            List<UserEntity> users = userRepository.getAllUsers();
            allUsers.postValue(users);
        });
        return allUsers;
    }

    public void updateUser(UserEntity user) {
        executorService.execute(() -> userRepository.updateUser(user));
    }

    public void deleteUser(UserEntity user) {
        executorService.execute(() -> userRepository.deleteUser(user));
    }

}
