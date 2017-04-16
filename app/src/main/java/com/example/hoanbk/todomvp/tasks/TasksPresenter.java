package com.example.hoanbk.todomvp.tasks;

import com.example.hoanbk.todomvp.data.Task;
import com.example.hoanbk.todomvp.data.source.TaskRepository;
import com.example.hoanbk.todomvp.data.source.TasksDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoanbk on 4/16/2017.
 * Listens to user actions from the UI ({@Link TasksFragment}), retrieves the data and updates the
 * UI as required
 */

public class TasksPresenter implements TasksContract.Presenter {

    private final TaskRepository mTaskRepository;

    private final TasksContract.View mTasksView;

    private boolean mFirstLoad = true;

    public TasksPresenter(TaskRepository taskRepository, TasksContract.View tasksView){
        mTaskRepository = taskRepository;
        mTasksView = tasksView;
    }

    @Override
    public void start() {
        loadTasks(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
        // If a task was successfully added, show snackbar
        // TODO: 4/16/2017

    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        // Simplification for sample: a network reload will be forced on first load
        loadTasks(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    /**
     * @param forceUpdate   Pass in true to refresh the data in the {@Link TaskDataSource}
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    private void loadTasks(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mTasksView.setLoadIndicator(true);
        }
        if (forceUpdate) {
            mTaskRepository.refreshTasks();
        }

        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy util the response is handled
        // TODO: 4/16/2017

        mTaskRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                List<Task> tasksToShow = new ArrayList<>();
                
                // this callback may be called twice, once for the cache and once for loading
                // the data from server API, so we check before decrementing, otherwise
                // it throws "Counters has been corrupted!" exception
                // TODO: 4/16/2017
                
                // We filter the tasks based on the requestType
                for (Task task : tasks) {
                    // TODO: 4/16/2017  
                    tasksToShow.add(task);
                }
                // The view may not be able to handle UI updates anymore
                if (!mTasksView.isActive()) {
                    mTasksView.setLoadIndicator(false);
                }
                
                processTasks(tasksToShow);
            }

            @Override
            public void onDataNotAvailable() {
                // The view may not be able to handle UI updates anymore
                if (!mTasksView.isActive()) {
                    return;
                }
                mTasksView.showLoadingTaskError();
            }
        });
    }

    private void processTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type
            processEmptyTasks();
        } else {
            // Show the list of tasks
            mTasksView.showTasks(tasks);
            // Set the filter label's text
            showFilterLabel();
        }
    }
    
    private void showFilterLabel() {
        // TODO: 4/16/2017  
    }
    
    private void processEmptyTasks() {
        // TODO: 4/16/2017
    }
    
    @Override
    public void addNewTask() {
        mTasksView.showAddTask();
    }

    @Override
    public void openTaskDetails(Task requestedTask) {
        if (requestedTask == null) {
            return;
        }
        mTasksView.showTaskDetailsUi(requestedTask.getId());
    }

    @Override
    public void completeTask(Task completedTask) {
        if (completedTask == null) {
            return;
        }
        mTaskRepository.completeTask(completedTask);
        mTasksView.showTaskMarkedComplete();
        loadTasks(false, false);
    }

    @Override
    public void activateTask(Task activeTask) {
        if (activeTask == null) {
            return;
        }
        mTaskRepository.activateTask(activeTask);
        mTasksView.showTaskMarkedActive();
        loadTasks(false, false);
    }

    @Override
    public void clearCompletedTask() {
        mTaskRepository.clearCompletedTasks();
        mTasksView.showCompletedTasksCleared();
        loadTasks(false, false);
    }

    /**
     * Sets the current task filtering type
     *
     * @param requestType Can be {@link TasksFilterType#ALL_TASKS},
     *                    {@link TasksFilterType#COMPLETED_TASKS}, or
     *                    {@link TasksFilterType#ACTIVE_TASKS}
     */
}
