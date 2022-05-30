package com.linkedin.model.subModel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class DataProfil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nama;
	private int id_universitas;
	private int id_tahun_lulus;
	private int id_jk;
	private String no_hp;
	private int id_skill1;
	private int id_skill2;
	private int id_skill3;
	private int id_skill4;
	private int id_skill5;
	
}