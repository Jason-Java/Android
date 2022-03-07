package net;


import net.domain.BaseDomain;
import net.domain.DetailData;
import net.domain.DetailList;

import util.LogUtil;
import util.StringUtil;

public class UniteRequest {

    protected IApi api = RetrofitUtilGosn.getRetrofit().create(IApi.class);
    protected IShowDialog showDialog = null;

    public void setOnShowDialogListener(IShowDialog showDialog) {
        this.showDialog = showDialog;
    }

    //判断返回的数据对象是否为空数据
    public final <T> boolean objectNonNull(T data) {
       if(!isNull(data)) return false;

       if(data instanceof BaseDomain)
       {
           BaseDomain baseDomain= (BaseDomain) data;
           if(!baseDomain.getSuccess())
           {
               if (showDialog != null) {
                   showDialog.showErrorDialog(baseDomain.getMsg());
               } else {
                   LogUtil.e(baseDomain.getMsg());
               }
               return false;
           }
       }
        Class cla = data.getClass();
        if (StringUtil.equals(cla.getName(), "net.domain.DetailData") ) {
            DetailData detailData = (DetailData) data;
            return isNull(detailData);
        }
        if (StringUtil.equals(cla.getName(), "net.domain.DetailList")) {
            DetailList detailList = (DetailList) data;
            return isNull(detailList);
        }
        return false;
    }

    private boolean isNull(Object ob) {
        if (ob != null) return true;

        if (showDialog != null) {
            showDialog.showErrorDialog("返回空数据");
        } else {
            LogUtil.e("返回空数据");
        }
        return false;
    }


}
