package net;

import net.domain.Box;
import net.domain.DetailData;
import net.domain.DetailList;
import net.domain.DomainBoxId;
import net.domain.DomainGetInfoByToken;
import net.domain.DomainReagentModel;
import net.domain.DomainReagentStored;
import net.domain.Humiture;
import net.domain.Instrument;
import net.domain.InstrumentAlarm;
import net.domain.InstrumentCheck;
import net.domain.InstrumentRecord;
import net.domain.Login;
import net.domain.Reagent;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IApi {
    /**
     * ApiUser
     */

    @GET("/api/User/GetInfoByToken")
    Observable<DetailData<DomainGetInfoByToken>> getInfoByToken(@Query("token") String token);


    /**
     * ApiReagentModel
     */

    //获取说明书
    @GET("/api/ReagentModel/Extension")
    Observable<DetailData<Reagent>> reagentModelExtension(@Query("cas") String cas);

    //获取试剂模板
    @GET("/api/ReagentModel")
    Observable<DetailData<DomainReagentModel>> reagentModel(@QueryMap Map<String, String> map);


    /**
     * ApiReagent
     */
    //获取试剂品牌
    @GET("/api/Reagent/Brands")
    Observable<DetailList<String>> reagentBrands(@QueryMap HashMap<String, String> map);

    //批量添加试剂
    @POST("/api/Reagent")
    Observable<DetailList<Reagent>> postRegent(@Body RequestBody body);

    //获取可领用试剂
    @GET("/api/Reagent/Stored")
    Observable<DetailData<DomainReagentStored>> reagentStored(@QueryMap HashMap<String, String> map);

    //根据条码获取试剂信息
    @GET("/api/Reagent/Scan")
    Observable<DetailData<Reagent>> rReagentScan(@Query("barcode") String barcode);

    /**
     * ApiLogin
     */
    //登录
    @GET("/api/Login/JWTToken3.0")
    Observable<Login> loginJWTToken(@Query("name") String name, @Query("pass") String pass);

    @GET("/api/Login/facetoken/{id}")
    Observable<Login> loginFaceToken(@Path("id") String id);

    //刷新Token
    @GET("/api/Login/RefreshToken")
    Observable<Login> loginRefreshToken(@Query("token") String token);

    /**
     * ApiGroup
     */
    //根据GroupId 获取所有的柜子
    @GET("/api/Group/{id}/box")
    Observable<DetailList<Box>> groupIdBox(@Path("id") String GroupId);

    /**
     * ApiBox
     */

    //根据柜子id获取柜子详情信息 包括抽屉信息
    @GET("/api/Box/{id}")
    Observable<DetailData<DomainBoxId>> boxId(@Path("id") String BoxId);

    /**
     * ApiStats
     */

    //库存统计
    @GET("/api/Stats/Storage/Reagent")
    Observable<DetailList<Reagent>> statsStorageReagent(@QueryMap Map<String, String> map);

    //领用记录
    @GET("/api/Stats/Use/Reagent")
    Observable<DetailList<Reagent>> statsUseReagent(@QueryMap HashMap<String, String> map);


    /**
     * ApiInstrument
     */

    //获取温湿度
    @GET("api/Instrument/{id}/rsdata")
    Observable<DetailData<Humiture>> InstrumentIdRsdata(@Path("id") String id);

    //获取仪器基本信息
    @GET("api/Instrument/{id}/info")
    Observable<DetailData<Instrument>> InstrumentIdInfo(@Path("id") String id);

    //获取仪器最新的检定信息
    @GET("/api/Instrument/{id}/lastcheck")
    Observable<DetailData<InstrumentCheck>> InstrumentIdLastCheck(@Path("id") String id);

    //添加异常使用记录
    @POST("/api/InstrumentAlarm")
    Observable<DetailData<InstrumentAlarm>> instrumentAlarmPost(@Body RequestBody body);

    //获取异常使用记录
    @GET("/api/InstrumentAlarm")
    Observable<DetailData<InstrumentAlarm.Data>> instrumentAlarmGet(@QueryMap HashMap<String, String> map);

    //上传使用记录
    @POST("/api/InstrumentRecord")
    Observable<DetailList<InstrumentRecord>> instrumentRecordPost(@Body RequestBody body);


}
