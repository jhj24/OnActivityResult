package com.jhj.activityresult;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

public final class OnResultFragment extends Fragment {

    static int ACTIVITY_CODE = 0x11000000;
    private ActivityResult.OnActivityResultListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    void startActivityForResult(Class<? extends Activity> activity, Bundle bundle, ActivityResult.OnActivityResultListener listener) {
        this.mListener = listener;
        Intent intent = new Intent(getActivity(), activity);
        intent.putExtras(bundle);
        startActivityForResult(intent, ACTIVITY_CODE);
    }

    void startActivityForResult(Intent intent, ActivityResult.OnActivityResultListener listener) {
        this.mListener = listener;
        startActivityForResult(intent, ACTIVITY_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_CODE && mListener != null) {
            mListener.onResult(data);
        }
    }
}
