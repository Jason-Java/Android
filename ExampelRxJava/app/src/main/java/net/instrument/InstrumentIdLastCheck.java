package net.instrument;

import  util.LogUtil;
import net.Constant;
import net.HttpObservable;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailData;
import net.domain.Instrument;
import net.domain.InstrumentCheck;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InstrumentIdLastCheck extends UniteRequest {
    private InstrumentIdLastCheck() {

    }

    /**
     * 根据仪器ID获取仪器最新的检测信息 若需要获得鉴定信息请传入 INetworkResponse<'InstrumentCheck'>对象
     * @param id 不可为空  仪器ID
     * @param INetworkResponse 可为空
     */
   private void request(String id, INetworkResponse INetworkResponse) {

        api.InstrumentIdLastCheck(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<InstrumentCheck>>(showDialog) {
                    @Override
                    protected void success(DetailData<InstrumentCheck> data) {
                        if (data.getResponse() == null) {
                            if (INetworkResponse != null) {
                                INetworkResponse.error(-1, "返回空数据");
                                return;
                            }
                            LogUtil.e("返回空数据");
                            return;
                        }
                        if (INetworkResponse != null) {
                            INetworkResponse.success(data.getResponse());
                        }
                    }

                    @Override
                    protected boolean interceptError(int e, String message) {
                        if (INetworkResponse != null) {
                            return INetworkResponse.error(e, message);
                        }
                        return false;
                    }
                });
    }

    /**
     * 根据仪器ID获取仪器最新的检测信息 若需要获得鉴定信息请传入 INetworkResponse<'InstrumentCheck'>对象
     * @param id 不可为空 仪器id
     * @param iNetworkResponse 可为空
     */
    public void getInstrumentLastCheckInfo(String id,INetworkResponse<InstrumentCheck> iNetworkResponse)
    {
        if (id == null) {
            LogUtil.e("未传递仪器id");
            return;
        }
        request(id,iNetworkResponse);
    }


}
