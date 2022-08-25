package com.reminder.Entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity

public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "task")
    private String task;


    @ColumnInfo(name = "priority")
    private String priority;

    @ColumnInfo(name = "date")
    private String date;



    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }


}