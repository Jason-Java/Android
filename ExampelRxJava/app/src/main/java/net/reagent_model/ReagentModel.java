package net.reagent_model;
/**
 * create by 2021.1
 */

import net.Constant;
import net.HttpObservable;

import net.IShowDialog;
import net.INetworkResponse;
import net.UniteRequest;
import net.domain.DetailData;
import net.domain.DomainReagentModel;
import net.domain.ReagentTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReagentModel extends UniteRequest {
    private ReagentModel() {
    }

    /**
     * 获取全部试剂模板,若需要获取数据请传入 NetworkResponse<"List<"ReagentTemplate">">对象
     *
     * @param map             可为空 key=Constant.Page[指定第几页],Constant.Rows[一页有多少条数据]
     * @param networkResponse 可为空 networkResponse==null将导致返回数据失败
     */
   private void request(HashMap<String, String> map,  INetworkResponse networkResponse) {
        api.reagentModel(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObservable<DetailData<DomainReagentModel>>(showDialog) {
                    @Override
                    protected void success(DetailData<DomainReagentModel> data) {
                        if (data.getResponse() == null) {
                            showDialog.showErrorDialog("返回空数据");
                            return;
                        }
                        if (networkResponse != null) {
                            networkResponse.success(data.getResponse().getData());
                        }
                    }

                    @Override
                    protected boolean interceptError(int e, String message) {
                        return networkResponse.error(e, message);
                    }
                });
    }

    /**
     * 获取全部的试剂名称和CAS 若需要获取 CAS 和 Name 数据请传入NetworkResponse<"List<"String">">对象
     *
     * @param showDialog      可为空 activity==null 网络请求不显示对话框 activity!=null 可能会显示
     * @param networkResponse 可为空 networkResponse==null将导致返回数据失败
     */
    public void getAllCASAndName(IShowDialog showDialog, INetworkResponse<List<String>> networkResponse) {
        HashMap<String, String> map = new HashMap<>();
        map.put(Constant.KeyWords, "CommonName,CAS");
        map.put(Constant.Page, "1");
        map.put(Constant.Rows, "100000");
        request(map,  new INetworkResponse<List<ReagentTemplate>>() {
            @Override
            public void success(List<ReagentTemplate> value) {
                ArrayList<String> casAndName = new ArrayList<>();
                for (int i = 0; i < value.size(); i++) {
                    String name = value.get(i).getCommonName();
                    String cas = value.get(i).getCAS();
                    if (!casAndName.contains(name)) {
                        casAndName.add(name);
                    }
                    if (!casAndName.contains(cas)) {
                        casAndName.add(cas);
                    }
                }
                networkResponse.success(casAndName);
            }

            @Override
            public boolean error(int e, String msg) {
                return networkResponse.error(e, msg);
            }
        });
    }


    /**
     * 分页获取 普通试剂 每页 20条数据 若需要获取数据请传入 NetworkResponse<"List<"ReagentTemplate">">对象
     *
     * @param networkResponse 可为空 networkResponse==null将导致返回数据失败
     */
    public void getPageCommonReagent(Integer page, INetworkResponse<List<ReagentTemplate>> networkResponse) {
        HashMap<String, String> map = new HashMap<>();
        if (page == null || page == 0) {
            map.put(Constant.Page, "1");
            map.put(Constant.Rows, "100000");
        } else {
            map.put(Constant.Page, page.toString());
            map.put(Constant.Rows, "20");
        }
        map.put("keywords", "CommonName,Label,Classification,CAS,Alias,Model,Consistency,Brand,Vender,ArtNo");
        request(map,new INetworkResponse<List<ReagentTemplate>>() {
            @Override
            public void success(List<ReagentTemplate> value) {
                ArrayList<ReagentTemplate> reagentTemplates = new ArrayList<>();
                for (int i = 0; i < value.size(); i++) {
                    String label = value.get(i).getLabel();
                    if (label.equals("普通危化品") || label.equals("易制爆") || label.equals("剧毒品") || label.equals("易制毒易制爆")) {
                        value.remove(i);
                        i--;
                    }
                }
                if (networkResponse != null) {
                    networkResponse.success(value);
                }
            }

            @Override
            public boolean error(int e, String msg) {
                return networkResponse.error(e, msg);
            }
        });
    }


    /**
     * 仅获取全部普通试剂 若需要获取数据请传入 NetworkResponse<"List<"ReagentTemplate">">对象
     *
     * @param INetworkResponse 可为空 networkResponse==null将导致返回数据失败
     */
    public void getAllCommonReagent( INetworkResponse<List<ReagentTemplate>> INetworkResponse) {
        getPageCommonReagent(null, INetworkResponse);
    }

    /**
     * 仅获取全部危险品试剂 若需要获取数据请传入 NetworkResponse<"List<"ReagentTemplate">">对象
     *
     * @param page            page==null 获取全部的危险品试剂 page!=null 获取指定页的试剂
     * @param networkResponse 可为空 networkResponse==null将导致返回数据失败
     */
    public void getPageDangerReagent(Integer page, INetworkResponse<List<ReagentTemplate>> networkResponse) {
        HashMap<String, String> map = new HashMap<>();
        if (page == null) {
            map.put(Constant.Page, "1");
            map.put(Constant.Rows, "100000");
        } else {
            map.put(Constant.Page, page.toString());
            map.put(Constant.Rows, "20");
        }
        map.put("keywords", "CommonName,Label,Classification,CAS,Alias,Model,Consistency,Brand,Vender,ArtNo");
        request(map,  new INetworkResponse<List<ReagentTemplate>>() {
            @Override
            public void success(List<ReagentTemplate> value) {
                ArrayList<ReagentTemplate> reagentTemplates = new ArrayList<>();
                for (int i = 0; i < value.size(); i++) {
                    String label = value.get(i).getLabel();
                    if (!(label.equals("普通危化品") || label.equals("易制爆") || label.equals("剧毒品") || label.equals("易制毒易制爆"))) {
                        value.remove(i);
                        i--;
                    }
                }
                if (networkResponse != null) {
                    networkResponse.success(value);
                }
            }

            @Override
            public boolean error(int e, String msg) {
                return networkResponse.error(e, msg);
            }
        });
    }

    /**
     * 仅获取全部危险试剂  若需要获取数据请传入 NetworkResponse<"List<"ReagentTemplate">">对象
     */
    public void getAllDangerReagent( INetworkResponse<List<ReagentTemplate>> INetworkResponse) {
        getPageDangerReagent(null,  INetworkResponse);
    }


}
