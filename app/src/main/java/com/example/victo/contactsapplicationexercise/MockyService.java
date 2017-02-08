package com.example.victo.contactsapplicationexercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by victo on 2/7/2017.
 */

public interface MockyService {
    @GET("technical-challenge/Contacts.json")
    Observable<List<Contact>> getContacts();
}
