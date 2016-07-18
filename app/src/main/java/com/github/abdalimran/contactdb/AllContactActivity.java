package com.github.abdalimran.contactdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.abdalimran.contactdb.Database.ContactDBOperations;
import com.github.abdalimran.contactdb.Model.Contact;

import java.util.ArrayList;

public class AllContactActivity extends AppCompatActivity {

    private ListView contactListView;
    private ContactDBOperations contactDBOperations;
    private ArrayList<Contact>contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contact);

        contactListView= (ListView) findViewById(R.id.contactListView);
        contactDBOperations=new ContactDBOperations(this);
        contacts=contactDBOperations.getAllContact();

        ArrayAdapter<Contact> arrayAdapter = new ArrayAdapter<Contact>(this,android.R.layout.simple_list_item_1,contacts);

        contactListView.setAdapter(arrayAdapter);
    }
}
