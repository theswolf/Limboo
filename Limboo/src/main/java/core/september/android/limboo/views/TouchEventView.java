package core.september.android.limboo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class TouchEventView  extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    public int width;
    public  int height;
    private Bitmap  mBitmap;
    private Canvas  mCanvas;
    private Path    mPath;
    private Paint   mBitmapPaint;
    Context context;
    private Paint circlePaint;
    private Path circlePath;
    private Paint       mPaint;
    private GestureDetectorCompat mDetector;


    public TouchEventView(Context c, Paint p) {
    super(c);
    context=c;
    mPaint = p;
    mPath = new Path();
    mBitmapPaint = new Paint(Paint.DITHER_FLAG);  
    circlePaint = new Paint();
    circlePath = new Path();
    circlePaint.setAntiAlias(true);
    circlePaint.setColor(Color.BLUE);
    circlePaint.setStyle(Paint.Style.STROKE);
    circlePaint.setStrokeJoin(Paint.Join.MITER);
    circlePaint.setStrokeWidth(4f); 
    mDetector = new GestureDetectorCompat(context, this);


    }
    
    public Bitmap getBitmap() {
    	return mBitmap;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);

    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    mCanvas = new Canvas(mBitmap);

    }
    @Override
    protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);

    canvas.drawPath( mPath,  mPaint);

    canvas.drawPath( circlePath,  circlePaint);
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
    mPath.reset();
    mPath.moveTo(x, y);
    mX = x;
    mY = y;
    }
    private void touch_move(float x, float y) {
    float dx = Math.abs(x - mX);
    float dy = Math.abs(y - mY);
    if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
         mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
        mX = x;
        mY = y;

        circlePath.reset();
        circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
    }
    }
    private void touch_up() {
    mPath.lineTo(mX, mY);
    circlePath.reset();
    // commit the path to our offscreen
    mCanvas.drawPath(mPath,  mPaint);
    // kill this so we don't double draw
    mPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();

    this.mDetector.onTouchEvent(event);
    
    switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            touch_start(x, y);
            break;
        case MotionEvent.ACTION_MOVE:
            touch_move(x, y);
            break;
        case MotionEvent.ACTION_UP:
            touch_up();
            break;
        default:
        	return false;
    }
    invalidate();
    return true;
    }

	@Override
	public boolean onDoubleTap(MotionEvent arg0) {
		mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}  
    
  
}
