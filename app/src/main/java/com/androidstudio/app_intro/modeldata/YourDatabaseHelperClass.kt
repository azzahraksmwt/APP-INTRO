package com.androidstudio.app_intro.modeldata

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class YourDatabaseHelperClass(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "aplintro_dev.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_INVENTORY = "inventorys"
        private const val COLUMN_ID = "idbarang"
        private const val COLUMN_NAME = "namabarang"
        private const val COLUMN_TYPE = "jenisbarang"
        private const val COLUMN_QUANTITY = "jumlahbarang"
        private const val COLUMN_UOM = "uom"
        // You can add more columns based on your requirements
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createInventoryTable = """
            CREATE TABLE $TABLE_INVENTORY (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_TYPE TEXT,
                $COLUMN_QUANTITY INTEGER,
                $COLUMN_UOM TEXT
            )
        """.trimIndent()

        db.execSQL(createInventoryTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if it existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_INVENTORY")
        // Create tables again
        onCreate(db)
    }

    // Add methods to insert, update, delete, and select data as needed
}
