package com.iteso.msc_testsession8.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.msc_testsession8.beans.Author;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscarvargas on 08/03/15.
 */
public class AuthorStore {
    public long addAuthor(Author author, DataBaseHandler dh) {
        long inserted = 0;

        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_AUTHOR_ID, author.getId());
        values.put(DataBaseHandler.KEY_AUTHOR_NAME, author.getNombre());
        values.put(DataBaseHandler.KEY_AUTHOR_COUNTRY, author.getPais());
        values.put(DataBaseHandler.KEY_AUTHOR_EXTRA, author.getExtra());
        // Inserting Row
        inserted = db.insert(DataBaseHandler.TABLE_AUTHOR, null, values);
        // Closing database connection
        try {db.close();} catch (Exception e) {}
        db = null;
        values = null;
        return inserted;
    }
    public void deleteAuthor(String idAuthor, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(DataBaseHandler.TABLE_AUTHOR, DataBaseHandler.KEY_AUTHOR_ID
                + " = ?", new String[] { idAuthor });
        try {
            db.close();
        } catch (Exception e) {
        }
        db = null;
    }
    public int updateAuthor(Author author, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_AUTHOR_ID, author.getId());
        values.put(DataBaseHandler.KEY_AUTHOR_NAME, author.getNombre());
        values.put(DataBaseHandler.KEY_AUTHOR_COUNTRY, author.getPais());
        values.put(DataBaseHandler.KEY_AUTHOR_EXTRA, author.getExtra());
        int count = db.update(DataBaseHandler.TABLE_AUTHOR, values,
                DataBaseHandler.KEY_AUTHOR_ID + " = ?",
                new String[] { String.valueOf(author.getId()) });
        try {db.close();} catch (Exception e) {}
        db = null;
        return count;
    }
    public List<Author> getAuthorsWhere(int idAuthor,
                                       String strWhere, String strOrderBy, DataBaseHandler dh) {
        List<Author> authorList = new ArrayList<>();
        // SELECT
        String selectQuery = "SELECT  " + DataBaseHandler.KEY_AUTHOR_ID + ", "
                + DataBaseHandler.KEY_AUTHOR_NAME + ", "
                + DataBaseHandler.KEY_AUTHOR_COUNTRY + ", "
                + DataBaseHandler.KEY_AUTHOR_EXTRA + "  FROM "
                + DataBaseHandler.TABLE_AUTHOR + " WHERE "
                + DataBaseHandler.KEY_AUTHOR_ID + "=" + String.valueOf(idAuthor);
        // WHERE
        if (strWhere != null) {
            selectQuery += " AND " + strWhere;
        }
        // ORDER BY
        if (strOrderBy != null) {
            selectQuery += " " + strOrderBy;
        }
        SQLiteDatabase db = dh.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Author author = new Author();
                author.setId(cursor.getInt(0));
                author.setNombre(cursor.getString(1));
                author.setPais(cursor.getString(2));
                author.setExtra(cursor.getString(3));

                authorList.add(author);
            } while (cursor.moveToNext());
        }
        try {db.close();} catch (Exception e) {}
        db = null;
        cursor = null;
        // return autor list
        return authorList;
    }

    public List<Author> getAuthorsWhere(
            String strWhere, String strOrderBy, DataBaseHandler dh) {
        List<Author> authorList = new ArrayList<>();
        // SELECT
        String selectQuery = "SELECT  " + DataBaseHandler.KEY_AUTHOR_ID + ","
                + DataBaseHandler.KEY_AUTHOR_NAME + ","
                + DataBaseHandler.KEY_AUTHOR_COUNTRY + ","
                + DataBaseHandler.KEY_AUTHOR_EXTRA + "  FROM "
                + DataBaseHandler.TABLE_AUTHOR;

        // WHERE
        if (strWhere != null) {
            selectQuery += " WHERE " + strWhere;
        }

        // ORDER BY
        if (strOrderBy != null) {
            selectQuery += " ORDER BY " + strOrderBy;
        }

        SQLiteDatabase db = dh.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Author autor = new Author();
                autor.setId(cursor.getInt(0));
                autor.setNombre(cursor.getString(1));
                autor.setPais(cursor.getString(2));
                autor.setExtra(cursor.getString(3));

                // Adding autor to list
                authorList.add(autor);
            } while (cursor.moveToNext());
        }
        try {
            db.close();
        } catch (Exception e) {
        }
        db = null;
        cursor = null;
        // return autor list
        return authorList;
    }


    public int getAutorCount(DataBaseHandler dh) {
        // SELECT
        String selectQuery =
                "SELECT  " + DataBaseHandler.KEY_AUTHOR_ID
                        + "  FROM " + DataBaseHandler.TABLE_AUTHOR;

        SQLiteDatabase db = dh.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int count = cursor.getCount();
        try{db.close();}catch(Exception e){}
        try{cursor.close();}catch(Exception e){}
        db = null;
        cursor = null;
        return count;
    }


}
