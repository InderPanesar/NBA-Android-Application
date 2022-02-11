package com.aston.basketballapp.utils.livedata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LiveDataStateData<T> {

    @NonNull
    private DataStatus status;

    @Nullable
    private T data;

    @Nullable
    private Throwable error;

    public LiveDataStateData() {
        this.status = DataStatus.LOADING;
        this.data = null;
        this.error = null;
    }

    public LiveDataStateData<T> loading() {
        this.status = DataStatus.LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    public LiveDataStateData<T> success(@NonNull T data) {
        this.status = DataStatus.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    public LiveDataStateData<T> error(@NonNull Throwable error) {
        this.status = DataStatus.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }


    @NonNull
    public DataStatus getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    public enum DataStatus {
        SUCCESS,
        ERROR,
        LOADING,
    }
}