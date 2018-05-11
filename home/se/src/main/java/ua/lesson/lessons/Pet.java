package ua.lesson.lessons;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "pet")
@Access(AccessType.FIELD)
public class Pet{

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pid")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private String type;

	private static int count=0;


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private Client client;

	@Column(name = "age")
	private int age=-1;

	// it`s list of procedures with this pet
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="pet", cascade = CascadeType.ALL, targetEntity = Procedure.class)
	private List<Procedure> proceduresList=new ArrayList<Procedure>();

	@Column(name= "summaryprice")
	//summaryPrice it`s cost of all procedures with this pet
	private Double summaryPrice=0D;

	public Pet(){

	}

	public Pet(int id,String name,String type){
		this.id=id;
		this.name=name;
		this.type=type;
		proceduresList=new ArrayList<Procedure>();
	}
	public Pet(int id,String name,String type, ArrayList<Procedure> procList){
		this.id=id;
		this.name=name;
		this.type=type;
		this.proceduresList=procList;
		for(Procedure p: proceduresList){
			summaryPrice+=p.getPrice();
		}
	}
	public Pet(int id,String name,String type, ArrayList<Procedure> procList, Client client, int age){
		this.id=id;
		this.name=name;
		this.type=type;
		this.proceduresList=procList;
		for(Procedure p: proceduresList){
			summaryPrice+=p.getPrice();
		}
		this.client=client;
		this.age=age;
	}

	public void addProcedure(String name, double price){
		Procedure proc=new Procedure(Procedure.generateId(),name,price);
		proc.setPet(this);
		proceduresList.add(proc);
		summaryPrice+=price;
	}
	public void addProcedure(Procedure procedure){
		proceduresList.add(procedure);
		summaryPrice+=procedure.getPrice();
		procedure.setPet(this);

	}
	/**
	 * Method removes procedure from list of procedures
	 * @return true if remove success, otherwise return false;
	 */
	public boolean removeProcedureByName(String name){
		boolean result=false;
		for(Procedure p: proceduresList){
			if(p.getName().equals(name)){
				summaryPrice-=p.getPrice();
				proceduresList.remove(p);
				result=true;
				break;
			}
		}
		return result;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setSummaryPrice(double summaryPrice) {
		this.summaryPrice = summaryPrice;
	}
	public double getSummaryPrice() {
		return summaryPrice;
	}
	public double getPetPrice(){
		return this.getSummaryPrice();
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getName(){
		return name;
	}



	public void setType(String type) {
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setAge(int age){this.age=age;}

	public int getAge(){return age;}

	public void setProceduresList(ArrayList<Procedure> proceduresList) {
		this.proceduresList = proceduresList;
	}

	public List<Procedure> getProceduresList() {
		return proceduresList;
	}

	public List<Procedure> getProcedures(){
		return this.getProceduresList();
	}

	public String toShortString(){
		return name+": "+summaryPrice;
	}
	public String toString(){
		return name+": "+proceduresList+": "+ summaryPrice;
	}

	/**
	 * Method compares 2 pets
	 * @param arg pet to compare
	 * @return true if pets are equals or false otherwise
	 */
	@Override
	public boolean equals(Object arg){
		if(arg == null) return false;
		if(this == arg) return true;
		if (!(arg instanceof Pet)) return false;


		Pet p= (Pet)arg;

		return this.getId() == p.getId() &&
				this.getType().equals(p.getType()) &&
				this.getName().equals(p.getName()) &&
				this.getAge() == p.getAge() &&
				this.getClient().getId() == p.getClient().getId() &&
				this.compareProcedures(p);

	}

	@Override
	public int hashCode(){
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.getId();
		result = PRIME * result + (this.getName() == null? 0: this.getName().hashCode());
		result = PRIME * result + (this.getType() == null? 0: this.getType().hashCode());
		result = PRIME * result + this.getAge();
		result = PRIME * result + this.getClient().getId();
		result = PRIME * result + (this.getProceduresList() == null? 0: this.getProceduresList().hashCode());
		return result;
	}

	private boolean compareProcedures(Pet arg){
		if(this.proceduresList.size()!= arg.getProcedures().size()) return false;

		boolean result=true;
		for(int i=0;i<proceduresList.size();i++){
			if(!proceduresList.get(i).equals(arg.getProcedures().get(i))){
				result=false;
				break;
			}
		}
		return result;
	}


	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public static int generateId(){
		return count++;
	}
	public int getClientId(){
		return this.client.getId();
	}
}