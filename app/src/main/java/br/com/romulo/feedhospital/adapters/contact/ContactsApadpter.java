package br.com.romulo.feedhospital.adapters.contact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import br.com.romulo.feedhospital.R;
import br.com.romulo.feedhospital.adapters.hospital.HospitalViewHolder;
import br.com.romulo.feedhospital.listeners.OnItemClickListener;
import br.com.romulo.feedhospital.models.Contact;

/**
 * Created by romul_000 on 26/03/2016.
 */
public class ContactsApadpter extends RecyclerView.Adapter {

    private ArrayList<Contact> contacts;
    private Context context;
    private OnItemClickListener<Contact> listener;

    public ContactsApadpter(ArrayList<Contact> contacts, Context context, OnItemClickListener<Contact> listener) {
        this.contacts = contacts;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.contact_row, parent, false
        );

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContactViewHolder contactViewHolder = (ContactViewHolder) holder;
        Contact contact = contacts.get(position);

        contactViewHolder.bind(contact, listener);
        contactViewHolder.getContactContent().setText(contact.getContent());

        switch (contact.getType().toLowerCase()) {
            case Contact.EMAIL:
                contactViewHolder.getContactIcon().setImageResource(R.mipmap.ic_email);
                contactViewHolder.getContactType().setText(context.getText(R.string.details_contact_email));
                break;
            case Contact.PHONE:
                contactViewHolder.getContactIcon().setImageResource(R.mipmap.ic_call);
                contactViewHolder.getContactType().setText(context.getText(R.string.details_contact_phone));
                break;
            case Contact.SITE:
                contactViewHolder.getContactIcon().setImageResource(R.mipmap.ic_open_in_browser);
                contactViewHolder.getContactType().setText(context.getText(R.string.details_contact_site));
                contactViewHolder.getContactContent().setText(getDomainName(contact.getContent()));
                break;
            default:
                contactViewHolder.getContactType().setText(contact.getType()+":");
        }

    }

    @Override
    public int getItemCount() {
        return this.contacts.size();
    }

    private String getDomainName(String url) {
        try{
            URI uri = new URI(url);
            String domain = uri.getHost();
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (URISyntaxException e) {
            return url;
        }
    }

}
