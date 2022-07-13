package thread;

public class Thread1 implements Runnable{
	private int index;

	public Thread1(int index) {
		this.index = index;
	}

	public Thread1() {
	}

	@Override
	public void run() {
		doIt();
	}

	public void doIt() {
		System.out.println(index + " started and waiting..");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(index + " finished");
	}

}
