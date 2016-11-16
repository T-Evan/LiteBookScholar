package com.bitworkshop.litebookscholar.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AidChow on 2016/10/24.
 */

public class ChooseDialogFragment extends DialogFragment {
    @BindView(R.id.tv_choose_from_camera)
    TextView tvChooseFromCamera;
    @BindView(R.id.tv_choose_from_photo)
    TextView tvChooseFromPhoto;
    private OnDialogItemClickListener onDialogItemClickListener;
    CharSequence[] setItems = {"拍照", "从相册选择"};

    public static ChooseDialogFragment getInstance() {
        return new ChooseDialogFragment();
    }

    public ChooseDialogFragment() {
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("选择头像").setItems(setItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        onDialogItemClickListener.gotoCamera();
                        break;
                    case 1:
                        onDialogItemClickListener.gotoPhotos();
                        break;
                }
            }
        });
        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onDialogItemClickListener = (OnDialogItemClickListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnDialogItemClickListener");
        }
    }

    public interface OnDialogItemClickListener {
        void gotoCamera();

        void gotoPhotos();
    }
}
