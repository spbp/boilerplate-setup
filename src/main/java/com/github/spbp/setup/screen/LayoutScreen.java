/*
 * This file is part of boilerplate-setup, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015 SPBP <https://github.com/spbp/>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.spbp.setup.screen;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;

import org.qdwizard.Screen;

import com.github.spbp.setup.data.Data;
import com.github.spbp.setup.data.Layout;

public class LayoutScreen extends Screen
{

    private static final long serialVersionUID = -2155056712495474857L;
    
    private JRadioButton layoutSimpleRadioButton;
    private JRadioButton layoutMultiplatformRadioButton;
    private JRadioButton layoutApiPluginRadioButton;

    @Override
    public String getName()
    {
        return "Project Layout";
    }

    @Override
    public String getDescription()
    {
        return "Choose between different layouts for different use cases.";
    }

    @Override
    public void initUI()
    {
        setLayout(new MigLayout("ins 20, wrap 2", "[]30[100%]"));

        JLabel description;
        JSeparator separator;

        ButtonGroup buttonGroup = new ButtonGroup();

        layoutSimpleRadioButton = new JRadioButton("Simple Layout", true);
        buttonGroup.add(layoutSimpleRadioButton);

        description = new JLabel("<html>The recommended layout. <br>" + "Creates a simple Gradle project for your Sponge plugin.</html>");

        add(layoutSimpleRadioButton, Constraints.RADIO_BUTTON);
        add(description, Constraints.DESCRIPTION);

        separator = new JSeparator();
        add(separator, Constraints.SEPARATOR);

        layoutMultiplatformRadioButton = new JRadioButton("Multiplatform Layout", false);
        buttonGroup.add(layoutMultiplatformRadioButton);

        description = new JLabel("<html>Choose this if you want to develop a plugin for multiple server platforms. <br>"
                + "Creates a Gradle project with 3 subprojects ('Core', 'Sponge' and 'Bukkit').</html>");

        add(layoutMultiplatformRadioButton, Constraints.RADIO_BUTTON);
        add(description, Constraints.DESCRIPTION);

        separator = new JSeparator();
        add(separator, Constraints.SEPARATOR);

        layoutApiPluginRadioButton = new JRadioButton("Plugin-with-API Layout", false);
        buttonGroup.add(layoutApiPluginRadioButton);

        description = new JLabel("<html>Choose this if you want to develop a Sponge plugin with a separate API. <br>"
                + "Creates a Gradle project with 2 subprojects ('API' and 'Plugin').</html>");

        add(layoutApiPluginRadioButton, Constraints.RADIO_BUTTON);
        add(description, Constraints.DESCRIPTION);
    }
    
    @Override 
    public void onLeave() 
    {
        Layout layout;
        
        if(layoutSimpleRadioButton.isSelected()) layout = Layout.SIMPLE;
        else if(layoutMultiplatformRadioButton.isSelected()) layout = Layout.MULTIPLATFORM; 
        else layout = Layout.API_PLUGIN;
        
        data.put(Data.LAYOUT, layout);
    }
}
