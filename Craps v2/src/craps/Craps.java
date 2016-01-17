/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craps;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 01021164
 */
public class Craps {
    
    //create random number generator for use in method rolldice
    private static final Random randomNumbers = new Random();
    
    //enumeration with constants that represent the game status
    private enum Status {CONTINUTE, WON, LOST};
    
    //constants that represent common dice rolls
    private static final int SNAKE_EYES = 2;
    private static final int TREY = 3;
    private static final int SEVEN=7;
    private static final int YO_LEVEN =11;
    private static final int BOX_CARS = 12;
    
    //plays one game of craps
    public static void main(String[] args) {
        int bankBalance =1000;
        int wager  =0;
        int myPoint =0;     //point if no win or loss on first roll
        Scanner input = new Scanner (System.in);
        
        
        do{                           
            System.out.printf("Bank Balance: %d\n", bankBalance);
            if (bankBalance <=0){
                System.out.println("Bank Balance is to low.  Please Deposit More Money.");
                break;
            }
        
            System.out.print("Please Enter a wager(0=End): ");
            wager = input.nextInt();
            
        
            if (bankBalance < wager){
                System.out.println("Wager Can not exceed Bank Balance");
                continue;
            }
            else 
                if (wager ==0) {
                    break;
                }
                else{
            
                Status gameStatus;  // can contain CONTINUE , WON or LOST
        
                int sumOfDice = rollDice(); //first roll of the dice
        
                //determine game status and point based on first roll
        
                switch (sumOfDice){
                    case SEVEN:  //win with 7 on first roll
                    case YO_LEVEN: //win with 11 on first roll
                        gameStatus = Status.WON;
                        break;
                    case SNAKE_EYES: //lose with 2 on first roll
                    case TREY:      //lose with 3 on first roll
                    case BOX_CARS: //lose with 12 on first roll
                        gameStatus = Status.LOST;
                        break;
                    default: //did not win or lose, so remember point 
                        gameStatus = Status.CONTINUTE;
                        myPoint = sumOfDice;   //remember the point
                        System.out.printf("Point is %d\n", myPoint);
                        break; //optional at the en dof switch
                }//end switch
        
                while(gameStatus==Status.CONTINUTE){ //not win or lost                
                    sumOfDice = rollDice(); //roll dice again
                    //determine game status
                    if (sumOfDice == myPoint)  //win by making point
                        gameStatus =Status.WON;
                    else
                        if(sumOfDice ==SEVEN)  //lose by rolling 7 before point
                            gameStatus = Status.LOST;
                }//end while
        
                //display won or lost message
                if (gameStatus ==Status.WON){
                    System.out.println("Player wins\n");
                    bankBalance += wager;
                }
                else {
                    System.out.println("Player loses\n");
                    bankBalance -= wager;
                }  //end else
            }//end else
        
        }while(wager!=0);
        
    } //end main
    
    //roll dice, calculate sum and display results
    public static int rollDice()
    {
        int die1 = 1 + randomNumbers.nextInt(6);  //first die roll
        int die2 = 1 + randomNumbers.nextInt(6); //second die roll
        int sum = die1 + die2;
        
        //display results of this roll
        System.out.printf("Player rolled %d + %d = %d\n", die1,die2,sum);
        
        return sum; //return sum of dice
    }
}   //end class
    

