/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.Scanner;

/**
 * This class is used to store methods for inputting.
 * @author phamm
 */
public class Utilities {

    public static Scanner sc = new Scanner(System.in);
    public static boolean loopMore = true;
    public static String err = "ERROR!";

    /**
     * This method allow users to input a String from keyboard.
     * @param msg The announcement when users input data.
     * @return String that users have just input.
     */
    public static String inputAString(String msg) {
        String str = null;
        while (loopMore) {
            try {
                System.out.print(msg);
                str = sc.nextLine().trim().replaceAll("\\s+", " ");
                return str;
            } catch (Exception e) {
                System.out.println(err);
            }
        }
        return null;
    }

    /**
     * This method allow users to input an integer from keyboard.
     *
     * @param msg The announcement when users input data.
     * @param min The minimum integer that users can input.
     * @return The integer number that users have just input.
     */
    public static int inputAnInteger(String msg, int min) {
        String convert = null;
        int a = 0;

        while (loopMore) {
            try {
                System.out.print(msg);
                convert = sc.nextLine().trim();
                if (convert.isEmpty()) {
                    return 0;
                } else {
                    a = Integer.parseInt(convert);

                    if (a <= min) {
                        throw new Exception();
                    }
                    return a;
                }
            } catch (Exception e) {
                System.out.println(err);
            }
        }
        return 0;
    }
    
    /**
     * This method allow users to input Month, Year from the keyboard to generate format MM/YYYY
     *
     * @param msg The announcement when users input data.
     * @param min The minimum integer that users can input.
     * @return valid Month or Year that users have just input.
     */
    public static int inputMonthYear(String msg, int min, int max) {
        String convert = null;
        int a = 0;

        while (loopMore) {
            try {
                System.out.print(msg);
                convert = sc.nextLine().trim();
                if (convert.isEmpty()) {
                    return 0;
                } else {
                    a = Integer.parseInt(convert);

                    if (a <= min || a >= max) {
                        throw new Exception();
                    }
                    return a;
                }
            } catch (Exception e) {
                System.out.println(err);
            }
        }
        return 0;
    }
    
    /**
     * This method allow users to input a Item's code from keyboard.
     * @param msg The first announcement when users input data.
     * @return The valid code that users have just input.
     */
    public static String inputItemCode(String msg) {
        String format = "^RAM([A-Z]{3,5}[0-9]?_[0-9]+$)";
        boolean check = false;

        while (loopMore) {
            try {
                System.out.print(msg);
                String id = sc.nextLine().trim();
                check = id.matches(format);

                if (!check || id.isEmpty()) {
                    throw new Exception();
                }

                return id;
            } catch (Exception e) {
                System.out.println(err);
            }
        }
        return null;
    }
    
    /**
     * This method is used to generate an unique Product ID when users add a new Product.
     * @param index The current index or count used to generate the ID. It represents the sequential number that will be included in the Product ID.
     * @param type Item's type.
     * @return valid Item code.
     */
    public static String generateId(int index, String type) {
        return String.format("RAM" + type + "_%d", index);
    }
    
    /**
     * This method is used to get Item's index from Code. This index will help to update Item's code when users change Item's type.
     * @param code Item's code
     * @return Item's index.
     */
    public static int getIndexCode(String code) {
        String[] spl = code.split("_");
        return Integer.parseInt(spl[spl.length - 1]);
    }
}