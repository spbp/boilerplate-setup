package com.github.spbp.setup.data;

public enum License
{
    NONE("<none>"), 
    MIT("MIT License"), 
    APACHE_2("Apache 2.0 License"), 
    GPL_2("GPL v2.0 License"), 
    LGPL_2_1("LGPL v2.1 License"), 
    PUBLIC_DOMAIN("Public Domain"), 
    ALL_RIGHTS_RESERVED("All Rights Reserved");
    
    private final String displayName;

    private License(String displayName)
    {
        this.displayName = displayName;
    }

    @Override
    public String toString() 
    {
        return displayName;
    }
}
