package com.reminder.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.reminder.Dao.TaskDao;
import com.reminder.Entity.Task;


@Database(entities = {Task.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();



}