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

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.qdwizard.Screen;

import com.github.spbp.setup.components.Hyperlink;
import com.github.spbp.setup.data.Data;

public class BasicsScreen extends Screen
{
    private static final long serialVersionUID = 2372190762416860055L;
    
    private JTextField authorTextField;
    private JTextField urlTextField;
    private JTextField displayNameTextField;
    private JTextField idTextField;
    private JTextField descriptionTextField;
    private JTextField mainPackageTextField;
    
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
        setLayout(new MigLayout("ins 20, wrap 3", "[][][55%]"));

        JLabel label;
        JLabel description;
        JSeparator separator;
        JLabel help;

        label = new JLabel("Author/Organization");
        label.setFont(label.getFont().deriveFont(Font.BOLD));

        authorTextField = new JTextField(System.getProperty("user.name"));

        label.setLabelFor(authorTextField);

        description = new JLabel("<html>Your name or the name of your organization.</html>");

        add(label, Constraints.LABEL);
        add(authorTextField, Constraints.TEXTFIELD);
        add(description, Constraints.DESCRIPTION);

        label = new JLabel("Website");
        label.setFont(label.getFont().deriveFont(Font.BOLD));

        urlTextField = new JTextField("https://example.com");

        label.setLabelFor(urlTextField);

        description = new JLabel("<html>Your website or the website of the plugin.</html>");

        add(label, Constraints.LABEL);
        add(urlTextField, Constraints.TEXTFIELD);
        add(description, Constraints.DESCRIPTION);

        separator = new JSeparator();
        add(separator, Constraints.SEPARATOR);

        label = new JLabel("Plugin Display Name");
        label.setFont(label.getFont().deriveFont(Font.BOLD));

        displayNameTextField = new JTextField("My Plugin");

        label.setLabelFor(displayNameTextField);

        description = new JLabel("<html>The display name of the plugin.</html>");

        add(label, Constraints.LABEL);
        add(displayNameTextField, Constraints.TEXTFIELD);
        add(description, Constraints.DESCRIPTION);

        label = new JLabel("Plugin ID");
        label.setFont(label.getFont().deriveFont(Font.BOLD));

        idTextField = new JTextField("MyPlugin");

        label.setLabelFor(idTextField);

        description = new JLabel("<html>A unique ID for the plugin. The ID is also used as the project name.</html>");

        add(label, Constraints.LABEL);
        add(idTextField, Constraints.TEXTFIELD);
        add(description, Constraints.DESCRIPTION);

        label = new JLabel("Plugin Description");
        label.setFont(label.getFont().deriveFont(Font.BOLD));

        descriptionTextField = new JTextField("A simple Sponge plugin");
        label.setLabelFor(descriptionTextField);

        description = new JLabel("<html>A short one-line description of your plugin.</html>");

        add(label, Constraints.LABEL);
        add(descriptionTextField, Constraints.TEXTFIELD);
        add(description, Constraints.DESCRIPTION);

        label = new JLabel("Main Package");
        label.setFont(label.getFont().deriveFont(Font.BOLD));

        mainPackageTextField = new JTextField("com.example.myplugin");
        label.setLabelFor(mainPackageTextField);

        description = new JLabel("<html>A unique name for the package which contains the plugin main class.</html>");

        add(label, Constraints.LABEL);
        add(mainPackageTextField, Constraints.TEXTFIELD);
        add(description, Constraints.DESCRIPTION);

        separator = new JSeparator();
        add(separator, Constraints.SEPARATOR);

        help = new JLabel("<html><div style=font-size:13><p><b>Choosing the right package name:</b></p>"
                + "<p style=margin-top:5>If you own a domain name: " + "<i>com.yourdomain.pluginname</i></p>"
                + "<p style=margin-top:5>If you want to publish your plugin on GitHub: " + "<i>com.github.accountname.pluginname</i></p>"
                + "<p style=margin-top:5>Alternative: " + "<i>nickname.pluginname</i> or <i>lastname.firstname.pluginname</i></p></div></html>");
        add(help, Constraints.HELP);
        add(new Hyperlink("More Information", "https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html"), Constraints.HELP);
    }
    
    @Override 
    public void onLeave() 
    {
        data.put(Data.AUTHOR, authorTextField.getText());
        data.put(Data.URL, urlTextField.getText());
        data.put(Data.DISPLAY_NAME, displayNameTextField.getText());
        data.put(Data.ID, idTextField.getText());
        data.put(Data.DESCRIPTION, descriptionTextField.getText());
        data.put(Data.MAIN_PACKAGE, mainPackageTextField.getText());
    }
}
