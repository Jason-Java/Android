package net;

public abstract class NetworkResponse<T> implements INetworkResponse<T> {
    @Override
    public boolean error(int e,String msg) {
        return false;
    }
}
