package com.jhj.activityresult;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;

public final class ActivityResult {

    private Activity mActivity;
    private Class<? extends Activity> mTargetActivity;
    private Bundle mBundle = new Bundle();
    private Intent mIntent;

    private ActivityResult(Activity activity) {
        this.mActivity = activity;
    }

    public static ActivityResult init(Activity activity) {
        return new ActivityResult(activity);
    }

    public ActivityResult putByte(String key, byte value) {
        mBundle.putByte(key, value);
        return this;
    }


    public ActivityResult putChar(String key, char value) {
        mBundle.putChar(key, value);
        return this;
    }

    public ActivityResult putShort(String key, short value) {
        mBundle.putShort(key, value);
        return this;
    }

    public ActivityResult putInt(String key, int value) {
        mBundle.putInt(key, value);
        return this;
    }


    public ActivityResult putFloat(String key, float value) {
        mBundle.putFloat(key, value);
        return this;
    }

    public ActivityResult putDouble(String key, Double value) {
        mBundle.putDouble(key, value);
        return this;
    }

    public ActivityResult putLong(String key, Long value) {
        mBundle.putLong(key, value);
        return this;
    }

    public ActivityResult putString(String key, String value) {
        mBundle.putString(key, value);
        return this;
    }

    public ActivityResult putBoolean(String key, boolean value) {
        mBundle.putBoolean(key, value);
        return this;
    }

    public ActivityResult putCharSequence(String key, CharSequence value) {
        mBundle.putCharSequence(key, value);
        return this;
    }

    public ActivityResult putParcelable(String key, Parcelable value) {
        mBundle.putParcelable(key, value);
        return this;
    }

    public ActivityResult putDoubleArray(String key, double[] value) {
        mBundle.putDoubleArray(key, value);
        return this;
    }

    public ActivityResult putLongArray(String key, long[] value) {
        mBundle.putLongArray(key, value);
        return this;
    }

    public ActivityResult putIntArray(String key, int[] value) {
        mBundle.putIntArray(key, value);
        return this;
    }

    public ActivityResult putBooleanArray(String key, boolean[] value) {
        mBundle.putBooleanArray(key, value);
        return this;
    }

    public ActivityResult putStringArray(String key, String[] value) {
        mBundle.putStringArray(key, value);
        return this;
    }

    public ActivityResult putParcelableArray(String key, Parcelable[] value) {
        mBundle.putParcelableArray(key, value);
        return this;
    }

    public ActivityResult putParcelableArrayList(String key,
                                                 ArrayList<? extends Parcelable> value) {
        mBundle.putParcelableArrayList(key, value);
        return this;
    }


    public ActivityResult putSparseParcelableArray(String key,
                                                   SparseArray<? extends Parcelable> value) {
        mBundle.putSparseParcelableArray(key, value);
        return this;
    }

    public ActivityResult putIntegerArrayList(String key, ArrayList<Integer> value) {
        mBundle.putIntegerArrayList(key, value);
        return this;
    }

    public ActivityResult putStringArrayList(String key, ArrayList<String> value) {
        mBundle.putStringArrayList(key, value);
        return this;
    }


    public ActivityResult putCharSequenceArrayList(String key,
                                                   ArrayList<CharSequence> value) {
        mBundle.putCharSequenceArrayList(key, value);
        return this;
    }

    public ActivityResult putSerializable(String key, Serializable value) {
        mBundle.putSerializable(key, value);
        return this;
    }

    public ActivityResult putByteArray(String key, byte[] value) {
        mBundle.putByteArray(key, value);
        return this;
    }


    public ActivityResult putShortArray(String key, short[] value) {
        mBundle.putShortArray(key, value);
        return this;
    }


    public ActivityResult putCharArray(String key, char[] value) {
        mBundle.putCharArray(key, value);
        return this;
    }


    public ActivityResult putFloatArray(String key, float[] value) {
        mBundle.putFloatArray(key, value);
        return this;
    }


    public ActivityResult putCharSequenceArray(String key, CharSequence[] value) {
        mBundle.putCharSequenceArray(key, value);
        return this;
    }


    public ActivityResult putBundle(String key, Bundle value) {
        mBundle.putBundle(key, value);
        return this;
    }

    public ActivityResult putAll(Bundle bundle) {
        this.mBundle.putAll(bundle);
        return this;
    }


    public ActivityResult targetActivity(Class<? extends Activity> targetActivity) {
        this.mTargetActivity = targetActivity;
        return this;
    }

    public ActivityResult targetIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }


    public void onResult(OnActivityResultListener listener) {
        try {
            if (mActivity == null) {
                return;
            }
            String TAG = getClass().getName();
            FragmentManager fragmentManager = mActivity.getFragmentManager();
            OnResultFragment fragment = (OnResultFragment) fragmentManager.findFragmentByTag(TAG);

            if (fragment == null) {
                fragment = new OnResultFragment();
                fragmentManager
                        .beginTransaction()
                        .add(fragment, TAG)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
            if (mTargetActivity != null) {
                fragment.startActivityForResult(mTargetActivity, mBundle, listener);
            } else if (mIntent != null) {
                fragment.startActivityForResult(mIntent, listener);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface OnActivityResultListener {
        void onResult(Intent data);
    }
}