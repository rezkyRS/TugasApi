package com.linkedin.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.linkedin.model.Profil;
import com.linkedin.model.subModel.DataProfil;
import com.linkedin.model.subModel.EditProfil;
import com.linkedin.model.subModel.ProfilDenganSkill;
import com.linkedin.model.subModel.ProfilSkill;
import com.linkedin.repository.ProfilDAO;

@Repository
public class ProfilDAOImpl implements ProfilDAO{
	
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public int save(Profil p) {
		int profil = jdbc.update("insert into profil(nama, id_universitas, id_tahun_lulus, id_jk, no_hp)"
				+ "values"
				+ "(?,?,?,?,?)", new Object[] {p.getNama(),p.getId_universitas(),p.getId_tahun_lulus(),p.getId_jk(),p.getNo_hp()});
		return profil;
	}

	@Override
	public int saveData(DataProfil dp) {
		int profil = jdbc.update("insert into profil(nama, id_universitas, id_tahun_lulus, id_jk, no_hp)"
				+ "values"
				+ "(?,?,?,?,?)", new Object[] {dp.getNama(),dp.getId_universitas(),dp.getId_tahun_lulus(),dp.getId_jk(),dp.getNo_hp()});
		int skill1 = jdbc.update("insert into list_skill(id_profil,id_skill) values((select id from profil order by id desc limit 1),?)", new Object[] {dp.getId_skill1()});
		int skill2 = jdbc.update("insert into list_skill(id_profil,id_skill) values((select id from profil order by id desc limit 1),?)", new Object[] {dp.getId_skill2()});
		int skill3 = jdbc.update("insert into list_skill(id_profil,id_skill) values((select id from profil order by id desc limit 1),?)", new Object[] {dp.getId_skill3()});
		int skill4 = jdbc.update("insert into list_skill(id_profil,id_skill) values((select id from profil order by id desc limit 1),?)", new Object[] {dp.getId_skill4()});
		int skill5 = jdbc.update("insert into list_skill(id_profil,id_skill) values((select id from profil order by id desc limit 1),?)", new Object[] {dp.getId_skill5()});
		int delskill = jdbc.update("delete from list_skill where id_skill=0 or id_profil=0");
		return profil + skill1 + skill2 + skill3 + skill4 + skill5 + delskill;
	}

	@Override
	public DataProfil tampilData(int page) {
		
		return jdbc.queryForObject("select * from profil limit 10", new BeanPropertyRowMapper<DataProfil>(DataProfil.class), page);
	}

	@Override
	public List<DataProfil> tampilProfil(int page) {
		List<DataProfil> dp =jdbc.query("select * from profil limit 10 offset ?", new BeanPropertyRowMapper<DataProfil>(DataProfil.class), page);
	
		return  dp;
	}

	@Override
	public List<ProfilSkill> profilSkill(int page) {
		List<ProfilSkill> dp =jdbc.query("SELECT p.id,p.nama, u.nama as universitas, tl.tahun_lulus as lulusan,jk.jk,p.no_hp,(SELECT\r\n"
				+ "group_concat(s.nama) as \"skill\" \r\n"
				+ "FROM list_skill ls inner join skill s on s.id=ls.id_skill where ls.id_profil=p.id group by id_profil) as skill\r\n"
				+ "FROM ((profil p INNER JOIN \r\n"
				+ "jenis_kelamin jk ON p.id_jk = jk.id) INNER JOIN \r\n"
				+ "universitas u ON p.id_universitas = u.id) INNER JOIN \r\n"
				+ "tahun_lulus tl ON p.id_tahun_lulus = tl.id limit 10 offset ?\r\n"
				+ "", new BeanPropertyRowMapper<ProfilSkill>(ProfilSkill.class), page);
		return dp;
	}

	@Override
	public List<ProfilDenganSkill> tampilProfilSkill(int id) {
		
		return jdbc.query("SELECT p.id, p.nama,u.nama as universitas, tl.tahun_lulus, jk.jk, p.no_hp\r\n"
				+ "FROM (((profil p INNER JOIN list_skill ls ON p.id = ls.id_profil)\r\n"
				+ "INNER JOIN jenis_kelamin jk ON p.id_jk = jk.id)\r\n"
				+ "INNER JOIN tahun_lulus tl ON p.id_tahun_lulus = tl.id)\r\n"
				+ "INNER JOIN universitas u ON p.id_universitas = u.id \r\n"
				+ "where ls.id_skill=? ORDER BY p.id ASC", new BeanPropertyRowMapper<ProfilDenganSkill>(ProfilDenganSkill.class),id);
	}

	@Override
	public int edit(EditProfil ep,int id) {
		int profil = jdbc.update("UPDATE profil SET nama=?,id_universitas = ?, id_tahun_lulus= ?, id_jk=?, no_hp=? WHERE id = ?",
				new Object[] {ep.getNama(),ep.getId_universitas(),ep.getId_tahun_lulus(),ep.getId_jk(),ep.getNo_hp(),id});
		int delskill = jdbc.update("delete from list_skill where id_profil=?",id);
		int skill1 = jdbc.update("insert into list_skill(id_profil,id_skill) values(?,?)", new Object[] {id,ep.getId_skill1()});
		int skill2 = jdbc.update("insert into list_skill(id_profil,id_skill) values(?,?)", new Object[] {id,ep.getId_skill2()});
		int skill3 = jdbc.update("insert into list_skill(id_profil,id_skill) values(?,?)", new Object[] {id,ep.getId_skill3()});
		int skill4 = jdbc.update("insert into list_skill(id_profil,id_skill) values(?,?)", new Object[] {id,ep.getId_skill4()});
		int skill5 = jdbc.update("insert into list_skill(id_profil,id_skill) values(?,?)", new Object[] {id,ep.getId_skill5()});
		int delskill2 = jdbc.update("delete from list_skill where id_skill=0");
		return profil + delskill + skill1 + skill2 + skill3 + skill4 + skill5 + delskill2;
	}

	@Override
	public int delete(int id) {
		int delprofil = jdbc.update("delete from profil where id=?",id);
		int delskill = jdbc.update("delete from list_skill where id_profil=?",id);
		return delprofil + delskill;
	}

}
