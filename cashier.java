
public class cashier implements Runnable{
	private int id;
    public static long time = System.currentTimeMillis();
    public static String petFood = "who want food and toy for their pet";
    public static String shop = "who want to do regular shoping before considering for pet";
    
    public cashier(int id) {
    	Thread.currentThread().setName("Cashier"+ "[" + id + "]");
    	msg("In store");
    	this.id = id; // use for debug to see which cashier is in action
    }
    
    public static void serve(int customerId, int customerNumber) {
    	String i = null;
    	if (customerNumber < 4) {
    		i = petFood;
    	}
    	else if (customerNumber >= 4 && customerNumber %2 != 0) {
    		i = shop;
    	}
    	msg("serves customer" + "[" + customerId + "]-" + customerNumber + " " + i);
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
    	Thread.currentThread().setName("Cashier"+ "[" + id + "]");
	}
}
