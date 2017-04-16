package com.example.hoanbk.todomvp.tasks;

import com.example.hoanbk.todomvp.base.BasePresenter;
import com.example.hoanbk.todomvp.base.BaseView;
import com.example.hoanbk.todomvp.data.Task;

import java.util.List;

/**
 * Created by hoanbk on 4/16/2017.
 * This specifies the contract between the view and the presenter
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {

        void setLoadIndicator(boolean active);

        void showTasks(List<Task> tasks);

        void showAddTask();

        void showTaskDetailsUi(String taskId);

        void showTaskMarkedComplete();

        void showTaskMarkedActive();

        void showCompletedTasksCleared();

        void showLoadingTaskError();

        void showNoTasks();

        void showNoActiveTasks();

        void showNoCompletedTasks();

        void showSuccessfullySavedMessage();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadTasks(boolean forceUpdate);

        void addNewTask();

        void openTaskDetails(Task requestedTask);

        void completeTask(Task completedTask);

        void activateTask(Task activeTask);

        void clearCompletedTask();
    }
}
