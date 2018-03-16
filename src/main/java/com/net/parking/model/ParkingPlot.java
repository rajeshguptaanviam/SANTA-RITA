package com.net.parking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "parking_plot")
public class ParkingPlot implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "PP", strategy = "com.net.parking.model.generator.ParkingPlotIDGenerator")
	@GeneratedValue(generator = "PP")  
	@Column(name = "id", length = 255, nullable = false)
	private String id;
	
	@Column(name = "encendidoDelSistema")
	private Integer encendidoDelSistema;
	
	@Column(name = "modoDeOperacionManual")
	private Integer modoDeOperacionManual;
	
	@Column(name = "plot_name")
	private String plotName;
	
	@Column(name = "userid", unique = true)
	private String userid;
	
	@Column(name = "parking_space")
	private String space;
	
	@Column(name = "createdby")
	private String createdBy = "DEV";
	
	@Column(name = "modifiedby")
	private String modifiedby = "DEV";
	
	@Column(name = "createdon")
	private String createdon;
	
	@Column(name = "modifiedon")
	private String modifiedon;
	
	public ParkingPlot() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getEncendidoDelSistema() {
		return encendidoDelSistema;
	}

	public void setEncendidoDelSistema(Integer encendidoDelSistema) {
		this.encendidoDelSistema = encendidoDelSistema;
	}

	public Integer getModoDeOperacionManual() {
		return modoDeOperacionManual;
	}

	public void setModoDeOperacionManual(Integer modoDeOperacionManual) {
		this.modoDeOperacionManual = modoDeOperacionManual;
	}

	public String getPlotName() {
		return plotName;
	}

	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public String getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(String modifiedon) {
		this.modifiedon = modifiedon;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
