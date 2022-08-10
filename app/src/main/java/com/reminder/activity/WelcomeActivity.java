package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.reminder.MainActivity;
import com.reminder.R;
import com.reminder.databinding.ActivityWelcomeBinding;
import com.reminder.utils.PrefManager;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class WelcomeActivity extends AppCompatActivity {
    private ActivityWelcomeBinding binding;
    private Context context;
    private View view;
   private ViewPager viewPager;
   private DotsIndicator dots_indicator;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private TextView txSkip;
    Button btNext,btStart;
    int maintain = 0;
    private PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        binding= ActivityWelcomeBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        setContentView(view);

        context=this;

        viewPager = (ViewPager) findViewById(R.id.view_pagers);
        dots_indicator = findViewById(R.id.dots_indicator);

        txSkip = (TextView) findViewById(R.id.txSkip);
        btNext = (Button) findViewById(R.id.btNext);
        btStart = (Button) findViewById(R.id.btStart);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.easy_track_layout,
                R.layout.send_reminder_layout,
                R.layout.self_check_layout,
                R.layout.easy_widget_layout};
/*

        // adding bottom dots
        addBottomDots(0);
*/

        // making notification bar transparent
        changeStatusBarColor();


        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        dots_indicator.attachTo(viewPager);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        txSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });


        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maintain == layouts.length - 1) {
                    // last page. make button text to GOT IT
                    int current = getItem(+1);
                    if (current < layouts.length) {
                        // move to next screen
                        viewPager.setCurrentItem(current);
                    } else {

                        if (current>layouts.length){
                            viewPager.setCurrentItem(current);

                        }
                        startActivity(new Intent(WelcomeActivity.this,SplashActivity.class));
                    }

                }

            }
        });
    }
    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array1_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array1_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }



    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
           // addBottomDots(position);
            maintain = position;
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                //btNext.setText(getString(R.string.next));
                txSkip.setVisibility(View.GONE);
                btStart.setVisibility(View.VISIBLE);
                btNext.setVisibility(View.GONE);
                btStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                    }
                });
            } else {
                // still pages are left
              //  btNext.setText(getString(R.string.next));
                txSkip.setVisibility(View.VISIBLE);
                btNext.setVisibility(View.VISIBLE);
                btStart.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //    Toast.makeText(WelcomeActivity.this, "" + maintain, Toast.LENGTH_SHORT).show();
                    if (maintain == layouts.length - 1) {
                        // last page. make button text to GOT IT
                        int current = getItem(+1);
                        if (current < layouts.length) {
                            // move to next screen
                            viewPager.setCurrentItem(current);
                        } else {
                            finish();
                            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));


                        }
                    }
                }
            });

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}