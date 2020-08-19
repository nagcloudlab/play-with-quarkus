package example;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

//------------------------------------------------------
//data model
//------------------------------------------------------

class User {
	String name;

	public User(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

}

//------------------------------------------------------
//repository  layer
//------------------------------------------------------

interface UserRepositry {
//	User findById(String id) throws IOException;
//	Future<User> findById(String id);
	CompletableFuture<User> findById(String id);
}

class JpaUserRepositry implements UserRepositry {

	public static void sleep() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	public User findById(String id) {
//		// i.e. io
//		sleep()
//		return new User("Nag");
//		
//	}

//	public Future<User> findById(String id) {
//      ExecutorService executorService = Executors.newCachedThreadPool();
//		Callable<User> task = () -> {
//			// i.e. io
//			sleep()
//			return new User("Nag");
//		};
//		return executorService.submit(task);
//
//	}

	@Override
	public CompletableFuture<User> findById(String id) {
		ExecutorService ioThreadPool = Executors.newCachedThreadPool();
		return CompletableFuture.supplyAsync(() -> {
			// i.e. io
			System.out.println("repo : " + Thread.currentThread());
			sleep();
			return new User("Nag");
		}, ioThreadPool);
	}
}

public class Ex1 {

	public static void main(String[] args) {

		UserRepositry userRepositry = new JpaUserRepositry();

		//
		try {
//			 User user = userRepositry.findById("1"); // blocks

//			Future<User> future = userRepositry.findById("1"); // non-blocking
//			// ...
//			// ...
//			// ...
//			User result = future.get();
//			System.out.println(result);

		} catch (Exception e) {
			// ....
		}

		ExecutorService computationThreadPool = Executors.newFixedThreadPool(5);

		userRepositry.findById("1") // non-blocking
				.thenAcceptAsync(user -> {
					System.out.println("main : " + Thread.currentThread());
					System.out.println(user);
				}, computationThreadPool);

		sleep();

	}

	public static void sleep() {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
