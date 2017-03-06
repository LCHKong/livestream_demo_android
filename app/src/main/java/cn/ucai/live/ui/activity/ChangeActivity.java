package cn.ucai.live.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.live.R;
import cn.ucai.live.data.NetDao;
import cn.ucai.live.data.model.Result;
import cn.ucai.live.data.model.Wallet;
import cn.ucai.live.utils.CommonUtils;
import cn.ucai.live.utils.MFGT;
import cn.ucai.live.utils.OnCompleteListener;
import cn.ucai.live.utils.PreferenceManager;
import cn.ucai.live.utils.ResultUtils;

public class ChangeActivity extends BaseActivity {

    @BindView(R.id.tv_change_balance)
    TextView mTvChangeBalance;
    @BindView(R.id.target_layout)
    LinearLayout mTargetLayout;
    View loadingview;
    int change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
        loadingview = LayoutInflater.from(ChangeActivity.this).inflate(R.layout.rp_loading, mTargetLayout, false);
        mTargetLayout.addView(loadingview);
        setChange();
        initData();
    }

    private void initData() {
        NetDao.loadChange(ChangeActivity.this, EMClient.getInstance().getCurrentUser(), new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                boolean success = false;
                if (s != null) {
                    Result result = ResultUtils.getResultFromJson(s, Wallet.class);
                    if (result != null && result.isRetMsg()) {
                        success = true;
                        Wallet wallet = (Wallet) result.getRetData();
                        PreferenceManager.getInstance().setCurrentUserChange(wallet.getBalance());
                        change = wallet.getBalance();
                        setChange();
                    }
                }
                if (!success) {
                    PreferenceManager.getInstance().setCurrentUserChange(0);
                }
                loadingview.setVisibility(View.GONE);
            }

            @Override
            public void onError(String error) {
                loadingview.setVisibility(View.GONE);
                CommonUtils.showShortToast(error);
            }
        });

    }

    private void setChange() {
        change = PreferenceManager.getInstance().getCurrentUserChange();
        mTvChangeBalance.setText("￥" + Float.valueOf(String.valueOf(change)));

    }

    @OnClick(R.id.ivBack)
    public void onClick() {
        MFGT.finish(ChangeActivity.this);
    }
}
