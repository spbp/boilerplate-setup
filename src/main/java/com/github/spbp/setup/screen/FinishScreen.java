package com.github.spbp.setup.screen;

import org.qdwizard.Screen;

public class FinishScreen extends Screen
{

	private static final long serialVersionUID = -278768917403972461L;

	@Override
	public String getName()
	{
		return "Setup Completed";
	}

	@Override
	public String getDescription()
	{
		return "The boilerplate for your plugin has been created. Happy coding!";
	}

	@Override
	public void initUI()
	{
		this.setCanFinish(true);

	}

}
