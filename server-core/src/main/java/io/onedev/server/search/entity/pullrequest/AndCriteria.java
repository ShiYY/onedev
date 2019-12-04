package io.onedev.server.search.entity.pullrequest;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import io.onedev.server.model.PullRequest;

import io.onedev.server.search.entity.AndCriteriaHelper;
import io.onedev.server.search.entity.EntityCriteria;
import io.onedev.server.search.entity.ParensAware;

public class AndCriteria extends EntityCriteria<PullRequest> implements ParensAware {
	
	private static final long serialVersionUID = 1L;

	private final List<EntityCriteria<PullRequest>> criterias;
	
	public AndCriteria(List<EntityCriteria<PullRequest>> criterias) {
		this.criterias = criterias;
	}

	@Override
	public Predicate getPredicate(Root<PullRequest> root, CriteriaBuilder builder) {
		return new AndCriteriaHelper<PullRequest>(criterias).getPredicate(root, builder);
	}

	@Override
	public boolean matches(PullRequest request) {
		return new AndCriteriaHelper<PullRequest>(criterias).matches(request);
	}

	@Override
	public String toString() {
		return new AndCriteriaHelper<PullRequest>(criterias).toString();
	}
	
}
