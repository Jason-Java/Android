package net.Reagent;


import util.LogUtil;
import util.StringUtil;
import net.Constant;
import net.HttpObservable;
import net.INetworkResponse;
import net.NetworkResponse;
import net.UniteRequest;
import net.domain.DetailData;
import net.domain.Reagent;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReagentScan extends UniteRequest {

    private ReagentScan() {

    }

   public  void request(String barCode,  INetworkResponse networkResponse) {

        api.rReagentScan(barCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<Reagent>>() {
                    @Override
                    protected void success(DetailData<Reagent> data) {
                        if (data.getResponse() == null) {
                            if(showDialog!=null)
                            {
                                showDialog.showErrorDialog("返回空数据");
                            }
                            else
                            {
                                LogUtil.e("返回空数据");
                            }
                            return;
                        }
                        if (networkResponse != null) {
                            networkResponse.success(data.getResponse());
                        }
                    }

                    @Override
                    protected boolean interceptError(int e, String message) {
                        return networkResponse.error(e,message);
                    }
                });
    }

    /**
     * 根据条码获取试剂信息  若需要试剂信息请传入NetworkResponse<'Reagent'>对象
     * @param barCode 不可为空 试剂条码
     * @param networkResponse 可为空 为空则返回试剂失败
     */
    public void getReagentByBarCode(String barCode, NetworkResponse<Reagent> networkResponse)
    {
        if (StringUtil.isEmpty(barCode)) {
            LogUtil.e("未传递 条码");
            return;
        }
        request(barCode, networkResponse);
    }
}
