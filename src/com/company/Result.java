package com.company;

/**
 * Created by Magdalena Polak on 06.04.2016.
 */
public class Result
{
    String a;
    int k;

    public Result(String a, int k)
    {
        this.a = a;
        this.k = k;
    }
    public String toString ()
    {
        return a + " " +  k;
    }
}