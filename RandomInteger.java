import java.util.*;

class RandomInteger implements Randomy<Integer> {
	public Integer makeRandom() {
		Random r1 = new Random();
		return r1.nextInt((100 - 0) + 1);
	}
}