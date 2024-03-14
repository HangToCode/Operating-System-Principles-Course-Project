import java.util.Random;

public class customer implements Runnable{
	//Initialize variable
	private int id;
	private int ran;
	private int sleeping;
	//private Vector<customer> customerLine;
	
	//Initialize time
    public static long time = System.currentTimeMillis();
    
	//Initialize dialog 
	public static String buyPetFood= "Is interested in buying a food for pet";
	public static String adoptPet= "Is interested in adopting a pet";
	public static String otherWise = "Is interest in shopping first before considering for pet adoption";
	
    public customer(int id) {
    	Thread.currentThread().setName("customer"+ "[" + id + "]");
    	msg("Walk outside");
    	this.id = id; // use for debug on seeing which customer is on where
    }
    
    
    public void sleepTimes() {
    	Random rand = new Random();
    	//random number from 1 to 10 for random desire
    	this.ran = rand.nextInt(10)+1;
    	//random number from 1 to 1000 for sleep
    	this.sleeping = rand.nextInt(1000)+1;
    	
    	// New thread waiting for the customer to wait in
    	// The use is to hopefully allowing sleep to happen constantly for customer instead of 
    	// customer 2 waiting on customer 1 to finish sleep before customer 2 can sleep
    	new Thread(new Runnable() {
    		public void run() { 
    			try {
    				sleep(sleeping);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			choosePath();
    		}
    	}).start();
    }
    
    public void sleep(int s) throws InterruptedException {
    	Thread.currentThread().setName("customer"+ "[" + id + "]");
        msg("Traveling for " + s + " milliseconds.");
        Thread.sleep(s);
        
    }
    
    public void choosePath(){
    	Thread.currentThread().setName("customer"+ "[" + id + "]");
    	msg("arrive to the store");
    	msg("generate random number " + this.ran);
    	Thread.currentThread().setName("customer"+ "[" + id + "]" + "-" + this.ran);

		if(this.ran < 4) {
			msg(buyPetFood);
			cashier.serve(id, ran);
		}
		else if(this.ran >= 4 && this.ran%2 == 0) {
			msg(adoptPet);
			adoption_clerk.serve(id,ran);
		}
		else {
			msg(otherWise);
			cashier.serve(id, ran);
		}
    }

	@Override
	public void run() {
	 	sleepTimes();
	}
	
	public void msg(String m) {
	    System.out.println("["+(System.currentTimeMillis()-time)+"] "+ Thread.currentThread().getName()+": "+m);
	}


	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}


}
