package com.example.victo.contactsapplicationexercise;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private static final String TAG = "MainActivity";
    List<Contact> myContacts = new ArrayList<>();
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        contactAdapter = new ContactAdapter(myContacts,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactAdapter);
        contactAdapter.setClickListener(this);

        Observable<List<Contact>> resultObservable = RetrofitHandler.Factory.createObservable();

        Observer myObserver = new Observer<List<Contact>>(){

            @Override
            public void onCompleted() {
                Log.d(TAG, "Task completed");
                contactAdapter.notifyDataSetChanged();
                
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Contact> contactList) {
                for (Contact contact: contactList){
                    myContacts.add(contact);
                    Log.d(TAG, "Contact: "+contact.toString());
                }
            }
        };
        resultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myObserver);
    }


    @Override
    public void onClick(View view, int position) {
        Contact contact = myContacts.get(position);
        Log.d(TAG, "Contact selected: "+contact.toString()+" "+ position);
    }
}
