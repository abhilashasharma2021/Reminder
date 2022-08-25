package com.reminder.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.reminder.Entity.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);

}