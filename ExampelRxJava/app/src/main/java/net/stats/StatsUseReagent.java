package net.stats;

import  util.LogUtil;
import net.Constant;
import net.HttpObservable;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailList;
import net.domain.Reagent;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StatsUseReagent extends UniteRequest
{

    private StatsUseReagent() {}

    private  void request(HashMap<String, String> map,  INetworkResponse networkResponse)
    {
        api.statsUseReagent(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailList<Reagent>>(showDialog) {
                    @Override
                    protected void success(DetailList<Reagent> data) {
                        if (data.getResponse() == null || data.getResponse().size() <= 0) {
                            showDialog.showErrorDialog("返回空数据");
                            return;
                        }
                        if (networkResponse != null) {
                            networkResponse.success(data.getResponse());
                            return;
                        }
                        LogUtil.e("networkResponse==null");

                    }

                    @Override
                    protected boolean interceptError(int e, String message) {
                        return networkResponse.error(e, message);
                    }
                });
    }

    /**
     * 获取所有的领用记录 若需要获取领用记录请传入NetworkRequest<'List<'Reagent'>'>对象
     *
     * @param INetworkResponse networkResponse==null将不会返回数据
     */
    public void getAllUserReagent(INetworkResponse<List<Reagent>> INetworkResponse)
    {
        HashMap<String, String> map = new HashMap<>();
        map.put(Constant.Rows, "100000");
        map.put(Constant.Page, "1");
        request(map,  INetworkResponse);
    }
}
