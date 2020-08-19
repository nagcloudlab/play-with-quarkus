package example;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

//----------------------------------------------------
// publisher
//----------------------------------------------------

class Academy {

	public static Observable<String> getSubject() {

		// Trainer
		Observable<String> stream = Observable.create(emitter -> {
			int i = 1;
			while (i <= 5) {
				System.out.println("T1 : " + Thread.currentThread().getName() + " propagting topic");
				emitter.onNext("topic-" + i);
				// emitter.onError(new IllegalStateException("WebEx down"));
				i++;
			}
			emitter.onComplete();
		});
		return stream;

	}

}

public class Ex1 {

	public static void main(String[] args) {

		// Learner-1
		Observable<String> stream = Academy.getSubject();
		stream
		.subscribeOn(Schedulers.io())
		.observeOn(Schedulers.computation())
		.subscribe(next -> {
			System.out.println("L1 : " + Thread.currentThread().getName() + " reacting to topic");
			System.out.println("L1 : " + next);
		}, error -> {
			System.out.println("L1 : " + error.getMessage());
		}, () -> {
			System.out.println("L1 : Learning complete");
		});

		
		
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
