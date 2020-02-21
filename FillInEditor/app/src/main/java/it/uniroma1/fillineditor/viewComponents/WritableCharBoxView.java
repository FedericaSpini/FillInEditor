package it.uniroma1.fillineditor.viewComponents;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import it.uniroma1.fillineditor.data.DeviceData;
import it.uniroma1.fillineditor.data.ItemData;
import it.uniroma1.fillineditor.data.SessionData;
import it.uniroma1.fillineditor.data.TimedComponentFloatPoint;
import it.uniroma1.fillineditor.util.Chronometer;

/**
 * TODO: document your custom view class.
 */
public class WritableCharBoxView extends View {

    //acquisition
    private int component_count;

    private ItemData itemData;
    private SessionData sessionData;
    private DeviceData deviceData;

    public static final float TOUCH_TOLERANCE = 0;

    //appearance
    private Activity activity;

    public static final float RADIUS_CURSOR = 10;
    public static final float RADIUS_UP_DOWN = 5;
    public static final float RADIUS_MOVE = 2;
    public static final float RADIUS_SAMPLED = 15;
    public static final Path.Direction CIRCLE_DIRECTION = Path.Direction.CW;


    //private stuff
    private final Paint privateBitmapPaint;
    public Bitmap privateBitmap;
    private Canvas privateCanvas;

    private final Path permanentLinePath;

    private final Path linePath;
    private final Paint linePaint;

    private final Path cursorPath;
    private Paint cursorPaint;

    private final Path sampledCirclePath;
    private final Paint sampledCirclePaint;
    private final Path touchMoveCirclePath;
    private final Paint sampleMovePaint;
    private final Path touchDownCirclePath;
    private Paint sampleDownPaint;
    private Path touchUpCirclePath;
    private Paint sampleUpPaint;

    public WritableCharBoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        
        privateBitmapPaint = new Paint(Paint.DITHER_FLAG);
        // LinePath must be resetted each time for efficiency
        permanentLinePath = new Path();

        linePath = new Path();
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setDither(true);
        linePaint.setColor(Color.DKGRAY);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeJoin(Paint.Join.ROUND);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setStrokeWidth(2);

        sampledCirclePath = new Path();
        sampledCirclePaint = new Paint();
        sampledCirclePaint.setAntiAlias(true);
        sampledCirclePaint.setDither(true);
        sampledCirclePaint.setColor(Color.CYAN);
        sampledCirclePaint.setAlpha(50);
        sampledCirclePaint.setStyle(Paint.Style.STROKE);
        sampledCirclePaint.setStrokeJoin(Paint.Join.ROUND);
        sampledCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        sampledCirclePaint.setStrokeWidth(1);

        touchMoveCirclePath = new Path();
        sampleMovePaint = new Paint();
        sampleMovePaint.setAntiAlias(true);
        sampleMovePaint.setColor(Color.BLACK);
        sampleMovePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        sampleMovePaint.setStrokeJoin(Paint.Join.MITER);
        sampleMovePaint.setStrokeWidth(1f);

        touchDownCirclePath = new Path();
        sampleDownPaint = new Paint();
        sampleDownPaint.setAntiAlias(true);
        sampleDownPaint.setColor(Color.GREEN);
        sampleDownPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        sampleDownPaint.setStrokeJoin(Paint.Join.MITER);
        sampleDownPaint.setStrokeWidth(1f);

        touchUpCirclePath = new Path();
        sampleUpPaint = new Paint();
        sampleUpPaint.setAntiAlias(true);
        sampleUpPaint.setColor(Color.RED);
        sampleUpPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        sampleUpPaint.setStrokeJoin(Paint.Join.MITER);
        sampleUpPaint.setStrokeWidth(1f);

        cursorPath = new Path();
        cursorPaint = new Paint();
        cursorPaint.setAntiAlias(true);
        cursorPaint.setColor(Color.GRAY);
        cursorPaint.setStyle(Paint.Style.STROKE);
        cursorPaint.setStrokeJoin(Paint.Join.MITER);
        cursorPaint.setStrokeWidth(1f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        privateBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        privateCanvas = new Canvas(privateBitmap);
        privateCanvas.translate(-getLeft(), -getTop());
        initScreen();
    }

    private void initScreen()
    {
        privateBitmap.eraseColor(0xEEEEEE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("DRAW");

        super.onDraw(canvas);
        canvas.drawBitmap(privateBitmap, 0, 0, privateBitmapPaint);

        canvas.translate(-getLeft(), -getTop());
        canvas.drawPath(linePath, linePaint);
        canvas.drawPath(touchMoveCirclePath, sampleMovePaint);
        canvas.drawPath(touchDownCirclePath, sampleDownPaint);
        canvas.drawPath(touchUpCirclePath, sampleUpPaint);
        canvas.drawPath(sampledCirclePath, sampledCirclePaint);
        canvas.drawPath(cursorPath, cursorPaint);
    }

    private Chronometer chrono;
    private float mX, mY;
    private void touch_start(float x, float relative_x, float y)
    {
        long time;
        if (chrono == null) {
            chrono = new Chronometer();
            time = 0;
        } else {
            time = chrono.getElapsedTime();
        }

        linePath.rewind();
        linePath.moveTo(x, y);
        permanentLinePath.moveTo(x, y);

//        saveMoveEvent(time, component_count, x, y);
//        saveDownEvent(time, component_count, x, y);
        saveMoveEvent(time, component_count, x, relative_x, y);
        saveDownEvent(time, component_count, x, relative_x, y);

        mX = x;
        mY = y;

        getParent().requestDisallowInterceptTouchEvent(true);
    }

    private void touch_move(float x, float relative_x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        float xavg;
        float yavg;
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            xavg = (x + mX) / 2;
            yavg = (y + mY) / 2;

            linePath.quadTo(mX, mY, xavg, yavg);
            permanentLinePath.quadTo(mX, mY, xavg, yavg);

            mX = x;
            mY = y;

            saveMoveEvent(chrono.getElapsedTime(), component_count, x, relative_x,  y);

            cursorPath.rewind();
            cursorPath.addCircle(x, y, RADIUS_CURSOR, CIRCLE_DIRECTION);
        }
    }

    private void touch_up() {
        linePath.lineTo(mX, mY);
        permanentLinePath.lineTo(mX, mY);

        saveUpEvent(chrono.getElapsedTime(), component_count, mX, mY);

        cursorPath.rewind();
        //TODO capisci quale è il senso delle prossime 8 righe
        // commit the path to our offscreen
        privateCanvas.drawPath(linePath, linePaint);
        privateCanvas.drawPath(touchMoveCirclePath, sampleMovePaint);
        privateCanvas.drawPath(touchDownCirclePath, sampleDownPaint);
        privateCanvas.drawPath(touchUpCirclePath, sampleUpPaint);
        // kill this so we don't double draw
//        linePath.rewind();
//        touchMoveCirclePath.rewind();
//        touchDownCirclePath.rewind();
//        touchUpCirclePath.rewind();

        component_count++;
        getParent().requestDisallowInterceptTouchEvent(false);
    }

    private void saveDownEvent(long time, int component, float x,  float relative_x, float y) {
        System.out.println("DOWN");

//        itemData.addTouchDownPoint(new TimedComponentFloatPoint(time, component, x, y));

        touchDownCirclePath.addCircle(x, y, RADIUS_UP_DOWN, CIRCLE_DIRECTION);
        invalidate();

//        setTimerText(time + "");
    }

    private void saveUpEvent(long time,int component, float x, float y) {
        System.out.println("UP");

        itemData.addTouchUpPoint(new TimedComponentFloatPoint(time, component, x, y));
        touchUpCirclePath.addCircle(mX, mY, RADIUS_UP_DOWN, CIRCLE_DIRECTION);
        invalidate();

//        setTimerText(time + "");
    }

    private void saveMoveEvent(long time,int component, float x,  float relative_x, float y) {
        itemData.addMovementPoint(new TimedComponentFloatPoint(time, component, x, y));
        System.out.println("MOVE");


        touchMoveCirclePath.addCircle(x, y, RADIUS_MOVE, CIRCLE_DIRECTION);
        privateCanvas.drawPath(touchMoveCirclePath, sampleMovePaint);
        invalidate();
//        setTimerText(time + "");
    }

    boolean is_only_down = false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int[] boxLocation = new int[2];
        getLocationInWindow(boxLocation);

        float x = event.getRawX();
        float y = event.getRawY()- boxLocation[1];
        float relative_x = x - boxLocation[0];
        float relative_y = y;
//        System.out.println(String.format("\n Le x sono: %f, %f \n le y sono %f, %f", x, relative_x, y, relative_y));
        System.out.println(boxLocation[0] + ", " + boxLocation[1] + "\n");
        if (relative_x>=0 && relative_x<=getWidth() && relative_y>=0 && relative_y<=getHeight()+ (RADIUS_CURSOR/2)){
            System.out.println(String.format("\n Le x sono: %f, %f \n le y sono %f, %f", x, relative_x, y, relative_y));
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    is_only_down = true;
                    touch_start(x, relative_x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    is_only_down = false;
                    touch_move(x, relative_x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    if (is_only_down) {
                        is_only_down = false;
                        touch_move((float) (x + 0.01), (float) (relative_x + 0.01), (float) (y + 0.01));
                        invalidate();
                    }
                    touch_up();
                    invalidate();
                    break;
            }
        }
        else {
            touch_up();
        }
        return true;
    }

//    private void sampleFuctionLine() {
//        itemData.setSampledPoints(extractSampling());
//    }
//
//    public List<FloatPoint> extractSampling()
//    {
//        List<FloatPoint> path_points = new ArrayList<>();
//
//        PathMeasure pm = new PathMeasure(permanentLinePath, false);
//        float[] coordinates = new float[2];
//
//        int connected_component = 0;
//        while (pm.nextContour()) {
//            for (float i = 0; i <= pm.getLength(); i = i + SAMPLING_RATE) {
//                pm.getPosTan(i, coordinates, null);
//                path_points.add(new ComponentFloatPoint(connected_component, coordinates[0], coordinates[1]));
//            }
//
//            connected_component +=1 ;
//        }
//        return path_points;
//    }
//    public void drawExtractSampling(List<FloatPoint> path_points)
//    {
//        sampledCirclePath.rewind();
//        for (FloatPoint p : path_points) {
//            sampledCirclePath.addCircle(p.x, p.y, RADIUS_SAMPLED, CIRCLE_DIRECTION);
//        }
//        invalidate();
//    }

    public void restart() {
        itemData = new ItemData(sessionData, itemData.item_index);
        component_count = 0;

//        setTimerText(getResources().getString(R.string.time));
        chrono = null;

        resetPath();
    }

    public void resetPath()
    {
        cursorPath.reset();

        linePath.reset();
        permanentLinePath.reset();

        touchDownCirclePath.reset();
        touchMoveCirclePath.reset();
        touchUpCirclePath.reset();

        sampledCirclePath.reset();

        initScreen();
    }

    public ItemData getItemData() {
//        sampleFuctionLine();

        return itemData;
    }

    public void setItemData(ItemData data) {
        itemData = data;
        sessionData = data.session_data;
//        deviceData = sessionData.device_data;
    }

    public void finish()
    {
        if (privateCanvas != null) {
            privateCanvas.setBitmap(null);
            privateCanvas = null;
        }

        if (privateBitmap != null) {
            privateBitmap.recycle();
            privateBitmap = null;
        }
    }


    public Activity getActivity() {return activity;}

    public void setActivity(Activity activity) {this.activity = activity;}



}
