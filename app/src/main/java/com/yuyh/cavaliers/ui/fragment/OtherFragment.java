package com.yuyh.cavaliers.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuyh.cavaliers.R;
import com.yuyh.cavaliers.base.BaseLazyFragment;
import com.yuyh.cavaliers.http.utils.UserStorage;
import com.yuyh.cavaliers.ui.LoginActivity;
import com.yuyh.cavaliers.ui.PlayerListActivity;
import com.yuyh.cavaliers.ui.TeamsListActivity;
import com.yuyh.cavaliers.ui.ThreadDetailActivity;
import com.yuyh.cavaliers.utils.SettingPrefUtils;
import com.yuyh.library.AppUtils;
import com.yuyh.library.utils.data.ACache;
import com.yuyh.library.utils.data.PrefsUtils;
import com.yuyh.library.utils.toast.ToastUtils;
import com.yuyh.library.view.image.CircleImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author yuyh.
 * @date 16/6/5.
 */
public class OtherFragment extends BaseLazyFragment {

    private static final int REQ_LOGIN = 1;

    @InjectView(R.id.rlLogin)
    RelativeLayout rlLogin;
    @InjectView(R.id.rlPlayer)
    RelativeLayout rlPlayer;
    @InjectView(R.id.rlTeam)
    RelativeLayout rlTeam;
    @InjectView(R.id.rlTeamSchedule)
    RelativeLayout rlTeamSchedule;
    @InjectView(R.id.rlNBACal)
    RelativeLayout rlNBACal;
    @InjectView(R.id.rlClearCache)
    RelativeLayout rlClearCache;
    @InjectView(R.id.rlFeedback)
    RelativeLayout rlFeedback;
    @InjectView(R.id.rlAbout)
    RelativeLayout rlAbout;

    @InjectView(R.id.tvUserName)
    TextView tvUserName;
    @InjectView(R.id.ivUserHead)
    CircleImageView ivHead;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_other);
        ButterKnife.inject(this, getContentView());
        initData();
    }

    private void initData() {
        String nickname = SettingPrefUtils.getNickname();
        if (!TextUtils.isEmpty(nickname)) {
            tvUserName.setText(nickname);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mActivity.invalidateOptionsMenu();
        }
    }

    @OnClick(R.id.rlLogin)
    public void login() {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        startActivityForResult(intent, REQ_LOGIN);
    }

    @OnClick(R.id.rlClearCache)
    public void clearCache() {
        PrefsUtils prefs = new PrefsUtils();
        prefs.clearAll();
        ACache cache = ACache.get(AppUtils.getAppContext());
        cache.clear();
        ToastUtils.showSingleLongToast("缓存清理成功");
    }

    @OnClick(R.id.rlTeam)
    public void allTeams() {
        Intent intent = new Intent(mActivity, TeamsListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rlPlayer)
    public void allPlayers() {
        Intent intent = new Intent(mActivity, PlayerListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rlTeamSchedule)
    public void teamSchedule() {
        Intent intent = new Intent(mActivity, ThreadDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQ_LOGIN:
                if (resultCode == Activity.RESULT_OK) {
                    tvUserName.setText(UserStorage.getInstance().getUser().getNickName());
                }
                break;
            default:
                break;
        }
    }
}