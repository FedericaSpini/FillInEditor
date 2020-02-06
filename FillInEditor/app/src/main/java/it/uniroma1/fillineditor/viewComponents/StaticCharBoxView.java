package it.uniroma1.fillineditor.viewComponents;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
@Deprecated
public class StaticCharBoxView extends RelativeLayout implements DynamicDocViewComponents {

    private TextView textView;


    public StaticCharBoxView(Context context) {
        super(context);
    }

    public StaticCharBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StaticCharBoxView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public int getViewType() {
        return 0;
    }

    public TextView getTextView() {return textView;}

    public void setTextView(TextView textView) {this.textView = textView;}

    public void setText(String s){
        this.textView.setText(s);
    }

    @Override
    public void setContents(String s) {
    }
}
