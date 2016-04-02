package br.com.romulo.feedhospital.listeners;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.romulo.feedhospital.R;
import br.com.romulo.feedhospital.models.Contact;

/**
 * Created by romul_000 on 26/03/2016.
 */
public class ContactItemListener implements OnItemClickListener<Contact> {

    private Activity context;

    public ContactItemListener(Activity context){
        this.context = context;
    }


    @Override
    public void onItemClick(Contact contact) {
        switch (contact.getType()) {
            case Contact.PHONE:
                this.call(contact);
                break;
            case Contact.EMAIL:
                this.sendEmail(contact);
                break;
            case Contact.SITE:
                this.openInBrowser(contact);
                break;
        }
    }

    private void openInBrowser(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(contact.getContent()));

        try{
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, R.string.details_could_not_open_browser, Toast.LENGTH_SHORT).show();
        }
    }

    private void call(Contact contact){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String number = "";

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(contact.getContent());
        while (m.find()) {
            number +=m.group();
        }

        intent.setData(Uri.parse("tel:" +number));

        try{
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, R.string.details_could_not_call_phone, Toast.LENGTH_SHORT).show();
        }
    }

    private void sendEmail(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{contact.getContent()});

        try{
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, R.string.details_could_not_send_email, Toast.LENGTH_SHORT).show();
        }
    }
}
