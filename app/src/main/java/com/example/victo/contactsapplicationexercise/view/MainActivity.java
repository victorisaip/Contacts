package com.example.victo.contactsapplicationexercise.view;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.victo.contactsapplicationexercise.R;
import com.example.victo.contactsapplicationexercise.model.Contact;
import com.example.victo.contactsapplicationexercise.presenter.IPresenterMainActivity;
import com.example.victo.contactsapplicationexercise.presenter.PresenterMainActivity;
import com.example.victo.contactsapplicationexercise.utilities.ContactAdapter;
import com.example.victo.contactsapplicationexercise.utilities.OnItemClickListener;
import com.example.victo.contactsapplicationexercise.utilities.RetrofitHandler;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements OnItemClickListener,IMainActivityView {

    private static final String TAG = "MainActivity";
    List<Contact> myContacts = new ArrayList<>();
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    IPresenterMainActivity iPresenterMainActivity;
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
        iPresenterMainActivity = new PresenterMainActivity(this);
        iPresenterMainActivity.getContactsList();
    }

    @Override
    public void onClick(View view, int position) {
        Contact contact = myContacts.get(position);
        Intent i = new Intent(this,ContactDetail.class);
        i.putExtra("name",contact.getName());
        i.putExtra("company",contact.getCompany());
        i.putExtra("email",contact.getEmail());
        i.putExtra("website",contact.getWebsite());
        i.putExtra("image",contact.getLargeImageURL());
        startActivity(i);
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        for (Contact contact: contacts){
            myContacts.add(contact);
        }
        contactAdapter.notifyDataSetChanged();
    }
}
