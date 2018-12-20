package com.jhj.activityresult;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class OnResultFragment extends Fragment {

    static int ACTIVITY_CODE = 0x11000000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    void startActivityForResult(Class<? extends Activity> activity) {
        Intent intent = new Intent(getActivity(), activity);
        intent.putExtras(getArguments());
        startActivityForResult(intent, ACTIVITY_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_CODE) {
            ActivityResult.getInstance(getActivity()).activityResult(data);
        }

    }
}
