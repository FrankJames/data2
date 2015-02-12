class RandomString implements Randomy<String> {
	public static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyz1234567890";
	public static final int RAND_STRING_LENGTH = getRandomInt();

	public static int getRandomInt() {
		int grabInt = (int)(Math.random() * (20 - 1) + 1);
		return grabInt;
	}

	public String makeRandom() {
		StringBuffer grabString = new StringBuffer();

		for ( int i = 0; i < RAND_STRING_LENGTH; i++ ) {
			int num = getRandomInt();
			char c = CHAR_LIST.charAt(num);
			grabString.append(c);
		}

		return grabString.toString();
	}
}