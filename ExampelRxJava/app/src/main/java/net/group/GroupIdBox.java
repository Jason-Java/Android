package net.group;


import util.LogUtil;
import util.StringUtil;
import net.Constant;
import net.HttpObservable;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.Box;
import net.domain.DetailList;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GroupIdBox extends UniteRequest
{
    private GroupIdBox()
    {

    }
    /**
     * 根据groupId 获取group下所有的柜子 若需要柜子信息 请传入NetworkRequest<'List<'Box'>'>对象
     *
     * @param groupID             不可为空 柜子的组ID
     * @param INetworkResponse 可为空
     */
    private  void request(String groupID,  INetworkResponse INetworkResponse)
    {
        api.groupIdBox(groupID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailList<Box>>(showDialog)
                {
                    @Override
                    protected void success(DetailList<Box> data)
                    {
                        if (data.getResponse() == null || data.getResponse().size() <= 0) {
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
                        if (INetworkResponse != null)
                        {
                            INetworkResponse.success(data.getResponse());
                        }
                    }

                    @Override
                    public boolean interceptError(int e, String message) {
                        return INetworkResponse.error(e,message);
                    }
                });
    }

    /**
     * 根据groupId 获取group下所有的柜子 若需要柜子信息 请传入NetworkRequest<'List<'Box'>'>对象
     *
     * @param INetworkResponse 可为空
     */
    public void getAllBox(String groupID, INetworkResponse<List<Box>> INetworkResponse)
    {
        if(StringUtil.isEmpty(groupID))
        {
            LogUtil.e("未传递 groupId");
            return;
        }
        request(groupID, INetworkResponse);
    }


    // todo 获取不同类型的柜子方法 后续扩展

}
