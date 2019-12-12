package com.example.esoftwaricaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactActivity  extends RecyclerView.Adapter<ContactActivity.ContactViewHolder>{

    Context mContext;
    List<Contacts>contactsList;

    public ContactActivity(Context mContext,List<Contacts>contactsList){
        this.mContext=mContext;
        this.contactsList=contactsList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contactlayout,parent,false);
        return  new ContactViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {

      final Contacts contacts=contactsList.get(position);
      holder.tvName.setText(contacts.getName());
      holder.tvAddress.setText(contacts.getAddress());
      holder.tvGender.setText(contacts.getGender());
      holder.tvAge.setText(Integer.toString(contacts.getAge()));


     // holder.imgProfile.setImageResource(contacts.getImgProfileId());
    //  holder.imgRemove.setImageResource(contacts.getImgRemoveId());
        holder.imgRemove.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bin));
        String gender=contacts.getGender();
        if (gender=="male") {

            holder.imgProfile.setImageResource(R.drawable.male);
        }
        else if(gender=="female"){
            holder.imgProfile.setImageResource(R.drawable.female);
        }
        else {
            holder.imgProfile.setImageDrawable(mContext.getResources().getDrawable(R.drawable.female));
        }

        holder.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"hi"+contacts.getName(),Toast.LENGTH_SHORT).show();
            }
        });

      holder.imgRemove.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Contacts cont=contactsList.get(position);
              contactsList.remove(position);
           notifyItemRemoved(position);
           Toast.makeText(mContext,"Removed:"+cont.getName(),Toast.LENGTH_SHORT).show();
          }
      });


    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public  class ContactViewHolder extends RecyclerView.ViewHolder {

    TextView tvName,tvAddress,tvAge,tvGender;
    ImageView imgProfile,imgRemove;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvname);
            tvAddress=itemView.findViewById(R.id.tvaddress);
            tvAge=itemView.findViewById(R.id.tvage);
            tvGender=itemView.findViewById(R.id.tvgender);
            imgProfile=itemView.findViewById(R.id.imgprofile);
            imgRemove=itemView.findViewById(R.id.imgremove);


        }
    }


}
