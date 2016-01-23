package com.modelgarden.common;

public class ResultMessage
{
    public static final int RESULT_SUCCEED = 0;
    public static final int RESULT_FAILED = 1;
    
    private int result;
    private Object message = "";
    private String error = "";
    
    public int getResult()
    {
        return result;
    }
    
    public void setResult(int result)
    {
        this.result = result;
    }

    public Object getMessage()
    {
        return message;
    }

    public void setMessage(Object message)
    {
        this.message = message;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }
}
