package com.example.myfirstquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class QuestionsActivity extends AppCompatActivity {

    private static float col = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        ViewPager2 pager = findViewById(R.id.pager);
        FragmentStateAdapter pageAdapter = new MyAdapter(this);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                if (position+positionOffset > col & positionOffset > 0.9){
                    col=position+1;
                }
                if (position+positionOffset < col){
                    pager.setCurrentItem(position+1);
                }
            }
        });
        pager.setAdapter(pageAdapter);
    }
}