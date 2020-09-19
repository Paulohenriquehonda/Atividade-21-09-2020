package observer.cofre;

import java.util.Date;

public class CofreListenerErro implements CofreListenerE {

@Override
	public void cofreErro() {
		System.out.println("O cofre teve um tentativa inválida de abertura:" + new Date().toLocaleString());		
	}

}
