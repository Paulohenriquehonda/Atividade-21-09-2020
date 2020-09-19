package observer.cofre;

import java.util.Date;

public class CofreListenerAberto implements CofreListenerA {

	@Override
	public void cofreFoiAberto() {
		System.out.println("O cofre foi aberto: " + new Date().toLocaleString());
	}
}
