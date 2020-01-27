package it.uniroma1.fillineditor.viewComponents;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import it.uniroma1.fillineditor.R;

/**
 * TODO: document your custom view class.
 */
public class CustomStaticTextView extends RelativeLayout implements DynamicDocViewComponents{
    private TextView textView;

    public CustomStaticTextView(Context context) {
        super(context);

        init(null, 0);
    }

    public CustomStaticTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs, 0);
    }

    public CustomStaticTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
    }

    @Override
    public int getViewType() {
        return STATIC_TEXT_ID;
    }

    @Override
    public void setContents(String s) {
        try {
            this.textView = (TextView)findViewById(R.id.text);
            this.textView.setText(s.substring(1));
        }
        catch (Exception e){
            System.out.println("!!!Not valid data" + e.toString());
        }
    }

    public TextView getTextView() {return textView;}

    public void setTextView(TextView textView) {this.textView = textView;}
}
