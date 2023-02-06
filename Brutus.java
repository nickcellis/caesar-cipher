public class Brutus {

  private static String UPPER_CASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     *  Array of English Character frequency.
     *  <p>
     *  This method contans from 0 to 25 or a to z, frequency of per character that are closest,
     *  to the english text.
     *  </p>
     *  @return array of double that contains the frequencies of the english text
     */
  public static final double[] english = {
    0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
    0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
    0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
  };

    /**
     *  This the main method that is requied to make this class executable.
     *  <p>
     *  We use this method to get the user input via the command line arguments to,
     *  decrypt the message to english words.
     *  The message is looped through and compared with the expect Engllish
     *  freqency so that it can determine which is freqency is the closest to the English letter.
     *  </p>
     *  @param args command line arguments.
     */
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Too few parameters!");
      System.out.println("Usage: java Brutus \"cipher text\"");
      return;
    }

    if (args.length > 1) {
      System.out.println("Too many parameters!");
      System.out.println("Usage: java Brutus \"cipher text\"");
      return;
    }

    String input = args[0];
    double bestFreqency = Double.POSITIVE_INFINITY;
    String result = "";
    for (int i = 0; i < 26; i++) {
      String text = Caesar.rotate(i, input);
      double frequency = chiSquared(frequency(text), english);
      if (frequency < bestFreqency) {
        bestFreqency = frequency;
        result = text;
      }
    }
    System.out.println(result);
  }

  /**
   *  Array of English character count.
   *  <p>
   *  We use the inputted String and check how many times the same character has been repeated, then its collected into
   *  an int array that goes from 0 to 25, in the same order of the alphabet.
   *  Example. int[0](would be equavilent to A).
   *  </p>
   *  @param value the String that is use to count from.
   *  @return array of int that contains the count of the english text
   */
  public static int[] count(String value) {
    int length = value.length();
    int[] countArray = new int[26];

    for (int i = 0; i < length; i++) {
      if (!isAlphabet(value.charAt(i))) continue;
      int position = getPosition(value.charAt(i));
      countArray[position] += 1;
    }
    return countArray;
  }

  /**
   *  Array of double that contains the frequency of our String.
   *  <p>
   *  In here, we link to the count method to get the count of our english text so we can get divide the count of each
   *  character by the length which will gives us the freqency of the english character.
   *  </p>
   *  @param value - the message that needs decrypting.
   *  @return array of double that contains the frequencies of the english text.
   */
  public static double[] frequency(String value) {
    int length = value.length();
    double[] frequencyArray = new double[26];
    int[] countArray = count(value);
    for (int i = 0; i < countArray.length; i++) {
      int count = countArray[i];
      if (count > 0) frequencyArray[i] = ((double)count / value.length());
    }
    return frequencyArray;
  }

  /**
   *  chiSquared value.
   *  <p>
   *  We compare our own frequencies with the expected english frequency, by looping through our freqency array
   *  and we create a XS variable to store our calculation(freq - expectFreq^2) / expectFreq to get our score value.
   *  </p>
   *  @param frequency is the frequency of our english text
   *  @param expecFreq is the freqency of the expected english text that is provided above.
   *  @return double of the score of the freqency.
   */
  public static double chiSquared(double[] frequency, double[] expecFreq) {
    double xs = 0;
    for (int i = 0; i < frequency.length; i++) {
      double freq = frequency[i];
      double engFreq = expecFreq[i];
      double score = Math.pow(freq - engFreq, 2) / engFreq;
      xs += score;
    }
    return xs;
  }


  /**
   *  Position of a character in the alphabet.
   *  <p>
   *  Here, we use String.IndexOf to get our position.
   *  </p>
   *  @param character character from a to z
   *  @return position of the character in the alphabet.
   */
  private static int getPosition(char character) {
    return UPPER_CASE_ALPHABET.indexOf(Character.toUpperCase(character));
  }


  /**
   *  A helpful method that check if the character is in the english alphabet.
   *
   *  This method. takes in a character and change it to an Upper case string and is check if String.Contains
   * in the english alphabet String which is all upper case.
   *
   *  @param character character that needs to be checked if its in the alphabet.
   *  @return true/false if its in the alphabet.
   */
  private static boolean isAlphabet(char character) {
    return (UPPER_CASE_ALPHABET.contains(Character.toString(character).toUpperCase()));
  }
}
