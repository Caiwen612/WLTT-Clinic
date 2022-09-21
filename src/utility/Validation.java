package utility;

//Author: Team
public class Validation {
    // Exception Handling
    //Check Menu option
   public static void validOption(int option, int lowerLimit, int upperLimit) throws ValidationException {
        // Check only the integer range be allow.
        if (option < lowerLimit || option > upperLimit) {
            throw new ValidationException(
                    Font.useFont(Font.BOLD_RED, "Please only enter from the range of " + lowerLimit + " to " + upperLimit));
        }
    }

    // Check whether is yes or not
    public static void validCharYN(char yesOrNo) throws ValidationException {
        Character.toUpperCase(yesOrNo);
        if (Character.toUpperCase(yesOrNo) != 'Y' && Character.toUpperCase(yesOrNo) != 'N') {
            throw new ValidationException(Font.useFont(Font.BOLD_RED, "Please only key in Y or N."));
        }
    }

    //Check product ID is correct or not
    public static boolean validProductID(char productID){
        if(productID == 'T' || productID == 'P' || productID == 'A' || productID == 'S'){
            return true;
        } else{
            System.out.println(Font.useFont(Font.BOLD_RED,"Please only key in the product ID start with T,P,A,S" ));
            return false;
        }
    }

    //Check product quantity (Cannot order more than stock of item)
    public static void validProductQuantity(int quantity, int productQuantity) throws ValidationException {
        if(quantity > productQuantity){
            throw new ValidationException(Font.useFont(Font.BOLD_RED, "Please only key in the quantity less than or equal to " + productQuantity));
        }
    }

    //Check price range (Check user input price is correct or not)
    public static void validPriceRange(double lowerPrice, double upperPrice) throws ValidationException {
        if(lowerPrice > upperPrice){
            throw new ValidationException(Font.useFont(Font.BOLD_RED, "Invalid price range: Lower price must be less than upper price"));
        }
    }

    public static void validIC(String ic) throws ValidationException {
        if (ic.length()!= 12) {
            throw new ValidationException(Font.useFont(Font.BOLD_RED, "Please enter a valid IC Number!"));
        }
    }
}
