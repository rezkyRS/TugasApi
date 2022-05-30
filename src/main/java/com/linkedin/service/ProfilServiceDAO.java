package com.linkedin.service;

import java.util.List;

import com.linkedin.model.subModel.ProfilSkill;

public interface ProfilServiceDAO {

	List<ProfilSkill> tampilProfil(int page);
	
}
