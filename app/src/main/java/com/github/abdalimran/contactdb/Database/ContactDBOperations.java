package com.github.abdalimran.contactdb.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.abdalimran.contactdb.Model.Contact;

import java.util.ArrayList;

public class ContactDBOperations {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Contact contact;

    public ContactDBOperations(Context context){
        databaseHelper=new DatabaseHelper(context);
    }

    public void open(){
        db=databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();
    }

    public boolean addContact(Contact contact){
        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME,contact.getName());
        contentValues.put(DatabaseHelper.COL_PHONE,contact.getPhoneNo());

        long inserted=db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);

        this.close();

        if(inserted>0)
            return true;
        else
            return false;
    }

    public Contact getContact(int id){

        this.open();

        Cursor cursor=db.query(
                DatabaseHelper.TABLE_NAME,
                new String[]{
                        DatabaseHelper.COL_ID,
                        DatabaseHelper.COL_NAME,
                        DatabaseHelper.COL_PHONE},

                DatabaseHelper.COL_ID+" = "+id,
                null,null,null,null);

        cursor.moveToFirst();

        int mID=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
        String mName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
        String mPhone=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));

        cursor.close();

        contact=new Contact(mID,mName,mPhone);

        this.close();

        return contact;
    }

    public ArrayList<Contact>getAllContact(){
        ArrayList<Contact>allcontacts=new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from "+DatabaseHelper.TABLE_NAME +";",null);

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();

            for(int i=0;i<cursor.getCount();i++){
                int mID=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String mName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
                String mPhone=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));

                contact=new Contact(mID,mName,mPhone);
                allcontacts.add(contact);

                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return allcontacts;
    }

    public boolean updateContact(int id,Contact contact){
        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME,contact.getName());
        contentValues.put(DatabaseHelper.COL_PHONE,contact.getPhoneNo());

        int update=db.update(DatabaseHelper.TABLE_NAME,contentValues,
                DatabaseHelper.COL_ID+ " = "+id,null);

        this.close();

        if(update>0)
            return  true;
        else
            return false;
    }

    public boolean deleteContact(int id){
        this.open();
        int deleted=db.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.COL_ID+" = "+id,null);
        this.close();

        if(deleted>0)
            return true;
        else
            return false;
    }

}
