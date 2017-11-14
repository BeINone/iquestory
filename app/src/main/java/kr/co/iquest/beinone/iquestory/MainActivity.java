package kr.co.iquest.beinone.iquestory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Company iquest;

    private TextView mYearTV;
    private TextView mMonthTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mYearTV = findViewById(R.id.tv_main_year);
        mMonthTV = findViewById(R.id.tv_main_month);

        CardView magicbillCV = findViewById(R.id.cv_products_magicbill);
        CardView ulmaCV = findViewById(R.id.cv_products_ulma);
        CardView ulmaerpCV = findViewById(R.id.cv_products_ulmaerp);

        magicbillCV.setOnClickListener(productClickListener);
        ulmaCV.setOnClickListener(productClickListener);
        ulmaerpCV.setOnClickListener(productClickListener);

        Iquestory.startGame(timeChangedListener);
        Iquestory.releaseProducts(createProductList());
    }

    private Time.OnTimeChangedListener timeChangedListener = new Time.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(Time time) {
            mYearTV.setText(String.valueOf(time.getYear()));
            mMonthTV.setText(String.valueOf(time.getMonth()));

            if (iquest.getEmployees() != null) {
                for (Employee employee : iquest.getEmployees()) {
                    employee.increaseWorkMonth();
                }
            }

            iquest.receiveSales();
            iquest.paySalary();
        }
    };

    private View.OnClickListener productClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private List<Product> createProductList() {
        Product magicbill = new Product(getString(R.string.magicbill_name),
                getResources().getInteger(R.integer.magicbill_price));
        Product ulma = new Product(getString(R.string.ulma_name),
                getResources().getInteger(R.integer.ulma_price));
        Product ulmaerp = new Product(getString(R.string.ulmaerp_name),
                getResources().getInteger(R.integer.ulmaerp_price));

        return createList(magicbill, ulma, ulmaerp);
    }

    private <T> List<T> createList(T... objects) {
        List<T> productList = new ArrayList<>();
        for (int index = 0; index < objects.length; index++) {
            productList.add(objects[index]);
        }

        return productList;
    }
}
