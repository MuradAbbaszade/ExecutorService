package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import thread.Thread1;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Thread1(1));
		Thread t2 = new Thread(new Thread1(2));
		Thread t3 = new Thread(new Thread1(3));
		Thread t4 = new Thread(new Thread1(4));

		List<Runnable> list = new ArrayList<Runnable>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);


		startThreadsInOrder(list);


		startThreadsInThreadPool(list, 3);


		cachedThreadPool(list);


		scheduleThreadsExecution(list);
		
	}

	public static void startThreadsInOrder(List<Runnable> list) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		for (Runnable r : list) {
			service.submit(r);
		}
		service.shutdown();
	}

	public static void startThreadsInThreadPool(List<Runnable> list, int poolSize) {
		ExecutorService service = Executors.newFixedThreadPool(poolSize);
		for (Runnable r : list) {
			service.submit(r);
		}
		service.shutdown();
	}

	public static void cachedThreadPool(List<Runnable> list) {
		ExecutorService service = new ThreadPoolExecutor(1, 5, 5, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(20));
		for (Runnable r : list) {
			service.submit(r);
		}
		service.shutdown();
	}

	public static void scheduleThreadsExecution(List<Runnable> list) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		for (Runnable r : list) {
			service.schedule(new Thread1((list.indexOf(r)) + 1), 5, TimeUnit.SECONDS);
		}
		service.shutdown();
	}

}
