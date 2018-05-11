package ua.lesson.lessons;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "client")
@Access(AccessType.FIELD)
public class Client{

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private int id;

	//name - client name
	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@OneToMany(fetch = FetchType.EAGER,  mappedBy = "client" , cascade = CascadeType.ALL, targetEntity = Pet.class)
	private List<Pet> pets =new ArrayList<Pet>();





	private static int count=0;
	//private double summaryCost=0;

	public Client(){

	}

	public Client(int id,String name, String password){
		this.id=id;
		this.name=name;
		this.password=password;
	}

	public Client(int id, String name, ArrayList<Pet> petList, String password){
		this.id=id;
		this.name=name;
		this.password=password;
		this.pets =petList;
		/*for(Pet p: petsList){
			summaryCost+=p.getPetPrice();
		}*/
	}

	/**
	 * Method creates new Pet in client petslist
	 * @param name name of Pet
	 * @param type type of Pet
	 * @throws UserException if new Pet already exist in this client
	 * @throws IllegalArgumentException if name or type == null
	 */
	public void addNewPet(String name,String type) throws UserException, IllegalArgumentException{
		if(name.toLowerCase().equals("null") || name == null ||
                type.toLowerCase().equals("null") || type == null)
			throw new IllegalArgumentException("Name or Type can`t be null or == \'null\'");

		Pet possiblePet=this.searchByPetNameAndType(name,type);
		// if this pet does not exist in this client
		if(possiblePet.getName().equals("Null") && possiblePet.getType().equals("Null")){
			pets.add(new Pet(Pet.generateId(),name,type));
			pets.get(pets.size()-1).setClient(this);
		}
		else{
			throw new UserException("This pet already exist in this client!");
		}
	}

	/**
	 * Method creates new Pet in client petslist
	 * @param p new Pet to add this list
	 * @throws UserException if new Pet already exist in this client
	 * @throws IllegalArgumentException if name or type == null
	 */
	public void addNewPet(Pet p)throws UserException,IllegalArgumentException{
		if(p.getName().toLowerCase().equals("null") || p.getName()==null ||
				p.getType().toLowerCase().equals("null") || p.getType()==null)
			throw new IllegalArgumentException("Name or Type can`t be null or == \'null\'");
		boolean flag=false;
		for(Pet pet: pets){
			if(pet.equals(p)){
				flag=true;
				break;
			}
		}
		if(!flag){
			pets.add(p);
			//summaryCost+=p.getPetPrice();
			p.setClient(this);
		}
		else
			throw new UserException("This pet already exist on this client");
	}

	/**
	 * Method removes pet from list of client pets
	 * @return true if remove success, otherwise return false;
	 */
	public boolean removePet(Pet pet){
		boolean result=false;
		for(Pet p: pets){
			if(p.equals(pet)){
			//	summaryCost-=p.getPetPrice();
				pets.remove(p);
				result=true;
				break;
			}
		}
		return result;
	}


	/**
	 * Method searches client pet by name
	 * @return Pet or Pet with name "Null" if pets doesn`t exist
	 */
	public Pet searchByPetName(String petName){
		Pet result=new Pet(-1,"Null","Null");
		for(Pet p: pets)
			if(p.getName().equals(petName)){
				result=p;
				break;
			}
		return result;
	}
	public Pet searchByPetNameAndType(String petName, String petType){
		Pet result=new Pet(-1,"Null","Null");
		for(Pet p: pets)
			if(p.getName().equals(petName) && p.getType().equals(petType)){
				result=p;
				break;
			}
		return result;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getName(){
		return this.name;
	}


	public void setPets(ArrayList<Pet> petsList) {
		this.pets = petsList;
	}

	public List<Pet> getPets() {
		return pets;
	}

	/*public void setSummaryCost(double summaryCost) {
		this.summaryCost = summaryCost;
	}
	public double getSummaryCost(){
		return summaryCost;
	}*/

	public void setPassword(String password) {
		this.password = password;
	}


	public String getPassword() {	return password;	}




	@Override
	public boolean equals(Object arg){
		if(arg== null) return false;
		if(this==arg) return true;
		if(!(arg instanceof Client)) return false;

		Client cl=(Client)arg;
		return 	this.getId() == cl.getId() &&
				this.getName().equals(cl.getName()) &&
				//this.getSummaryCost() == cl.getSummaryCost() &&
				this.getPassword().equals(cl.getPassword()) &&
				this.comparePets(cl);

	}

	@Override
	public int hashCode(){
		final int PRIME =31;
		int result=1;
		result = PRIME * result + this.getId();
		result = PRIME * result + (this.getName() == null? 0: this.getName().hashCode());
		result = PRIME * result + (this.getPassword() == null? 0: this.getPassword().hashCode());
		result = PRIME * result + (this.getPets() == null? 0: this.getPets().hashCode());
		//result = PRIME * result + ((int) Math.round(this.getSummaryCost()));
		return result;
	}

	/**
	 * This method comparing pets of this client
	 * @param arg client to compare
	 * @return true if petsList is equal
	 */
	private boolean comparePets(Client arg){
		if(this.pets.size()!= arg.getPets().size()) return false;

		boolean result=true;
		for(int i = 0; i< pets.size(); i++){
			if(!pets.get(i).equals(arg.getPets().get(i))){
				result=false;
				break;
			}
		}
		return result;
	}

	public String toString(){
		StringBuilder result=new StringBuilder("");
		result.append(this.name+ " {");
		for(int i = 0; i< pets.size(); i++){
			result.append(pets.get(i));
			if(i< pets.size()-1)
				result.append(", ");
		}
		//result.append(" } " + this.summaryCost );
		result.append(" } " );
		return result.toString();
	}

	public static int generateId(){
		return count++;
	}
}