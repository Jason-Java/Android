package net.Reagent;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.HttpObservable;
import net.INetworkResponse;
import net.IShowDialog;
import net.UniteRequest;
import net.domain.DetailList;
import net.domain.Reagent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import util.LogUtil;

public class PostReagent extends UniteRequest
{
    private PostReagent()
    {}
    /**
     * 批量添加试剂 返回添加成功的试剂信息 若需要成功的试剂信息请传入 NetworkResponse<'List<'Reagent'>'>
     *
     * @param reagentJSONArray  不可为空 试剂信息
     * @param networkResponse 可为空 若为空会导致返回数据失败
     */
   private void request(JSONArray reagentJSONArray, INetworkResponse networkResponse)
    {
        RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), reagentJSONArray.toString());
        api.postRegent(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new HttpObservable<DetailList<Reagent>>(showDialog)
               {
                   @Override
                   protected void success(DetailList<Reagent> data)
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
                       if(networkResponse!=null) {
                           return networkResponse.error(e, message);
                       }
                      return false;
                   }
               });
    }
    /**
     * 添加一条试剂
     * <p>
     * 返回添加成功的试剂信息 若需要成功的试剂信息请传入 NetworkResponse<'Reagent'>
     *
     * @param reagent         不可为空
     * @param showDialog      可为空 showDialog==null 网络请求不显示对话框 showDialog!=null 可能会显示
     * @param networkResponse 可为空 若为空会导致返回数据失败
     */
    public void addReagent(Reagent reagent, IShowDialog showDialog, INetworkResponse<Reagent> networkResponse)
    {
        if (reagent == null)
        {
            LogUtil.e("未传递 试剂");
            return;
        }
        JSONArray jsonArray = new JSONArray();

        JSON json = (JSON) JSON.toJSON(reagent);
        jsonArray.add(json);

        request(jsonArray, new INetworkResponse<List<Reagent>>()
        {
            @Override
            public void success(List<Reagent> value)
            {
                if (value.size() > 0 && networkResponse != null)
                {
                    networkResponse.success(value.get(0));
                }
            }

            @Override
            public boolean error(int e, String msg) {
                return networkResponse.error(e, msg);
            }
        });
    }

    /**
     * 批量添加试剂
     * <p>
     * 返回添加成功的试剂信息 若需要成功的试剂信息请传入 NetworkResponse<'List<'Reagent'>'>
     *
     * @param reagents        不可为空
     * @param showDialog      可为空 showDialog==null 网络请求不显示对话框 showDialog!=null 可能会显示
     * @param INetworkResponse 可为空 若为空会导致返回数据失败
     */
    public void batchAddReagent(ArrayList<Reagent> reagents, IShowDialog showDialog, INetworkResponse<List<Reagent>> INetworkResponse)
    {
        if (reagents.size() <= 0)
        {
            LogUtil.e("未传递 试剂");
            return;
        }
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < reagents.size(); i++)
        {
            jsonArray.add(JSON.toJSON(reagents.get(i)));
        }

        request(jsonArray,  INetworkResponse);
    }
}
