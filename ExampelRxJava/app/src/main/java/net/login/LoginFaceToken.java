package net.login;

import util.LogUtil;
import util.StringUtil;
import net.NetConfig;
import net.HttpObservable;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.Login;

import java.util.HashMap;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginFaceToken extends UniteRequest {

   private LoginFaceToken() {

   }

   private void request(String id,INetworkResponse networkResponse)
   {
      api.loginFaceToken(id)
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
               networkResponse.error(e, message);
            }
            return false;
         }
      });
   }

   /**
    * 通过刷脸功能进行登录 如果需要获取登录的token 请传入INetworkResponse<'String'>对象
    * @param id 不可为空
    * @param networkResponse 可为空 为空时将导致返回数据失败
    */
   public void faceLogin(String id,INetworkResponse<String> networkResponse)
   {
      if(StringUtil.isEmpty(id))
      {
         LogUtil.e("刷脸登录  未传入用户id");
         return;
      }
      request(id, networkResponse);
   }

   /**
    * 通过刷脸功能进行登录
    * @param id 不可为空
    */
   public void faceLogin(String id)
   {
      faceLogin(id, null);
   }
}
