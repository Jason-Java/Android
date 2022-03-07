package net.login;


import net.HttpObservable;
import net.INetworkResponse;
import net.NetConfig;
import net.UniteRequest;
import net.domain.Login;

import  util.LogUtil;
import  util.StringUtil;


import java.util.HashMap;

import io.reactivex.schedulers.Schedulers;

public class LoginRefreshToken extends UniteRequest {

    private LoginRefreshToken() {

    }

    private void request(String token, INetworkResponse INetworkResponse) {
        api.loginRefreshToken(token)
                .subscribeOn(Schedulers.io())
                .subscribe(new HttpObservable<Login>(showDialog) {
                    @Override
                    protected void success(Login data) {
                        if (StringUtil.isEmpty(data.getToken())) {
                            if (showDialog != null) {
                                showDialog.showErrorDialog("返回空数据");
                            } else {
                                LogUtil.e("返回空数据");
                            }
                            return;
                        }

                        if (INetworkResponse != null) {
                            INetworkResponse.success(data.getToken());
                        }
                        NetConfig.currentToken = data.getToken();
                        LogUtil.i("新 token " + data.getToken());
                    }

                    @Override
                    protected boolean interceptError(int e, String message) {
                        if (INetworkResponse != null) {
                            return INetworkResponse.error(e, message);
                        }
                        return false;
                    }
                });
    }

    /**
     * 刷新Token自动保存到  NetConfig.currentToken 若需要获取token数据请传递INetworkResponse<'String'>对象
     * @param token 不可为空
     * @param iNetworkResponse 可为空
     */
    public void refreshToken(String token, INetworkResponse<String> iNetworkResponse) {
        if (StringUtil.isEmpty(token)) {
            LogUtil.e("未传递 token 刷新失败");
            return;
        }

        request(token, iNetworkResponse);
    }

    /**
     * 刷新Token自动保存到 NetConfig.currentToken
     * @param token 不可为空
     */
    public void refreshToken(String token) {
        refreshToken(token, null);
    }

}
