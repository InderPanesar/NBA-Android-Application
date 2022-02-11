package com.aston.basketballapp.utils.livedata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class LiveDataStateData<T> {

    public enum LiveDataStatus {
        SUCCESS,
        ERROR,
        LOADING,
    }

    @NonNull private LiveDataStatus status;
    @Nullable private T data;
    @Nullable private Throwable error;

    @NonNull public LiveDataStatus getStatus() {
        return status;
    }
    @Nullable public T getData() {
        return data;
    }
    @Nullable public Throwable getError() {
        return error;
    }


    public LiveDataStateData() {
        this.status = LiveDataStatus.LOADING;
        this.data = null;
        this.error = null;
    }

    public LiveDataStateData<T> loading() {
        this.status = LiveDataStatus.LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    public LiveDataStateData<T> success(@NonNull T data) {
        this.status = LiveDataStatus.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    public LiveDataStateData<T> error(@NonNull Throwable error) {
        this.status = LiveDataStatus.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }
}