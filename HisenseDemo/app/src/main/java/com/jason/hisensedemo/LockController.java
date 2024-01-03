package com.jason.hisensedemo;


import com.topband.cabinetsdk.CabinetManager;
import com.topband.cabinetsdk.interf.ICabinetListener;
import com.topband.cabinetsdk.model.bean.AlarmAndSignalInputState;
import com.topband.cabinetsdk.model.bean.ControllerOutputState;
import com.topband.libserial.core.Logger;
import com.topband.libserial.core.SerialConfig;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年11月03日
 */
public class LockController {
    public static void init() {
        SerialConfig config = new SerialConfig() {
            @Override
            public String getSerialPath() {
                return "/dev/ttyS1";
            }

            @Override
            public int getRate() {
                return 9600;
            }

            @Override
            public boolean printLog() {
                return true;
            }
        };
        CabinetManager.init(config, new Logger() {
            @Override
            public void d(String s, String s1) {
                System.out.println("Jason" + s + "---" + s1);
            }

            @Override
            public void w(String s, String s1) {
                System.err.println("Jason" + s + "---" + s1);
            }

            @Override
            public void e(String s, String s1) {
                System.err.println("Jason" + s + "---" + s1);
            }
        });
        CabinetManager.getInstance().setCabinetListener(new ICabinetListener() {
            @Override
            public void onLockStatusChanged(int i, boolean b) {

            }

            @Override
            public void onDoorStatusChanged(int i, boolean b) {

            }

            @Override
            public void onSensorTemperatureChanged(int i, double v) {

            }

            @Override
            public void onSensorHumidityChanged(int i, double v) {

            }

            @Override
            public void onSensorRingTemperatureChanged(int i, double v) {

            }

            @Override
            public void onAlarmAndSignalInputStateChanged(int i, AlarmAndSignalInputState alarmAndSignalInputState) {

            }

            @Override
            public void onControllerOutputStateChanged(int i, ControllerOutputState controllerOutputState) {

            }

            @Override
            public void onControllerModelNumber(int i, String s) {

            }

            @Override
            public void onSoftwareVersionNumber(int i, String s) {

            }

            @Override
            public void onHighTemperatureAlarmTemperatureDifferenceChanged(int i, int i1) {

            }

            @Override
            public void onLowTemperatureAlarmTemperatureDifferenceChanged(int i, int i1) {

            }

            @Override
            public void onHumidityAlarmUpperLimitChanged(int i, int i1) {

            }

            @Override
            public void onHumidityAlarmLowerLimitChanged(int i, int i1) {

            }

            @Override
            public void onSensorMeasurementStabilityChanged(int i, int i1) {

            }

            @Override
            public void onCabinetTemperatureControlSensorOffsetChanged(int i, double v) {

            }

            @Override
            public void onHumiditySensorOffsetChanged(int i, int i1) {

            }

            @Override
            public void onCabinetTemperatureDisplaySensorOffsetChanged(int i, double v) {

            }

            @Override
            public void onDecimalPointEnableChanged(int i, boolean b) {

            }

            @Override
            public void onBuzzerEnableChanged(int i, boolean b) {

            }

            @Override
            public void onTemperatureHumidityRegularChanged(int i, boolean b) {

            }

            @Override
            public void onMinimumTemperatureChanged(int i, int i1) {

            }

            @Override
            public void onMaximumTemperatureChanged(int i, int i1) {

            }

            @Override
            public void onWorkModeChanged(int i, boolean b) {

            }

            @Override
            public void onRefrigerationUpperTemperatureDifferenceChanged(int i, float v) {

            }

            @Override
            public void onRefrigerationLowerTemperatureDifferenceChanged(int i, float v) {

            }

            @Override
            public void onHeatingStartedTemperatureDifferenceChanged(int i, float v) {

            }

            @Override
            public void onHeatingStoppedTemperatureDifferenceChanged(int i, float v) {

            }

            @Override
            public void onCompressorStoppedHumidityChanged(int i, int i1) {

            }

            @Override
            public void onExitCompressorStoppedHumidityChanged(int i, int i1) {

            }

            @Override
            public void onCompressorStoppedTemperatureChanged(int i, float v) {

            }

            @Override
            public void onHumidificationLowerLimitChanged(int i, int i1) {

            }

            @Override
            public void onHumidificationUpperLimitChanged(int i, int i1) {

            }

            @Override
            public void onDehumidificationLowerLimitChanged(int i, int i1) {

            }

            @Override
            public void onDehumidificationUpperLimitChanged(int i, int i1) {

            }

            @Override
            public void onDehumidificationStoppedTemperatureDifferenceChanged(int i, float v) {

            }

            @Override
            public void onCompressorDelayChanged(int i, int i1) {

            }

            @Override
            public void onCompressorMinimumStopTimeChanged(int i, int i1) {

            }

            @Override
            public void onSensorFaultCompressorWorkTimeChanged(int i, int i1) {

            }

            @Override
            public void onSensorFaultCompressorStopTimeChanged(int i, int i1) {

            }

            @Override
            public void onCompressorFaultCheckTime(int i, int i1) {

            }

            @Override
            public void onCompressorFaultStopTime(int i, int i1) {

            }

            @Override
            public void onCompressorFaultTemperatureDifference(int i, float v) {

            }

            @Override
            public void onFanControlType(int i, int i1) {

            }

            @Override
            public void onDefrostingFanStatus(int i, boolean b) {

            }

            @Override
            public void onFanStartDelay(int i, int i1) {

            }

            @Override
            public void onFanCloseDelay(int i, int i1) {

            }

            @Override
            public void onFanCloseAfterDripped(int i, int i1) {

            }

            @Override
            public void onDefrostMode(int i, boolean b) {

            }

            @Override
            public void onDefrostPeriod(int i, int i1) {

            }

            @Override
            public void onHeatingWireStateDuringDefrostingChanged(int i, boolean b) {

            }

            @Override
            public void onDefrostTimeChanged(int i, int i1) {

            }

            @Override
            public void onDefrostStartTemperatureDifferenceChanged(int i, double v) {

            }

            @Override
            public void onDefrostTerminationTemperatureChanged(int i, double v) {

            }

            @Override
            public void onDefrostTerminationHumidityChanged(int i, int i1) {

            }

            @Override
            public void onDefrostDisplayChanged(int i, int i1) {

            }

            @Override
            public void onDrippingTimeChanged(int i, int i1) {

            }

            @Override
            public void onDefrostHighTemperatureAlarmShieldingTimeChanged(int i, int i1) {

            }

            @Override
            public void onAlarmDelayChanged(int i, int i1) {

            }

            @Override
            public void onBuzzerDelayTimeChanged(int i, int i1) {

            }

            @Override
            public void onDoorOpeningAlarmDelayChanged(int i, int i1) {

            }

            @Override
            public void onStorageIntervalChanged(int i, int i1) {

            }

            @Override
            public void onStorageModeChanged(int i, boolean b) {

            }

            @Override
            public void onSettingTemperatureChanged(int i, int i1) {

            }
        });
       CabinetManager.getInstance().queryCabinetLock(new int[]{1});
//        CabinetManager.getInstance().unlockCabinet(new int[]{1});
//        CabinetManager.getInstance().queryCabinetDoor(new int[]{1});
       // CabinetManager.getInstance().queryAlarmDelay(new int[]{1});
//        CabinetManager.getInstance().quer
    }
}
