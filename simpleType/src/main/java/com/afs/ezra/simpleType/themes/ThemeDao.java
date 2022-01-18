package com.afs.ezra.simpleType.themes;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.afs.ezra.simpleType.common.AbstractDao;

@Repository
public class ThemeDao extends AbstractDao{
	
	@Transactional
	public Theme getTheme(String themeName) {
		@SuppressWarnings("deprecation")
		Criteria cr = getSession().createCriteria(Theme.class);
		cr.add(Restrictions.eq("name", themeName));
		List<Theme> result = cr.list();

		if(result.size() == 0) throw new EntityNotFoundException();
		
		return result.get(0);
	}
	
	@Transactional
	public List<Theme> getAllThemes() {
		return getSession().createQuery("From Theme T Order by T.name", Theme.class).list();
	}

	@Transactional
	public void saveTheme(Theme theme) {
		persist(theme);
	}
	
	@Transactional
	public void updateTheme(Theme theme) {
		getSession().update(theme);
	}
	
	@Transactional
	public void deleteTheme(String themeName) {
		Query query = getSession().createQuery("Delete From Theme T Where T.name = :name");
		query.setParameter("name", themeName);
		query.executeUpdate();
	}
}
