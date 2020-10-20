/** 
*  CECS 277 / Lab 3 / 9/8/2020
*  Jacob Sunia, Johnerson Li
*  A program that reads in data from a text file and stores it in two arraylists and then displays state and population information based on the data.
*/
import java.util.*;
import java.io.*;

class Main {

  /**
   * Main method; Arraylists are initialized and menu is displayed.
   * @param args default argument
   */
  public static void main(String[] args) {
    ArrayList<String> states = new ArrayList<String>();
    ArrayList<Integer> population = new ArrayList<Integer>();
    readFile(states, population);
    Scanner sc = new Scanner(System.in);
    boolean repeat = true;
    while(repeat){
      try {
          menu();
          int input = sc.nextInt();
          if (input == 1){
            sortStates(states, population);
          }
          else if (input == 2){
            sortPop(states, population);
          }
          else if (input == 3){
            totalSum(population);
          }
          else if (input == 4) {
            greaterThan(states,population);
          }
          else if (input == 5) {
            repeat = false;
          }
      } catch (InputMismatchException e){
          String st = sc.next(); //Clears buffer from invalid
          System.out.print("\nInvalid Entry!\nPlease Enter a Valid Number: ");
      }
    }
  }
  /**
   * readFile method reads from the text file and stores data to the pair of arraylists
   * @param name ArrayList that contains the name of US states and territories
   * @param pop Arraylist that contains the population of US states and territories
   */
  public static void readFile(ArrayList<String> name, ArrayList<Integer> pop){
      try {
      Scanner read = new Scanner(new File("StatePops.txt"));
      while(read.hasNextLine()) {
        String readLn = read.nextLine();
        int i = 0; 
        
        while(!Character.isDigit(readLn.charAt(i))){
            //checkdigit add
            i++;
        }
        name.add(readLn.substring(0,i-1));
        pop.add(Integer.parseInt(readLn.substring(i)));
      }
    } catch(FileNotFoundException e) {
        System.out.println("File Not Found - Place in Project Folder! ");
    }
  }
  /**
     * Method which parameters are name and populations of type ArrayList 
     * sorts each list by state or territory name alphabetically first by using the algorithm given and is passed through
     * a do while loop where it is outputted by using printf 
     * @param name  Taken parameter for each name of state/territory
     * @param pop   Taken parameter for each population of state/territory
     */
  public static void sortStates(ArrayList<String> name, ArrayList<Integer> pop) {
    boolean swapped = false;
        do{
            swapped = false;
            for (int i = 0; i < name.size() - 1; i++){
                if(name.get(i).compareTo(name.get(i+1)) > 0){
                    int popSwitch = pop.get(i);
                    pop.set(i, pop.get(i + 1));
                    pop.set(i + 1, popSwitch);

                    String nameSwitch = name.get(i);
                    name.set(i, name.get(i + 1));
                    name.set(i + 1, nameSwitch);
                    swapped = true;
                }
            }
        }while(swapped);
        for (int i = 0; i < name.size(); i++) {
            System.out.printf("%-25s %,10d \n", name.get(i),pop.get(i));
        }
  }
   /**
     * Method which parameters are name and populations of type ArrayList 
     * sorts each list by population first by using the algorithm given and is passed through
     * a do while loop where it is outputted by using printf 
     * @param name  Taken parameter for each name of state/territory
     * @param pop   Taken parameter for each population of state/territory
     */
  public static void sortPop(ArrayList<String> name, ArrayList<Integer> pop) {
    boolean swap = false;
        do{
            swap = false;
            for (int i =0; i < pop.size() - 1; i++){
                if(pop.get(i) > pop.get(i+1)){
                    int popSwap = pop.get(i);
                    pop.set(i,pop.get(i+1));
                    pop.set(i+1, popSwap);

                    String nameSwap = name.get(i);
                    name.set(i, name.get(i+1));
                    name.set(i+1, nameSwap);
                    swap = true;
                }
            }
        }while(swap);
         for (int i = 0; i < name.size(); i++) {
            System.out.printf("%-25s %,10d \n", name.get(i),pop.get(i));
        }
  }
   /**
     * Method that asks for user input and replicates each state that is greater than
     * the states actual population
     * @param name  Taken parameter for each name of state/terretory
     * @param pop   Taken parameter for each population of state/terretory
     */
  public static void greaterThan(ArrayList<String> name, ArrayList<Integer> pop) {
    Scanner input= new Scanner(System.in);
    
    System.out.print("Enter Population: ");
        int sc = input.nextInt();
          for (int i = 0; i < name.size(); i++) {
            if (pop.get(i) > sc) {
                System.out.printf("%s   %,d\n", name.get(i), pop.get(i));
            }
        }
  }
  /**
     * Method that sums the total population 
     * @param pop   Taken parameter for each population of state/terretory
     */
  public static void totalSum(ArrayList<Integer> population){
       int sum = 0;
       for(int individual : population){
         sum += individual;
       }
       System.out.print("US Population = " + sum + "\n");
    } 
  public static void menu() {  
    System.out.println("State Stats\n1. Display Sorted States\n2. Display Sorted Populations\n3. Display Total US Population\n4.Display States with Population Greater Than\n5. Quit");

  }    
}