package net.domain;

public class Login extends BaseDomain
{
    private String token;

    private String expires_in;
    private String token_type;

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getExpires_in()
    {
        return expires_in;
    }

    public void setExpires_in(String expires_in)
    {
        this.expires_in = expires_in;
    }

    public String getToken_type()
    {
        return token_type;
    }

    public void setToken_type(String token_type)
    {
        this.token_type = token_type;
    }


}
