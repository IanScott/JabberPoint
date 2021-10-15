package nl.ou.jp.gui.model;

public interface ProjectorCommand {
	public void execute();
	public String getName();
	void setProjectorMediator(ProjectorMediator mediator);
	void setProjectorConfiguration(ProjectorConfiguration configuration);
}