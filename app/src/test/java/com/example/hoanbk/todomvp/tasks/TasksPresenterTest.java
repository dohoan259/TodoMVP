package com.example.hoanbk.todomvp.tasks;

import com.example.hoanbk.todomvp.data.Task;
import com.example.hoanbk.todomvp.data.source.TasksDataSource;
import com.example.hoanbk.todomvp.data.source.TasksRepository;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hoanbk on 5/3/2017.
 */

public class TasksPresenterTest {

    @Mock
    private TasksContract.View mTasksView;

    @Mock
    private TasksRepository mTasksRepository;

    @Captor
    private ArgumentCaptor<TasksDataSource.LoadTasksCallback> mLoadTasksCallbackCaptor;

    private TasksPresenter mTasksPresenter;

    private List<Task> mTasks;

    @Before
    public void setupTasksPresenter() {
        MockitoAnnotations.initMocks(this);

        // cac gia tri khoi tao
        mTasksPresenter = new TasksPresenter(mTasksRepository, mTasksView);

        when(mTasksView.isActive()).thenReturn(true);

        mTasks = Lists.newArrayList(new Task("Title1", "Description1", false),
                new Task("Title2", "Description2", true), new Task("Title3", "Description3", true));
    }

    // function test
    @Test
    public void GetAllTasksFromRepositoryAndLoadIntoView() {
        mTasksPresenter.setFiltering(TasksFilterType.ALL_TASKS);
        mTasksPresenter.loadTasks(true);

        // call back
        verify(mTasksRepository).getTasks(mLoadTasksCallbackCaptor.capture());
        mLoadTasksCallbackCaptor.getValue().onTasksLoaded(mTasks);

        // progress indicator
        InOrder inOrder = inOrder(mTasksView);
        inOrder.verify(mTasksView).setLoadIndicator(true);
        inOrder.verify(mTasksView).setLoadIndicator(false);

        ArgumentCaptor<List> showTasksArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mTasksView).showTasks(showTasksArgumentCaptor.capture());
        assertTrue(showTasksArgumentCaptor.getValue().size() == 3);
    }

    @Test
    public void GetCompletedTasksFromRepositoryAndLoadIntoView() {
        mTasksPresenter.setFiltering(TasksFilterType.COMPLETED_TASKS);
        mTasksPresenter.loadTasks(true);

        // call back test for repository
        verify(mTasksRepository).getTasks(mLoadTasksCallbackCaptor.capture());
        mLoadTasksCallbackCaptor.getValue().onTasksLoaded(mTasks);

        // test for view
        verify(mTasksView).setLoadIndicator(false);
        ArgumentCaptor<List> showCompletedTasksArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mTasksView).showTasks(showCompletedTasksArgumentCaptor.capture());
        assertTrue(showCompletedTasksArgumentCaptor.getValue().size() == 2);
    }

    @Test
    public void GetActiveTasksFromRepositoryAndLoadIntoView() {
        // execute
        mTasksPresenter.setFiltering(TasksFilterType.ACTIVE_TASKS);
        mTasksPresenter.loadTasks(true);

        // verify
        verify(mTasksRepository).getTasks(mLoadTasksCallbackCaptor.capture());
        mLoadTasksCallbackCaptor.getValue().onTasksLoaded(mTasks);

        verify(mTasksView).setLoadIndicator(false);
        ArgumentCaptor<List> showActiveTasksArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mTasksView).showTasks(showActiveTasksArgumentCaptor.capture());
        assertTrue(showActiveTasksArgumentCaptor.getValue().size() == 1);
    }

    @Test
    public void clickOnFab_ShowAddTaskUi() {
        // give a stubbed
        Task requestedTask = new Task("Detail title", "Detail description");

        mTasksPresenter.openTaskDetails(requestedTask);

        verify(mTasksView).showTaskDetailsUi(any(String.class));
    }

    @Test
    public void completeTask_ShowTaskMarkedComplete() {
        Task completeTask = new Task("complete title", "complete description");
        mTasksPresenter.completeTask(completeTask);

        // verify
        verify(mTasksRepository).completeTask(any(Task.class));
        verify(mTasksView).showTaskMarkedComplete();
    }

    @Test
    public void activeTask_ShowTaskMarkedActive() {
        Task activeTask = new Task("active title", "active description", true);
        mTasksPresenter.activateTask(activeTask);

        // verify
        verify(mTasksRepository).activateTask(any(Task.class));
        verify(mTasksView).showTaskMarkedActive();
    }

    @Test
    public void unavailableTasks_showError() {
        mTasksPresenter.setFiltering(TasksFilterType.ALL_TASKS);
        mTasksPresenter.loadTasks(true);

        verify(mTasksRepository).getTasks(mLoadTasksCallbackCaptor.capture());
        mLoadTasksCallbackCaptor.getValue().onDataNotAvailable();

        // then an error message is show
        verify(mTasksView).showLoadingTaskError();
    }
}
