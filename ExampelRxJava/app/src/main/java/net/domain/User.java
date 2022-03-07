package net.domain;

public class User
{
    private String UserName;
    private String Pwd;
    private String UserRealName;
    private String Mobile;
    private String Email;
    private String Status;
    private String SignPic;
    private String AvatarPath;
    private String CompanyId;
    private String VerifyCode;
    private String VerifyTime;
    private String Id;

    public String getUserName()
    {
        return UserName == null ? "" : UserName;
    }

    public void setUserName(String userName)
    {
        UserName = userName;
    }

    public String getPwd()
    {
        return Pwd == null ? "" :Pwd;
    }

    public void setPwd(String pwd)
    {
        Pwd = pwd;
    }

    public String getUserRealName()
    {
        return UserRealName == null ? "" :UserRealName;
    }

    public void setUserRealName(String userRealName)
    {
        UserRealName = userRealName;
    }

    public String getMobile()
    {
        return Mobile == null ? "" :Mobile;
    }

    public void setMobile(String mobile)
    {
        Mobile = mobile;
    }

    public String getEmail()
    {
        return Email == null ? "" :Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }

    public String getStatus()
    {
        return Status == null ? "" :Status;
    }

    public void setStatus(String status)
    {
        Status = status;
    }

    public String getSignPic()
    {
        return SignPic == null ? "" :SignPic;
    }

    public void setSignPic(String signPic)
    {
        SignPic = signPic;
    }

    public String getAvatarPath()
    {
        return AvatarPath == null ? "" :AvatarPath;
    }

    public void setAvatarPath(String avatarPath)
    {
        AvatarPath = avatarPath;
    }

    public String getCompanyId()
    {
        return CompanyId == null ? "" :CompanyId;
    }

    public void setCompanyId(String companyId)
    {
        CompanyId = companyId;
    }

    public String getVerifyCode()
    {
        return VerifyCode == null ? "" :VerifyCode;
    }

    public void setVerifyCode(String verifyCode)
    {
        VerifyCode = verifyCode;
    }

    public String getVerifyTime()
    {
        return VerifyTime == null ? "" :VerifyTime;
    }

    public void setVerifyTime(String verifyTime)
    {
        VerifyTime = verifyTime;
    }

    public String getId()
    {
        return Id == null ? "" : Id;
    }

    public void setId(String id)
    {
        Id = id;
    }
}
