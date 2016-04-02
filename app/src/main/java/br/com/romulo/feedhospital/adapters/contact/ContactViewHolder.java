package br.com.romulo.feedhospital.adapters.contact;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.romulo.feedhospital.R;
import br.com.romulo.feedhospital.listeners.OnItemClickListener;
import br.com.romulo.feedhospital.models.Contact;

/**
 * Created by romul_000 on 26/03/2016.
 */
public class ContactViewHolder extends RecyclerView.ViewHolder {

    private ImageView contactIcon;
    private TextView contactType;
    private TextView contactContent;
    private LinearLayout contactContainer;

    public ContactViewHolder(View view) {
        super(view);
        contactIcon = (ImageView) view.findViewById(R.id.contact_icon);
        contactType = (TextView) view.findViewById(R.id.contact_type);
        contactContent = (TextView) view.findViewById(R.id.contact_content);
        contactContainer = (LinearLayout) view.findViewById(R.id.contact_container);
    }

    public void bind(final Contact contact, final OnItemClickListener<Contact> listener) {
        contactContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(contact);
            }
        });
    }

    public ImageView getContactIcon() {
        return contactIcon;
    }

    public TextView getContactType() {
        return contactType;
    }

    public TextView getContactContent() {
        return contactContent;
    }

    public LinearLayout getContactContainer() {
        return contactContainer;
    }
}
