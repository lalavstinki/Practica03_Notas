package com.example.lala.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class Database extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Notas.db";
    public static final String DB_TABLE_NAME = "Tabla";
    public static final String DB_COL_ID = BaseColumns._ID;
    public static final String DB_COL_TITLE = "Título";
    public static final String DB_COL_NOTE = "Nota";
    public static final String DB_ROW_ID = "ID_Lista";
    public static final String DB_ROW_TITLE = "Titulo_Lista";
    public static final String DB_ROW_NOTE = "Nota_Lista";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ DB_TABLE_NAME + "("
                + DB_COL_ID + " INTEGER PRIMARY KEY, "
                + DB_COL_TITLE + " TEXT, "
                + DB_COL_NOTE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void noteAdd(String nota, String texto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.putNull(DB_COL_ID);
        values.put(DB_COL_TITLE, nota);
        values.put(DB_COL_NOTE, texto);
        db.insert(DB_TABLE_NAME, null, values);
    }

    public Cursor leer_notas(){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {DB_COL_ID, DB_COL_TITLE, DB_COL_NOTE};
        return db.query(DB_TABLE_NAME, columns, null, null, null, null, null);
    }

    public void noteUpdate(long id, String title, String body){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COL_TITLE, title);
        values.put(DB_COL_NOTE, body);
        String selection = DB_COL_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };
        int count = db.update(DB_TABLE_NAME, values, selection, selectionArgs);
    }
}
