package id.co.noz.viewpager.lib;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import id.co.noz.viewpager.CirclePageIndicator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context c;
    private View finish_tutorial;
    private boolean sudah_baca = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = this;

        finish_tutorial = findViewById(R.id.finish_tutorial);
        finish_tutorial.setOnClickListener(this);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.howto1);
        list.add(R.drawable.howto2);
        list.add(R.drawable.howto3);
        list.add(R.drawable.howto4);
        list.add(R.drawable.howto5);
        list.add(R.drawable.howto6);
        list.add(R.drawable.howto7);
        list.add(R.drawable.howto8);

        new Slider(list);
    }

    @Override
    public void onClick(View v) {
//        if(v == finish_tutorial) {
//            if(sudah_baca) lanjut();
//            else {
//                Dialog d = new AlertDialogWrapper.Builder(c)
//                        .setTitle(R.string.perhatian)
//                        .setMessage(R.string.perhatian_msg)
//                        .setNegativeButton(R.string.no, null)
//                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                                lanjut();
//                            }
//                        })
//                        .create();
//                d.setCanceledOnTouchOutside(false);
//                d.show();
//            }
//        }
    }

//    private void lanjut(){
//        new TutorialPrefs(c).setNewUser(false);
//        aa.startForwardAndFinish(new Intent(c, Awal.class));
//    }

    private class Slider extends PagerAdapter {

        private ArrayList<Integer> items;
        private LayoutInflater inf;
        private ViewPager vp;
        private CirclePageIndicator cpi;
        private View left, right;
        private ViewPager.SimpleOnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                showPanah(position);
            }
        };

        private void showPanah(int pos){
            left.setVisibility(pos == 0 ? View.GONE : View.VISIBLE);
            right.setVisibility(pos == items.size() - 1 ? View.GONE : View.VISIBLE);

            if(pos == items.size() - 1) {
                if(! sudah_baca) {
                    sudah_baca = true;
                }
            }
        }

        public Slider(ArrayList<Integer> items) {
            this.items = items;
            inf = LayoutInflater.from(c);

            vp = (ViewPager) findViewById(R.id.main_banner);
            cpi = (CirclePageIndicator) findViewById(R.id.indikator_slider);
            left = findViewById(R.id.panah_kiri);
            right = findViewById(R.id.panah_kanan);

            vp.addOnPageChangeListener(onPageChangeListener);
            vp.setAdapter(this);

            cpi.setViewPager(vp);

            cpi.setVisibility(items == null || items.size() <= 1 ? View.GONE : View.VISIBLE);
            showPanah(0);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Integer si = items.get(position);
            View v = inf.inflate(R.layout.image_slider, null);

            ImageView img = (ImageView) v.findViewById(R.id.image);
            img.setImageResource(si);

            ((ViewGroup)v).removeView(v.findViewById(R.id.pb));

            container.addView(v);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }
    }

}