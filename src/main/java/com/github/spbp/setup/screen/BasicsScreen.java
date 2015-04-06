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

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.qdwizard.Screen;

import com.github.spbp.setup.form.FormEntry;

public class BasicsScreen extends Screen
{
	private static final long serialVersionUID = 2372190762416860055L;

	@Override
	public String getName()
	{
		return "Project Information";
	}

	@Override
	public String getDescription()
	{
		return "Enter the basic information about your plugin.";
	}

	@Override
	public void initUI()
	{
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(15, 15, 15, 15));
        
	    FormEntry[] entries = {
	            new FormEntry(
	                    "Plugin Name", 
	                    "The display name of the plugin. Should be a single word.", 
	                    "MyPlugin", 
	                    "pluginName"),
                new FormEntry(
                        "Plugin ID", 
                        "A unique ID for the plugin to be used internally",
                        "myplugin", 
                        "pluginId"), 
                new FormEntry(
                        "Main Package", 
                        "<html><p>A unique name for the package which contains the plugin main class.</p>"
                        + "<p style=margin-top:5>If you own a domain name:</p>"
                        + "<i>com.yourdomain.pluginname</i><br>"
                        + "<p style=margin-top:5>If you want to publish your plugin on GitHub:</p>"
                        + "<i>com.github.yourname.pluginname</i><br>"
                        + "<p style=margin-top:5>Alternative:</p>"
                        + "<i>yournickname.pluginname</i></html>",
                        "com.example.myplugin", 
                        "mainPackage") 
	    };
        
        
        JPanel panel = this;
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 0;
        
        for (FormEntry entry : entries) {
            JLabel label = new JLabel(entry.getLabel(), JLabel.RIGHT);
            label.setFont(new Font(label.getFont().getFontName(), Font.BOLD, label.getFont().getSize()));
            c.gridx = 0;
            c.weightx = 0;
            panel.add(label, c);
            
            JTextField field = new JTextField(entry.getText(), 20);
            label.setLabelFor(field);
            c.gridx = 1;
            c.weightx = 0.4;
            panel.add(field, c);
            
            JLabel description = new JLabel(entry.getDescription(), JLabel.LEFT);
            c.gridx = 2;
            c.weightx = 0.3;
            panel.add(description, c);
            
            c.gridy++;
        }
	}

}
