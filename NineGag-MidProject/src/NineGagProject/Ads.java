package NineGagProject;

public class Ads extends Thread{
	
	private static final String[] systemAds = { "ad1", "ad2", "ad3", "ad4", "ad5", 
			"ad6", "ad7", "ad8", "ad9", "ad10", "ad11", "ad12", "ad13", "ad14", 
			"ad15", "ad16", "ad17", "ad18", "ad19", "ad20", "ad21", "ad22", "ad23"};
	
	private static final int AD_SECONDS = 10;
	
	@Override
	public void run() {
		//TODO change while according to logic of site
		while(true) {
			try {
				Thread.sleep(AD_SECONDS * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Advertisment");
			System.out.println(systemAds[(int) (Math.random() * systemAds.length)]);
		}
		
	}

}
