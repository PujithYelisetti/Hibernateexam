package com.klef.jfsd.HQL;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

public class HQLOperations 
{
	public static void main(String[] args) 
	{	
		HQLOperations operations = new HQLOperations();
		operations.addEmployee();
		//operations.displayallempscompleteobject();
		//operations.displayallempspartialobject();
		//operations.aggregatefunctions();
		//operations.updatepositionalparams();
		//operations.updatenamedparameters();
		//operations.deletepositionalparams();
		//operations.deletenamedparms();
		//operations.hqldemo();
	}
	public void addEmployee()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Employee emp = new Employee();
		emp.setEmpname("MSWD");
		emp.setEmpdesignation("Asst Professor");
		emp.setEmpsalary(19000);
		
		session.persist(emp);
		
		transaction.commit();
		System.out.println("Employee Added Successfully");
		
		session.close();
		sf.close();
	}
	
	public void displayallempscompleteobject()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		String hql = "from Employee";
		Query<Employee>	qry = session.createQuery(hql, Employee.class);
		List<Employee> emps =  qry.getResultList();		
		
		System.out.println("Total Employee ="+emps.size());
		for( Employee e : emps)
		{
			System.out.println("ID:"+e.getEmpid());
			System.out.println("Name:"+e.getEmpname());
			System.out.println("Designation:"+e.getEmpdesignation());
			System.out.println("Salary:"+e.getEmpsalary());
		}
		session.close();
		sf.close();
	}
	
	public void displayallempspartialobject()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		String hql = "select e.empid,e.empname,e.empsalary from Employee e"; 
		//e is an alias or reference object of type Employee
		
		Query<Object[]> qry =  session.createQuery(hql, Object[].class);
		List<Object[]> emps = qry.getResultList();		
		
		System.out.println("Total Employee ="+emps.size());
		
		for(Object[] obj:emps)
		{
			System.out.println("ID:"+obj[0]);
			System.out.println("Name:"+obj[1]);
			System.out.println("Salary:"+obj[2]);
		}
		session.close();
		sf.close();
	}
	public void aggregatefunctions()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		
		String hql1 = "select count(*) from Employee";
		Query<Long> qry1 = session.createQuery(hql1,Long.class);
	    Long count = qry1.getSingleResult();
	    System.out.println("Total Employee Count="+count);
		
		String hql2 = "select sum(empsalary) from Employee";
		Query<Double> qry2 = session.createQuery(hql2,Double.class);
		double totalcount = qry2.getSingleResult();
		System.out.println("Total Employees Salary="+totalcount);
		
		String hql3 = "select avg(empsalary) from Employee";
		Query<Double> qry3 = session.createQuery(hql3,Double.class);
		double avg = qry3.getSingleResult();
		System.out.println("Average Salary="+avg);
		
		String hql4 = "select min(empsalary) from Employee";
		Query<Double> qry4 = session.createQuery(hql4,Double.class);
		double minsal = qry4.getSingleResult();
		System.out.println("Minimum Salary="+minsal);

		String hql5 = "select max(empsalary) from Employee";
		Query<Double> qry5 = session.createQuery(hql5,Double.class);
		double maxsal = qry5.getSingleResult();
		System.out.println("Maximum Salary="+maxsal);
		
		session.close();
		sf.close();
		
	}
	
	public void updatepositionalparams()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		Scanner sc =  new Scanner(System.in);
		
		System.out.println("Enter Employee ID");
		int eid = sc.nextInt();
		System.out.println("Enter Employee name");
		String ename = sc.next();
		System.out.println("Enter employee salary");
		double esal = sc.nextDouble();
		
		String hql = "update Employee set empname=?1, empsalary=?2 where empid = ?3";
		MutationQuery qry = session.createMutationQuery(hql);
		qry.setParameter(1, ename);
		qry.setParameter(2, esal);
		qry.setParameter(3, eid);

		int n = qry.executeUpdate();
		if(n > 0)
		{
			System.out.println("Employee updated successfully");
		}
		else
		{
			System.out.println("Employee ID not found");
		}
		
		transaction.commit();
		
		session.close();
		sf.close();
	}
	
	public void updatenamedparameters()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		Scanner sc =  new Scanner(System.in);
		
		System.out.println("Enter Employee ID");
		int eid = sc.nextInt();
		System.out.println("Enter Employee name");
		String ename = sc.next();
		System.out.println("Enter employee salary");
		double esal = sc.nextDouble();
		
		String hql = "update Employee set empname=:v1, empsalary=:v2 where empid =:v3";
		MutationQuery qry = session.createMutationQuery(hql);
		qry.setParameter("v1", ename);
		qry.setParameter("v2", esal);
		qry.setParameter("v3", eid);

		int n = qry.executeUpdate();
		if(n > 0)
		{
			System.out.println("Employee updated successfully");
		}
		else
		{
			System.out.println("Employee ID not found");
		}
		
		transaction.commit();
		
		session.close();
		sf.close();
	}
	public void deletepositionalparams()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Scanner sc =  new Scanner(System.in);
		
		System.out.println("Enter Employee ID:");
		int eid = sc.nextInt();
		
		String hql = "delete from Employee where empid = ?1";
		
		MutationQuery qry = session.createMutationQuery(hql);
		qry.setParameter(1, eid);

		int n = qry.executeUpdate();
		if(n > 0)
		{
			System.out.println("Employee Deleted successfully");
		}
		else
		{
			System.out.println("Employee ID not found");
		}
		
		transaction.commit();
		
		session.close();
		sf.close();
	}
	
	public void deletenamedparms()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		Scanner sc =  new Scanner(System.in);
		
		System.out.println("Enter Employee ID");
		int eid = sc.nextInt();
		
		String hql = "delete from Employee where empid = :v";
		MutationQuery qry = session.createMutationQuery(hql);
		qry.setParameter("v", eid);

		int n = qry.executeUpdate();
		if(n > 0)
		{
			System.out.println("Employee deleted successfully");
		}
		else
		{
			System.out.println("Employee ID not found");
		}
		
		transaction.commit();
		
		session.close();
		sf.close();
	}
	
	//Display employee objects based on designation(professor) and salary should be >= 10000
	public void hqldemo()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		String hql = "from Employee where empdesignation=?1 and empsalary>=?2";
		
		Query<Employee>	qry = session.createQuery(hql, Employee.class);
		
		qry.setParameter(1, "Professor");
		qry.setParameter(2, 10000);
		
		List<Employee> emps =  qry.getResultList();		
		
		System.out.println("Total Employee ="+emps.size());
		
		for( Employee e : emps)
		{
			System.out.println("ID:"+e.getEmpid());
			System.out.println("Name:"+e.getEmpname());
			System.out.println("Designation:"+e.getEmpdesignation());
			System.out.println("Salary:"+e.getEmpsalary());
		}
		session.close();
		sf.close();
	}
}
