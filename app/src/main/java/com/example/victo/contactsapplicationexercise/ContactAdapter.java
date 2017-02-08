package com.example.victo.contactsapplicationexercise;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private static final String TAG = "Adapter";
    private List<Contact> contactList;
    Context context;
    OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName;
        ImageView myImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            myImage = (ImageView) itemView.findViewById(R.id.myImageView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public ContactAdapter(List<Contact> contactList, Context context) {
        this.context = context;
        this.contactList = contactList;
        this.onItemClickListener = onItemClickListener;
    }

    public ContactAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_data, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.MyViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.tvName.setText(contact.getName());
        Picasso.with(context).load(contact.getSmallImageURL()).into(holder.myImage);
    }

    public void setClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public int getItemCount() {
        return contactList.size();
    }


}