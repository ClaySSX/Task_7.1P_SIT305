package com.example.test.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.test.model.itemAdvertised;
import com.example.test.util.Util;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.Nullable;





public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ADS_TABLE = ( "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.AD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Util.USERS_NAME + " TEXT , " + Util.MOBILENUMB + " TEXT , " + Util.ITEMNAME + " TEXT , " + Util.DATE + " TEXT , " + Util.LOCATION + " TEXT )" );
        db.execSQL(CREATE_ADS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + new String[]{Util.TABLE_NAME};
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public long insertNewItem(itemAdvertised addItem){

        SQLiteDatabase sqldb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.ITEMNAME, addItem.getItemName());
        contentValues.put(Util.MOBILENUMB, addItem.getMobileNum());
        contentValues.put(Util.USERS_NAME, addItem.getUsersName());
        contentValues.put(Util.DATE, addItem.getItemDate());
        contentValues.put(Util.LOCATION, addItem.getItemLocation());
        long newRowId = sqldb.insert(Util.TABLE_NAME,null, contentValues);
        sqldb.close();
        return  newRowId;
    }



    public int findAdNumber(String addName)
    {
        int addIdNumber = -1;
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = sqldb.rawQuery(selectAll, null);

        if (cursor.moveToFirst())
        {
            do
            {
                if (cursor.getString(3).equalsIgnoreCase(addName))
                {
                    addIdNumber = cursor.getInt(0);
                }
            }
            while (cursor.moveToNext());
        }
        if (addIdNumber == - 1){return 0;}
        else{
            return addIdNumber;}
    }

    public itemAdvertised findItem(int addId)
    {
        itemAdvertised foundAdd = new itemAdvertised();
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = sqldb.rawQuery(selectAll, null);

        if (cursor.moveToFirst())
        {
            do
            {
                if (cursor.getInt(0) == addId)
                {
                    itemAdvertised advItem = new itemAdvertised();
                    advItem.setAdvert_ID(cursor.getInt(0));
                    advItem.setUsersName(cursor.getString(1));
                    advItem.setMobileNum(cursor.getString(2));
                    advItem.setItemName(cursor.getString(3));
                    advItem.setItemDate(cursor.getString(4));
                    advItem.setItemLocation(cursor.getString(5));

                    foundAdd = advItem;
                }
            }
            while (cursor.moveToNext());
        }
        return foundAdd;
    }

    public List<itemAdvertised> viewAllAds()
    {
        List<itemAdvertised> adsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst())
        {
            do {
                itemAdvertised advItem = new itemAdvertised();
                advItem.setAdvert_ID(cursor.getInt(0));
                advItem.setUsersName(cursor.getString(1));
                advItem.setMobileNum(cursor.getString(2));
                advItem.setItemName(cursor.getString(3));
                adsList.add(advItem);
            } while (cursor.moveToNext());
        }
        return  adsList;
    }

    public void removeAdvert(int addId) {

        SQLiteDatabase sqldb = this.getWritableDatabase();
        sqldb.execSQL("DELETE FROM " + Util.TABLE_NAME+ " WHERE "+ Util.AD_ID + "='" + addId+ "'");
        sqldb.close();

    }
}

/*    public boolean fetchUser(String item){
        SQLiteDatabase sqldb = this.getReadableDatabase();
        Cursor cursor = sqldb.query(Util.TABLE_NAME, new String[]{Util.ADD_ID}, Util.ITEMNAME + "=?", new String[]{item}, null, null,null);
        int numberOfRows = cursor.getCount();
        sqldb.close();
        if (numberOfRows > 0 ){
            return true;
        }
        else {return false;}
    }*/