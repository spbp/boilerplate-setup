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

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import org.qdwizard.Screen;
import org.qdwizard.Wizard;

import com.github.spbp.setup.screen.*;

public class SetupWizard extends Wizard
{
	private final static List<Class<? extends Screen>> screens = new ArrayList<>();
	
	static 
	{
		screens.add(WelcomeScreen.class);
		screens.add(LayoutScreen.class);
		screens.add(BasicsScreen.class);
		screens.add(OpenSourceScreen.class);
		screens.add(DependencyScreen.class);
		screens.add(MavenScreen.class);
		screens.add(InstallationScreen.class);
		screens.add(FinishScreen.class);
	}
	
	public SetupWizard(JFrame frame)
	{
		super(new Wizard.Builder("Sponge Plugin Boilerplate Setup", WelcomeScreen.class, frame)
		//.icon(new ImageIcon(SetupWizard.class.getResource("/assets/spbp.png")))
		);
	}

	@Override
	public Class<? extends Screen> getPreviousScreen(Class<? extends Screen> screen)
	{
		int index = screens.indexOf(screen);
		
		if(index == 0) return null;
		
		return screens.get(index - 1);
	}

	@Override
	public Class<? extends Screen> getNextScreen(Class<? extends Screen> screen)
	{
		int index = screens.indexOf(screen);
		
		if(index + 1 == screens.size()) return null;
		
		return screens.get(index + 1);
	}

	@Override
	public void finish()
	{
		// TODO Auto-generated method stub
	}
}
