package com.linkedin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.linkedin.model.subModel.DataProfil;
import com.linkedin.model.subModel.EditProfil;
import com.linkedin.model.subModel.ProfilDenganSkill;
import com.linkedin.model.subModel.ProfilSkill;
import com.linkedin.repository.ProfilDAO;
import com.linkedin.service.ProfilServiceDAO;

@Component
@RestController
public class ProfilControler {
	
	@Autowired
	ProfilDAO profilDAO;
	
	@Autowired
	ProfilServiceDAO profilServiceDAO;

	@PostMapping("/postProfilSkill")
	public String save(@RequestBody DataProfil dp)
	{
		return profilDAO.saveData(dp)+" data berhasil disimpan";
	}
	
	@GetMapping("/profil/{page}")
	public List<ProfilSkill> tampilProfil(@PathVariable int page)
	{
		return profilServiceDAO.tampilProfil(page);
	}
	
	@GetMapping("/profilWithSkill/{id}")
	public List<ProfilDenganSkill> tampilProfilSkill(@PathVariable int id)
	{
		return profilDAO.tampilProfilSkill(id);
	}
	
	@PostMapping("/editProfilSkill/{id}")
	public String edit(@PathVariable int id,@RequestBody EditProfil ep)
	{
		return profilDAO.edit(ep,id)+" data berhasil diedit";
	}
	
	@PostMapping("/deleteProfil/{id}")
	public String delete(@PathVariable int id)
	{
		return profilDAO.delete(id)+" data berhasil dihapus";
	}
	
}
