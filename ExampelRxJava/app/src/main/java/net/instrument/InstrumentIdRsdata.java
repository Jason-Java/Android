package net.instrument;

import  util.LogUtil;
import  util.StringUtil;
import net.Constant;
import net.HttpObservable;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailData;
import net.domain.Humiture;

import java.util.HashMap;

import io.reactivex.schedulers.Schedulers;

//获取温湿度
public class InstrumentIdRsdata extends UniteRequest {
    private InstrumentIdRsdata() {

    }

    /**
     * 获取指定温湿度记录仪的数据,若startTime或endTime 任一个为空则返回最新的数据
     * 若需要获取温湿度信息请传入请传入NetworkRequest<'Humiture'>对象
     * @param deviceId              key=DeviceID,StartTime,EndTime
     * @param INetworkResponse
     */
   private  void request(String deviceId, INetworkResponse INetworkResponse) {
        api.InstrumentIdRsdata(deviceId)
                .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<Humiture>>(showDialog) {
                    @Override
                    protected void success(DetailData<Humiture> data) {
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
                        if(INetworkResponse!=null)
                        {
                            INetworkResponse.success(data.getResponse());
                        }
                    }

                    @Override
                    protected boolean interceptError(int e, String message) {
                        return INetworkResponse.error(e, message);
                    }
                });
    }


    /**
     * 获取最新的温湿度
     * @param id 温湿度仪器ID
     * @param iNetworkResponse 若iNetworkResponse==null则不会返回数据
     */
    public void getNewHumiture(String id, INetworkResponse<Humiture> iNetworkResponse) {
        if(StringUtil.isEmpty(id))
        {
            LogUtil.e("未传递 温湿度仪器id");
            return;
        }
        request(id,iNetworkResponse);
    }

    @Deprecated
    public void getRangeHumitrue(String id, INetworkResponse INetworkResponse) {

    }
}
