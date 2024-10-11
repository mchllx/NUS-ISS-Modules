public class CommandLineArgsApplication {
	public static void main(String[] args) {
		String name = "fred";
		if (args.length > 0)
			name = args[0];
		System.out.printf("Hello %s\n", name);
	}
}
