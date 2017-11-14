package kr.co.iquest.beinone.iquestory;

import android.content.Context;
import android.support.v7.widget.CardView;

/**
 * Created by BeINone on 2017-11-14.
 */

public class EmployeeCardView extends CardView {

    private Employee mEmployee;

    public EmployeeCardView(Context context) {
        super(context);
    }

    public Employee getEmployee() {
        return mEmployee;
    }

    public void setEmployee(Employee employee) {
        mEmployee = employee;
    }
}
