
package com.example.healthcare;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qryl = "CREATE TABLE users(username text, email text, password text)";
        sqLiteDatabase.execSQL(qryl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    public void register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email); // Corrected
        cv.put("password", password); // Corrected
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username, String password){
        int result = 0;
        String[] str = new String[]{username, password};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", str);
        if(c.moveToFirst()){
            result = 1;
        }
        c.close();
        db.close();
        return result;
    }
}

