package com.AymanSamir.PopularMovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayman on 21/04/2016.
 */
public class SQLiteMoviesAdapter {

    private SQLite sqLite ;

    SQLiteMoviesAdapter (Context c)
    {

        sqLite = new SQLite(c);
    }


    public long insertMovie(String name , String id )
    {

         if(this.isMovieInFavorite(id))
             return 1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(sqLite.ID , id);
        contentValues.put(sqLite.name , name);

        SQLiteDatabase db = sqLite.getWritableDatabase();
        return db.insert(sqLite.tableName , null ,contentValues);
    }

    public boolean isMovieInFavorite(String id)
    {
        SQLiteDatabase db = sqLite.getReadableDatabase();
        String[] columns = {sqLite.ID  };

        Cursor cursor =  db.query(sqLite.tableName, columns, sqLite.ID + "= '" + id + "'", null, null, null, null);

        while (cursor.moveToNext())
        {
            return true;

        }

        return false;
    }


    public boolean deleteMovieFromFavorite(String id){
        SQLiteDatabase db = sqLite.getWritableDatabase();
        String[] ids = {id};
        long n = db.delete(sqLite.tableName, sqLite.ID + "=?", ids);

        if(n > 0)
            return true;

        return false;

    }

    public List<Movie> selectAllMovies()
    {
        SQLiteDatabase db = sqLite.getReadableDatabase();

        String[] columns = {sqLite.ID , SQLite.name};
        Cursor cursor = db.query(SQLite.tableName, columns, null, null, null, null, null);
        List<Movie> movies = new ArrayList<>();
        while (cursor.moveToNext())
        {
            Movie movie = new Movie();

            String ID =   cursor.getString( cursor.getColumnIndex(columns[0]) );
            String title = cursor.getString( cursor.getColumnIndex(columns[1]) );
            movie.setID(ID);
            movie.setTitle(title);
            movies.add(movie);
        }

        Log.v("SQLite" , Integer.toString( movies.size() ) );
        return movies ;
    }

 class SQLite  extends SQLiteOpenHelper{

    private static final String databaseName= "MOVIES_DB";
    private static final String tableName= "Movies";
    private static final String ID =  "_id";
    private static final  String name = "name";
    private static final int databaseVersion = 1;


    public SQLite (Context c)
    {
        super(c , databaseName , null , databaseVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

        db.execSQL( "CREATE TABLE " + tableName + " (_id VARCHAR(255) PRIMARY KEY , name VARCHAR (255));" );
        }catch (SQLException e)
        {
            Log.e("SQLite" , e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }
}
}
