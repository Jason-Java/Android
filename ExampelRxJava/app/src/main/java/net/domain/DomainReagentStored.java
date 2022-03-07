package net.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DomainReagentStored
{
    private String Total;
    @SerializedName("List")
    private List<Info> infos;

    public String getTotal()
    {
        return Total;
    }

    public void setTotal(String total)
    {
        Total = total;
    }

    public List<Info> getInfos()
    {
        return infos;
    }

    public void setInfos(List<Info> infos)
    {
        this.infos = infos;
    }

    public class Info{
        private Box Box;
        private Container Container;
        private Reagent Reagent;
        private boolean IsProcess;

        public Box getBox()
        {
            return Box;
        }

        public void setBox(Box box)
        {
            Box = box;
        }

        public Container getContainer()
        {
            return Container;
        }

        public void setContainer(Container container)
        {
            Container = container;
        }

        public Reagent getReagent()
        {
            return Reagent;
        }

        public void setReagent(Reagent reagent)
        {
            this.Reagent = reagent;
        }

        public boolean getIssProcess()
        {
            return IsProcess;
        }

        public void setIsProcess(boolean process)
        {
            IsProcess = process;
        }
    }
}

