package com.github.spbp.setup.screen;

import org.qdwizard.Screen;

public class InstallationScreen extends Screen
{

	private static final long serialVersionUID = -3610374783949451091L;

	@Override
	public String getName()
	{
		return "Installing";
	}

	@Override
	public String getDescription()
	{
		return "Please wait while the setup is configuring your plugin workspace.";
	}

	@Override
	public void initUI()
	{
		this.setCanCancel(false);

	}

}
