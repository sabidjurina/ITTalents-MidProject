package NineGagProject;

import java.util.concurrent.TimeUnit;

public class SpecialSectionWorker extends Thread{

	private static final int SECONDS_TO_READJUST = 10;

	SpecialSectionWorker(){
		this.setDaemon(true);
	}
	
	@Override
	public void run() {
		while(true){
			
			PostStorage.givePostStorage().putInFresh();
			PostStorage.givePostStorage().putInHot();
			PostStorage.givePostStorage().putInTrending(); 
			System.out.println("Prenaredih!");
			try {
				TimeUnit.SECONDS.sleep(SECONDS_TO_READJUST);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}
