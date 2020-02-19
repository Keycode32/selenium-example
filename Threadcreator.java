import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Threadcreator implements Runnable{

	private String sp;
	public Threadcreator(String sp) {
		this.sp = sp;
	}
	
	@Override
	public void run() {
		
			ChromeOptions chromop = new ChromeOptions();
			chromop.addArguments("--headless");
		
		ChromeDriver ch = new ChromeDriver(chromop);
		try{
	    ch.get("https://monitor.firefox.com/");
		
	    WebElement test = ch.findElementByCssSelector("#scan-email");
	    test.click();
	    test.sendKeys(sp);
	    
	    WebElement submit = ch.findElementByCssSelector("#scan-user-email > div.input-group-button");
	    submit.click();
	    
	    try {Thread.sleep(5000);} catch (InterruptedException e) {}
	    
	    WebElement counter = ch.findElementByCssSelector("body > main > div.row.jst-cntr.scan-results > div > h2 > span");
	    
	    if(counter.getText().startsWith("0")){
	    	Emailchecker.addlogtoconsole(sp+" is invaild");
	    	Emailchecker.SaveBad(sp);
	    }else{
	    	System.out.println(sp +"is vaild");
	    	Emailchecker.addlogtoconsole(sp+" is vaild");
	    	Emailchecker.SaveValid(sp);
	    }
	    ch.close();
	    ch.quit();
	    Emailchecker.restartthread();
	    Emailchecker.Emailstodominus();
		}catch (Exception e) {
			
			
			Emailchecker.Saveerror(sp);
			ch.close();
		    ch.quit();
		    Emailchecker.restartthread();
		    Emailchecker.addlogtoconsole(sp+" has an Error");
		    Emailchecker.Emailstodominus();
			
			// TODO: falid
		}
	}

}
