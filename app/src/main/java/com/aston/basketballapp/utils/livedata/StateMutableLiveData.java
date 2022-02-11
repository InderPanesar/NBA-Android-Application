package com.aston.basketballapp.utils.livedata;

import androidx.lifecycle.MutableLiveData;

//Extension of MutableLiveData to allow LiveData to have different states
public class StateMutableLiveData<T> extends MutableLiveData<LiveDataStateData<T>> {

    //Post Loading with no parameters
    public void postValueLoading() {
        postValue(new LiveDataStateData<T>().loading());
    }

    //Post Error with throwable value
    public void postValueError(Throwable throwable) {
        postValue(new LiveDataStateData<T>().error(throwable));
    }

    //Post Success with generics data
    public void postValueSuccess(T data) {
        postValue(new LiveDataStateData<T>().success(data));
    }


}