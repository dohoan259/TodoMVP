package com.example.hoanbk.todomvp.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.util.UUID;

/**
 * Created by hoanbk on 4/15/2017.
 * Immutable model class for a Task
 */

public final class Task {

    @NonNull
    private final String mId;

    @Nullable
    private final String mTitle;

    @Nullable
    private final String mDescription;

    private final boolean mCompleted;

    /**
     * Use this constructor to create a new active Task
     *
     * @param title         title of the task
     * @param description   description os the task
     */

    public Task(@Nullable String title, @Nullable String description) {
        this(title, description, UUID.randomUUID().toString(), false);
    }

    /**
     * Use this constructor to create an active Task if the Task already has an id
     *
     * @param title         title of the task
     * @param description   description os the task
     * @param id            id os the task
     */
    public Task(@Nullable String title, @Nullable String description, @NonNull String id) {
        this(title, description, id, false);
    }

    /**
     * Use this constructor to create a new completed Task
     *
     * @param title         title of the task
     * @param description   description os the task
     * @param completed     true if the task is completed, false if it's active
     */
    public Task(@Nullable String title, @Nullable String description, boolean completed) {
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
    public Task(@Nullable String title, @Nullable String description,
                @NonNull String id, boolean completed) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = completed;
    }

    @NonNull
    public String getId() {return mId;}

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getTitleForList() {
        if (!Strings.isNullOrEmpty(mTitle)) {
            return mTitle;
        } else {
            return mDescription;
        }
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public boolean isActive() {return !mCompleted;}

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mTitle)
                || Strings.isNullOrEmpty(mDescription);
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
