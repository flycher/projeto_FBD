package app;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interface {

    final static Scanner scan = new Scanner(System.in);

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
    	
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
        
    }

    public static String matchEmail(String email) {

        while (!validate(email)) {

            System.out.print("Please enter a valid email(someone@something.domain): ");
            email = scan.next();

        }

        return email;
    }

    public static String validatePassword(String password) {

        while (!((password.length() >= 5) && (password.length() <= 50))) {

            System.out.print("Please enter a valid password: ");
            password = scan.next();

        }

        return password;
    }


    public static void login(User usuario) {

        String username;
        String email;
        String password;

        boolean running = true;
        int option;

        while (running) {

            System.out.println("Welcome to QTwitter!");
            System.out.println(" [1] - Login \n [2] - Register \n [3] - Quit");
            System.out.print(" > ");

            try {
                option = scan.nextInt();
            } catch (Exception e) {
                scan.nextLine();
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("\nInsert your username: ");
                    username = scan.next();
                    System.out.print("Insert your password: ");
                    password = scan.next();
                    usuario.getUser(username, password);
                    if (usuario.getUsername() != null)
                        profile(usuario);
                    else
                        System.out.println("User not found.");
                    break;

                case 2:
                    System.out.print("\nInsert your username: @");
                    username = '@' + scan.next();
                    System.out.print("Inset your email: ");
                    email = scan.next();
                    email = matchEmail(email);
                    System.out.print("Insert your password(5 to 50 characters): ");
                    password = scan.next();
                    password = validatePassword(password);
                    usuario.addUser(username, email, password);
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    running = false;

            }

        }

    }

    public static void profile(User usuario) {

        String email;
        String password;
        String message;
        String username;

        boolean running = true;
        int option;

        while (running) {

            System.out.println("Hi " + usuario.getUsername() + "!\n" +
                " [1] - Timeline \n [2] - New Post \n [3] - Followers \n [4] - Following \n" +
                " [5] - Start Following \n [6] - Stop following \n [7] - My posts \n" +
                " [8] - Update account \n [9] - Delete Account \n [0] - Quit");
            System.out.print(" > ");

            try {
                option = scan.nextInt();
            } catch (Exception e) {
                continue;
            }

            switch (option) {
                case 1:
                    Timeline.timeline(scan, usuario);
                    break;

                case 2:
                    System.out.println("Limit your message to 280 characters.");
                    System.out.print("> ");
                    scan.nextLine();
                    message = scan.nextLine();
                    Post.addPost(message, usuario);
                    break;

                case 3:
                    Followers.getFollowers(usuario.getUserId());
                    scan.nextLine();
                    System.out.println("Press enter to go back.");
                    scan.nextLine();
                    break;

                case 4:
                    Followers.getFollowing(usuario.getUserId());
                    scan.nextLine();
                    System.out.println("Press enter to go back.");
                    scan.nextLine();
                    break;

                case 5:
                    System.out.print("Enter the name of the user you want to follow: ");
                    username = scan.next();
                    Followers.startFollowing(usuario.getUserId(), username);
                    scan.nextLine();
                    break;

                case 6:
                    System.out.print("Enter the name of the user you want to stop following: ");
                    username = scan.next();
                    Followers.stopFollowing(usuario.getUserId(), username);
                    scan.nextLine();
                    break;

                case 7:
                    Post.getPosts(scan, usuario);
                    break;

                case 8:
                    System.out.println("Current email: " + usuario.getEmail());
                    System.out.println("Current password: " + usuario.getPasswrd());
                    System.out.print("Inset your new email: ");
                    email = scan.next();
                    email = matchEmail(email);
                    System.out.print("Insert your new assword(5 to 50 characters): ");
                    password = scan.next();
                    password = validatePassword(password);
                    usuario.alterUser(email, password);
                    break;

                case 9:
                    System.out.println("Are you sure you want to delete your account?");
                    System.out.println("[1]Yes [2]No\nThis cant be undone!");
                    int del = scan.nextInt();
                    if (del == 1) {
                        usuario.removeUser();
                        System.out.println("User " + usuario.getUserId() + " deleted.");
                        running = false;
                    }
                    break;

                case 0:
                    running = false;

            }

        }

    }

    public static void main(String[] args) {

        User usuario = new User();

        login(usuario);

        scan.close();

    }

}