package observer.cofre;

import java.util.Date;

public class CofreListenerFechado implements CofreListenerF {

	@Override
	public void cofreFoiFechado() {
		System.out.println("O cofre foi fechado: " + new Date().toLocaleString());		
	}

}
