package com.linkedin.model.subModel;

import javax.persistence.Id;

import lombok.Data;

@Data
public class ProfilDenganSkill {
	@Id
	private int id;
	private String nama;
	private String universitas;
	private String tahun_lulus;
	private String jk;
	private String no_hp;
}
