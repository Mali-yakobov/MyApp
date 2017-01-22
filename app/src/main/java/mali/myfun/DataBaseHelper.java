package mali.myfun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mali on 1/13/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=2;
    private static final String DATABASE_NAME="contacts.db";
    private static final String TABLE_NAME="contacts";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_UNAME="uname";
    private static final String COLUMN_PASS="pass";
    SQLiteDatabase db;

    private static final String TABLE_CREATE= "create table contacts (id integer primary key not null , "+
            "name text not null, email text not null, uname text not null, pass text not null);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query= "DROP TABLE IF EXISTS" +TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }

    public void insertContact(Contact c){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        String query="select * from contacts";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_UNAME,c.getUsername());
        values.put(COLUMN_PASS,c.getPassword());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public String searchPass(String username) {
        db=this.getReadableDatabase();
        String query="select uname, pass from"+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        String strUsername,strPassword;
        strPassword="not found";

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                strUsername=cursor.getString(0);
                if(strUsername.equals(username)) {
                    strPassword = cursor.getString(1);
                    break;
                }
            }

        }
        return strPassword;
    }
}
