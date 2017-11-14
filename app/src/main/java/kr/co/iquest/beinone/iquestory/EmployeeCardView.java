package kr.co.iquest.beinone.iquestory;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import kr.co.iquest.beinone.iquestory.model.Employee;

/**
 * Created by BeINone on 2017-11-14.
 */

public class EmployeeCardView extends CardView {

    private Employee mEmployee;

    public EmployeeCardView(Context context) {
        super(context);
    }

    public EmployeeCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Employee getEmployee() {
        return mEmployee;
    }

    public void setEmployee(Employee employee) {
        mEmployee = employee;
    }
}
