package com.example.victo.contactsapplicationexercise;

import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.schedulers.Schedulers;

public class RetrofitHandler {

    private static final String TAG = "Retrofit";
    private static final String BASE_URL = "https://s3.amazonaws.com/";

    public static class Factory {
        public static Retrofit create(){
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }
        public static Observable<List<Contact>> createObservable(){
            Retrofit retrofit = create();
            MockyService mockyService = retrofit.create(MockyService.class);
            return mockyService.getContacts();
        }
    }
}