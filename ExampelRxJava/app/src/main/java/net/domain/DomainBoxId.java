package net.domain;

import java.util.List;

public class DomainBoxId
{
    private Box BoxInfo;
    private BoxModel BoxModel;
    private BoxType Boxtype;
    private int TotalHole;
    private int UsedHole;
    private int ReceivedNum;
    private int ExpiriedNum;
    private int ExpirieSoon;
    private int Capacity;
    private List<ContainerInfo> Containers;


    public Box getBoxInfo()
    {
        return BoxInfo;
    }

    public void setBoxInfo(Box boxInfo)
    {
        BoxInfo = boxInfo;
    }

    public  BoxModel getBoxModel()
    {
        return BoxModel;
    }

    public void setBoxModel(  BoxModel boxModel)
    {
        BoxModel = boxModel;
    }

    public BoxType getBoxtype()
    {
        return Boxtype;
    }

    public void setBoxtype(BoxType boxtype)
    {
        Boxtype = boxtype;
    }

    public int getTotalHole()
    {
        return TotalHole;
    }

    public void setTotalHole(int totalHole)
    {
        TotalHole = totalHole;
    }

    public int getUsedHole()
    {
        return UsedHole;
    }

    public void setUsedHole(int usedHole)
    {
        UsedHole = usedHole;
    }

    public int getReceivedNum()
    {
        return ReceivedNum;
    }

    public void setReceivedNum(int receivedNum)
    {
        ReceivedNum = receivedNum;
    }

    public int getExpiriedNum()
    {
        return ExpiriedNum;
    }

    public void setExpiriedNum(int expiriedNum)
    {
        ExpiriedNum = expiriedNum;
    }

    public int getExpirieSoon()
    {
        return ExpirieSoon;
    }

    public void setExpirieSoon(int expirieSoon)
    {
        ExpirieSoon = expirieSoon;
    }

    public int getCapacity()
    {
        return Capacity;
    }

    public void setCapacity(int capacity)
    {
        Capacity = capacity;
    }

    public List<ContainerInfo> getContainers()
    {
        return Containers;
    }

    public void setContainers(List<ContainerInfo> containers)
    {
        Containers = containers;
    }
}
