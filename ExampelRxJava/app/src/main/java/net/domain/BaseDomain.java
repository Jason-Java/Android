package net.domain;

public class BaseDomain
{
    private String msg;
    private String message;
    private boolean success;

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public boolean getSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        if (message != null)
        {
            setMsg(message);
        }
        this.message = message;
    }
}
