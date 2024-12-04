package org.FOOD;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String answer;



            System.out.println("Would you like: \n\"EVERYTHING\"\n\"SOURCES\"\n\"HEADLINES\"");


            String type = scanner.nextLine().toUpperCase().trim();
            RequestHandler.RequestType requestType = RequestHandler.RequestType.EVERYTHING;

            switch (type) {
                case "EVERYTHING" -> requestType = RequestHandler.RequestType.EVERYTHING;
                case "SOURCES" -> requestType = RequestHandler.RequestType.SOURCES;
                case "HEADLINES" -> requestType = RequestHandler.RequestType.HEADLINES;

            }
            System.out.println("What is your query?: ");


            RequestHandler requestHandler = new RequestHandler(scanner.nextLine(), requestType);


            answer = scanner.nextLine().toLowerCase().trim();


    }
}