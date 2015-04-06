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
package com.github.spbp.setup;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.qdwizard.Screen;
import org.qdwizard.Wizard;

import com.github.spbp.setup.screen.*;

public class BoilerplateSetupWizard extends Wizard
{
	private final static List<Class<? extends Screen>> SCREENS = new ArrayList<>();
	
    private final static List<Image> ICONS = new ArrayList<>();
	
	static 
	{
		SCREENS.add(WelcomeScreen.class);
		SCREENS.add(LayoutScreen.class);
		SCREENS.add(BasicsScreen.class);
		SCREENS.add(OpenSourceScreen.class);
		SCREENS.add(DependencyScreen.class);
		SCREENS.add(MavenScreen.class);
		SCREENS.add(InstallationScreen.class);
		SCREENS.add(FinishScreen.class);
		
		ICONS.add(new ImageIcon(BoilerplateSetupWizard.class.getResource("/assets/spbp_16.png")).getImage());
	    ICONS.add(new ImageIcon(BoilerplateSetupWizard.class.getResource("/assets/spbp_32.png")).getImage());
	    ICONS.add(new ImageIcon(BoilerplateSetupWizard.class.getResource("/assets/spbp_48.png")).getImage());
	    ICONS.add(new ImageIcon(BoilerplateSetupWizard.class.getResource("/assets/spbp_64.png")).getImage());
	    
	}
	
    private final static ImageIcon WIZARD_ICON = new ImageIcon(BoilerplateSetupWizard.class.getResource("/assets/gradle.png"));
	
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        JFrame frame = new JFrame("Sponge Boilerplate Setup");
        frame.setIconImages(ICONS);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        BoilerplateSetupWizard wizard = new BoilerplateSetupWizard(frame);
        wizard.show();

        System.exit(0);
    }

    public BoilerplateSetupWizard(JFrame frame)
	{
		super(new Wizard.Builder("Sponge Plugin Boilerplate Setup", WelcomeScreen.class, frame)
		.icon(WIZARD_ICON)
		);
	}

	@Override
	public Class<? extends Screen> getPreviousScreen(Class<? extends Screen> screen)
	{
		int index = SCREENS.indexOf(screen);
		
		if(index == 0) return null;
		
		return SCREENS.get(index - 1);
	}

	@Override
	public Class<? extends Screen> getNextScreen(Class<? extends Screen> screen)
	{
		int index = SCREENS.indexOf(screen);
		
		if(index + 1 == SCREENS.size()) return null;
		
		return SCREENS.get(index + 1);
	}

	@Override
	public void finish()
	{
	}
}
