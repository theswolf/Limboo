package core.september.android.limboo.views;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DrawingFragment extends Fragment {

	private Paint mPaint;
	private TouchEventView mView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		TouchEventView tv = new TouchEventView(this);
//		setContentView(tv);
		//addContentView(tv, tv.params);
		
	}
	
	public void setColor(int color) {
		if(mPaint != null)
		{
			mPaint.setColor(color);
		}
			
		
		//
	}
	
		public Bitmap getBitmap() {
			Bitmap mBitMap =  mView.getBitmap();
			return mBitMap;
		}
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 
		 	mPaint = new Paint();
	        View v = new TouchEventView(getActivity(),mPaint);
	        mView = (TouchEventView) v;
	        
	        //dv = new DrawingView(this);
	        //setContentView(dv);
	        
	        mPaint.setAntiAlias(true);
	        mPaint.setDither(true);
	        mPaint.setColor(Color.parseColor("#822111"));
	        mPaint.setStyle(Paint.Style.STROKE);
	        mPaint.setStrokeJoin(Paint.Join.ROUND);
	        mPaint.setStrokeCap(Paint.Cap.ROUND);
	        mPaint.setStrokeWidth(12);  
	      
	        
	        return v;
	    }

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		getActivity().finish();
	}

}
