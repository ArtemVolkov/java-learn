package ua.lesson.lessons;

import java.util.ArrayList;
import java.util.Date;

public class Client{
	//name - client name
	private String name;

	public int getId() {
		return id;
	}

	private final int id;
	private static int count=0;
	private ArrayList<Pet> petsList;

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private double summaryCost=0;
	private String password;
	public Client(int id,String name, String password){
		this.id=id;
		this.name=name;
		this.password=password;
		petsList=new ArrayList<Pet>();
	}

	public Client(int id, String name, ArrayList<Pet> petList, String password){
		this.id=id;
		this.name=name;
		this.password=password;
		this.petsList=petList;
		for(Pet p: petsList){
			summaryCost+=p.getPetPrice();
		}
	}

	/**
	 * Method create new Pet in client petslist
	 * @param name name of Pet
	 * @param type type of Pet
	 * @throws UserException if new Pet already exist in this client
	 * @throws IllegalArgumentException if name or type == null
	 */
	public void addNewPet(String name,String type) throws UserException, IllegalArgumentException{
		if(name=="Null" || name==null||type=="Null"|| type==null)
			throw new IllegalArgumentException("Name or Type can`t be null or == \'null\'");

		Pet possiblePet=this.searchByPetNameAndType(name,type);
		// if this pet does not exist in this client
		if(possiblePet.getName().equals("Null") && possiblePet.getType().equals("Null")){
			petsList.add(new Pet(Pet.generateId(),name,type));
			petsList.get(petsList.size()-1).setClientId(this.getId());
		}
		else{
			throw new UserException("This pet already exist in this client!");
		}
	}

	/**
	 * Method create new Pet in client petslist
	 * @param p new Pet to add this list
	 * @throws UserException if new Pet already exist in this client
	 * @throws IllegalArgumentException if name or type == null
	 */
	public void addNewPet(Pet p)throws UserException,IllegalArgumentException{
		if(p.getName()=="Null" || p.getName()==null ||
				p.getType()=="Null" || p.getType()==null)
			throw new IllegalArgumentException("Name or Type can`t be null or == \'null\'");
		boolean flag=false;
		for(Pet pet:petsList){
			if(pet.equals(p)){
				flag=true;
				break;
			}
		}
		if(!flag){
			petsList.add(p);
			summaryCost+=p.getPetPrice();
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
		for(Pet p: petsList){
			if(p.equals(pet)){
				summaryCost-=p.getPetPrice();
				petsList.remove(p);
				result=true;
				break;
			}
		}
		return result;
	}


	/**
	 * Method search client pet by name
	 * @return Pet or Pet with name "Null" if pets doesn`t exist
	 */
	public Pet searchByPetName(String petName){
		Pet result=new Pet(-1,"Null","Null");
		for(Pet p: petsList)
			if(p.getName().equals(petName)){
				result=p;
				break;
			}
		return result;
	}
	public Pet searchByPetNameAndType(String petName, String petType){
		Pet result=new Pet(-1,"Null","Null");
		for(Pet p: petsList)
			if(p.getName().equals(petName) && p.getType().equals(petType)){
				result=p;
				break;
			}
		return result;
	}
	public void SetName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	public double getSummaryCost(){
		return summaryCost;
	}
	public ArrayList<Pet> getPetsList() {
		return petsList;
	}
	public String getPassword() {	return password;	}

	public boolean equals(Client arg){
		boolean result=false;
		if(this.name.equals(arg.name) &&
				this.comparePets(arg)&&
				this.summaryCost==arg.summaryCost)
			result=true;
		return result;
	}

	private boolean comparePets(Client arg){
		if(this.petsList.size()!= arg.getPetsList().size()) return false;

		boolean result=true;
		for(int i=0;i<petsList.size();i++){
			if(!petsList.get(i).equals(arg.getPetsList().get(i))){
				result=false;
				break;
			}
		}
		return result;
	}

	public String toString(){
		StringBuilder result=new StringBuilder("");
		result.append(this.name+ " {");
		for(int i=0;i<petsList.size();i++){
			result.append(petsList.get(i));
			if(i<petsList.size()-1)
				result.append(", ");
		}
		result.append(" } " + this.summaryCost );
		return result.toString();
	}

	public static int generateId(){
		return count++;
	}
}