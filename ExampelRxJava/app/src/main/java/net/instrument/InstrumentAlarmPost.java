package net.instrument;


import com.alibaba.fastjson.JSONObject;
import util.LogUtil;
import net.HttpObservable;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailData;
import net.domain.InstrumentAlarm;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class InstrumentAlarmPost extends UniteRequest {

    private InstrumentAlarmPost() {

    }

    private  void request(JSONObject jsonObject, INetworkResponse INetworkResponse) {
        RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toJSONString());
        api.instrumentAlarmPost(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<InstrumentAlarm>>(showDialog) {
                    @Override
                    protected void success(DetailData<InstrumentAlarm> data) {
                        if(data.getResponse()==null){
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
                        return INetworkResponse.error(e,message);
                    }
                });

    }

    /**
     * 上传仪器异常警报信息  如需获取返回数据请传入INetworkResponse<'InstrumentAlarm'>对象
     * @param alarm 不可为空
     * @param INetworkResponse 可为空
     */
    public  void uploadAlarm(InstrumentAlarm alarm, INetworkResponse<InstrumentAlarm> INetworkResponse)
    {
        if(alarm==null)
        {
            LogUtil.e("未传递仪器异常警报记录");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Type",alarm.getType());
        jsonObject.put(  "InstrumentId",alarm.getInstrumentId());
        jsonObject.put(  "UserId",alarm.getUserId());
        jsonObject.put(  "UserName",alarm.getUserName());
        jsonObject.put(  "Pic",alarm.getPic());
        jsonObject.put(  "Time",alarm.getTime());
        jsonObject.put(  "CompanyId",alarm.getCompanyId());
        request(jsonObject, INetworkResponse);
    }

}