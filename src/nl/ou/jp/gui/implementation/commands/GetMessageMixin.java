package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorConfiguration;

public interface GetMessageMixin {
	public default String getMessage(ProjectorConfiguration configuration, String id) {
		return configuration.getMessage(id);
	}
}
