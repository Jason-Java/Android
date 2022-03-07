package net.instrument;

import  util.LogUtil;
import  util.StringUtil;
import net.Constant;
import net.HttpObservable;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailData;
import net.domain.InstrumentAlarm;

import net.UniteRequest;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InstrumentAlarmGet extends UniteRequest {
    private InstrumentAlarmGet() {
    }

    private void request(HashMap<String, String> map, INetworkResponse INetworkResponse) {
        api.instrumentAlarmGet(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<InstrumentAlarm.Data>>(showDialog) {
                    @Override
                    protected void success(DetailData<InstrumentAlarm.Data> data) {
                        if (data.getResponse().getData().size() <= 0) {
                            if (showDialog != null) {
                                showDialog.showErrorDialog("返回空数据");
                            } else {
                                LogUtil.e("返回空数据");
                            }
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

    private void getAllAlarm(String page, String rows, String companyId, String instrumentId, INetworkResponse<InstrumentAlarm.Data> INetworkResponse) {
        if (StringUtil.isEmpty(companyId)) {
            LogUtil.e("未传递公司ID");
            return;
        }
        if (StringUtil.isEmpty(instrumentId)) {
            LogUtil.e("未传递仪器Id");
            return;
        }
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        if (StringUtil.isEmpty(rows)) {
            rows = "20";
        }
        HashMap<String, String> map = new HashMap<>();
        map.put(Constant.CompanyId, companyId);
        map.put(Constant.Page, page);
        map.put(Constant.Rows, rows);
        map.put(Constant.InstrumentId, instrumentId);
        request(map, INetworkResponse);
    }

    /**
     * 根据公司ID 获取所有的仪器异常警报 若需要获得仪器异常警报数据请传入 INetworkResponse<'ArrayList<'InstrumentAlarm'>'>对象
     *
     * @param companyId        不可为空 公司ID
     * @param INetworkResponse 可为空 为空时则不返回数据
     */
    public void getInstrumentAlarm(String companyId, String instrumentId, INetworkResponse<InstrumentAlarm.Data> INetworkResponse) {
        getAllAlarm(null, null, companyId, instrumentId, INetworkResponse);
    }

    /**
     * 根据公司ID 分页获取仪器异常警报数据 一页包含五条数据  若需要获得仪器异常警报数据请传入 INetworkResponse<'ArrayList<'InstrumentAlarm'>'>对象
     *
     * @param page             页码
     * @param companyId        公司Id
     * @param INetworkResponse 可为空 为空时则不返回数据
     */
    public void getInstrumentAlarmByPage(String page, String companyId, String instrumentId, INetworkResponse<InstrumentAlarm.Data> INetworkResponse) {
        getAllAlarm(page, "5", companyId, instrumentId, INetworkResponse);
    }

    /**
     * 根据公司ID 分页获取仪器异常警报数据  自定义一页包含的数据条数  若需要获得仪器异常警报数据请传入 INetworkResponse<'ArrayList<'InstrumentAlarm'>'>对象
     *
     * @param page             可为空  为空请求第一页的数据
     * @param rows             可为空 为空一页包含20条数据
     * @param companyId        不可为空
     * @param INetworkResponse 可为空 为空时不返回数据
     */
    public void getInstrumentAlarmCustomRow(String page, String rows, String companyId, String instrumentId, INetworkResponse<InstrumentAlarm.Data> INetworkResponse) {
        getAllAlarm(page, rows, companyId, instrumentId, INetworkResponse);
    }
}
