package com.example.hoanbk.todomvp.data;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.util.UUID;

/**
 * Created by hoanbk on 4/15/2017.
 * Immutable model class for a Task
 */

public final class Task {

    private final String mId;

    private final String mTitle;

    private final String mDescription;

    private final boolean mCompleted;

    /**
     * Use this constructor to create a new active Task
     *
     * @param title         title of the task
     * @param description   description os the task
     */

    public Task(String title, String description) {
        this(title, description, UUID.randomUUID().toString(), false);
    }

    /**
     * Use this constructor to create an active Task if the Task already has an id
     *
     * @param title         title of the task
     * @param description   description os the task
     * @param id            id os the task
     */
    public Task(String title, String description, String id) {
        this(title, description, id, false);
    }

    /**
     * Use this constructor to create a new completed Task
     *
     * @param title         title of the task
     * @param description   description os the task
     * @param completed     true if the task is completed, false if it's active
     */
    public Task(String title, String description, boolean completed) {
        this(title, description, UUID.randomUUID().toString(), completed);
    }

    /**
     * Use this constructor to specify a completed Task if the Task already has an id
     * (copy of another Task)
     *
     * @param title         title of the task
     * @param description   description os the task
     * @param id            id os the task
     * @param completed     true if the task is completed, false if it's active
     */
    public Task(String title, String description,
                String id, boolean completed) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = completed;
    }

    public String getId() {return mId;}

    public String getTitle() {
        return mTitle;
    }

    public String getTitleForList() {
        if (mTitle == null || mTitle.equals("")) {
            return mDescription;
        } else {
            return mTitle;
        }
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public boolean isActive() {return !mCompleted;}

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mTitle)
                && Strings.isNullOrEmpty(mDescription);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return Objects.equal(mId, task.getId())
                && Objects.equal(mTitle, task.getTitle())
                && Objects.equal(mDescription, task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mTitle, mDescription);
    }

    @Override
    public String toString() {
        return "Task with title " + mTitle;
    }
}
