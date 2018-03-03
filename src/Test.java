import org.springframework.util.StopWatch;


public class Test {

	public static void main(String[] args) {
		StopWatch watch=new StopWatch("Sample Operation");
		watch.start("Task#A");
		for(int i=1;i<=20000000;i++){
			Math.random();
		}
		watch.stop();
		watch.start("Task#B");
		for(int i=1;i<=30000000;i++){
			Math.random();
		}
		for(int i=1;i<=30000000;i++){
			Math.random();
		}
		watch.stop();
		watch.start("Task#C");
		for(int i=1;i<=30000000;i++){
			Math.random();
		}
		watch.stop();
		
		
		String s=watch.prettyPrint();
		
		System.out.println(s);
		
		
		
		
		
	}
}
