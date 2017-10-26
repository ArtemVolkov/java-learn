package ua.lesson.lessons;

import java.util.Date;


public class Procedure{

	private String name;
	private double price;
	private Date date;
	private static int count=0;
	private final int id;
	private int petId=-1;
	public int getId() {
		return id;
	}

	public Procedure(int id,String name, double price){
		this.id=id;
		this.name=name;
		this.price=price;
		this.date=new Date(System.currentTimeMillis());
	}

	public Procedure(int id,String name, double price,Date date, int petId){
		this.id=id;
		this.name=name;
		this.price=price;
		this.date=date;
		this.petId=petId;
	}
	public void set(String name, double price){
		this.name=name;
		this.price=price;
	}

	public int getPetId(){return petId;}
	public void setPetId(int petId){this.petId=petId;}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}
	public Date getProcedureDate(){
		return date;
	}
	public String toString(){
		return name+": "+price+" "+date;
	}
	public boolean equals(Procedure arg){
		return this.name.equals(arg.getName()) && this.price==arg.getPrice();
	}

	public static int generateId(){
		return count++;
	}
}