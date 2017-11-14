package kr.co.iquest.beinone.iquestory;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.iquest.beinone.iquestory.model.Counselor;
import kr.co.iquest.beinone.iquestory.model.Designer;
import kr.co.iquest.beinone.iquestory.model.Developer;
import kr.co.iquest.beinone.iquestory.model.Employee;

/**
 * Created by BeINone on 2017-11-13.
 */

public class EmployDialogFragment extends DialogFragment {

    private EmployCallback mEmployCallback;

    public static EmployDialogFragment newInstance(EmployCallback callback) {
        EmployDialogFragment fragment = new EmployDialogFragment();
        fragment.mEmployCallback = callback;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_employ, container, false);

        EmployeeCardView developerCV = rootView.findViewById(R.id.cv_dialog_employ_developer);
        EmployeeCardView designerCV = rootView.findViewById(R.id.cv_dialog_employ_designer);
        EmployeeCardView counselorCV = rootView.findViewById(R.id.cv_dialog_employ_counselor);

        developerCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setMessage("개발자를 고용하시겠습니까?")
                        .setPositiveButton("고용", new OnPositiveButtonClickListener(new Developer()))
                        .setNegativeButton("취소", mOnNegativeButtonClickListener)
                        .create()
                        .show();
            }
        });

        designerCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setMessage("디자이너를 고용하시겠습니까?")
                        .setPositiveButton("고용", new OnPositiveButtonClickListener(new Designer()))
                        .setNegativeButton("취소", mOnNegativeButtonClickListener)
                        .create()
                        .show();
            }
        });

        counselorCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setMessage("상담원을 고용하시겠습니까?")
                        .setPositiveButton("고용", new OnPositiveButtonClickListener(new Counselor()))
                        .setNegativeButton("취소", mOnNegativeButtonClickListener)
                        .create()
                        .show();
            }
        });

        return rootView;
    }

    private DialogInterface.OnClickListener mOnNegativeButtonClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    };

    private class OnPositiveButtonClickListener implements DialogInterface.OnClickListener {
        private Employee mEmployee;

        public OnPositiveButtonClickListener(Employee employee) {
            mEmployee = employee;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            mEmployCallback.employ(mEmployee);
        }
    };

    public interface EmployCallback {
        void employ(Employee employee);
    }
}
