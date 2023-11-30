public static void register(){
        System.out.println("Please enter the following information to register:");
        System.out.print("First Name: ");
        String firstname = sc.nextLine();
        System.out.print("Last Name: ");
        String lastname = sc.nextLine();
        System.out.print("New Username: ");
        String username = sc.nextLine();
        System.out.print("New Pin Number: ");
        String pin = sc.nextLine();
        System.out.print("State (ex. TX): ");
        String state = sc.nextLine();
        System.out.print("City: ");
        String city = sc.nextLine();
        System.out.print("Zip Code: ");
        String zip = sc.nextLine();
        System.out.print("Date of Birth (mm/dd/yy): ");
        String dateOfBirth = sc.nextLine();
        utility.registerUser(state,username,firstname,pin,lastname,city,zip,dateOfBirth);
        System.out.println("Registration Complete!");
        Log.msg(username + " has registered");
        login();
    }
