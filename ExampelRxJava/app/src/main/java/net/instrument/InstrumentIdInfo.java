package net.instrument;

import  util.LogUtil;
import net.Constant;
import net.HttpObservable;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailData;
import net.domain.Instrument;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InstrumentIdInfo extends UniteRequest {
    private InstrumentIdInfo() {

    }

    /**
     * 获取仪器的基本信息 包括仪器二维码  若需要获取请传入 INetworkResponse<'Instrument'>对象
     * @param id 仪器id
     * @param INetworkResponse

     */

   private void request(String id,  INetworkResponse INetworkResponse) {
        api.InstrumentIdInfo(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<Instrument>>(showDialog) {
                    @Override
                    protected void success(DetailData<Instrument> data) {
                        if(data.getResponse()==null)
                        {
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
     * 获取仪器的基本信息 包括仪器二维码,仪器检定信息等信息  若需要获取请传入 INetworkResponse<'Instrument'>对象
     * @param id 不可为空
     * @param iNetworkResponse  可为空 若为空返回信息失败
     */
    public void getInstrumentInfo(String id,INetworkResponse<Instrument> iNetworkResponse)
    {
        if(id==null)
        {
            LogUtil.i("未传递仪器Id");
            return;
        }
        request(id, iNetworkResponse);
    }




}
