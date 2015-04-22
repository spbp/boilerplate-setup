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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import net.miginfocom.swing.MigLayout;

import org.qdwizard.Screen;
import org.qdwizard.Wizard;

import com.github.spbp.setup.data.Data;
import com.github.spbp.setup.data.Status;

public class InstallationScreen extends Screen
{
    private static final long serialVersionUID = -3610374783949451091L;

    private JProgressBar progressBar;

    @Override
    public String getName()
    {
        return "Installing";
    }

    @Override
    public String getDescription()
    {
        return null;
    }

    @Override
    public void initUI()
    {
        setLayout(new MigLayout("ins 20, wrap 1", "[100%]"));

        add(new JLabel("Please wait while the setup is configuring your plugin workspace."));

        progressBar = new JProgressBar();
        add(progressBar, "growx, height 25");
    }
    
    public void onEnter() 
    {
        if(data.get(Status.INSTALLED) != null || data.get(Status.SHIT_HAPPENED) != null) setInstalled();
        
        copyFiles();
    }
    
    public void onCancelled() 
    {
        undoCopyFiles();
    }

    public void setProgress(int n)
    {
        progressBar.setValue(n);
    }
    
    public void copyFiles() 
    {
        File workspaceDir = (File) data.get(Data.WORKSPACE_DIR);
        
        File testFile = new File(workspaceDir, "hello.txt");
        
        try
        {
            testFile.createNewFile();
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, "An error occurred during the installation:\n" + e.getMessage());
            setShitHappened();
            undoCopyFiles();
            return;
        }
        setInstalled();
    }

    public void undoCopyFiles()
    {
    }
    
    public void setShitHappened()
    {
        data.put(Status.SHIT_HAPPENED, true);
        setInstalled();
    }

    public void setInstalled()
    {
        data.put(Status.INSTALLED, true);
        
        setProgress(100);
        
        Timer timer = new Timer();
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                forceNext();
            }
          }, 1000);
    }
    
    private void forceNext() 
    {
        // a little hack to automatically go to the next screen
        try
        {
            Field wizardField = Screen.class.getDeclaredField("wizard");
            wizardField.setAccessible(true);
            Wizard wizard = (Wizard) wizardField.get(this);

            Method screenMethod = Wizard.class.getDeclaredMethod("setScreen", Class.class);

            screenMethod.setAccessible(true);
            screenMethod.invoke(wizard, FinishScreen.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
