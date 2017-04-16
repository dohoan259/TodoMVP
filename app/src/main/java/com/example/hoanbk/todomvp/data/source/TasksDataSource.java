package com.example.hoanbk.todomvp.data.source;

import com.example.hoanbk.todomvp.data.Task;

import java.util.List;

/**
 * Created by hoanbk on 4/16/2017.
 *
 * Main entry point for accessing tasks data.
 * <p>
 * For simplicity, only getTasks() and getTask() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 * For example, when a new task is created, it's synchronously stored in cache but usually every
 * operation on database or network should be excuted in a different thread.
 */

public interface TasksDataSource {

    interface LoadTasksCallback {

        void onTasksLoaded(List<Task> tasks);

        void onDataNotAvailable();
    }

    interface GetTaskCallback {

        void onTaskLoaded(Task task);

        void onDataNotAvailable();
    }

    void getTasks(LoadTasksCallback callback);

    void getTask(String taskId, GetTaskCallback callback);

    void saveTask(Task task);

    void completeTask(Task task);

    void completeTask(String taskId);

    void activateTask(Task task);

    void activateTask(String taskId);

    void clearCompletedTasks();

    void refreshTasks();

    void deleteAllTasks();

    void deleteTask(String taskId);
}
