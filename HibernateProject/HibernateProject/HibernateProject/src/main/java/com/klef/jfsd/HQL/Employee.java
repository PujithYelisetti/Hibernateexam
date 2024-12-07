package com.klef.jfsd.HQL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_table")
public class Employee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empid;
	@Column(name = "emp_name",length = 30,nullable = false)
	private String empname;
	@Column(name = "emp_designation",length = 30,nullable = false)
	private String empdesignation;
	@Column(name = "emp_salary",length = 10,nullable = false)
	private double empsalary;
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Employee(int empid, String empname, String empdesignation, double empsalary) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.empdesignation = empdesignation;
		this.empsalary = empsalary;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpdesignation() {
		return empdesignation;
	}
	public void setEmpdesignation(String empdesignation) {
		this.empdesignation = empdesignation;
	}
	public double getEmpsalary() {
		return empsalary;
	}
	public void setEmpsalary(double empsalary) {
		this.empsalary = empsalary;
	}
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", empname=" + empname + ", empdesignation=" + empdesignation
				+ ", empsalary=" + empsalary + "]";
	}
	
	
}
