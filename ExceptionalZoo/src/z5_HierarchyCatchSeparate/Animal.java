package z5_HierarchyCatchSeparate;

public class Animal { 
	String status;
	
	//REQUIRES: food to be "red meat"
	public void feed(String food) throws FeedingException {
		if(food.equals("veggies")){
			throw new VeggiesException();
		}
		else if (!food.equals("red meat")){
			throw new FoodException();
		}
		Printer.print("I got fed:"+food);
		this.status="fed";
	}
}
