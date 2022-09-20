package com.unite.customepack;

import androidx.annotation.NonNull;


import com.alibaba.fastjson.JSONObject;
import com.unite.jasonjar.util.LogUtil;
import com.unite.jasonjar.util.StringUtil;


import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


public class UniteWebSocket  {

    private final OkHttpClient client;
    private WebSocket mWebSocket;
    private volatile AtomicReference<WebSocketMessage> message = new AtomicReference<>();
    private ArrayBlockingQueue<WebSocketMessage> messageQueue = new ArrayBlockingQueue<WebSocketMessage>(20);
    //建立链接消息
    private volatile AtomicReference<WebSocketMessage> createLinkedMessage = new AtomicReference<>();

    private final AtomicReference<WebSocketMessage> openLockMessage = new AtomicReference<>();
    private final AtomicReference<WebSocketMessage> destroyMessage = new AtomicReference<>();
    private final AtomicReference<WebSocketMessage> startMessage = new AtomicReference<>();
    private final AtomicReference<WebSocketMessage> endMessage = new AtomicReference<>();
    private final AtomicReference<ArrayList<String>> queryRFIDMessage = new AtomicReference<>();


    private String deviceCodes;

    public UniteWebSocket() {
        client = new OkHttpClient.Builder()
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }


    /**
     * 打开webSocket
     *
     * @param deviceCodes 若有多个押运箱deviceCode用分号相隔 例如 ws://192.168.1.65:6001/ws?code=1;2
     */
    public void openSocket(String deviceCodes) {
        this.deviceCodes = deviceCodes;

        Request request = new Request.Builder().url(getBaseUrl() + deviceCodes).build();
        client.newWebSocket(request, createListener());
    }

    private WebSocketListener createListener() {
        return new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                LogUtil.i("onOpen: " + response.toString());
                mWebSocket = webSocket;
                query(deviceCodes);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                JSONObject jsonObject = JSONObject.parseObject(text);
                LogUtil.i("webSocket消息 :" + jsonObject);
                int drt = jsonObject.getInteger("Drt");


                //RFID信息
                if (drt == 4) {
                    ArrayList<String> wb = queryRFIDMessage.get();
                    ArrayList<String> rfidCodeList = (ArrayList<String>) JSONObject.parseArray(jsonObject.getString("Result"), String.class);
                    queryRFIDMessage.compareAndSet(wb, rfidCodeList);
                    return;
                }
                //收取用户消息
                WebSocketMessage webSocketMessage = JSONObject.parseObject(text, WebSocketMessage.class);
                if (drt == 2) {
                    WebSocketMessage wb = message.get();
                    message.compareAndSet(wb, webSocketMessage);

                    if (messageQueue.size() > 18) {
                        while(!messageQueue.isEmpty())
                        {
                            messageQueue.remove();
                        }
                    }
                    //存入阻塞队列
                    try {
                        messageQueue.put(webSocketMessage);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                if (drt == 0 && webSocketMessage.getResult() != null) {
                    if (webSocketMessage.getResult().getDots()[0] == null) {
                        return;
                    } else if (webSocketMessage.getResult().getDots()[0] == 0) {
                        WebSocketMessage wb = startMessage.get();
                        startMessage.compareAndSet(wb, webSocketMessage);
                    } else if (webSocketMessage.getResult().getDots()[0] == 1) {
                        WebSocketMessage wb = endMessage.get();
                        endMessage.compareAndSet(wb, webSocketMessage);
                    } else if (webSocketMessage.getResult().getDots()[0] == 2) {
                        WebSocketMessage wb = openLockMessage.get();
                        openLockMessage.compareAndSet(wb, webSocketMessage);
                    } else if (webSocketMessage.getResult().getDots()[0] == 4) {
                        WebSocketMessage wb = destroyMessage.get();
                        destroyMessage.compareAndSet(wb, webSocketMessage);
                    } else if (webSocketMessage.getResult().getDots()[0] == 5) {
                        WebSocketMessage wb = createLinkedMessage.get();
                        createLinkedMessage.compareAndSet(wb, webSocketMessage);
                    }
                }
            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
                super.onMessage(webSocket, bytes);

            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);

            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);

            }
        };
    }

    //获取基础URL
    private String getBaseUrl() {
        String value ="";
        if (StringUtil.isEmpty(value)) {
            return "ws://61.175.196.174:6001/ws?code=";
        }
        return value + "code=";
    }

    /**
     * deviceCode 是单个设备的设备编号
     *
     * @param deviceCode
     */
    public boolean openLock(String deviceCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("OrderType", 2);
        jsonObject.put("DeviceCode", deviceCode);
        if (mWebSocket == null) {
            return false;
        }
        return sendMessage(jsonObject);

    }

    //关锁
    public boolean closeLock(String deviceCode) {
        if (mWebSocket == null) {
            return false;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("OrderType", 3);
        jsonObject.put("DeviceCode", deviceCode);
        return sendMessage(jsonObject);
    }

    //开始任务
    public boolean startTask(String deviceCode) {
        if (mWebSocket == null) {
            return false;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("OrderType", 0);
        jsonObject.put("DeviceCode", deviceCode);
        return sendMessage(jsonObject);
    }

    //结束任务
    public boolean endTask(String deviceCode) {
        if (mWebSocket == null) {
            return false;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("OrderType", 1);
        jsonObject.put("DeviceCode", deviceCode);
        return sendMessage(jsonObject);

    }

    //灭活
    public boolean destroy(String deviceCode) {
        if (mWebSocket == null) {
            return false;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("OrderType", 4);
        jsonObject.put("DeviceCode", deviceCode);
        return sendMessage(jsonObject);

    }

    //查询
    public boolean query(String deviceCode) {
        if (mWebSocket == null) {
            return false;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("OrderType", 5);
        jsonObject.put("DeviceCode", deviceCode);
        return sendMessage(jsonObject);
    }

    //获取RFID
    public boolean queryRfid(String deviceCode) {
        if (mWebSocket == null) {
            return false;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("OrderType", 6);
        jsonObject.put("DeviceCode", deviceCode);
        return sendMessage(jsonObject);
    }

    private boolean sendMessage(JSONObject jsonObject) {
        LogUtil.i("webSocket 发送消息" + jsonObject.toString());
        return mWebSocket.send(jsonObject.toString());
    }

    //关闭链接
    public void closeSocket() {
        if (mWebSocket != null) {
            mWebSocket.cancel();
        }
    }

    //读取开锁的消息
    public WebSocketMessage readOpenLockMessage() {
        return readMessage(openLockMessage);
    }

    //读取灭活的消息
    public WebSocketMessage readDestroyMessage() {
        return readMessage(destroyMessage);
    }

    //读取开始消息
    public WebSocketMessage readStartMessage() {

        WebSocketMessage message=null ;
        if (messageQueue.isEmpty()) {
            return null;
        }
        try {
            message=messageQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            LogUtil.e("崩溃");
        }
        return message;
    }

    //读取结束消息
    public WebSocketMessage readEndMessage() {
        return readMessage(endMessage);
    }

    //读取RFID信息
    public ArrayList<String> readRFIDMessage() {
        long oldTime = System.currentTimeMillis();
        long newTime = System.currentTimeMillis();
        while ((newTime - oldTime < 10 * 1000)) {
            LogUtil.i("读数据");
            if (queryRFIDMessage.get() != null) {
                LogUtil.i("我退出");
                break;
            }
            newTime = System.currentTimeMillis();
        }
        ArrayList<String> webSocketMessage = queryRFIDMessage.get();
        queryRFIDMessage.compareAndSet(webSocketMessage, null);
        return webSocketMessage;
    }

    //读取创建链接消息
    public WebSocketMessage readCreateLinked() {
        long oldTime = System.currentTimeMillis();
        long newTime = System.currentTimeMillis();
        while ((newTime - oldTime < 10 * 1000)) {
            LogUtil.i("读数据");
            if (createLinkedMessage.get() != null) {
                LogUtil.i("我退出");
                break;
            }
            newTime = System.currentTimeMillis();
        }
        return createLinkedMessage.get();
    }

    //后台自动发送的消息
    public WebSocketMessage readMessage() {
        long oldTime = System.currentTimeMillis();
        long newTime = System.currentTimeMillis();
        while ((newTime - oldTime < 10 * 1000)) {
            if (message.get() != null) {
                break;
            }
            newTime = System.currentTimeMillis();
        }
        WebSocketMessage webSocketMessage = message.get();
        return webSocketMessage;
    }

    //后台自动发送的消息
    public WebSocketMessage readMessage1() {
        WebSocketMessage message=null ;
        try {
            message=messageQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message;
    }


    private WebSocketMessage readMessage(AtomicReference<WebSocketMessage> reference) {
        long oldTime = System.currentTimeMillis();
        long newTime = System.currentTimeMillis();
        while ((newTime - oldTime < 10 * 1000)) {
            if (reference.get() != null) {
                break;
            }
            newTime = System.currentTimeMillis();
        }
        WebSocketMessage webSocketMessage = reference.get();
        reference.compareAndSet(webSocketMessage, null);
        return webSocketMessage;
    }
}
