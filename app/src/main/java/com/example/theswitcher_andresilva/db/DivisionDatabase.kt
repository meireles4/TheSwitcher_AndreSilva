package com.example.theswitcher_andresilva.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.theswitcher_andresilva.ioThread

@Database(entities = arrayOf(Division::class), version = 1, exportSchema = false)
abstract class DivisionDatabase: RoomDatabase() {

    abstract fun getDivisionDao(): DivisionDao

    companion object {
        @Volatile
        private var INSTANCE: DivisionDatabase? = null

        fun getInstance(context: Context): DivisionDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DivisionDatabase::class.java, "Division.db"
            )
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        ioThread {
                            getInstance(context).getDivisionDao().insertAll(PREPOPULATE_DATA)
                        }
                    }
                })
                .build()

        val PREPOPULATE_DATA = listOf(
            Division("Kitchen", false),
            Division("Living room", false),
            Division("Master bedroom", false),
            Division("Guest's bedroom", false)
        )
    }
}