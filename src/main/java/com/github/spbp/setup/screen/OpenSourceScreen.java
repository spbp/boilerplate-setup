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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;

import org.qdwizard.Screen;

import com.github.spbp.setup.components.Hyperlink;
import com.github.spbp.setup.data.Data;
import com.github.spbp.setup.data.License;

public class OpenSourceScreen extends Screen
{
    private static final long serialVersionUID = 400642662641678145L;

    private JComboBox<License> licenseComboBox;
    private JCheckBox licenseHeadersCheckBox;
    private JCheckBox gitignoreCheckBox;
    private JCheckBox readmeCheckBox;
    
    @Override
    public String getName()
    {
        return "Open Source";
    }

    @Override
    public String getDescription()
    {
        return "Prepare your plugin source code for publishing.";
    }

    @Override
    public void initUI()
    {
        setLayout(new MigLayout("ins 20, wrap 2", "[]30[100%]"));

        JLabel description;
        JSeparator separator;
        
        JLabel help;
        JPanel panel;

        licenseComboBox = new JComboBox<License>(License.values());
        
        licenseComboBox.setSelectedIndex(1);

        panel = new JPanel(new MigLayout("wrap 1, ins 0"));

        panel.add(
                new JLabel(
                        "<html>Choose a license for your source code. The Sponge project uses the MIT license, which is a short, permissive license.</html>"),
                        Constraints.DESCRIPTION);
        panel.add(new Hyperlink("More Information About Licenses", "http://choosealicense.com/licenses/"), Constraints.DESCRIPTION);

        add(licenseComboBox, Constraints.COMBO_BOX);
        add(panel, "");

        licenseHeadersCheckBox = new JCheckBox("Add License Headers", true);

        final JLabel lhDescription = new JLabel("<html>Configure Gradle to add license headers to your source files (licenseFormat task).</html>");

        licenseComboBox.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent event)
            {
                if (event.getStateChange() == ItemEvent.SELECTED)
                {
                    boolean license = !event.getItem().equals("<none>");
                    licenseHeadersCheckBox.setEnabled(license);
                    lhDescription.setEnabled(license);
                }
            }
        });

        add(licenseHeadersCheckBox, Constraints.CHECK_BOX);
        add(lhDescription, Constraints.DESCRIPTION);

        separator = new JSeparator();
        add(separator, Constraints.SEPARATOR);

        gitignoreCheckBox = new JCheckBox("Create .gitignore", true);
        gitignoreCheckBox.setName("gitignore");

        description = new JLabel("<html>Adds a default .gitignore file for Java projects.</html>");

        add(gitignoreCheckBox, Constraints.CHECK_BOX);
        add(description, Constraints.DESCRIPTION);
        


        readmeCheckBox = new JCheckBox("Create README.md", true);
        readmeCheckBox.setName("readme");

        description = new JLabel("<html>Adds a README file with the plugin description.</html>");

        add(readmeCheckBox, Constraints.CHECK_BOX);
        add(description, Constraints.DESCRIPTION);

        separator = new JSeparator();
        add(separator, Constraints.SEPARATOR);

        help = new JLabel("<html><div style=font-size:13><p><b>Share your source code with the Sponge Community!</b></p>"
                + "<p style=margin-top:5>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, "
                + "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, "
                + "sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. "
                + "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</p></div></html>");
        add(help, Constraints.HELP);

        add(new Hyperlink("Tutorial: How to publish your plugin code on GitHub", "http://github.com/"), Constraints.HELP); //TODO find/make tutorial
        //TODO add more information/tutorials

    }
    
    @Override
    public void onLeave() 
    {
        data.put(Data.LICENSE, (License) licenseComboBox.getSelectedItem());
        data.put(Data.LICENSE_HEADERS, licenseHeadersCheckBox.isSelected());
        data.put(Data.GITIGNORE, gitignoreCheckBox.isSelected());
        data.put(Data.README, readmeCheckBox.isSelected());
    }

}
