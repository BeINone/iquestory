package kr.co.iquest.beinone.iquestory;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BeINone on 2017-11-13.
 */

public class EmployDialogFragment extends DialogFragment {

    public static EmployDialogFragment newInstance(Product product) {
        Bundle args = new Bundle();
        args.putParcelable("product", product);

        EmployDialogFragment fragment = new EmployDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_employ, container, false);

        EmployeeCardView developerCV = rootView.findViewById(R.id.cv_dialog_employ_developer);
        EmployeeCardView designerCV = rootView.findViewById(R.id.cv_dialog_employ_designer);
        EmployeeCardView CounselorCV = rootView.findViewById(R.id.cv_dialog_employ_counselor);

        return rootView;
    }

    private View.OnClickListener employeeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EmployeeCardView employeeCV = (EmployeeCardView) view;
            Employee employee = employeeCV.getEmployee();
            if (employee != null) {
                if (employee instanceof Developer) {
                    new AlertDialog.Builder(getContext())
                            .setMessage("개발자를 고용하시겠습니까?")
                            .setPositiveButton("고용", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .create()
                            .show();
                } else if (employee instanceof Designer) {

                } else if (employee instanceof Counselor) {

                }
            }
        }
    };
}
