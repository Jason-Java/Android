package net.domain;

import java.util.List;

public class DetailList<T> extends BaseDomain
{
    private List<T> response;

    public List<T> getResponse()
    {
        return response;
    }

    public void setResponse(List<T> response)
    {
        this.response = response;
    }


}
