package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SachDataBase(context: Context): SQLiteOpenHelper(context, DATABASE, null, VERSION) {
    companion object{
        const val DATABASE = "SQ12.db"
        const val VERSION = 1
        const val TABLE = "sach"
        const val ID = "id"
        const val NAME = "name"
        const val THELOAI = "theloai"
        const val IMAGE = "image"
        const val CREATE_TABLE =
            " CREATE TABLE " + TABLE + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    THELOAI + " TEXT, " +
                    IMAGE + " INTEGER " + ");"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun getAllSach(): MutableList<Sach>{
        val db = readableDatabase
        val cursor = db.query(TABLE, null,null,null,null,null,null,null)
        val list = mutableListOf<Sach>()
        if(cursor.moveToFirst()){
            do {
                val sach = Sach()
                sach.id = cursor.getInt(0)
                sach.name = cursor.getString(1)
                sach.theloai = cursor.getString(2)
                sach.image = cursor.getInt(3)
                list.add(sach)
            }while (cursor.moveToNext())

        }
        cursor.close()
        db.close()
        return list
    }
    fun insertSach(sach: Sach){
        val db = writableDatabase
        val values = ContentValues()
        values.put(NAME, sach.name)
        values.put(THELOAI, sach.theloai)
        values.put(IMAGE, sach.image)
        db.insert(TABLE, null, values)
        db.close()
    }
}