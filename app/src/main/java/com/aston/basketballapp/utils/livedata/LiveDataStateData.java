package com.aston.basketballapp.utils.livedata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class LiveDataStateData<T> {

    //The LiveDataStatus of the LiveDataStateData
    public enum LiveDataStatus {
        SUCCESS,
        ERROR,
        LOADING,
    }

    //Contains the current status of the LiveDataStatus (SUCCESS, ERROR, LOADING)
    @NonNull private LiveDataStatus status;
    //If state is successful then user can get data from the LiveDataStateData
    @Nullable private T data;
    //If state is successful then error can get throwable from the LiveDataStateData
    @Nullable private Throwable error;

    //Get the state
    @NonNull public LiveDataStatus getStatus() {
        return status;
    }
    //Get the data of the LiveDataStateData
    @Nullable public T getData() {
        return data;
    }
    //Get the throwable information
    @Nullable public Throwable getError() {
        return error;
    }

    //Normal Constructor
    public LiveDataStateData() {
        this.status = LiveDataStatus.LOADING;
        this.data = null;
        this.error = null;
    }

    //Loading State Constructor
    public LiveDataStateData<T> loading() {
        this.status = LiveDataStatus.LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    //Success State Constructor
    public LiveDataStateData<T> success(@NonNull T data) {
        this.status = LiveDataStatus.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    //Error State Constructor
    public LiveDataStateData<T> error(@NonNull Throwable error) {
        this.status = LiveDataStatus.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }
}