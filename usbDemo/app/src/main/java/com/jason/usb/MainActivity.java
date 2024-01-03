package com.jason.usb;


import android.app.Activity;
import android.hardware.usb.UsbManager;
import android.os.Bundle;

import com.jason.usb.command.MingYinBackPaperCommand;
import com.jason.usb.command.MingYinClearCommand;
import com.jason.usb.command.MingYinCutPaperCommand;
import com.jason.usb.command.MingYinEnterChineseModelCommand;
import com.jason.usb.command.MingYinExitChineseModel;
import com.jason.usb.command.MingYinFeedLineCommand;
import com.jason.usb.command.MingYinFontBoldCommand;
import com.jason.usb.command.MingYinFontDirectionCommand;
import com.jason.usb.command.MingYinHorizontalTable;
import com.jason.usb.command.MingYinPrintAndFeedCommand;
import com.jason.usb.command.MingYinPrintBitmapCommand;
import com.jason.usb.command.MingYinPrintMarkCutPaperCommand;
import com.jason.usb.command.MingYinPrintStringCommand;
import com.jason.usb.command.MingYinQrCodeCommand;
import com.jason.usb.command.MingYinSetInitialPositionCommand;
import com.jason.usb.command.MingYinSetLeftMarginCommand;
import com.jason.usb.command.MingYinSetLineSpacingCommand;
import com.jason.usb.command.MingYinSetMarkOffSetCutCommand;
import com.jason.usb.command.MingYinSetPageModelCommand;
import com.jason.usb.command.MingYinSetRightMarginCommand;
import com.jason.usb.command.MingYinTestCommand;
import com.jason.usb.command.MingYinTestMarkPositionCommand;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsbDeriveManage usbDeriveManage = UsbDeriveManage.getInstance();
        usbDeriveManage.init((UsbManager) getSystemService(USB_SERVICE));

        findViewById(R.id.btn_setRightMargin).setOnClickListener(v -> {
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinSetRightMarginCommand(255).getCmd());
        });
        findViewById(R.id.btn_setLeftMargin).setOnClickListener(v -> {
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinSetLeftMarginCommand(200).getCmd());
        });
        //打印内容
        findViewById(R.id.btn_print).setOnClickListener(v -> {
            //清除缓存
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinClearCommand().getCmd());
            //退纸
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinBackPaperCommand(190).getCmd());
            //取消字体翻转
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontDirectionCommand(0).getCmd());
            //设置左边距
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinSetLeftMarginCommand(250).getCmd());
            //打印二维码
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinQrCodeCommand(5, "1234567").getCmd());
            //打印标签
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintBitmapCommand(CreateBitmap.createBitmap("易制爆")).getCmd());
            //退纸
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinBackPaperCommand(190).getCmd());
            // 进入中文模式
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinEnterChineseModelCommand().getCmd());
            // 设置左边距
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinSetLeftMarginCommand(220).getCmd());
            // 设置字体翻转
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontDirectionCommand(1).getCmd());
            //走纸
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFeedLineCommand(1).getCmd());
            //取消字体加粗
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontBoldCommand(false).getCmd());
            //打印品名
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintStringCommand("存放地点：", false).getCmd());
            //字体加粗
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontBoldCommand(true).getCmd());
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintStringCommand("光电大楼B", true).getCmd());
            //设置行间距
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinSetLineSpacingCommand(19).getCmd());
            // 字体取消加粗
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontBoldCommand(false).getCmd());
            //打印规格
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintStringCommand("入库日期：", false).getCmd());
            //字体加粗
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontBoldCommand(true).getCmd());
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintStringCommand("2023-3-5", true).getCmd());
            //取消字体加粗
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontBoldCommand(false).getCmd());
            //打印规格
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintStringCommand("申购人：", false).getCmd());
            //字体加粗
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontBoldCommand(true).getCmd());
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintStringCommand("小李飞刀", true).getCmd());
            //取消字体加粗
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontBoldCommand(false).getCmd());
            //打印规格
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintStringCommand("规格：", false).getCmd());
            //字体加粗
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontBoldCommand(true).getCmd());
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintStringCommand("500ml", true).getCmd());
            //取消字体加粗
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontBoldCommand(false).getCmd());
            //打印规格
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintStringCommand("品名：", false).getCmd());
            //字体加粗
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFontBoldCommand(true).getCmd());
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintStringCommand("过氧化钠", true).getCmd());
            //退出中文模式
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinExitChineseModel().getCmd());

            // 设置左边距
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinSetLeftMarginCommand(250).getCmd());


            //走纸
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinFeedLineCommand(4).getCmd());
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinPrintMarkCutPaperCommand().getCmd());
        });
        findViewById(R.id.btn_setCutPaper).setOnClickListener(v -> {
            //切纸
            usbDeriveManage.sendCmd(UsbPrintBrand.MING_YIN_PRINT, new MingYinCutPaperCommand().getCmd());
        });
    }


}