package net.Reagent;

import android.util.Log;

import androidx.annotation.NonNull;

import io.reactivex.Observer;
import  util.LogUtil;
import net.HttpObservable;
import net.HttpObservable2;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import util.StringUtil;

public final class ReagentBrand extends UniteRequest {
    private ReagentBrand() {
    }

    /**
     * map==null 查询所有的试剂品牌, map!=null 查询指定试剂的品牌,
     * 如果需要品牌信息请传入 NetworkRequest<'List<'String'>'>对象
     *
     * @param map             可为空  Key=Constant.ReagentName;Constant.ReagentBrand 这两个参数是可选的,
     *                        Constant.ReagentName是查询指定试剂的品牌  value为String类型
     * @param networkResponse 可为空
     */
    private void request(HashMap<String, String> map,  INetworkResponse networkResponse) {
        api.reagentBrands(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailList<String>>(showDialog) {
                    @Override
                    protected void success(DetailList<String> data) {
                        if (data.getResponse() == null || data.getResponse().size() <= 0) {
                            if (showDialog != null) {
                                showDialog.showErrorDialog("返回空数据");
                            } else {
                                LogUtil.e("返回空数据");
                            }
                            return;
                        }
                        if (networkResponse != null) {
                            networkResponse.success(data.getResponse());
                        }
                    }

                    @Override
                    public boolean interceptError(int e, String message) {
                        if (networkResponse != null) {
                            return networkResponse.error(e, message);
                        }
                        return false;
                    }
                });
    }

    /**
     * 查询所有的试剂品牌, 如果需要品牌信息请传入 NetworkRequest<'List<'String'>'>对象
     * @param networkResponse 可为空
     */
    public final void getAllBrand(INetworkResponse<List<String>> networkResponse) {
        request(new HashMap<>(),  new INetworkResponse<List<String>>() {
            @Override
            public void success(List<String> data) {
                for (int i = 0; i < data.size(); i++) {
                    String brand = data.get(i);
                    for (int j = 0; j < data.size(); j++) {
                        String brand_1 = data.get(j);
                        if (brand.equals(brand_1)) {
                            data.remove(j);
                            j--;
                        }
                    }
                }
                networkResponse.success(data);
            }

            @Override
            public boolean error(int e, String msg) {
                return networkResponse.error(e, msg);
            }
        });
    }


    private Observable<String> request(HashMap<String, String> map) {
        return api.reagentBrands(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<DetailList<String>>() {
                    @Override
                    public boolean test(DetailList<String> stringDetailList) throws Exception {
                        LogUtil.i("线程状态 "+Thread.currentThread().getName());
                        return objectNonNull(stringDetailList);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<DetailList<String>, List<String>>() {
                    @Override
                    public List<String> apply(DetailList<String> stringDetailList) throws Exception {
                        LogUtil.i("线程状态 "+Thread.currentThread().getName());
                        return stringDetailList.getResponse();
                    }
                })
                .flatMap(new Function<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> apply(@NonNull List<String> strings) throws Exception {
                        return Observable.fromIterable(strings).filter(new Predicate<String>() {
                            @Override
                            public boolean test(@NonNull String s) throws Exception {
                                LogUtil.i("线程状态 "+Thread.currentThread().getName());
                                return !StringUtil.isEmpty(s);
                            }
                        }).distinct();
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    public void getAll(INetworkResponse<List<String>> networkResponse) {
        request(new HashMap<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable2<String>() {
                    @Override
                    protected void success(ArrayList<String> data) {
                        LogUtil.i("线程状态 "+Thread.currentThread().getName());
                        if (networkResponse != null) {
                            networkResponse.success(data);
                        }
                    }

                    @Override
                    protected boolean interceptError(int e, String message) {
                        if (networkResponse != null) {
                            return networkResponse.error(e, message);
                        }
                        return false;
                    }
                });
//                .subscribe(new HttpObservable<List<String>>() {
//                    @Override
//                    protected void success(List<String> data) {
//                        if (networkResponse != null) {
//                            for (int i = 0; i < data.size(); i++) {
//                                String brand = data.get(i);
//                                for (int j = 0; j < data.size(); j++) {
//                                    String brand_1 = data.get(j);
//                                    if (brand.equals(brand_1)) {
//                                        data.remove(j);
//                                        j--;
//                                    }
//                                }
//                            }
//                            networkResponse.success(data);
//                        }
//                    }
//                    @Override
//                    protected boolean interceptError(int e, String message) {
//                        if (networkResponse != null) {
//                            return networkResponse.error(e, message);
//                        }
//                        return false;
//                    }
//                });

    }

}
