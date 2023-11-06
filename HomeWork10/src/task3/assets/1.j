class Main {
	public static void two(){
		System.out.println(2);
	}
	public static void main(String[] args){
		int code = 1;
		switch(code){
			case 3 -> System.out.println("3");
			case 2 -> two();
			case 1 -> {
				System.out.println("1");
			}
		}
	}
}