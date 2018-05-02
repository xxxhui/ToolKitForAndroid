package com.jacob.toolkit.toolkitforandroid.seekbar;

import android.util.Log;

import com.jacob.toolkit.toolkitforandroid.R;
import com.jacob.toolkit.toolkitforandroid.base.BaseActivity;
import com.jacob.toolkit.toolkitforandroid.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: Jacob
 * @date: 2018/4/23
 */
public class SeekBarActivity extends BaseActivity {

    private Unbinder unbinder;

    @BindView(R.id.bubble_seekbar) BubbleSeekBar bubbleSeekBar;

    @Override
    protected void initView() {
       bubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
           @Override
           public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
               Log.d(TAG, "onProgressChanged() called with: bubbleSeekBar = [" + bubbleSeekBar + "], progress = [" + progress + "], progressFloat = [" + progressFloat + "], fromUser = [" + fromUser + "]");
           }

           @Override
           public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

           }

           @Override
           public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

           }
       });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.acivity_seekbar);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }
}
