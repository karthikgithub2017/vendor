package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="VEN_TAB")
public class Vendor {
	@Id
	@Column(name="v_id")
	private int venId;
	@Column(name="v_name")
	private String venName;
	@Column(name="v_mail")
	private String venMail;
	@Column(name="v_mobile")
	private String mobile;
	@Column(name="v_addr")
	private String address;
	
	@ManyToOne
	@JoinColumn(name="locFk")
	private Location loc;

	public Vendor() {
	}
	public Vendor(int venId) {
		this.venId = venId;
	}
	public int getVenId() {
		return venId;
	}
	public void setVenId(int venId) {
		this.venId = venId;
	}
	public String getVenName() {
		return venName;
	}
	public void setVenName(String venName) {
		this.venName = venName;
	}
	public String getVenMail() {
		return venMail;
	}
	public void setVenMail(String venMail) {
		this.venMail = venMail;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Location getLoc() {
		return loc;
	}
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	@Override
	public String toString() {
		return "Vendor [venId=" + venId + ", venName=" + venName + ", venMail="
				+ venMail + ", mobile=" + mobile + ", address=" + address
				+ ", loc=" + loc + "]";
	}
	
	
}
