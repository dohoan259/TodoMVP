package com.example.hoanbk.todomvp.prod;

import android.content.Context;

import com.example.hoanbk.todomvp.data.source.TaskRepository;
import com.example.hoanbk.todomvp.data.source.local.TasksLocalDataSource;
import com.example.hoanbk.todomvp.data.source.remote.TasksRemoteDataSource;

/**
 * Created by hoanbk on 4/17/2017.
 */

public class Injection {

    public static TaskRepository provideTasksRepository(Context context) {
        if (context == null) {
            return null;
        }
        return TaskRepository.getInstance(TasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance(context));
    }
}
