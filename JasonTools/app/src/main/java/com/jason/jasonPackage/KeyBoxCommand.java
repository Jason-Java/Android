package com.jason.jasonPackage;

import com.jason.jasontools.commandbus.AbsCommand;
import com.jason.jasontools.commandbus.IMessageListener;
import com.jason.jasontools.commandbus.IProtocol;
import com.jason.jasontools.serialport.IResultListener;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月22日
 */
public class KeyBoxCommand extends AbsCommand {

    private IProtocol protocol;

    public KeyBoxCommand(IMessageListener<IProtocol> messageListener) {
        super(messageListener);
    }

    public KeyBoxCommand(IMessageListener<IProtocol> messageListener, IProtocol protocol) {
        super(messageListener);
        this.protocol = protocol;
    }

    @Override
    protected void execute() {
        DeviceKeyBoxSp.getInstance().registerListener(new IResultListener() {
            @Override
            public void onResult(IProtocol iProtocol) {
                stopCheckTimeOutThread();
                if (getRepeaterListener() != null) {
                    getRepeaterListener().onNext();
                    setRepeaterListener(null);
                }
                IMessageListener<IProtocol> listener = getMessageListener();
                if(listener != null)
                    listener.success(iProtocol);
                setMessageListener(null);
            }
        });
        DeviceKeyBoxSp.getInstance().sendData(protocol);
    }

    @Override
    public boolean getRepeater() {
        return true;
    }

    @Override
    protected boolean isStartCheckTimeOutThread() {
        return true;
    }
}
