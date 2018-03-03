package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUST_TAB")
public class Customer {
	@Id
	@Column(name="cid")
	private int custId;
	@Column(name="cname")
	private String custName;
	@Column(name="email")
	private String custEmail;
	@Column(name="ctype")
	private String custType;
	@Column(name="caddr")
	private String custAddr;
	@Column(name="pwd")
	private String password;
	@Column(name="token")
	private String accToken;
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName
				+ ", custEmail=" + custEmail + ", custType=" + custType
				+ ", custAddr=" + custAddr + ", password=" + password
				+ ", accToken=" + accToken + "]";
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getCustAddr() {
		return custAddr;
	}
	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccToken() {
		return accToken;
	}
	public void setAccToken(String accToken) {
		this.accToken = accToken;
	}
	
	
}
