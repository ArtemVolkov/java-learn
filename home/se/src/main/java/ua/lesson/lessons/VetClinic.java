package ua.lesson.lessons;

import ua.lesson.lessons.UserException;

import java.util.ArrayList;
import java.util.Date;

public class VetClinic{
	private ArrayList<Client> clients=new ArrayList<Client>();

	/**
	 * Method create new client in list of clients
	 * @param name name of client
	 * @throws UserException  if client with this name already exist
	 * @throws IllegalArgumentException  if name is null or == "Null"
	 */
	public void addNewClient(String name)throws UserException,IllegalArgumentException{
		if(name.equals("Null") || name == null){
			throw new IllegalArgumentException("Name of client can`t be Null");
		}
		boolean flag=false;
		Client possibleClient=new Client(Client.generateId(),name, "123");
		for(Client c:clients)
			if(c.equals(possibleClient))
				flag = true;

		if(!flag)
			clients.add(possibleClient);
		else
			throw new UserException("Client with this name already exist!");
	}

	public void addNewClient(Client client)throws UserException,IllegalArgumentException{
		if(client.getName().equals("Null") || client.getName() == null){
			throw new IllegalArgumentException("Name of client can`t be Null");
		}
		boolean flag=false;
		for(Client c:clients)
			if(c.equals(client))
				flag = true;

		if(!flag)
			clients.add(client);
		else
			throw new UserException("Client with this name already exist!");
	}

	/**
	 * Method removes client from list of clients
	 * @return true if remove success, otherwise return false;
	 * @param client client to remove
	 */
	public boolean removeClient(Client client){
		boolean result=false;
		for(Client c: clients){
			if(c.equals(client)){
				clients.remove(c);
				result=true;
				break;
			}
		}
		return result;
	}

	/**
	 * Method removes client from list of clients
	 * @return true if remove success, otherwise return false;
	 * @param name name of client
	 */
	public boolean removeClient(String name){
		boolean result=false;
		for(Client c: clients){
			if(c.getName().equals(name)){
				clients.remove(c);
				result=true;
				break;
			}
		}
		return result;
	}

	public ArrayList<Client> getClients(){
		return clients;
	}

	/**
	 * Method search client by his name
	 * @return Searched client or client with name "Null" if client doesn`t exist
	 */
	public Client searchClientByName(String clientName){
		Client result=new Client(-1,"Null", "Null");
		for(Client client:clients){
			if(client.getName().equals(clientName)){
				result=client;
				break;
			}
		}
		return result;
	}

	/**
	 * Method search client by his pet name, if 2 or more client has pet with this name
	 * method return first found client, if you need to get all clients use "seachClientsByPetName'
	 * @return First finded client or client with name "Null" if client doesn`t found
	 */
	public Client searchClientByPetName(String petName){
		Client result=new Client(-1,"Null", "Null");
		for(Client c:clients){
			Pet temp=c.searchByPetName(petName);
			if(!temp.getName().equals("Null")){
				result=c;
				break;
			}
		}
		return result;
	}

	/**
	 * Method find all clients who have pet with equal name
	 * @param petName - name of pet
	 * @return ArrayList with finded clients
	 */
	public ArrayList<Client> searchAllClientsByPetName(String petName){
		ArrayList<Client> result=new ArrayList<Client>();
		//Client result=new Client("Null");
		for(Client c:clients){
			Pet temp=c.searchByPetName(petName);
			if(!temp.getName().equals("Null")){
				result.add(c);
			}
		}
		return result;
	}

	public int clientCount(){
		return clients.size();
	}

	@Override
	public String toString() {
		StringBuilder result=new StringBuilder("");
		for(Client c: clients){
			result.append(c);
			result.append("\n");
		}
		return result.toString();
	}
}







