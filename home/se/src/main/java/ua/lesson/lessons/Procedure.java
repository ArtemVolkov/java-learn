package ua.lesson.lessons;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "procedure")
public class Procedure{

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pcid")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "coast")
	private double price;

	@Column(name = "date")
	private Date date;

	private static int count=0;


	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_id" )
	private Pet pet;

	public Procedure(){

	}

	public Procedure(int id,String name, double price){
		this.id=id;
		this.name=name;
		this.price=price;
		this.date=new Date(System.currentTimeMillis());
	}

	public Procedure(int id,String name, double price,Date date, Pet pet){
		this.id=id;
		this.name=name;
		this.price=price;
		this.date=date;
		this.pet=pet;
	}
	public void set(String name, double price){
		this.name=name;
		this.price=price;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}

	public Date getProcedureDate(){
		return date;
	}
	public String toString(){
		return name+": "+price+" "+date;
	}

	@Override
	public boolean equals(Object arg){
		if(arg == null) return false;
		if(this == arg) return true;
		if(!(arg instanceof Procedure)) return false;

		Procedure p = (Procedure)arg;
		return this.getId()== p.getId() &&
				this.getPet().getId() == p.getPet().getId() &&
				this.getName().equals(p.getName()) &&
				this.dateCompare(p.getDate());
	}

	@Override
	public int hashCode(){
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.getId();
		result = PRIME * result + this.getPet().getId();
		result = PRIME * result + (this.getName() == null? 0: this.getName().hashCode());
		result = PRIME * result + (this.getProcedureDate() == null? 0: this.getProcedureDate().hashCode());
		return  result;
	}

	public static int generateId(){
		return count++;
	}

	public int getPetId(){
		return this.pet.getId();
	}


	private boolean dateCompare(Date arg){
		boolean b1 = this.date.getDate() == arg.getDate();
		boolean b2 = this.date.getHours() == arg.getHours();
		boolean b3 = this.date.getMinutes() == arg.getMinutes();
		boolean b4 = this.date.getSeconds() == arg.getSeconds();

		return b1 && b2 && b3 && b4;
	}
}