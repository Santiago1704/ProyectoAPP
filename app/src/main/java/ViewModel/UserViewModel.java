package ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.w3c.dom.Entity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import data.entity.UserEntity;
import data.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private MutableLiveData<UserEntity> loginUserData = new MutableLiveData<>();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
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

}
