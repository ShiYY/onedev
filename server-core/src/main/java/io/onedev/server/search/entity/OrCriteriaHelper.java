package io.onedev.server.search.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import io.onedev.server.model.AbstractEntity;


public class OrCriteriaHelper<T extends AbstractEntity> extends EntityCriteria<T> {
	
	private static final long serialVersionUID = 1L;

	private final List<? extends EntityCriteria<T>> criterias;
	
	public OrCriteriaHelper(List<? extends EntityCriteria<T>> criterias) {
		this.criterias = criterias;
	}

	@Override
	public Predicate getPredicate(Root<T> root, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		for (EntityCriteria<T> criteria: criterias)
			predicates.add(criteria.getPredicate(root, builder));
		return builder.or(predicates.toArray(new Predicate[0]));
	}

	@Override
	public boolean matches(T entity) {
		for (EntityCriteria<T> criteria: criterias) {
			if (criteria.matches(entity))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<criterias.size(); i++) {
			EntityCriteria<T> criteria = criterias.get(i);
			if (i != 0)
				builder.append(" or ");
			if (criteria instanceof ParensAware) {
				builder.append("(");
				builder.append(criteria.toString());
				builder.append(")");
			} else {
				builder.append(criteria.toString());
			}
		}
		return builder.toString().trim();
	}
	
}
