package com.jdbide.domain.model.bankaccount;

import com.jdbide.domain.model.bankaccount.domain.model.Account;
import com.jdbide.domain.model.bankaccount.domain.model.Amount;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Account account = new Account.AccountBuilder().build();

        int choice = -1;
        Scanner in = new Scanner(System.in);

        while (choice != 4) {

            System.out.println("");
            System.out.println("-------- ************************************* --------");
            System.out.println("-------- Welcome to your personal bank account --------");
            System.out.println("-------- ************************************* --------");
            System.out.println("---------------- Please choose an action --------------");
            System.out.println("");

            System.out.println("1- Deposit");
            System.out.println("2- Withdraw");
            System.out.println("3- Statement history");
            System.out.println("4- Exit");


            try {
                choice = in.nextInt();
                in.nextLine(); //throw away the \n not consumed by nextInt()

                System.out.println("you choosed : " + choice);

                switch (choice) {
                    case 1:
                        try {
                            deposit(account, in);
                        } catch (InputMismatchException ime) {
                            System.out.println("invalid input. please type a number");
                        }
                        break;
                    case 2:
                        try {
                            withdraw(account, in);
                        } catch (InputMismatchException ime) {
                            System.out.println("invalid input. please type a number");
                        }
                        break;
                    case 3:
                        account.printHistory();
                        break;
                    case 4:
                        System.out.println("You are now exiting your account");
                }

            } catch (InputMismatchException ime) {
                System.out.println("invalid input. please choose a number between 1 and 4");
                in.nextLine(); //throw away the \n not consumed by nextInt()
            }

        }
        in.close();

    }

    private static void withdraw(Account account, Scanner in) {
        System.out.println("Withdraw amount ?");
        int amount = in.nextInt();
        in.nextLine(); //throw away the \n not consumed by nextInt()

        account.withdraw(new Amount(amount));
        System.out.println("You withdrawed " + amount + " from your account !");
    }

    private static void deposit(Account account, Scanner in) {
        System.out.println("Deposit amount ?");
        int amount = in.nextInt();
        in.nextLine(); //throw away the \n not consumed by nextInt()

        account.deposit(new Amount(amount));
        System.out.println(amount + "has been added to your account ! ");
    }
}
