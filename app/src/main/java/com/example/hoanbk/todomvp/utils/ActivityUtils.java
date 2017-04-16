package com.example.hoanbk.todomvp.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by hoanbk on 4/15/2017.
 */

public class ActivityUtils {

    /**
     * The {@code fragment is added to the container view with id {@code frameId}}.
     * performed by the fragmentManager
     */
    public static void addFragmentToActivity(FragmentManager fragmentManager,
                                             Fragment fragment, int frameId) {
        if (fragmentManager != null && fragment != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment);
            transaction.commit();
        }
    }
}
