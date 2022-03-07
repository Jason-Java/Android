package net.domain;

public class BoxType
{
   private String  Code;
   private String  Name;
   private String  TargetTypeId;
   private String  TargetType;
   private String  Id;

   private String format(String str)
   {
       return str == null ? "" : str;
   }

    public String getCode()
    {
        return format(Code);
    }

    public String getName()
    {
        return format(Name);
    }

    public String getTargetTypeId()
    {
        return format(TargetTypeId);
    }

    public String getTargetType()
    {
        return format(TargetType);
    }

    public String getId()
    {
        return format(Id);
    }
}
