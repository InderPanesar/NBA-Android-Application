package com.aston.basketballapp.utils.livedata;

import androidx.lifecycle.MutableLiveData;

public class StateMutableLiveData<T> extends MutableLiveData<LiveDataStateData<T>> {

    /**
     * Use this to put the Data on a LOADING Status
     */
    public void postLoading() {
        postValue(new LiveDataStateData<T>().loading());
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     * @param throwable the error to be handled
     */
    public void postError(Throwable throwable) {
        postValue(new LiveDataStateData<T>().error(throwable));
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     * @param data
     */
    public void postSuccess(T data) {
        postValue(new LiveDataStateData<T>().success(data));
    }


}