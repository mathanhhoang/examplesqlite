package my.thanhhoang.sqliteexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "qlsv.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTableSV = "CREATE TABLE IF NOT EXISTS SinhVien (MaSV INTEGER PRIMARY KEY AUTOINCREMENT, TenSV VARCHAR(200), Lop VARCHAR(200))";
        db.execSQL(sqlCreateTableSV);
        db.execSQL("INSERT INTO SinhVien VALUES(null, 'Ma Thanh Hoang', '41C')");
        db.execSQL("INSERT INTO SinhVien VALUES(null, 'Nguyen Trung Thanh', '41A')");
        db.execSQL("INSERT INTO SinhVien VALUES(null, 'Nguyen Van Hoa', '41C')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SinhVien");
        onCreate(db);
    }

    public void themSV(String tenSinhVien, String lop) {
        String sqlInsert = String.format("INSERT INTO SinhVien VALUES(null, '%s', '%s')", tenSinhVien, lop);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlInsert);
        db.close();
    }

    public void suaSV(int maSV, String tenSinhVien, String lop) {
        String sqlUpdate = String.format("UPDATE SinhVien SET TenSV = '%s', Lop = '%s' WHERE MaSV = %d", tenSinhVien, lop, maSV);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }

    public void xoaSV(int maSV) {
        String sqlDelete = String.format("DELETE FROM SinhVien WHERE MaSV = %d", maSV);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }

    public List<SinhVien> layDanhSachSinhVien () {
        List<SinhVien> listSinhVien = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sqlSelect = "SELECT * FROM SinhVien";

        Cursor cursor = db.rawQuery(sqlSelect, null); //con tro nek

        while (cursor.moveToNext()) {
            int maSV = cursor.getInt(0);
            String tenSV = cursor.getString(1);
            String lop = cursor.getString(2);

            listSinhVien.add(new SinhVien(maSV, tenSV, lop));
        }
        cursor.close();

        return listSinhVien;
    }
}
