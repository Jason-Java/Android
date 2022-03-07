package net.box;

import util .LogUtil;
import util .StringUtil;

import net .HttpObservable;
import net .INetworkResponse;
import net .UniteRequest;
import net .domain.Box;
import net .domain.ContainerInfo;
import net .domain.DetailData;
import net .domain.DomainBoxId;

import net.UniteRequest;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BoxId extends UniteRequest
{
    private BoxId()
    {

    }
    /**
     * 根据Box的Id 获取柜子的详细信息 包括 柜子的基础信息(BoxInfo),柜子的类型信息(BoxType),柜子的模型信息(BoxModel),
     * 以及柜子的抽屉信息(Containers)  若需要柜子的详细信息 请传入 NetworkRequest<"DomainBoxId">对象
     *
     * @param boxId             不可为空  柜子Id
     * @param networkResponse 可为空
     */

    private void request(String boxId,  INetworkResponse networkResponse)
    {

//        if (!(map.containsKey(Constant.BoxId)))
//        {
//            LogUtil.e("未传入 boxId信息");
//            return;
//        }

        api.boxId(boxId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<DomainBoxId>>(showDialog)
                {
                    @Override
                    protected void success(DetailData<DomainBoxId> data)
                    {
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

                        if (networkResponse != null)
                        {
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
     * 根据Box的Id 获取柜子的基础信息(BoxInfo),
     * 若需要柜子的基础信息 请传入 NetworkRequest<"Box">对象
     *
     * @param boxId           不可为空  柜子Id
     * @param networkResponse 不可为空
     */
    public void getBoxInfo(String boxId, INetworkResponse<Box> networkResponse)
    {
        if (StringUtil.isEmpty(boxId))
        {
            LogUtil.e("未传递 boxId");
            return;
        }
        request(boxId,  new INetworkResponse<DomainBoxId>()
        {
            @Override
            public void success(DomainBoxId value)
            {
                networkResponse.success(value.getBoxInfo());
            }

            @Override
            public boolean error(int e, String msg) {
                return networkResponse.error(e, msg);
            }
        });
    }

    /**
     * 根据Box的Id 获取柜子的柜子的抽屉信息(Containers), 若需要
     * 若需要柜子的基础信息 请传入 NetworkRequest<"List<"ContainerInfo">">对象
     *
     * @param boxId           不可为空  柜子Id
     * @param networkResponse 不可为空
     */
    public void getContainers(String boxId,  INetworkResponse<List<ContainerInfo>> networkResponse)
    {
        if (StringUtil.isEmpty(boxId))
        {
            LogUtil.e("未传递 boxId");
            return;
        }
        request(boxId,  new INetworkResponse<DomainBoxId>()
        {
            @Override
            public void success(DomainBoxId value)
            {
                List<ContainerInfo> containers = value.getContainers();
                for (int i = 0; i < containers.size(); i++)
                {
                    if (containers.get(i).getCapacity() == 0)
                    {
                        containers.remove(i);
                    }
                }
                networkResponse.success(containers);
            }

            @Override
            public boolean error(int e, String msg) {
                return networkResponse.error(e, msg);
            }
        });
    }
}
