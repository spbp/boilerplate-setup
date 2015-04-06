package com.github.spbp.setup.form;

public class FormEntry
{
    private final String label;
    private final String description;
    private final String text;
    private final String name;
    
    public FormEntry(String label, String description, String text, String name)
    {
        this.label = label;
        this.description = description;
        this.text = text;
        this.name = name;
    }

    public String getLabel()
    {
        return label;
    }

    public String getDescription()
    {
        return description;
    }

    public String getText()
    {
        return text;
    }

    public String getName()
    {
        return name;
    }
    
}
