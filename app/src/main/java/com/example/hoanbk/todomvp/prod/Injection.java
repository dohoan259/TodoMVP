package com.example.hoanbk.todomvp.prod;

import android.content.Context;

import com.example.hoanbk.todomvp.data.source.TasksRepository;
import com.example.hoanbk.todomvp.data.source.local.TasksLocalDataSource;
import com.example.hoanbk.todomvp.data.source.remote.TasksRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hoanbk on 4/17/2017.
 */

public class Injection {

    public static TasksRepository provideTasksRepository(Context context) {
        checkNotNull(context);
        return com.example.hoanbk.todomvp.data.source.TasksRepository.getInstance(TasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance(context));
    }
}
