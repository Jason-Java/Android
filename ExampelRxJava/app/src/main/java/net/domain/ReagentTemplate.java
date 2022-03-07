package net.domain;

public class ReagentTemplate
{
   private String  CommonName;
   private String  Label;
   private String  Classification;
   private String  CAS;
   private String  Alias;
   private String  Model;
   private String  Consistency;
   private String  Category;
   private String  Brand;
   private String  Vender;
   private String  ArtNo;
   private String  ModelId;

    private String format(String str)
    {
        return str == null ? "" : str;
    }

    public String getCommonName()
    {
        return format(CommonName);
    }

    public String getLabel()
    {
        return format(Label);
    }

    public String getClassification()
    {
        return format(Classification);
    }

    public String getCAS()
    {
        return format(CAS);
    }

    public String getAlias()
    {
        return format(Alias);
    }

    public String getModel()
    {
        return format(Model);
    }

    public String getConsistency()
    {
        return format(Consistency);
    }

    public String getCategory()
    {
        return format(Category);
    }

    public String getBrand()
    {
        return format(Brand);
    }

    public String getVender()
    {
        return format(Vender);
    }

    public String getArtNo()
    {
        return format(ArtNo);
    }

    public String getModelId()
    {
        return format(ModelId);
    }



}
