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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.qdwizard.Screen;

import com.github.spbp.setup.data.Data;

public class WelcomeScreen extends Screen
{

    private static final long serialVersionUID = 993838440211268881L;

    private JTextField workspaceDirTextField;
    private JFileChooser chooser;
    private JLabel warning;


    @Override
    public String getName()
    {
        return "Welcome";
    }

    @Override
    public String getDescription()
    {
        return "This setup wizard will help you to get started with Sponge plugin development.";
    }

    @Override
    public void initUI()
    {
        setLayout(new MigLayout("ins 20, wrap 1"));

        add(new JLabel("<html><p>Plese select the root directory for the workspace of your new plugin project.</html>"), "grow");

        File defaultFile = new java.io.File("");
        File defaultDirectory = new java.io.File(".");

        chooser = new JFileChooser();
        chooser.setSelectedFile(defaultFile);
        chooser.setCurrentDirectory(defaultDirectory);
        chooser.setDialogTitle("Select Directory...");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        JPanel panel = new JPanel(new MigLayout("ins 0"));

        workspaceDirTextField = new JTextField(40);
        panel.add(workspaceDirTextField);

        workspaceDirTextField.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                validateInput();
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
            }
        });

        JButton button = new JButton("Choose...");

        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                chooser.showOpenDialog(null);

                updateTextField();
            }
        });

        panel.add(button);

        add(panel);

        warning = new JLabel();
        warning.setForeground(Color.RED);
        add(warning);

        updateTextField();
    }

    @Override 
    public boolean onNext() 
    {
        data.put(Data.WORKSPACE_DIR, new File(workspaceDirTextField.getText()));
        return true;
    }

    private void updateTextField()
    {
        workspaceDirTextField.setText(chooser.getSelectedFile().getAbsolutePath());

        validateInput();
    }

    private void validateInput()
    {
        //validate input
        File file = new File(workspaceDirTextField.getText());
        if (!file.exists())
        {
            warning.setText("Directory does not exist: The installer will attempt to create it.");
            setProblem(null);
        }
        else if (file.isDirectory())
        {
            if (file.list().length != 0) warning.setText("Warning: Directory not empty. Files may be overwritten!");
            else warning.setText("");
            setProblem(null);
            return;
        }

        else if (file.isFile())
        {
            setProblem("The selected path is not a directory!");
            warning.setText("");
            return;
        }
    }
}
