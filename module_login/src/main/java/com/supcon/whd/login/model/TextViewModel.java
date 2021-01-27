package com.supcon.whd.login.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TextViewModel extends ViewModel {
    private String mKey;
    private MutableLiveData<String> mNameEvent=new MutableLiveData<>();
    public MutableLiveData<String> getNameEvent(){
        return mNameEvent;
    }
    public TextViewModel(String key){
        mKey=key;
    }
    public static class Factory implements ViewModelProvider.Factory{

        private String mKey;
        public Factory(String key){
            mKey=key;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new TextViewModel(mKey);
        }
    }
    public String getKey(){
        return mKey;
    }
}
