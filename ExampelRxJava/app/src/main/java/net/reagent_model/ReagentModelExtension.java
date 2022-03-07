package net.reagent_model;

import util.LogUtil;
import util.StringUtil;

import net.Constant;
import net.HttpObservable;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailData;
import net.domain.Reagent;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReagentModelExtension extends UniteRequest {
    private ReagentModelExtension() {
    }

    /**
     * 根据CAS获取试剂的说明书信息,同时会返回试剂的一些基础信息 若需要请传入 NetWorkResponse<"Reagent">对象
     *
     * @param cas             不可我空 试剂的cas
     * @param networkResponse 可为空
     */
    private void request(String cas, INetworkResponse networkResponse) {

        api.reagentModelExtension( cas)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<Reagent>>(showDialog) {
                    @Override
                    protected void success(DetailData<Reagent> data) {
                        if (data.getResponse() == null) {
                            showDialog.showErrorDialog("返回空数据");
                            return;
                        }
                        if (networkResponse != null) {
                            networkResponse.success(data.getResponse());
                        }
                    }

                    @Override
                    protected boolean interceptError(int e, String message) {
                        return networkResponse.error(e, message);
                    }
                });
    }

    /**
     * 根据CAS 获取试剂的说明书信息,同时会返回试剂的一些基础信息 若需要请传入 NetWorkResponse<"Reagent">对象
     *
     * @param cas             不可为空
     * @param INetworkResponse 不可为空 为空将导致返回数据失败 请通过
     */
    public void getInstructionBook(String cas, INetworkResponse<Reagent> INetworkResponse) {
        if (StringUtil.isEmpty(cas)) {
            LogUtil.e("未 传递CAS");
            return;
        }
        request(cas,  INetworkResponse);
    }


    /**
     * 根据CAS获取试剂的说明书信息,只返回实际的说明书信息 请传入 NetWorkResponse<"HasMap<String,String">对象
     * 获取信息请通过Key进行获取--Constant.AQCC, Constant.FQCZ, Constant.FYCS, Constant.SGXY
     *
     * @param cas             不可为空
     * @param networkResponse 不可为空 为空将导致返回数据失败 请通过
     */
    public void getOnlyInstructionBook(String cas,INetworkResponse<HashMap<String, String>> networkResponse) {
        if (StringUtil.isEmpty(cas)) {
            LogUtil.e("未 传递CAS");
            return;
        }
        request(cas, new INetworkResponse<Reagent>() {
            @Override
            public void success(Reagent value) {
                HashMap<String, String> map = new HashMap<>();
                map.put(Constant.AQCC, value.getAQCC());
                map.put(Constant.FQCZ, value.getFQCZ());
                map.put(Constant.FYCS, value.getFYCS());
                map.put(Constant.SGXY, value.getSGXY());
                networkResponse.success(map);
            }
            @Override
            public boolean error(int e, String msg) {
                return networkResponse.error(e, msg);
            }
        });
    }
}
