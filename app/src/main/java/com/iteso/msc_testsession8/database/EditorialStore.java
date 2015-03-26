package com.iteso.msc_testsession8.database;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.msc_testsession8.beans.Editorial;

public class EditorialStore {
	public List<Editorial> getEditorialsWhere(
			String strWhere, String strOrderBy, DataBaseHandler dh) {
		List<Editorial> editorialList = new ArrayList<Editorial>();
		// SELECT
		String selectQuery = "SELECT _id, name FROM " 
				+ DataBaseHandler.TABLE_EDITORIAL;

		// WHERE
		if (strWhere != null) {
			selectQuery += " AND " + strWhere;
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
				Editorial editorial = new Editorial();
				editorial.setId(cursor.getInt(0));
				editorial.setNombre(cursor.getString(1));
				
				// Adding editorial to list
				editorialList.add(editorial);
			} while (cursor.moveToNext());
		}
		try {
			db.close();
		} catch (Exception e) {
		}
		db = null;
		cursor = null;
		// return editorial list
		return editorialList;
	}
}
