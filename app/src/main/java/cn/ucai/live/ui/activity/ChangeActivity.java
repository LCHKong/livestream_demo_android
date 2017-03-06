package cn.ucai.live.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.live.R;

public class ChangeActivity extends BaseActivity {

    @BindView(R.id.tv_change_balance)
    TextView mTvChangeBalance;
    @BindView(R.id.target_layout)
    LinearLayout mTargetLayout;
    View loadingview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
        loadingview = LayoutInflater.from(ChangeActivity.this).inflate(R.layout.rp_loading, mTargetLayout, false);
        mTargetLayout.addView(loadingview);
    }
}
