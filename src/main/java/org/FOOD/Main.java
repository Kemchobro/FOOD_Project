package org.FOOD;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your query?: ");

    RequestHandler requestHandler = new RequestHandler(scanner.nextLine(), RequestHandler.RequestType.EVERYTHING);



    }
}