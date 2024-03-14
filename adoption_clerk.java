import java.util.Random;

public class adoption_clerk implements Runnable {
	
	private int id;
    public static long time = System.currentTimeMillis();
    public static String adoptPet = "who want to do adopt a pet";
    public static String lookPet = "Is looking at pets avilible for adoption";
    private static int sleeping;
    
    private static int currentId;
    private static int currentNum;
    
    //use in adoption_clerk to see if it requires
    public static boolean checkSpot = false;
    
    public adoption_clerk(int id) {
    	Thread.currentThread().setName("adoption clerk"+ "[" + id + "]");
    	this.id = id; // use for debug to see which cashier is in action
    	
    	//for calling clerk when there is a customer calling
    	if(checkSpot == true) {
    		checkOut();
    	}
    }
    
    
    private void checkOut() {
    	Thread.currentThread().setName("adoption clerk"+ "[" + id + "]");
        msg("serves customer"+ "[" + currentId + "]" + "-" + currentNum);
        checkSpot = false;
	}


	public static void serve(int customerId, int customerNumber) {
    	// msg("serves customer" + "[" + customerId + "]-" + customerNumber + " " + adoptPet);
    	msg(lookPet);
    	lookAround(customerId, customerNumber);
    }
	
	private static void lookAround(final int id, final int num) {

		
    	Random rand = new Random();
    	//random number from 1 to 10 for random desire
    	int ran = rand.nextInt(10)+1;
    	//random number from 1 to 1000 for sleep
        sleeping = rand.nextInt(1000)+1;
    	
    	// New thread waiting for the customer to wait in
    	// The use is to hopefully allowing sleep to happen constantly for customer instead of 
    	// customer 2 waiting on customer 1 to finish sleep before customer 2 can sleep
    	new Thread(new Runnable() {
    		public void run() { 
    			try {
    				sleep(id, num,sleeping);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			finishLooking(id, num);
    		}
    	}).start();		
	}


    public static void sleep(int id, int num, int s) throws InterruptedException {
    	Thread.currentThread().setName("customer"+ "[" + id + "]");
        msg("Sleep for " + s + " milliseconds.");
        Thread.sleep(s);
    }

	protected static void finishLooking(int id, int num) {
		//random number to decide if the customer want a pet
    	Random rand = new Random();
    	int ranPet = rand.nextInt(10)+1;
    	
    	//If they roll a number that that allows pet adoption
    	if (ranPet < 6) {
    		msg("rolled a " + ranPet + " so decide to wanting to adopt a pet");
    		msg("Went to fill the adoption form");
    		
    		//sleep to fill paper
    		lookAround(id, num);
    		
    		//if there is no pet left in store
    		if(main.num_pet <= 0) {
    			msg("Find no pet left in store and leave");
    			customerLeave();
    			checkSpot = false;
    		}
    		//else call clerk to serve
    		else{
    			callClerk(id, num);
    		}
    	}
    	else if (ranPet >=6){
    		msg("rolled a " + ranPet + " so decide rather not adopt a pet and leave");
    	}
		
	}
	
	//calling to see if clerk is available to serve before serve
	private static void callClerk(int id, int num) {
		if (main.checkAdoptionClerk == true) {
			checkSpot = true;
			Thread adoptionClerk = new Thread(new adoption_clerk(1));
			adoptionClerk.start();
			currentId = id;
			currentNum = num;
		}
        
		//How many pet left in store
		main.num_pet -= 1;
		System.out.println("Annocement: " +  main.num_pet + " pets left in store");
	}

	private static void customerLeave() {
		msg("leave the store");
	}



	public static void msg(String m) {
	    System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}

	private static String getName() {
		Thread threadName = Thread.currentThread();
		return threadName.getName();
	}

	@Override
	public void run() {
    	Thread.currentThread().setName("adoption clerk"+ "[" + id + "]");
    	msg("In store");
	}
}
