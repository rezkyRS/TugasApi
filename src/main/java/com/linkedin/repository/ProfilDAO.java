package com.linkedin.repository;

import java.util.List;

import com.linkedin.model.Profil;
import com.linkedin.model.subModel.DataProfil;
import com.linkedin.model.subModel.EditProfil;
import com.linkedin.model.subModel.ProfilDenganSkill;
import com.linkedin.model.subModel.ProfilSkill;

public interface ProfilDAO {

	public int save(Profil p);
	
	public int saveData(DataProfil dp);
	
	public DataProfil tampilData(int page);
	
	List<DataProfil> tampilProfil(int page);
	
	List<ProfilSkill> profilSkill(int page);

	List<ProfilDenganSkill> tampilProfilSkill(int id);

	public int edit(EditProfil ep, int id);

	public int delete(int id);
	
}
