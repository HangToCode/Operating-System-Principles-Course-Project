//Name: Li Hang Biao

public class main{
	//Initialize default
	public static int num_customers = 20;
	public static int num_cashiers = 3;
	public static int num_adoption_clerk = 1;
	public static int num_vister = 3;
	public static int num_pet = 12;
	public static boolean checkAdoptionClerk = true;
	
	//Initialize time
	public static long time = System.currentTimeMillis();

	
	public static void main(String [] args) {
				
		
		
		//create and start threads
		
		//Start adoption clerk
		Thread adoptionClerk = new Thread(new adoption_clerk(1));
		adoptionClerk.start();
		//Start cashiers
		for(int i = 1;i <= num_cashiers; i++) {
			Thread cashiers = new Thread(new cashier(i));     
			cashiers.start();
		} 
		
		//start customers
		for(int i = 1;i <= num_customers; i++) {
			Thread customer = new Thread(new customer(i));
			customer.start();
		}
		
		
	}
	  
}

