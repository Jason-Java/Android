package net.domain;

public class DetailData<T> extends BaseDomain
{

    private T response;

    public T getResponse()
    {
        return response;
    }

    public void setResponse(T response)
    {
        this.response = response;
    }

}
