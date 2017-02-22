package com.example.victo.contactsapplicationexercise.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.victo.contactsapplicationexercise.R;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class ContactDetail extends AppCompatActivity {
    @BindView(R.id.tv_received_name) TextView tvName;
    @BindView(R.id.tv_received_company) TextView tvCompany;
    @BindView(R.id.tv_received_favorite) TextView tvFavorite;
    @BindView(R.id.tv_received_phone) TextView tvPhone;
    @BindView(R.id.tv_received_email) TextView tvEmail;
    @BindView(R.id.tv_received_website) TextView tvWebsite;
    @BindView(R.id.image_received)
    ImageView myImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        Intent i = getIntent();
        ButterKnife.bind(this);

        tvName.setText(i.getStringExtra("name"));
        tvCompany.setText(i.getStringExtra("company"));
        tvFavorite.setText(i.getStringExtra("favorite"));
        tvEmail.setText(i.getStringExtra("email"));
        tvWebsite.setText(i.getStringExtra("website"));
        Picasso.with(this).load(i.getStringExtra("image")).into(myImage);

    }


}
