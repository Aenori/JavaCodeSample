package thread;

import java.util.HashMap;
import java.util.Map;

public class ExempleThreading {
	private static class SaluteThread extends Thread {
		private String salut;
		private static int count = 0;
		private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		public SaluteThread(String salut) {
			this.salut = salut;
		}
		
		@Override
		public void run() {
			for(int i = 0; i < 10000; ++i) {
				if(i % 1000 == 0) {
					System.out.println(salut);
				}
				count += 1;
				map.put(1, count);
			}
		}
	}
	
	public static void main(String[] args) {
		SaluteThread thread1 = new SaluteThread("Bonjour !");
		SaluteThread thread2 = new SaluteThread("Hello !");
		SaluteThread thread3 = new SaluteThread("Oyaogosaimasu");
		
		thread1.start();
		thread2.start();
		thread3.start();
	}

}
