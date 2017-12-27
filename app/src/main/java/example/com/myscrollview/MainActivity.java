package example.com.myscrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ScrollView mScrollView;
    private TextView tv_context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_context = (TextView) findViewById(R.id.tv_context );
        mScrollView = (ScrollView) findViewById(R.id.sv_scroll);
        tv_context.setText(getResources().getString(R.string.tv_context));
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    //手指离开
                    case MotionEvent.ACTION_MOVE:
                        /**
                         * （1）getScrollY() -- 滚动条滑动的距离
                         * （2）getMeasuredHeight() ScrollView中内容的总高度
                         *  (3) getHeight() 手机屏幕的高度
                         * */
                        // Scrollview顶部状态
                        if (mScrollView.getScrollY() <=0){
                            Log.e(TAG,"Scrollview顶部");
                        }

                        //底部状态
                        //子控件的总高度<= 手机屏幕的高度 + 滚动条的滚动距离
                        if (mScrollView.getChildAt(0).getMeasuredHeight()
                                <= mScrollView.getHeight()
                                + mScrollView.getScrollY()){
                            Log.e(TAG,"滑动到底部");
                            Log.e(TAG,"mScrollView.getChildAt(0).getMeasuredHeight()="
                                +mScrollView.getChildAt(0).getMeasuredHeight()
                                +"mScrollView.getHeight()="
                                +mScrollView.getHeight()
                                +"mScrollView.getScrollY()="
                                +mScrollView.getScrollY());
                            tv_context.append(getResources().getString(R.string.tv_context));
                        }
                        break;
                }

                return false;
            }
        });
    }
}
