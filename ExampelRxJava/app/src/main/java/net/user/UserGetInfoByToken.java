package net.user;

import  util.LogUtil;
import  util.StringUtil;

import net.Constant;
import net.HttpObservable;
import net.IShowDialog;
import net.INetworkResponse;
import net.NetConfig;
import net.UniteRequest;
import net.domain.DetailData;
import net.domain.DomainGetInfoByToken;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserGetInfoByToken extends UniteRequest {
    private UserGetInfoByToken() {
    }

    /**
     * 根据token获取用户信息 包括用户信息 公司信息,角色信息 若需要获取其中的信息请传入 NetworkResponse<DomainGetInfoByToken>对象
     * 用户信息 存放在 NetConfig中
     *
     * @param token             不可为空 Key=Constant.Token
     * @param networkResponse 可为空
     */
   private   void request( String token, INetworkResponse networkResponse) {
        api.getInfoByToken(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<DomainGetInfoByToken>>(showDialog) {
                    @Override
                    protected void success(DetailData<DomainGetInfoByToken> data) {
                        if (data.getResponse() == null) {
                            if (showDialog != null) {
                                showDialog.showErrorDialog("返回空数据");
                            } else {
                                networkResponse.error(-1, "返回空数据");
                            }
                            return;
                        }
                        NetConfig.user = data.getResponse().getUser();
                        if (networkResponse != null) {
                            networkResponse.success(data.getResponse());
                        }
                    }

                    @Override
                    protected boolean interceptError(int e, String message) {
                        if (networkResponse != null) {
                            return networkResponse.error(e, message);
                        }
                        return false;
                    }
                });
    }


    /**
     * * 根据token获取用户信息 包括用户信息 公司信息,角色信息 若需要获取其中的信息请传入 NetworkResponse<DomainGetInfoByToken>对象
     * 用户信息 存放在 NetConfig中
     *
     * @param token            不可为空
     * @param INetworkResponse 可为空
     */
    public final void getInfoByToken(String token, INetworkResponse<DomainGetInfoByToken> INetworkResponse) {
        if (StringUtil.isEmpty(token)) {
            LogUtil.e("token不可为空");
            return;
        }

        request(token, INetworkResponse);
    }

    public final void getInfoByToken(String token) {
        getInfoByToken(token, null);
    }
}
