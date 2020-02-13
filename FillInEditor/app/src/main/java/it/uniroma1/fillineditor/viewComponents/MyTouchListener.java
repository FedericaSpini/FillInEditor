package it.uniroma1.fillineditor.viewComponents;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;


public class MyTouchListener implements RecyclerView.OnItemTouchListener {


    /*Change these as per your need*/
//    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
//    private static final int SWIPE_MAX_OFF_PATH = 250;

    private OnTouchActionListener mOnTouchActionListener;
    private GestureDetectorCompat mGestureDetector;

    public static interface OnTouchActionListener {
//        public void onLeftSwipe(View view, int position);
//        public void onRightSwipe(View view, int position);
        public void onClick(View view, int position);
        public void onFling(View view, int position);
        public void onScroll(View view, int position);

    }

    public MyTouchListener(Context context, final RecyclerView recyclerView,
                           OnTouchActionListener onTouchActionListener){

        this.mOnTouchActionListener = onTouchActionListener;
        this.mGestureDetector = new GestureDetectorCompat(context,new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                // Find the  item view that was swiped based on the coordinates
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                int childPosition = recyclerView.getChildLayoutPosition(child);
                mOnTouchActionListener.onClick(child, childPosition);
                return false;
            }
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                    float velocityX, float velocityY){
                try {
                    View child = recyclerView.findChildViewUnder(e1.getX(), e1.getY());
                    int childPosition = recyclerView.getChildLayoutPosition(child);
                    if (childPosition==-1){
                        System.out.println("SCROOOOOOOOOOOOOOOOOOOOOLL");
                        return true;
                    }
//                    mOnTouchActionListener.onScroll(child, childPosition);

                    if (Math.abs(velocityX)>SWIPE_THRESHOLD_VELOCITY){
                        mOnTouchActionListener.onScroll(child, childPosition);
                    }
                } catch (Exception e) {
                }
                return false;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2,
                                   float velocityX, float velocityY) {

                try {
//                    if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
//                        return false;
//                    }

                    // Find the item view that was swiped based on the coordinates
                    View child = recyclerView.findChildViewUnder(e1.getX(), e1.getY());
                    int childPosition = recyclerView.getChildPosition(child);
                    if (Math.abs(velocityX)>SWIPE_THRESHOLD_VELOCITY){
                        mOnTouchActionListener.onFling(child, childPosition);
                    }

                    // right to left swipe
//                    if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//
//
//                        if (mOnTouchActionListener != null && child != null) {
//                            mOnTouchActionListener.onLeftSwipe(child, childPosition);
//                        }
//
//                    } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                        if (mOnTouchActionListener != null && child != null) {
//                            mOnTouchActionListener.onRightSwipe(child, childPosition);
//                        }
//                    }
                } catch (Exception e) {
                    // nothing
                }

                return false;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        // do nothing
    }
}