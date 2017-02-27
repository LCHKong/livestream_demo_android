package cn.ucai.live.utils;

import android.app.Activity;
import android.content.Intent;


import cn.ucai.live.I;
import cn.ucai.live.R;
import cn.ucai.live.ui.activity.MainActivity;


/**
 * Created by LCH on 2017/1/10.
 */

public class MFGT {
    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void startActivity(Activity context, Class<?> clz) {
        context.startActivity(new Intent(context, clz));
        context.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public static void startActivity(Activity context, Intent intent) {
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }


    public static void gotoMainActivity(Activity activity) {
        startActivity(activity, new Intent(activity, MainActivity.class)
                .putExtra(I.BACK_MAIN_FORM_CHAT, true));
    }

}
