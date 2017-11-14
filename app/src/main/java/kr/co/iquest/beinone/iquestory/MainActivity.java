package kr.co.iquest.beinone.iquestory;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import kr.co.iquest.beinone.iquestory.model.Company;
import kr.co.iquest.beinone.iquestory.model.Counselor;
import kr.co.iquest.beinone.iquestory.model.DecrementPopularity;
import kr.co.iquest.beinone.iquestory.model.Designer;
import kr.co.iquest.beinone.iquestory.model.Developer;
import kr.co.iquest.beinone.iquestory.model.Employee;
import kr.co.iquest.beinone.iquestory.model.HoldingPopularity;
import kr.co.iquest.beinone.iquestory.model.IncrementPopularity;
import kr.co.iquest.beinone.iquestory.model.Popularity;
import kr.co.iquest.beinone.iquestory.model.Product;

public class MainActivity extends AppCompatActivity {

    private Company iquest;

    private TextView mAssetTV;
    private TextView mYearTV;
    private TextView mMonthTV;
    private ProductCardView mMagicbillCV;
    private ProductCardView mUlmaCV;
    private ProductCardView mUlmaerpCV;
    private ViewGroup mMagicbillInfoLayout;
    private ViewGroup mUlmaInfoLayout;
    private ViewGroup mUlmaerpInfoLayout;
    private ImageButton mMagicbillInfoCloseIB;
    private ImageButton mUlmaInfoCloseIB;
    private ImageButton mUlmaerpInfoCloseIB;
    private Button mMagicbillEmployBtn;
    private Button mUlmaEmployBtn;
    private Button mUlmaerpEmployBtn;
    private TextView mMagicbillDeveloperTV;
    private TextView mMagicbillDesignerTV;
    private TextView mMagicbillCounselorTV;
    private TextView mUlmaDeveloperTV;
    private TextView mUlmaDesignerTV;
    private TextView mUlmaCounselorTV;
    private TextView mUlmaerpDeveloperTV;
    private TextView mUlmaerpDesignerTV;
    private TextView mUlmaerpCounselorTV;
    private TextView mMagicbillCustomerTV;
    private TextView mUlmaCustomerTV;
    private TextView mUlmaerpCustomerTV;
    private ImageView mMagicbillPopularityIV;
    private ImageView mUlmaPopularityIV;
    private ImageView mUlmaerpPopularityIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAssetTV = findViewById(R.id.tv_main_asset);
        mYearTV = findViewById(R.id.tv_main_year);
        mMonthTV = findViewById(R.id.tv_main_month);
        mMagicbillCV = findViewById(R.id.cv_products_magicbill);
        mUlmaCV = findViewById(R.id.cv_products_ulma);
        mUlmaerpCV = findViewById(R.id.cv_products_ulmaerp);
        mMagicbillInfoLayout = findViewById(R.id.layout_magicbill_info);
        mUlmaInfoLayout = findViewById(R.id.layout_ulma_info);
        mUlmaerpInfoLayout = findViewById(R.id.layout_ulmaerp_info);
        mMagicbillInfoCloseIB = mMagicbillInfoLayout.findViewById(R.id.ib_product_info_close);
        mUlmaInfoCloseIB = mUlmaInfoLayout.findViewById(R.id.ib_product_info_close);
        mUlmaerpInfoCloseIB = mUlmaerpInfoLayout.findViewById(R.id.ib_product_info_close);
        mMagicbillEmployBtn = mMagicbillInfoLayout.findViewById(R.id.btn_product_info_employ);
        mUlmaEmployBtn = mUlmaInfoLayout.findViewById(R.id.btn_product_info_employ);
        mUlmaerpEmployBtn = mUlmaerpInfoLayout.findViewById(R.id.btn_product_info_employ);
        mMagicbillDeveloperTV = findViewById(R.id.tv_products_magicbill_developer);
        mMagicbillDesignerTV = findViewById(R.id.tv_products_magicbill_designer);
        mMagicbillCounselorTV = findViewById(R.id.tv_products_magicbill_counselor);
        mUlmaDeveloperTV = findViewById(R.id.tv_products_ulma_developer);
        mUlmaDesignerTV = findViewById(R.id.tv_products_ulma_designer);
        mUlmaCounselorTV = findViewById(R.id.tv_products_ulma_counselor);
        mUlmaerpDeveloperTV = findViewById(R.id.tv_products_ulmaerp_developer);
        mUlmaerpDesignerTV = findViewById(R.id.tv_products_ulmaerp_designer);
        mUlmaerpCounselorTV = findViewById(R.id.tv_products_ulmaerp_counselor);
        mMagicbillCustomerTV = findViewById(R.id.tv_main_magicbill_customer);
        mUlmaCustomerTV = findViewById(R.id.tv_main_ulma_customer);
        mUlmaerpCustomerTV = findViewById(R.id.tv_main_ulmaerp_customer);
        mMagicbillPopularityIV = findViewById(R.id.iv_magicbill_popularity);
        mUlmaPopularityIV = findViewById(R.id.iv_ulma_popularity);
        mUlmaerpPopularityIV = findViewById(R.id.iv_ulmaerp_popularity);

        setupProductCardViews();

        startGame();
    }

    private void startGame() {
        establishCompany();
        setTimer();
    }

    public void establishCompany() {
        iquest = new Company(new Company.OnAssetChangedListener() {
            @Override
            public void onAssetChanged(int asset) {
                mAssetTV.setText(Utils.toPriceString(asset));
            }
        });
        iquest.setProducts(createProductList());
        iquest.setEmployees(createEmployeeList());
    }

    private void setTimer() {
        Time time = new Time(new Handler(), mOnTimeChangedListener);
        new Timer().schedule(new GameTimerTask(time), 500, 500);
    }

    private List<Product> createProductList() {
        Product magicbill = new Product(getString(R.string.magicbill_name),
                getResources().getInteger(R.integer.magicbill_price),
                new Product.OnProductDataChangedListener() {
                    @Override
                    public void onNumCustomerChanged(int numCustomer) {
                        mMagicbillCustomerTV.setText(Utils.toPriceString(numCustomer));
                    }

                    @Override
                    public void onPopularityChanged(Popularity popularity) {
                        setPopularityImage(mMagicbillPopularityIV, popularity);
                    }
                });
        mMagicbillCV.setProduct(magicbill);

        Product ulma = new Product(getString(R.string.ulma_name),
                getResources().getInteger(R.integer.ulma_price),
                new Product.OnProductDataChangedListener() {
                    @Override
                    public void onNumCustomerChanged(int numCustomer) {
                        mUlmaCustomerTV.setText(Utils.toPriceString(numCustomer));
                    }

                    @Override
                    public void onPopularityChanged(Popularity popularity) {
                        setPopularityImage(mUlmaPopularityIV, popularity);
                    }
                });
        mUlmaCV.setProduct(ulma);

        Product ulmaerp = new Product(getString(R.string.ulmaerp_name),
                getResources().getInteger(R.integer.ulmaerp_price),
                new Product.OnProductDataChangedListener() {
                    @Override
                    public void onNumCustomerChanged(int numCustomer) {
                        mUlmaerpCustomerTV.setText(Utils.toPriceString(numCustomer));
                    }

                    @Override
                    public void onPopularityChanged(Popularity popularity) {
                        setPopularityImage(mUlmaerpPopularityIV, popularity);
                    }
                });
        mUlmaerpCV.setProduct(ulmaerp);

        return Utils.createList(magicbill, ulma, ulmaerp);
    }

    private void setPopularityImage(ImageView popularityIV, Popularity popularity) {
        if (popularity instanceof DecrementPopularity) {
            popularityIV.setImageResource(R.drawable.ic_decrease);
        } else if (popularity instanceof IncrementPopularity) {
            popularityIV.setImageResource(R.drawable.ic_increase);
        } else {
            popularityIV.setImageResource(R.drawable.ic_holding);
        }
    }

    private List<Employee> createEmployeeList() {
        List<Employee> employees = new ArrayList<>();

        Product magicbill = iquest.getProducts().get(0);

        Developer developer1 = new Developer();
        developer1.setOwnProduct(magicbill);
        Designer designer1 = new Designer();
        designer1.setOwnProduct(magicbill);
        Counselor counselor1 = new Counselor();
        counselor1.setOwnProduct(magicbill);
        magicbill.setNumCustomer(6000);

        Product ulma = iquest.getProducts().get(1);

        Developer developer2 = new Developer();
        developer2.setOwnProduct(ulma);
        Designer designer2 = new Designer();
        designer2.setOwnProduct(ulma);
        Counselor counselor2 = new Counselor();
        counselor2.setOwnProduct(ulma);
        ulma.setNumCustomer(1650);

        Product ulmaerp = iquest.getProducts().get(2);

        Developer developer3 = new Developer();
        developer3.setOwnProduct(ulmaerp);
        Designer designer3 = new Designer();
        designer3.setOwnProduct(ulmaerp);
        Counselor counselor3 = new Counselor();
        counselor3.setOwnProduct(ulmaerp);
        ulmaerp.setNumCustomer(1350);

        employees.add(developer1);
        employees.add(developer2);
        employees.add(developer3);
        employees.add(designer1);
        employees.add(designer2);
        employees.add(designer3);
        employees.add(counselor1);
        employees.add(counselor2);
        employees.add(counselor3);

        return employees;
    }

    private void setupProductCardViews() {
        /* 매직빌 */
        mMagicbillCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMagicbillInfoLayout.setVisibility(View.VISIBLE);
            }
        });
        mMagicbillEmployBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmployDialogFragment.newInstance(new EmployDialogFragment.EmployCallback() {
                    @Override
                    public void employ(Employee employee) {
                        employee.setOwnProduct(mMagicbillCV.getProduct());
                        iquest.employ(employee);
                        for (Product product : iquest.getProducts()) {
                            if (employee.getOwnProduct() == product) {
                                product.increaseNumCustomer(2000);
                                mMagicbillCustomerTV.setText(Utils.toPriceString(product.getNumCustomer()));
                            }
                        }
                        if (employee instanceof Developer) {
                            int employeeCnt = Integer.valueOf(mMagicbillDeveloperTV.getText().toString());
                            mMagicbillDeveloperTV.setText(String.valueOf(employeeCnt + 1));
                        } else if (employee instanceof Designer) {
                            int employeeCnt = Integer.valueOf(mMagicbillDesignerTV.getText().toString());
                            mMagicbillDesignerTV.setText(String.valueOf(employeeCnt + 1));
                        } else if (employee instanceof Counselor) {
                            int employeeCnt = Integer.valueOf(mMagicbillCounselorTV.getText().toString());
                            mMagicbillCounselorTV.setText(String.valueOf(employeeCnt + 1));
                        }
                    }
                }).show(getSupportFragmentManager(), null);
            }
        });
        mMagicbillInfoCloseIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMagicbillInfoLayout.setVisibility(View.INVISIBLE);
            }
        });

        /* 얼마에요 */
        mUlmaCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUlmaInfoLayout.setVisibility(View.VISIBLE);
            }
        });
        mUlmaEmployBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmployDialogFragment.newInstance(new EmployDialogFragment.EmployCallback() {
                    @Override
                    public void employ(Employee employee) {
                        employee.setOwnProduct(mUlmaCV.getProduct());
                        iquest.employ(employee);
                        for (Product product : iquest.getProducts()) {
                            if (employee.getOwnProduct() == product) {
                                product.increaseNumCustomer(550);
                                mUlmaCustomerTV.setText(Utils.toPriceString(product.getNumCustomer()));
                            }
                        }
                        if (employee instanceof Developer) {
                            int employeeCnt = Integer.valueOf(mUlmaDeveloperTV.getText().toString());
                            mUlmaDeveloperTV.setText(String.valueOf(employeeCnt + 1));
                        } else if (employee instanceof Designer) {
                            int employeeCnt = Integer.valueOf(mUlmaDesignerTV.getText().toString());
                            mUlmaDesignerTV.setText(String.valueOf(employeeCnt + 1));
                        } else if (employee instanceof Counselor) {
                            int employeeCnt = Integer.valueOf(mUlmaCounselorTV.getText().toString());
                            mUlmaCounselorTV.setText(String.valueOf(employeeCnt + 1));
                        }
                    }
                }).show(getSupportFragmentManager(), null);
            }
        });
        mUlmaInfoCloseIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUlmaInfoLayout.setVisibility(View.INVISIBLE);
            }
        });

        /* 얼마에요 ERP */
        mUlmaerpCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUlmaerpInfoLayout.setVisibility(View.VISIBLE);
            }
        });
        mUlmaerpEmployBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmployDialogFragment.newInstance(new EmployDialogFragment.EmployCallback() {
                    @Override
                    public void employ(Employee employee) {
                        employee.setOwnProduct(mUlmaerpCV.getProduct());
                        iquest.employ(employee);
                        for (Product product : iquest.getProducts()) {
                            if (employee.getOwnProduct() == product) {
                                product.increaseNumCustomer(450);
                                mUlmaerpCustomerTV.setText(Utils.toPriceString(product.getNumCustomer()));
                            }
                        }
                        if (employee instanceof Developer) {
                            int employeeCnt = Integer.valueOf(mUlmaerpDeveloperTV.getText().toString());
                            mUlmaerpDeveloperTV.setText(String.valueOf(employeeCnt + 1));
                        } else if (employee instanceof Designer) {
                            int employeeCnt = Integer.valueOf(mUlmaerpDesignerTV.getText().toString());
                            mUlmaerpDesignerTV.setText(String.valueOf(employeeCnt + 1));
                        } else if (employee instanceof Counselor) {
                            int employeeCnt = Integer.valueOf(mUlmaerpCounselorTV.getText().toString());
                            mUlmaerpCounselorTV.setText(String.valueOf(employeeCnt + 1));
                        }
                    }
                }).show(getSupportFragmentManager(), null);
            }
        });
        mUlmaerpInfoCloseIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUlmaerpInfoLayout.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void changePopularity() {
        Random random = new Random(System.currentTimeMillis());

        for (int index = 0; index < iquest.getProducts().size(); index++) {
            int popularityCode = random.nextInt(3) - 1;

            Popularity popularity = null;
            if (popularityCode < 0) {
                popularity = new DecrementPopularity();
            } else if (popularityCode > 0) {
                popularity = new IncrementPopularity();
            } else {
                popularity = new HoldingPopularity();
            }

            iquest.getProducts().get(index).setPopularity(popularity);
        }
    }

    private void changeNumCustomer() {
        for (int index = 0; index < iquest.getProducts().size(); index++) {
            iquest.getProducts().get(index).changeNumCustomer();
        }
    }

    private Time.OnTimeChangedListener mOnTimeChangedListener = new Time.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(Time time) {
            mYearTV.setText(String.valueOf(time.getYear()));
            mMonthTV.setText(String.valueOf(time.getMonth()));

            if (iquest.getEmployees() != null) {
                for (Employee employee : iquest.getEmployees()) {
                    employee.increaseWorkMonth();
                }
            }

            changeNumCustomer();

            iquest.receiveSales();
            iquest.paySalary();

            changePopularity();
        }
    };
}
