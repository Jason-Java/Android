package net.login;

import util.LogUtil;
import util.StringUtil;

import net.HttpObservable2;
import net.NetConfig;
import net.RetrofitUtilGosn;
import net.Constant;
import net.HttpObservable;
import net.IApi;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailData;
import net.domain.DomainGetInfoByToken;
import net.domain.Login;

import java.util.HashMap;
import java.util.Observable;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class LoginJWTTToken extends UniteRequest {

    private LoginJWTTToken() {
    }

    /**
     * 根据用户名和密码获取token,若需要token信息 请传入 NetworkResponse 对象
     * <p>
     * token 存放在NetConfig中
     * @param name 不可为空 用户名
     * @param password  不可为空  密码
     * @param networkResponse 可为空
     */
    private  void request(String name,String password, INetworkResponse networkResponse) {
        api.loginJWTToken(name, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<Login>(showDialog) {
                    @Override
                    protected void success(Login data) {
                        if (StringUtil.isEmpty(data.getToken())) {
                            if(showDialog!=null)
                            {
                                showDialog.showErrorDialog("登录失败");
                            }
                            else{
                                networkResponse.error(-1,"返回空数据");
                                LogUtil.e("返回空数据");
                            }
                            return;
                        }
                        NetConfig.currentToken = data.getToken();
                        if (networkResponse != null) {
                            networkResponse.success(data.getToken());
                        }
                        LogUtil.i(data.getToken());
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
     * 根据用户名和密码获取token,若需要token信息 请传入 NetworkResponse<'String'>对象
     *
     * @param name     不可为空 用户名
     * @param password 不可为空 密码
     */
    public void login(String name, String password, INetworkResponse<String> networkResponse) {
        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(password)) {
            LogUtil.e("用户名或密码不能为空");
            return;
        }
        request(name, password,networkResponse);
    }

    /**
     * 根据用户名和密码进行登录并且获得用户信息
     * @param name  不可为空 用户名
     * @param password  不可为空 密码
     * @param networkResponse 可为空
     */
    public void loginAndUserInfo(String name, String password,INetworkResponse<DomainGetInfoByToken> networkResponse)
    {
        api.loginJWTToken(name, password)
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Login>() {
                    @Override
                    public boolean test(Login login) throws Exception {
                        if(StringUtil.isEmpty(login.getToken()))
                        {
                            return objectNonNull(null);
                        }
                       return true;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Login, ObservableSource<DetailData<DomainGetInfoByToken>>>() {
                    @Override
                    public ObservableSource<DetailData<DomainGetInfoByToken>> apply(Login login) throws Exception {
                        String token = login.getToken();
                        NetConfig.currentToken = token;
                        return api.getInfoByToken(token);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<DomainGetInfoByToken>>(showDialog) {
                    @Override
                    protected void success(DetailData<DomainGetInfoByToken> data) {
                        if (data == null) {
                            if (showDialog != null) {
                                showDialog.showErrorDialog("返回空数据");

                            } else {
                                LogUtil.e("返回空数据");
                            }
                            networkResponse.error(-1, "返回空数据");
                            return;
                        }
                        networkResponse.success(data.getResponse());
                    }

                    @Override
                    protected boolean interceptError(int e, String message) {
                        return false;
                    }
                });
    }
}
