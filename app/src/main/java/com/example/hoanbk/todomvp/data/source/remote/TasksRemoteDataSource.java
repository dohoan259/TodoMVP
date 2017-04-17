package com.example.hoanbk.todomvp.data.source.remote;

import android.os.Handler;

import com.example.hoanbk.todomvp.data.Task;
import com.example.hoanbk.todomvp.data.source.TasksDataSource;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hoanbk on 4/18/2017.
 */

public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource sInstance;

    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;

    private final static Map<String, Task> TASKS_SERVICE_DATA;

    static {
        TASKS_SERVICE_DATA = new LinkedHashMap<>(2);
        addTask("Build tower in Pisa", "Ground looks good, no foundation work required");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!");
    }

    public static TasksRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new TasksRemoteDataSource();
        }
        return sInstance;
    }

    private TasksRemoteDataSource() {}

    private static void addTask(String title, String description) {
        Task newTask = new Task(title, description);
        TASKS_SERVICE_DATA.put(newTask.getId(), newTask);
    }

    /**
     * Note: {@Link LoadTasksCallback#onDatanotAvailable()} is never fired. In a real remote data
     * source implementation, this would be fired if server can't be contacted or server
     * returns an error
     * @param callback
     */
    @Override
    public void getTasks(final LoadTasksCallback callback) {
        // Simulate network by delaying the execution
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onTasksLoaded(Lists.newArrayList(TASKS_SERVICE_DATA.values()));
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    /**
     * Note: {@Link GetTaskCallback#onDataNotAvailable()} is nerver fired. In a real remote data
     * source implementation, this would be fired if the server can't be contacted or the server
     * returns an error.
     * @param taskId
     * @param callback
     */
    @Override
    public void getTask(String taskId, final GetTaskCallback callback) {
        final Task task = TASKS_SERVICE_DATA.get(taskId);

        // Simulate network by delaying the execution
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onTaskLoaded(task);
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void saveTask(Task task) {
        TASKS_SERVICE_DATA.put(task.getId(), task);
    }

    @Override
    public void completeTask(Task task) {
        Task completedTask = new Task(task.getTitle(), task.getDescription(), task.getId(), true);
        TASKS_SERVICE_DATA.put(task.getId(), completedTask);
    }

    @Override
    public void completeTask(String taskId) {
        // TODO: 4/18/2017
    }

    @Override
    public void activateTask(Task task) {
        Task activeTask = new Task(task.getTitle(), task.getDescription(), task.getId(), false);
        TASKS_SERVICE_DATA.put(task.getId(), activeTask);
    }

    @Override
    public void activateTask(String taskId) {
        // TODO: 4/18/2017
    }

    @Override
    public void clearCompletedTasks() {
        Iterator<Map.Entry<String, Task>> it = TASKS_SERVICE_DATA.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Task> entry = it.next();
            if (entry.getValue().isCompleted()) {
                it.remove();
            }
        }
    }

    @Override
    public void refreshTasks() {
        // TODO: 4/18/2017
    }

    @Override
    public void deleteAllTasks() {
        TASKS_SERVICE_DATA.clear();
    }

    @Override
    public void deleteTask(String taskId) {
        TASKS_SERVICE_DATA.remove(taskId);
    }
}
