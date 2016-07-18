package com.github.abdalimran.contactdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.abdalimran.contactdb.Database.ContactDBOperations;
import com.github.abdalimran.contactdb.Model.Contact;

public class MainActivity extends AppCompatActivity {

    private EditText contactName;
    private EditText contactPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactName= (EditText) findViewById(R.id.contactName);
        contactPhone= (EditText) findViewById(R.id.contactPhone);
    }

    public void saveContact(View view) {

        String name=contactName.getText().toString();
        String phone=contactPhone.getText().toString();

        Contact contact=new Contact(name,phone);
        ContactDBOperations contactDBOperations= new ContactDBOperations(this);
        boolean inserted=contactDBOperations.addContact(contact);
        Toast.makeText(getApplicationContext(),"Contact Saved:"+String.valueOf(inserted),Toast.LENGTH_SHORT).show();
    }

    public void showAllContact(View view) {
        Intent intent = new Intent(this, AllContactActivity.class);
        startActivity(intent);
    }
}
