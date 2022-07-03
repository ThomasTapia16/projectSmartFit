package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.models.Colaborador;

@Service
public class ChangePwd {
	
	public String pwd;
	public String pwd2;
	private Colaborador col;
	
	ChangePwd()
	{
		
	}
	public void setCol(Colaborador colaborador)
	{
		this.col = colaborador;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwd2() {
		return pwd2;
	}
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}
	public Colaborador getCol() {
		return col;
	}
	public boolean ooh()
	{
		if(this.pwd.equals(pwd2)) {
			
			return true;
		}
		else {return false;}
	}
}
