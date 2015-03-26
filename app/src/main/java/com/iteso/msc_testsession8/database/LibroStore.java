package com.iteso.msc_testsession8.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.msc_testsession8.beans.Libro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lingow on 25/03/15.
 */
public class LibroStore {
    public long addBook(Libro book, DataBaseHandler dh) {
        long inserted = 0;

        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_BOOK_NAME, book.getNombre());
        values.put(DataBaseHandler.KEY_BOOK_COUNTRY, book.getPais());
        values.put(DataBaseHandler.KEY_BOOK_IDEDITORIAL, book.getIdEditorial());
        values.put(DataBaseHandler.KEY_BOOK_LANGUAGE, book.getLenguaje());
        values.put(DataBaseHandler.KEY_BOOK_PUBLISHYEAR, book.getAnioPublicacion());
        values.put(DataBaseHandler.KEY_BOOK_IDAUTHOR, book.getIdAutor());
        // Inserting Row
        inserted = db.insert(DataBaseHandler.TABLE_BOOK, null, values);
        // Closing database connection
        try {db.close();} catch (Exception e) {}
        db = null;
        values = null;
        return inserted;
    }
    public void deleteLibro(int idLibro, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(DataBaseHandler.TABLE_BOOK, DataBaseHandler.KEY_BOOK_ID
                + " = ?", new String[] { (""+idLibro) });
        try {
            db.close();
        } catch (Exception e) {
        }
        db = null;
    }

    public int updateBook(Libro book, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_BOOK_NAME, book.getNombre());
        values.put(DataBaseHandler.KEY_BOOK_COUNTRY, book.getPais());
        values.put(DataBaseHandler.KEY_BOOK_IDEDITORIAL, book.getIdEditorial());
        values.put(DataBaseHandler.KEY_BOOK_LANGUAGE, book.getLenguaje());
        values.put(DataBaseHandler.KEY_BOOK_PUBLISHYEAR, book.getAnioPublicacion());
        values.put(DataBaseHandler.KEY_BOOK_IDAUTHOR, book.getIdAutor());
        int count = db.update(DataBaseHandler.TABLE_BOOK, values,
                DataBaseHandler.KEY_BOOK_ID + " = ?",
                new String[] { String.valueOf(book.getId()) });
        try {db.close();} catch (Exception e) {}
        db = null;
        return count;
    }

    public List<Libro> getBooksWhere(int idBook,
                                        String strWhere, String strOrderBy, DataBaseHandler dh) {
        List<Libro> libroList = new ArrayList<>();
        // SELECT
        String selectQuery = "SELECT  "
                + DataBaseHandler.KEY_BOOK_ID + ", "
                + DataBaseHandler.KEY_BOOK_NAME + ", "
                + DataBaseHandler.KEY_BOOK_COUNTRY + ", "
                + DataBaseHandler.KEY_BOOK_IDAUTHOR + ", "
                + DataBaseHandler.KEY_BOOK_LANGUAGE + ", "
                + DataBaseHandler.KEY_BOOK_PUBLISHYEAR + ", "
                + DataBaseHandler.KEY_BOOK_IDEDITORIAL
                + " FROM "
                + DataBaseHandler.TABLE_BOOK
                + " WHERE "
                + DataBaseHandler.KEY_BOOK_ID + "=" + String.valueOf(idBook);
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
                Libro book = new Libro();
                book.setId(cursor.getInt(0));
                book.setNombre(cursor.getString(1));
                book.setPais(cursor.getString(2));
                book.setIdAutor(cursor.getInt(3));
                book.setLenguaje(cursor.getString(4));
                book.setAnioPublicacion(cursor.getInt(5));
                book.setIdEditorial(cursor.getInt(6));
                libroList.add(book);
            } while (cursor.moveToNext());
        }
        try {db.close();} catch (Exception e) {}
        db = null;
        cursor = null;
        // return autor list
        return libroList;
    }

    public List<Libro> getBooksWhere(
            String strWhere, String strOrderBy, DataBaseHandler dh) {
        List<Libro> libroList = new ArrayList<>();
        // SELECT
        String selectQuery = "SELECT  "
                + DataBaseHandler.KEY_BOOK_ID + ", "
                + DataBaseHandler.KEY_BOOK_NAME + ", "
                + DataBaseHandler.KEY_BOOK_COUNTRY + ", "
                + DataBaseHandler.KEY_BOOK_IDAUTHOR + ", "
                + DataBaseHandler.KEY_BOOK_LANGUAGE + ", "
                + DataBaseHandler.KEY_BOOK_PUBLISHYEAR + ", "
                + DataBaseHandler.KEY_BOOK_IDEDITORIAL
                + " FROM "
                + DataBaseHandler.TABLE_BOOK;
        // WHERE
        if (strWhere != null) {
            selectQuery += " WHERE " + strWhere;
        }
        // ORDER BY
        if (strOrderBy != null) {
            selectQuery += " " + strOrderBy;
        }
        SQLiteDatabase db = dh.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Libro book = new Libro();
                book.setId(cursor.getInt(0));
                book.setNombre(cursor.getString(1));
                book.setPais(cursor.getString(2));
                book.setIdAutor(cursor.getInt(3));
                book.setLenguaje(cursor.getString(4));
                book.setAnioPublicacion(cursor.getInt(5));
                book.setIdEditorial(cursor.getInt(6));
                libroList.add(book);
            } while (cursor.moveToNext());
        }
        try {db.close();} catch (Exception e) {}
        db = null;
        cursor = null;
        // return libro list
        return libroList;
    }


    public int getBookCount(DataBaseHandler dh) {
        // SELECT
        String selectQuery =
                "SELECT  " + DataBaseHandler.KEY_BOOK_ID
                        + "  FROM " + DataBaseHandler.TABLE_BOOK;

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
