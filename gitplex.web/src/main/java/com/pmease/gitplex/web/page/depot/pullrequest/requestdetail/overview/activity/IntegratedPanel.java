package com.pmease.gitplex.web.page.depot.pullrequest.requestdetail.overview.activity;

import org.apache.wicket.markup.html.basic.Label;

import com.pmease.gitplex.web.component.AccountLink;
import com.pmease.gitplex.web.component.avatar.AvatarLink;
import com.pmease.gitplex.web.page.depot.pullrequest.requestdetail.overview.ActivityRenderer;
import com.pmease.gitplex.web.util.DateUtils;

@SuppressWarnings("serial")
class IntegratedPanel extends ActivityPanel {

	public IntegratedPanel(String id, ActivityRenderer activity) {
		super(id, activity);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new AvatarLink("avatar", userModel.getObject(), null));
		add(new AccountLink("name", userModel.getObject()));
		
		add(new Label("age", DateUtils.formatAge(renderer.getDate())));
	}

}