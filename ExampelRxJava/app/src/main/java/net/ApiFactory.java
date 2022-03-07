package net;

import net.Reagent.PostReagent;
import net.Reagent.ReagentBrand;
import net.Reagent.ReagentStorage;
import net.box.BoxId;
import net.group.GroupIdBox;
import net.instrument.InstrumentAlarmGet;
import net.instrument.InstrumentAlarmPost;
import net.instrument.InstrumentIdInfo;
import net.instrument.InstrumentIdLastCheck;
import net.instrument.InstrumentIdRsdata;
import net.login.LoginFaceToken;
import net.login.LoginJWTTToken;
import net.login.LoginRefreshToken;
import net.reagent_model.ReagentModel;
import net.reagent_model.ReagentModelExtension;
import net.stats.StatsStorageReagent;
import net.stats.StatsUseReagent;
import net.user.UserGetInfoByToken;

import java.lang.reflect.Constructor;

public final class ApiFactory {
    private static Object object = new Object();

    //账号登录
    private static LoginJWTTToken loginJWTTToken = null;
    //刷脸登录
    private static LoginFaceToken loginFaceToken=null;
    //刷新token
    private static LoginRefreshToken loginRefreshToken = null;
    //获取用户信息
    private static UserGetInfoByToken userGetInfoByToken = null;

    //获取库存统计信息
    private static StatsStorageReagent statsStorageReagent = null;
    //获取所有的领用记录
    private static StatsUseReagent statsUseReagent = null;
    //根据Box的Id 获取柜子的详细信息
    private static BoxId boxId = null;
    //根据groupId 获取group下所有的柜子
    private static GroupIdBox groupIdBox = null;
    //查询所有的试剂品牌
    private static ReagentBrand reagentBrand = null;
    //可领用试剂
    private static ReagentStorage reagentStorage = null;
    // 试剂模板
    private static ReagentModel reagentModel = null;
    //获取试剂说明书
    private static ReagentModelExtension reagentModelExtension = null;
    //添加试剂
    private static PostReagent postReagent = null;
    //获取温湿度信息
    private static InstrumentIdRsdata instrumentIdRsdata = null;
    //获取仪器信息
    private static InstrumentIdInfo instrumentIdInfo = null;
    //获取仪器检定信息
    private static InstrumentIdLastCheck instrumentIdLastCheck = null;

    //上传仪器异常警报信息
    private static InstrumentAlarmPost instrumentAlarmPost = null;
    //获取异常警报信息
    private static InstrumentAlarmGet instrumentAlarmGet = null;


    private static <T> T createAPi(Class<T> clasz) {
        synchronized (object) {
             UniteRequest uniteRequest = null;
            try {
                Class cl = Class.forName(clasz.getName());
                Constructor constructor = cl.getDeclaredConstructor();
                constructor.setAccessible(true);
                uniteRequest = ( UniteRequest) constructor.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return (T) uniteRequest;
        }
    }

    /**
     * 账号登录
     */
    public static LoginJWTTToken getLoginJWTTTokenInstance(IShowDialog showDialog) {

        if (loginJWTTToken == null) {
            loginJWTTToken = createAPi(LoginJWTTToken.class);
        }
        loginJWTTToken.setOnShowDialogListener(showDialog);
        return loginJWTTToken;
    }

    /**
     * 账号登录
     * @return
     */
    public static LoginJWTTToken getLoginJWTTTokenInstance() {
        return getLoginJWTTTokenInstance(null);
    }

    /**
     * 刷脸登录
     * @param showDialog
     * @return
     */
    public static LoginFaceToken getLoginFaceTokenInstance( IShowDialog showDialog)
    {
        if (loginFaceToken == null) {
            loginFaceToken = createAPi(LoginFaceToken.class);
        }
        loginFaceToken.setOnShowDialogListener(showDialog);
        return loginFaceToken;
    }

    /**
     * 刷脸登录
     * @return
     */
    public static LoginFaceToken getLoginFaceTokenInstance() {
        return getLoginFaceTokenInstance(null);
    }

    /**
     * 刷新token
     * @param showDialog
     * @return
     */
    public static LoginRefreshToken getLoginRefreshTokenInstance( IShowDialog showDialog) {
        if(loginRefreshToken==null)
        {
            loginRefreshToken = createAPi(LoginRefreshToken.class);
        }
        loginRefreshToken.setOnShowDialogListener(showDialog);
        return loginRefreshToken;
    }

    /**
     * 获取用户信息
     * @return
     */
    public static LoginRefreshToken getLoginRefreshTokenInstance()
    {
       return getLoginRefreshTokenInstance(null);
    }

    public static UserGetInfoByToken getUserGetInfoByTokenInstance(IShowDialog showDialog)
    {
        if (userGetInfoByToken == null) {
            userGetInfoByToken=createAPi(UserGetInfoByToken.class);
        }
        userGetInfoByToken.setOnShowDialogListener(showDialog);
        return userGetInfoByToken;
    }
    public static UserGetInfoByToken getUserGetInfoByTokenInstance()
    {
        return getUserGetInfoByTokenInstance(null);
    }

    /**
     * 获取库存统计信息
     *
     * @return
     */
    public static StatsStorageReagent getStatsStorageReagentInstance( IShowDialog showDialog) {
        if (statsStorageReagent == null) {
            statsStorageReagent = createAPi(StatsStorageReagent.class);
        }
        statsStorageReagent.setOnShowDialogListener(showDialog);
        return statsStorageReagent;
    }

    /**
     * 获取库存统计信息
     *
     * @return
     */
    public static StatsStorageReagent getStatsStorageReagentInstance() {
        return getStatsStorageReagentInstance(null);
    }

    /**
     * 获取所有的领用记录
     */
    public static StatsUseReagent getStatsUseReagentInstance( IShowDialog showDialog) {
        if (statsUseReagent == null) {
            statsUseReagent = createAPi(StatsUseReagent.class);
        }
        statsUseReagent.setOnShowDialogListener(showDialog);
        return statsUseReagent;
    }

    /**
     * 获取所有的领用记录
     */
    public static StatsUseReagent getStatsUseReagentInstance() {
        return getStatsUseReagentInstance(null);
    }

    /**
     * 根据Box的Id 获取柜子的详细信息
     *
     * @return
     */
    public static BoxId getBoxIdInstance( IShowDialog showDialog) {
        if (boxId == null) {
            boxId = createAPi(BoxId.class);
        }
        boxId.setOnShowDialogListener(showDialog);
        return boxId;
    }

    /**
     * 根据Box的Id 获取柜子的详细信息
     */
    public static BoxId getBoxIdInstance() {
        return getBoxIdInstance(null);
    }

    /**
     * 根据groupId 获取group下所有的柜子
     */
    public static GroupIdBox getGroupIdBox( IShowDialog showDialog) {
        if (groupIdBox == null) {
            groupIdBox = createAPi(GroupIdBox.class);
        }
        groupIdBox.setOnShowDialogListener(showDialog);
        return groupIdBox;
    }

    /**
     * 根据groupId 获取group下所有的柜子
     */
    public static GroupIdBox getGroupIdBox() {
        return getGroupIdBox(null);
    }


    /**
     * 查询所有的试剂品牌
     *
     * @return
     */
    public static ReagentBrand getReagentBrandInstance( IShowDialog showDialog) {
        if (reagentBrand == null) {
            reagentBrand = createAPi(ReagentBrand.class);
        }
        reagentBrand.setOnShowDialogListener(showDialog);
        return reagentBrand;
    }

    /**
     * 查询所有的试剂品牌
     */
    public static ReagentBrand getReagentBrandInstance() {
        return getReagentBrandInstance(null);
    }


    /**
     * 添加试剂
     */
    public static PostReagent getPostReagentInstance( IShowDialog showDialog) {
        if (postReagent == null) {
            postReagent = createAPi(PostReagent.class);
        }
        postReagent.setOnShowDialogListener(showDialog);
        return postReagent;
    }

    public static PostReagent getPostReagentInstance() {
        return getPostReagentInstance(null);
    }

    /**
     * 可领用试剂
     *
     * @return
     */
    public static ReagentStorage getReagentStorageInstance( IShowDialog showDialog) {
        if (reagentStorage == null) {
            reagentStorage = createAPi(ReagentStorage.class);
        }
        reagentStorage.setOnShowDialogListener(showDialog);
        return reagentStorage;
    }

    /**
     * 可领用试剂
     */
    public static ReagentStorage getReagentStorageInstance() {
        return getReagentStorageInstance(null);
    }


    /**
     * 试剂模板
     */
    public static ReagentModel getReagentModelInstance( IShowDialog showDialog) {
        if (reagentModel == null) {
            reagentModel = createAPi(ReagentModel.class);
        }
        reagentModel.setOnShowDialogListener(showDialog);
        return reagentModel;
    }

    /**
     * 试剂模板
     */
    public static ReagentModel getReagentModelInstance() {
        return getReagentModelInstance(null);
    }


    /**
     * 获取试剂说明书
     */
    public static ReagentModelExtension getReagentModelExtensionInstance( IShowDialog showDialog) {
        if (reagentModelExtension == null) {
            reagentModelExtension = createAPi(ReagentModelExtension.class);
        }
        reagentModelExtension.setOnShowDialogListener(showDialog);
        return reagentModelExtension;
    }

    /**
     * 获取试剂说明书
     */
    public static ReagentModelExtension getReagentModelExtensionInstance() {
        return getReagentModelExtensionInstance(null);
    }

    /**
     * 获取温湿度记录
     *
     * @return
     */
    public static InstrumentIdRsdata getInstrumentIdRsdata( IShowDialog showDialog) {

        if (instrumentIdRsdata == null) {
            instrumentIdRsdata = createAPi(InstrumentIdRsdata.class);
        }
        instrumentIdRsdata.setOnShowDialogListener(showDialog);
        return instrumentIdRsdata;
    }

    public static InstrumentIdRsdata getInstrumentIdRsdata() {
        return getInstrumentIdRsdata(null);
    }

    /**
     * 获取仪器基本信息
     *
     * @return
     */
    public static InstrumentIdInfo getInstrumentIdInfo( IShowDialog showDialog) {
        if (instrumentIdInfo == null) {
            instrumentIdInfo = createAPi(InstrumentIdInfo.class);
        }
        instrumentIdInfo.setOnShowDialogListener(showDialog);
        return instrumentIdInfo;
    }

    /**
     * 获取仪器基本信息
     *
     * @return
     */
    public static InstrumentIdInfo getInstrumentIdInfo() {
        return getInstrumentIdInfo(null);
    }

    /**
     * 获取仪器最新的检定信息
     *
     * @return
     */
    public static InstrumentIdLastCheck getInstrumentIdLastCheck( IShowDialog showDialog) {
        if (instrumentIdLastCheck == null) {
            instrumentIdLastCheck = createAPi(InstrumentIdLastCheck.class);
        }
        instrumentIdLastCheck.setOnShowDialogListener(showDialog);
        return instrumentIdLastCheck;
    }

    /**
     * 获取仪器最新的检定信息
     *
     * @return
     */
    public static InstrumentIdLastCheck getInstrumentIdLastCheck() {
        return getInstrumentIdLastCheck(null);
    }




    /**
     * 上传异常警报信息
     *
     * @return
     */
    public static InstrumentAlarmPost getInstrumentAlarmPost( IShowDialog showDialog) {
        if (instrumentAlarmPost == null) {
            instrumentAlarmPost = createAPi(InstrumentAlarmPost.class);
        }
        instrumentAlarmPost.setOnShowDialogListener(showDialog);
        return instrumentAlarmPost;
    }

    /**
     * 上传异常警报信息
     *
     * @return
     */
    public static InstrumentAlarmPost getInstrumentAlarmPost() {
        return getInstrumentAlarmPost(null);
    }

    /**
     * 获取仪器异常警报信息
     *
     * @return
     */
    public static InstrumentAlarmGet getInstrumentAlarmGet( IShowDialog showDialog) {
        if (instrumentAlarmGet == null) {
            instrumentAlarmGet = createAPi(InstrumentAlarmGet.class);
        }
        instrumentAlarmGet.setOnShowDialogListener(showDialog);
        return instrumentAlarmGet;
    }

    public static InstrumentAlarmGet getInstrumentAlarmGet() {
        return getInstrumentAlarmGet(null);
    }
}
