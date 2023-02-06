public class Caesar {

  private static String UPPER_CASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static int alphabetLength = 26;

  /**
   *  This the main method that is requied to make this class executable.
   *  <p>
   *  We use this method to get the user input via the command line arguments to,
   *  encrypt the message to random english letters and to PrintOut the encrypted message.
   *  It is also use this method to validate things from the command line arguments so we can limit inputs and
   *  create requirements.
   *  </p>
   *  @param args command line arguments.
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Too few parameters!");
      System.out.println("Usage: java Caesar n \"cipher text\"");
      return;
    }

    if (args.length > 2) {
      System.out.println("Too many parameters!");
      System.out.println("Usage: java Caesar n \"cipher text\"");
      return;
    }

    String inputtedText = args[1];
    int inputtedShift = Integer.parseInt(args[0]);

    String shifted = rotate(inputtedShift, inputtedText);
    System.out.println(shifted);
  }

  /**
   *  Rotate/Shift the Character.
   *  <p>
   *  Here, get the character position of the alphabet then, it is shift to the correct position of
   *  inpputed shift value, then it is check if the character is caseSensitive.
   *  </p>
   *  @param shift takes in an int where to shift the Character to.
   *  @param character input takes a Char where we use to shift this from into.
   *  @return Char - of a new character where its been shifted to.
   */
  public static char rotate(int shift, char character) {
    int position = getPosition(character);
    Character shiftedCharacter = shiftFrom(position, shift);
    return (Character.isUpperCase(character) ? Character.toUpperCase(shiftedCharacter)
    : Character.toLowerCase(shiftedCharacter) );
  }

  /**
   *  Rotate/Shift the String.
   *  <p>
   *  Here, the string is looped through per per character therefore, we can shift
   *  the character one by one to its destination. While loop is used here to iterate through
   *  from 0 to the length of the text to get the character.
   *  </p>
   *  @param shift takes in an int where to shift the string to.
   *  @param input takes a String where we use to shift this from into.
   *  @return String - of new characters where its been shifted to.
   */
  public static String rotate(int shift, String input) {
    StringBuilder builder = new StringBuilder();
    int length = input.length();
    int i = 0;
    while (i < length) {
      char character = input.charAt(i);
      char rotate = rotate(shift, character);
      if (!isAlphabet(character)) builder.append(character); else builder.append(rotate);
      i++;
    }
    return builder.toString();
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
   *  Calculate the correct shift to.
   *  <p>
   *  To calculate the correct shift, is to use the formula. (from + shiftTo) % 26. Which will give the correct
   *  position value, and then it linked to the getCharacterByPosition method to get the character which is use to
   *  return.
   *  </p>
   *  @param from value of where its current position is.
   *  @param shift value of where it should shift to.
   *  @return Character of the new shift.
   */
  private static char shiftFrom(int from, int shift) {
    int fixShift = (from + shift) % 26;
    Character toChar = getCharacterByPosition(fixShift);
    return toChar;
  }


  /**
   *  Get Character by Position.
   *  <p>
   *  So we get Position value and we determine if the value is in negative or not if so, then add +26
   *  and recurse back again until its above 0. So the correct position number can be compared to the
   *  English alphabet index.
   *  </p>
   *  @param position takes in an int position.
   *  @return Character of the position.
   */
  private static char getCharacterByPosition(int position) {
    if (isShiftingLeft(position)) {
      return getCharacterByPosition(26 + position);
    }
    return position < 26 ? UPPER_CASE_ALPHABET.charAt(position) : ' ';
  }

  /**
   *  A helpful method that check if the character is in the english alphabet.
   *  <p>
   *  This method. takes in a character and change it to an Upper case string and is check if String.Contains
   *  in the english alphabet String which is all upper case.
   *  </p>
   *  @param character character that needs to be checked if its in the alphabet.
   *  @return true/false if its in the alphabet.
   */
  private static boolean isAlphabet(char character) {
    return (UPPER_CASE_ALPHABET.contains(Character.toString(character).toUpperCase()));
  }


    /*
     * Check if the shift is shifting left.
     *
     * Determine this by checking if the shift is below 0. If so then
     * the method return true.
     *
     * @param value takes in an int value of shift.
     * @return true/false depending if the value is below 0
     */
  private static boolean isShiftingLeft(int value) {
    return value < 0;
  }
}
