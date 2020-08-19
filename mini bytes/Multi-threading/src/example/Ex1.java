package example;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 
 *  s/w , always do 2 types of tasks
 *  
 *    ---> computation   e.g executing some logic to compute something
 *    ---> io            e.g read/write on external sources ( file | db | n/w ...)
 *    
 *    
 *    
 *    -------------------------------------------------------------------
 *    
 *    home many threads can i create in java appln ?
 *    
 *    
 *                             cpus  e.g 8
 *    # no of threads  <=  --------------------
 *                          blocking-factor
 *                            
 *                            
 *                            
 *                            
 *    0 < blocking-factor < 1       
 *    
 *                     
 *    -------------------------------------------------------------------                 
 *    
 * 
 * 
 */

public class Ex1 {

	public static void computation() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " started computation....");
		while (true) {

		}
		// System.out.println(threadName + " finshed computation....");
	}

	public static void io() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " started io....");
		Scanner scanner = new Scanner(System.in);
		System.out.println("give me input..");
		String name = scanner.nextLine();
		System.out.println("hello " + name);
		System.out.println(threadName + " finished io....");
		scanner.close();
	}

	public static void main(String[] args) {

		String threadName = Thread.currentThread().getName();

		System.out.println(threadName + " kicked the execuation...");

		Runnable ioTask = () -> {
			io();
		};
		Thread thread1 = new Thread(ioTask, "Tom");

		Runnable computationTask = () -> {
			computation();
		};
		Thread thread2 = new Thread(computationTask, "Jerry");

		thread1.start(); // New stack....
		thread2.start(); // new stack..

		// -----------------------------------------------------------------------------------
		// JDK 1.5 Executor service , through thread pools , framework will manage
		// threads
		// -----------------------------------------------------------------------------------
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 10; i++) {
			Runnable task = () -> {
				System.out.println("hello " + Thread.currentThread().getName());
			};
			executorService.submit(task);
		}

		// ------------------------------------------------------------------------------

	}

}
