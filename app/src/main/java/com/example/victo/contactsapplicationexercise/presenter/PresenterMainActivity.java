package com.example.victo.contactsapplicationexercise.presenter;

import com.example.victo.contactsapplicationexercise.model.Contact;
import com.example.victo.contactsapplicationexercise.utilities.RetrofitHandler;
import com.example.victo.contactsapplicationexercise.view.IMainActivityView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by victo on 2/22/2017.
 */

public class PresenterMainActivity implements IPresenterMainActivity {
    IMainActivityView iMainActivityView;

    public PresenterMainActivity(IMainActivityView iMainActivityView) {
        this.iMainActivityView = iMainActivityView;
    }

    @Override
    public void getContactsList() {
        Observable<List<Contact>> resultObservable = RetrofitHandler.Factory.createObservable();

        Observer myObserver = new Observer<List<Contact>>(){

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Contact> contactList) {
                iMainActivityView.setContacts(contactList);
            }
        };
        resultObservable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(myObserver);

    }
}
