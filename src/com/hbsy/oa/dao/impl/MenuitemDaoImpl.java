package com.hbsy.oa.dao.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.hbsy.oa.dao.MenuitemDao;
import com.hbsy.oa.dao.base.impl.BaseDaoImpl;
import com.hbsy.oa.domain.Menuitem;
import com.hbsy.oa.domain.User;
import com.hbsy.oa.utils.OAUtils;


@Repository("menuitemDao")
public class MenuitemDaoImpl extends BaseDaoImpl<Menuitem> implements MenuitemDao<Menuitem>{
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Collection<Menuitem> getMenuitemsByPid(Long pid) {
		return this.getHibernateTemplate().find("from Menuitem m left outer join fetch m.users u where m.pid=?",pid);
	}

	public Collection<Menuitem> getAllMenuitems(Long uid) {
		List<Menuitem> allMenuitems = this.getHibernateTemplate().find("from Menuitem");
		List<Menuitem> userMenuitems =  this.getHibernateTemplate().find("from Menuitem m inner join fetch m.users u where u.uid=?",uid);
		for(Menuitem menuitem:allMenuitems){//鎵�湁鐨勮彍鍗�
			for(Menuitem menuitem2:userMenuitems){//鐢ㄦ埛鑳藉璁块棶鍒扮殑鑿滃崟
				if(menuitem.getMid().longValue()==menuitem2.getMid().longValue()){//鍦ㄦ墍鏈夌殑鑿滃崟涓敤鎴疯兘澶熻闂埌鐨勮彍鍗�
					menuitem.setChecked(true);
				}
			}
		}
		return allMenuitems;
	}

	public Set<Menuitem> getMenuitemsByMIDS(Long[] mids) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Menuitem ");
		stringBuffer.append("where mid in(");
		for(int i=0;i<mids.length;i++){
			if(i<mids.length-1){
				stringBuffer.append(mids[i]+",");
			}else{
				stringBuffer.append(mids[i]);
			}
		}
		stringBuffer.append(")");
		return new HashSet<Menuitem>(this.getHibernateTemplate().find(stringBuffer.toString()));
	}

	public Collection<Menuitem> getMenuitemsByUser() {
		/**
		 * 1銆佸洜涓簎ser鏄粠HttpSession涓緱鍒扮殑
		 * 2銆佽user娌℃湁Session鐜
		 * 3銆佹墍浠ser.getMenuitems()鏄緱涓嶅埌鑿滃崟椤圭殑
		 */
		User user = OAUtils.getUserFromSession();
		if("admin".equals(user.getUsername())){
			return this.getHibernateTemplate().find("from Menuitem");
		}else{
			return this.getHibernateTemplate().find("from Menuitem m inner join fetch m.users u where u.uid=?",user.getUid());
		}
		
	}

}
