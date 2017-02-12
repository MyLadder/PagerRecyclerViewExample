package samnnyexample.pagerrecyclerviewexample;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2017-02-12.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.RecyclerViewHolder> {
  private Context hello;
  private static final int IS_HEADER = 2;
  private static final int IS_FOOTER = 3;
  private static final int IS_NORMAL = 1;

//  private int[] imageRes = new int[] {R.drawable.home_canto, R.drawable.home_snack,R.drawable.home_canto,
//          R.drawable.home_jap, R.drawable.home_sefood, R.drawable.home_snack, R.drawable.home_schuan700,R.drawable.home_canto,
//          R.drawable.home_jap, R.drawable.home_sefood, R.drawable.home_snack, R.drawable.home_schuan700}; // 图片ID

  private String[] colorId = new String[]{"#00A5BF", "#1D697C", "#763568", "#A4345D", "#C91F37" ,"#FF4E20" ,"#FFA631"};
  private String[] SampleText = new String[]{"hello", "this", "is", "material", "design","!","!"};
  //private static List<Integer> titleList = new ArrayList<Integer>();


  //view pager
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private ViewPager viewPager; // android-support-v4中的滑动组件
  private List<ImageView> imageViews; // 滑动的图片集合

  private String[] titles; // 图片标题
  private int[] imageResId = new int[] { R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4 };
  private List<View> dots; // 图片标题正文的那些点

  private TextView tv_title;
  private int currentItem = 0; // 当前图片的索引号
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



  //constructor
  public CustomerAdapter(Context hello) {
    this.hello = hello;
//    for (int i = 0; i <= imageRes.length; i++) {
//      titleList.add(imageRes[i]);
//    }
  }

  @Override
  public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    RecyclerViewHolder holder;
    //对不同的flag创建不同的Holder
    if (viewType == IS_HEADER) {
      View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_header, viewGroup, false);


      imageViews = new ArrayList<ImageView>();
      //re 初始化图片资源
      for (int i = 0; i < imageResId.length; i++) {
        ImageView imageView = new ImageView(hello);
        imageView.setImageResource(imageResId[i]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageViews.add(imageView);

      }
      dots = new ArrayList<View>();; // 图片标题正文的那些点
      dots.add(view.findViewById(R.id.v_dot0));
      dots.add(view.findViewById(R.id.v_dot1));
      dots.add(view.findViewById(R.id.v_dot2));
      dots.add(view.findViewById(R.id.v_dot3));
      holder = new RecyclerViewHolder(view,IS_HEADER);
      return holder;


    }
//    else if (viewType == IS_FOOTER) {
//      View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_footer, viewGroup, false);
//      holder = new RecyclerViewHolder(view,IS_FOOTER);
//      return holder;
//    }

    else if(viewType==IS_NORMAL){
      View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_recycler_item, viewGroup, false);
      holder = new RecyclerViewHolder(view,IS_NORMAL);
      return holder;
    }
    return null;
  }

  @Override
  public void onBindViewHolder(final RecyclerViewHolder recyclerViewHolder, int position) {
    //对不同的Item相应不同的操作
    if(position!=0&&position!=colorId.length+1&&recyclerViewHolder.viewType==IS_NORMAL){
      //recyclerViewHolder.mTextView.setText(datas.get(position - 1));
//      recyclerViewHolder.imgView.setImageResource(imageRes[position]);


      recyclerViewHolder.imgView.setBackgroundColor(Color.parseColor(colorId[position]));
      recyclerViewHolder.txtView.setText(SampleText[position]);
//      recyclerViewHolder.imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
//    if(position==0&&recyclerViewHolder.viewType==IS_HEADER){
//      //header
////      recyclerViewHolder.mButton.setOnClickListener(new View.OnClickListener() {
////        int i=0;
////        @Override
////        public void onClick(View v) {
////          recyclerViewHolder.mButton.setText(++i+"");
////        }
////      });
//    }
//    if(position==datas.size()+1&&recyclerViewHolder.viewType==IS_FOOTER){
//      //footer
//    }

  }

  @Override
  public int getItemCount() {
    return colorId.length;

  }

  @Override
  public int getItemViewType(int position) {
    if (position == 0) {
      return IS_HEADER;
    }
//    else if(position==datas.size()+1){
//      return IS_FOOTER;
//    }

    else {
      return IS_NORMAL;
    }
  }

  class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public int viewType;

    public ImageView imgView;
    public TextView txtView;
    public RecyclerViewHolder(View itemView,int viewType) {
      super(itemView);
      this.viewType = viewType;
      if(viewType==IS_HEADER){

        ViewPager viewPager = (ViewPager) itemView.findViewById(R.id.vpheader);
        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());

      }
//      if(viewType==IS_FOOTER){
//        //do some sthing
//      }
      if(viewType==IS_NORMAL){
        txtView = (TextView) itemView.findViewById(R.id.homeTextView);
        imgView = (ImageView) itemView.findViewById(R.id.homeCateView);

      }
    }
  }







  //######################################################################################viewpager  */
  private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
    private int oldPosition = 0;

    public void onPageSelected(int position) {
      currentItem = position;
//      tv_title.setText(titles[position]);
      dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
      dots.get(position).setBackgroundResource(R.drawable.dot_focused);
      oldPosition = position;
    }

    public void onPageScrollStateChanged(int arg0) {

    }

    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }
  }

  private class MyAdapter extends PagerAdapter {

    @Override
    public int getCount() {
      return imageResId.length;
    }

    @Override
    public Object instantiateItem(View arg0, int arg1) {
      ((ViewPager) arg0).addView(imageViews.get(arg1));
      return imageViews.get(arg1);
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
      ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
      return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
      return null;
    }


    @Override
    public void startUpdate(View arg0) {

    }

    @Override
    public void finishUpdate(View arg0) {

    }
  }


}