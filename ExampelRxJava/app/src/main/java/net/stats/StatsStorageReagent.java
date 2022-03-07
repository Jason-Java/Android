package net.stats;

import  util.LogUtil;
import  util.StringUtil;
import net.HttpObservable;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailList;
import net.domain.Reagent;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StatsStorageReagent extends UniteRequest
{
    /**
     * 即将过期
     */
    public static String TIME_EXPIRED;
    /**
     * 正常
     */
    public static String TIME_NORMAL;
    /**
     * 过期
     */
    public static String TIME_PAST;
    /**
     * 核销
     */
    public static String STORAGE_DESTROY;
//    /**
//     * 可用
//     */
//    public static String STORAGE_USABLE;
    /**
     * 已领用
     */
    public static String STORAGE_RECEIVE;
    /**
     * 库存量少
     */
    public static String STORAGE_JUST_LITTLE;

    static
    {
        //核销
        STORAGE_DESTROY = "0904";
//        //可用
//        STORAGE_USABLE = "0903";
        //已领用
        STORAGE_RECEIVE = "0903";
        //库存少
        STORAGE_JUST_LITTLE = "0801";

        TIME_EXPIRED = "0906";
        TIME_NORMAL = "0900";
        TIME_PAST = "0907";
    }

    private StatsStorageReagent()
    {
    }

    /**
     * 获取库存统计信息
     *
     * @param map             可为空
     * @param networkResponse 可为空
     */
   private void request(HashMap<String, String> map,  INetworkResponse networkResponse)
    {
        api.statsStorageReagent( map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailList<Reagent>>(showDialog)
                {
                    @Override
                    protected void success(DetailList<Reagent> data)
                    {
                        if (networkResponse != null)
                        {
                            if (data.getResponse() == null)
                            {
                                showDialog.showErrorDialog("返回空数据");
                                return;
                            }

                            networkResponse.success(data.getResponse());
                        } else
                        {
                            LogUtil.i("networkResponse=null 返回数据失败");
                        }
                    }

                    @Override
                    protected boolean interceptError(int e, String message) {
                        return networkResponse.error(e, message);
                    }
                });
    }

    /**
     * 获取全部库存统计 若需要库存统计信息 请传入NetworkRequest<'List<'Reagent'>'>对象
     *
     * @param INetworkResponse 可为空 当networkResponse==null时将不返回数据
     */
    public void getAllStorageStats( INetworkResponse<List<Reagent>> INetworkResponse)
    {
        request(new HashMap<>(),  INetworkResponse);
    }

    /**
     * 根据柜子Id获取柜子内的库存统计信息 若需要获得统计信息 请传入 NetworkRequest<'List<'Reagent'>'>对象
     *
     * @param boxId           不可为空
     * @param INetworkResponse
     */
    public void getAllStorageStatsByBoxID(String boxId,  INetworkResponse<List<Reagent>> INetworkResponse)
    {
        if (StringUtil.isEmpty(boxId))
        {
            LogUtil.e("请传入boxId");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("BoxId", boxId);
        request(map,  INetworkResponse);
    }

    /**
     * 根据状态获取库存统计信息 若需要获得统计信息 请传入 NetworkRequest<'List<'Reagent'>'>对象
     *
     * @param status          不可为空     请看静态变量 如StatsStorageReagent.STORAGE_DESTROY
     * @param INetworkResponse
     */
    public void getAllStorageStatsByStatus(String status,  INetworkResponse<List<Reagent>> INetworkResponse)
    {
        if (StringUtil.isEmpty(status))
        {
            LogUtil.e("试剂状态不可为空");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        if (StringUtil.equals(status, "0906") ||
                StringUtil.equals(status, "0907") ||
                StringUtil.equals(status, "0900"))
        {
            map.put("ExpiryTime", status);
        }
        if (StringUtil.equals(status, "0903") ||
                StringUtil.equals(status, "0904") ||
                StringUtil.equals(status, "0801"))
        {
            map.put("Status", status);
        }
        request(map,  INetworkResponse);
    }


    /**
     * 自定义获取库存统计信息 若需要获得统计信息 请传入 NetworkRequest<'List<'Reagent'>'>对象
     *
     * @param map            不可为空 具体字段清查看 http://192.168.1.128:9091/index.html
     * @param networkRespons
     */
    public void getStorageStatsByCustom(HashMap<String, String> map, INetworkResponse<List<Reagent>> networkRespons)
    {
        if (map == null || map.isEmpty())
        {
            LogUtil.e("HashMap 不可为null或空");
            return;
        }

        for (String key : map.keySet())
        {
            if (map.get(key) == null)
            {
                LogUtil.e("HashMap 值不可为null");
                return;
            }
        }
        request(map,  networkRespons);
    }
}
