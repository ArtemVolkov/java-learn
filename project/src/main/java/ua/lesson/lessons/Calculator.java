package ua.lesson.lessons;

public class Calculator{
	private int result;
	public  void add(int... args) throws UserException{
		if(args.length > 0){
			int temp=0;
			for(int i: args)
				temp+= i;
			result=temp;
		}
		else{
			throw new UserException("You should enter some arguments in this method!");
		}

	}
	public void subtraction(int first,int second){
		result = first-second;
	}
	public void multiply(int... args) throws UserException{
		if(args.length > 0){
			int temp=0;
			for(int i: args)
				temp+= i;
			result=temp;
		}
		else{
			throw new UserException("You should enter some arguments in this method!");
		}
	}

	/**
	 * This method divides two integers.
	 * @throws ArithmeticException if second argument =0
	 * @param first first argument
	 * @param second second argument
	 *
	 */
	public void div(int first,int second){
		if(second==0) throw new ArithmeticException("\" by zero");
		else{
			result=first/second;
		}
	}
	public int getResult() {
		return result;
	}
	public void cleanResult(){
		result=-1;
	}
	public static void main(String[] arg) throws Exception {

	}
}