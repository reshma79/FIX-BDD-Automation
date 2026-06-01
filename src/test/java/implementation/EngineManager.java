package implementation;

public class EngineManager {

	private static Process process;
	
    public void startEngine() {
    	try {
        System.out.println("Starting FIX Engine...");
        ProcessBuilder pb = new ProcessBuilder(
                "cmd.exe", "/c",
                "java -jar C:\\RSP\\EclipseProjects\\FIXApp\\FIXApp.jar"
        );

        process = pb.start();
        Thread.sleep(500);
        System.out.println("FIX Engine Started Successfully");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void stopEngine() {
    	try {
        if (process != null) {
//            process.destroy();
//            process.destroyForcibly();
            Runtime.getRuntime().exec("taskkill /PID "+process.pid()+" /F /T");
            System.out.println("FIX Engine stopped");
        }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}