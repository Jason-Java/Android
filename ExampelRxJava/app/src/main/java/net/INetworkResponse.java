package net;

public interface INetworkResponse<T>
{
    void success(T value);

    boolean error(int e,String msg);

}