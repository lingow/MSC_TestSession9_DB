package com.iteso.msc_testsession8.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by oscarvargas on 07/03/15.
 */
public class DataBaseHandler extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "ItesoLibrary.db";
    private static final int DATABASE_VERSION = 1;

    // Table name's
    protected static final String TABLE_AUTHOR = "author";
    protected static final String TABLE_EDITORIAL = "editorial";
    protected static final String TABLE_BOOK = "book";

    // Columns Author
    protected static final String KEY_AUTHOR_ID = "idAuthor";
    protected static final String KEY_AUTHOR_NAME = "name";
    protected static final String KEY_AUTHOR_COUNTRY = "country";
    protected static final String KEY_AUTHOR_EXTRA = "extra";

    // Columns Editorial
    protected static final String KEY_EDITORIAL_ID = "_id";
    protected static final String KEY_EDITORIAL_NAME = "name";

    // Columns Book
    protected static final String KEY_BOOK_ID = "id";
    protected static final String KEY_BOOK_NAME = "name";
    protected static final String KEY_BOOK_IDAUTHOR = "idAuthor";
    protected static final String KEY_BOOK_IDEDITORIAL = "idEditorial";
    protected static final String KEY_BOOK_PUBLISHYEAR = "publishYear";
    protected static final String KEY_BOOK_COUNTRY = "country";
    protected static final String KEY_BOOK_LANGUAGE = "language";


    private static DataBaseHandler dataBaseHandler;
    private DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context){
        if(dataBaseHandler == null){
            dataBaseHandler = new DataBaseHandler(context);
        }
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_AUTHOR_TABLE = "CREATE TABLE " + TABLE_AUTHOR + "("
                + KEY_AUTHOR_ID + " INTEGER PRIMARY KEY," + KEY_AUTHOR_NAME
                + " TEXT," + KEY_AUTHOR_COUNTRY + " TEXT," + KEY_AUTHOR_EXTRA
                + " TEXT)";

        db.execSQL(CREATE_AUTHOR_TABLE);

        String CREATE_EDITORIAL_TABLE = "CREATE TABLE " + TABLE_EDITORIAL + "("
                + KEY_EDITORIAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_EDITORIAL_NAME + " TEXT DEFAULT '')";

        db.execSQL(CREATE_EDITORIAL_TABLE);
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOK + "("
                + KEY_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_BOOK_NAME
                + " TEXT," + KEY_BOOK_IDAUTHOR + " INTEGER,"
                + KEY_BOOK_IDEDITORIAL + " INTEGER," + KEY_BOOK_PUBLISHYEAR
                + " INTEGER," + KEY_BOOK_COUNTRY + " TEXT," + KEY_BOOK_LANGUAGE
                + " TEXT)";

        db.execSQL(CREATE_BOOK_TABLE);

        db.execSQL("INSERT INTO " + TABLE_EDITORIAL + " (" + KEY_EDITORIAL_NAME
                + ") VALUES ('MacGrawHill')");
        db.execSQL("INSERT INTO " + TABLE_EDITORIAL + " (" + KEY_EDITORIAL_NAME
                + ") VALUES ('Purrua')");
        db.execSQL("INSERT INTO " + TABLE_EDITORIAL + " (" + KEY_EDITORIAL_NAME
                + ") VALUES ('Wrox')");
        db.execSQL("INSERT INTO " + TABLE_EDITORIAL + " (" + KEY_EDITORIAL_NAME
                + ") VALUES ('Apress')");

        onUpgrade(db,1,DATABASE_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
