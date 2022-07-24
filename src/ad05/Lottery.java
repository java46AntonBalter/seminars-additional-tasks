package ad05;

public class Lottery {
	private int[] winningCombination;

	public Lottery(int[] winningCombination) {
		this.winningCombination = winningCombination;
	}

	public int rateCombination(int playerCombination[]) {
		int[] helper = new int[50];
		int rate = 0;
		for(int a: winningCombination) {
			helper[a] = 1;	
		}
		for(int a: playerCombination) {
			if(helper[a] == 1) ++rate;
		}
		return rate;
	}

	public static void main(String[] args) {
		Lottery loto = new Lottery(new int[]{5,2,17,48,43,7,9}) ;
		if (loto.rateCombination(new int[] {3,5,12,17,44,10,7}) != 3) {
			System.out.println("Test 1 failed");
           return;			
		}
		if (loto.rateCombination(new int[] {3,5,12,17,49,9,7}) != 5) {
			System.out.println("Test 2 failed");
           return;			
		}
		System.out.println("Success");
	}
}

