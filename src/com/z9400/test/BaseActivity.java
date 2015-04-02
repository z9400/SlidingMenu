
package com.z9400.test;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.z9400.test.activity.Activity1;
import com.z9400.test.activity.Activity2;
import com.z9400.test.activity.Activity3;
import com.z9400.test.activity.Activity4;
import com.z9400.test.activity.Activity5;
import com.z9400.wedget.slideingactivity.IntentUtils;
import com.z9400.wedget.slideingactivity.SlidingActivity;

public class BaseActivity extends SlidingActivity {
	
	private View view ;
	
    /** 手势监听1223 */
    // private GestureDetector mGestureDetector;      
	
    /** 是否�?��监听手势关闭功能 */
    private boolean mNeedBackGesture = false;
    // private BaseActivityHelper baseActivityHelper;
    private Dialog progressDialog;
    public static final int REQUEST_CODE = 1000;

    @Override
    public void onResume() {
        super.onResume();
        // baseActivityHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // baseActivityHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // baseActivityHelper.onDestroy();
    }

    public boolean isSupportSlide() {
        return true;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 无标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        initGestureDetector();
        initSlidingMenu();
        // baseActivityHelper = new BaseActivityHelper(this, isSupportSlide());
        // baseActivityHelper.onCreate();
    }

    private void initGestureDetector() {
        // if (mGestureDetector == null) {
        // mGestureDetector = new GestureDetector(getApplicationContext(),
        // new BackGestureListener(this));
        // }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mNeedBackGesture) {
            // return mGestureDetector.onTouchEvent(ev) ||
            // super.dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    /*
     * 设置是否进行手势监听
     */
    public void setNeedBackGesture(boolean mNeedBackGesture) {
        this.mNeedBackGesture = mNeedBackGesture;
    }

    /**
     * 显示dialog
     * 
     * @param msg 显示内容
     */
    public void showProgressDialog() {
        try {

            if (progressDialog == null) {
                progressDialog = DialogUtil.createLoadingDialog(this);

            }
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 隐藏dialog
     */
    public void dismissProgressDialog() {
        try {

            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更具类打�?citvity
     */
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null, 0);

    }

    public void openActivityForResult(Class<?> pClass, int requestCode) {
        openActivity(pClass, null, requestCode);
    }

    /**
     * 更具类打�?citvity,并携带参�?     */
    public void openActivity(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        if (requestCode == 0) {
            IntentUtils.startPreviewActivity(this, intent, 0);
            // startActivity(intent);
        } else {
            IntentUtils.startPreviewActivity(this, intent, requestCode);
            // startActivityForResult(intent, requestCode);
        }
        // actityAnim();
    }


    /**
     * 显示LongToast
     */
    public void showShortToast(String pMsg) {
        // ToastUtil.createCustomToast(this, pMsg, Toast.LENGTH_LONG).show();
        Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show();
    }
    
    
    protected void initSlidingMenu() {
	       view = getLayoutInflater().inflate(R.layout.activity_left, null) ;
	       Button pics = (Button)view.findViewById(R.id.pics) ;
	       Button video = (Button)view.findViewById(R.id.video) ;
	       Button ties = (Button)view.findViewById(R.id.ties) ;
	       Button tianqi = (Button)view.findViewById(R.id.tianqi) ;
	       Button more = (Button)view.findViewById(R.id.more) ;
	       pics.setOnClickListener(new MyOnclickListener());
	       video.setOnClickListener(new MyOnclickListener());
	       ties.setOnClickListener(new MyOnclickListener());
	       tianqi.setOnClickListener(new MyOnclickListener());
	       more.setOnClickListener(new MyOnclickListener());
	       SlidingMenuView.instance().initSlidingMenuView(this, view);
	        
	    }
	 
	 private class MyOnclickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
				
			Button b = (Button)v ;
			Toast.makeText(v.getContext(), b.getText(), Toast.LENGTH_SHORT).show();
			
			switch (v.getId()) {
			case R.id.pics:
				openActivity(Activity1.class);
				break;
			case R.id.video:
				openActivity(Activity2.class);
				break;
			case R.id.ties:
				openActivity(Activity3.class);
				break;
			case R.id.tianqi:
				openActivity(Activity4.class);
				break;
			case R.id.more:
				openActivity(Activity5.class);
				break;

			default:
				break;
			}
			
			if (SlidingMenuView.instance().slidingMenu.isMenuShowing()) {
	            SlidingMenuView.instance().slidingMenu.showContent();
	        }
		}
		 
	 }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

//    /**
//     * 显示ShortToast
//     */
//    public void showCustomToast(String pMsg) {
//        // ToastUtil.createCustomToast(this, pMsg, Toast.LENGTH_SHORT).show();
//        // Crouton.makeText(this, pMsg, Style.ALERT, R.id.toast_conten).show();
//        Crouton.makeText(this, pMsg, Style.ALERT, R.id.toast_conten).show();
//
//    }



    /**
     * 带动画效果的关闭
     */
    @Override
    public void finish() {
        super.finish();
        // baseActivityHelper.finish();
        actityAnim();
    }

    /**
     * 系统默认关闭
     */
    public void defaultFinish() {
        super.finish();
        // baseActivityHelper.finish();
    }

    /**
     * 系统默认关闭
     */
    public void defaultFinishNotActivityHelper() {
        super.finish();
    }

    public void actityAnim() {
        // overridePendingTransition(R.anim.slide_in_right,
        // R.anim.slide_right_out);
    }

    /**
     * 返回
     */
    public void doBack(View view) {
        onBackPressed();
    }

}
