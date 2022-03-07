package net.domain;

import java.util.List;

public class DomainReagentModel
{
    private int  Total;
    private List<ReagentTemplate> Data;

    public int getTotal()
    {
        return Total;
    }

    public void setTotal(int total)
    {
        Total = total;
    }

    public List<ReagentTemplate> getData()
    {
        return Data;
    }

    public void setData(List<ReagentTemplate> data)
    {
        Data = data;
    }
}
