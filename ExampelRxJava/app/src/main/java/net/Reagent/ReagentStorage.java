package net.Reagent;

import util.LogUtil;
import net.Constant;
import net.HttpObservable;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailData;
import net.domain.DomainReagentStored;
import net.domain.Reagent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReagentStorage extends UniteRequest {
    private ReagentStorage() {
    }

    /**
     * 获取可领用试剂  包括试剂所在的柜子,抽屉信息  需要获取可领用试剂请传入NetworkResponse<'DomainReagentStored'>
     *
     * @param map             可为空 为空的时候查询的是全部数据

     * @param networkResponse 可为空
     */
    private void request(HashMap<String,String> map,  INetworkResponse networkResponse) {
        api.reagentStored(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<DomainReagentStored>>(showDialog) {
                    @Override
                    protected void success(DetailData<DomainReagentStored> data) {
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
                        return networkResponse.error(e, message);
                    }
                });
    }


    /**
     * 获取可领用试剂    需要获取可领用试剂请传入NetworkResponse<'List<'Reagent'>'>
     *
     * @param networkResponse
     */
    public void getReceiveReagent(INetworkResponse<List<Reagent>> networkResponse) {
        HashMap<String, String> map = new HashMap<>();
        map = new HashMap<>();
        map.put(Constant.Page, "1");
        map.put(Constant.Rows, "100000");
        map.put(Constant.Status, "0902");
        request(map,  new INetworkResponse<DomainReagentStored>() {
            @Override
            public void success(DomainReagentStored value) {

                ArrayList<Reagent> reagents = new ArrayList<>();
                for (int i = 0; i < value.getInfos().size(); i++) {
                    reagents.add(value.getInfos().get(i).getReagent());
                }
                networkResponse.success(reagents);
            }

            @Override
            public boolean error(int e, String msg) {
                return networkResponse.error(e, msg);
            }
        });
    }

    /**
     * 获取需要流程的试剂
     *
     * @param networkResponse
     */
    public void getNeedProcessReagent( INetworkResponse<List<Reagent>> networkResponse) {
        HashMap<String, String> map = new HashMap<>();
        if (map == null) {
            map = new HashMap<>();
            map.put(Constant.Page, "1");
            map.put(Constant.Rows, "100000");
            map.put(Constant.Status, "0902");
        }
        request(null,new INetworkResponse<DomainReagentStored>() {
            @Override
            public void success(DomainReagentStored value) {
                ArrayList<Reagent> reagents = new ArrayList<>();
                for (int i = 0; i < value.getInfos().size(); i++) {
                    if(value.getInfos().get(i).getIssProcess())
                    {
                        reagents.add(value.getInfos().get(i).getReagent());
                    }
                }
                networkResponse.success(reagents);
            }

            @Override
            public boolean error(int e, String msg) {
                return networkResponse.error(e,msg);
            }
        });
    }
}
