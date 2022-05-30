package com.linkedin.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkedin.model.subModel.ProfilSkill;
import com.linkedin.repository.ProfilDAO;
import com.linkedin.service.ProfilServiceDAO;

@Service
public class ProfilServiceDAOImpl implements ProfilServiceDAO {

	@Autowired
	ProfilDAO profilDAO;

	@Override
	public List<ProfilSkill> tampilProfil(int page) {
		if (page == 1) {
			page = 0;
		} else {
			page = (page-1)*10;
		}
		return profilDAO.profilSkill(page);
	}


	
	

}
