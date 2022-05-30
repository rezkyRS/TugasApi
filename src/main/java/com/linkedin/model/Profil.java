package com.linkedin.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Profil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nama;
	private int id_universitas;
	private int id_tahun_lulus;
	private int id_jk;
	private String no_hp;
}
