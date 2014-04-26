package com.hbsy.oa.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbsy.oa.dao.KynamicDao;
import com.hbsy.oa.domain.Kynamic;
import com.hbsy.oa.domain.Version;
import com.hbsy.oa.service.KynamicService;


@Service("kynamicService")
public class KynamicServiceImpl implements KynamicService{
	@Resource(name="kynamicDao")
	private KynamicDao kynamicDao;

	@Transactional(readOnly=false)
	public void saveKynamic(Kynamic kynamic) {
		this.kynamicDao.saveEntry(kynamic);
	}

	public Collection<Kynamic> getKynamics() {
		// TODO Auto-generated method stub
		return this.kynamicDao.getAllEntry();
	}

	public Kynamic getKynamicByName(String name) {
		// TODO Auto-generated method stub
		return this.kynamicDao.getKynamicByName(name);
	}

	@Transactional(readOnly=false)
	public void updateKynamic(Kynamic kynamic) {
		// TODO Auto-generated method stub
		this.kynamicDao.updateEntry(kynamic);
	}

	public Kynamic getKynamicById(Long kid) {
		// TODO Auto-generated method stub
		return (Kynamic)this.kynamicDao.getEntryByID(kid);
	}

	@Transactional(readOnly=false)
	public void deleteKynamicByID(Long kid) {
		// TODO Auto-generated method stub
		this.kynamicDao.deleteEntry(kid);
	}

	public Collection<Version> getVersionsByKid(Long kid) {
		// TODO Auto-generated method stub
		return this.kynamicDao.getVersionsByKid(kid);
	}

	@Transactional(readOnly=false)
	public void saveVersionByKid(Version version) {
		Long currentVersion = this.kynamicDao.getCurrentVersionByKid(version.getKynamic().getKid());
		version.setVersionid(currentVersion);
		this.kynamicDao.saveEntry(version);
	}
}
