import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class FullElizaApp {
    public static void main(String[] args) {
        /*
        1. import Scanner
        2. Display welcome message
        3. Assign user input to a variable
        4. Split input and loop through the array to find
        specific words to be replaced and have eliza respond
        using new String with replacements.
        5. Initiate loop under the condition that the user
        does not enter either 'q' or 'I am feeling great'.
        6. if the user enters 'I am feeling great' display
        appropriate message before ending program.
         */
        Random r = new Random();
        Scanner scn = new Scanner(System.in);
        //imports local date and time, can pull individual day/hour/month/year with .get
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("Enter your first name: ");
        String userName = scn.nextLine();
        String userResponseSplit[];
        String userResponseRep;
        int questionCounter = 0;
        //using .getHour() from local date time to print the
        //appropriate message given the time of the day
        if (localDateTime.getHour() < 12) {
            System.out.println("Good morning " + userName);
        } else {
            System.out.println("Good afternoon " + userName);
        }

        //ElizaChat actually begins
        System.out.println("Welcome to ElizaChats, the #1 Yelp reviewed online therapist.\nNow, how can I help you today?");
        System.out.println("Enter response here or 'Q' to quit:");
        String userResponse = scn.nextLine();
        //wordReplace() takes the userResponse (user scanner input), splits it, and checks it for instances of 'I', 'me', 'am', and 'my'
        //and replaces them with 'you', 'you', 'are', and 'your' respectively. Then returns the new string to be displayed.
        userResponseRep = wordReplace(userResponse);
        while ((!userResponse.equalsIgnoreCase("Q")) && (!userResponse.equalsIgnoreCase("I am feeling great")) && (questionCounter<5)) {
            //responseRandomizer() takes what the new string returned from wordReplace() and a Random class to select a random
            //response every time the user enters something new.
            System.out.println(responseRandomizer(userResponseRep, r));
            userResponse = scn.nextLine();
            userResponseRep = wordReplace(userResponse);
            //questionCounter just caps the number of iterations to 5 and displays a funny message if an actual therapist said it.
            questionCounter++;
            if (questionCounter == 5){
                System.out.println("*YAWN* It's getting late, and honestly I'm a little bored. I'll show you out the door.");
            }

        }
        //If userResponse is 'I am feeling great' display special message
        if (userResponse.equalsIgnoreCase("I am feeling great")){
            System.out.println("Another success by ElizaChats! I will do you a favor and submit a Yelp review in your name.\nThank you "+userName+" :)");
        }
    }

    public static String wordReplace(String userResponse) {
        String userResponseSplit[] = userResponse.split(" ");
        String userResponseRep = "";
        for (int i = 0; i < Array.getLength(userResponseSplit); i++) {
            if ((userResponseSplit[i].equalsIgnoreCase("I")) || (userResponseSplit[i].equalsIgnoreCase("me"))) {
                userResponseSplit[i] = "you";
            } else if (userResponseSplit[i].equalsIgnoreCase("my")) {
                userResponseSplit[i] = "your";
            } else if (userResponseSplit[i].equalsIgnoreCase("am")) {
                userResponseSplit[i] = "are";
            }
            userResponseRep += userResponseSplit[i]+" ";
        }
        return userResponseRep;}
    public static String responseRandomizer(String userResponseRep, Random r){

        String responseArray[] = new String[8];
        responseArray[0] = "What makes you say " + userResponseRep + "? Enter response here or 'Q' to quit:";
        responseArray[1] = "It seems like you're holding back, please share more. Enter response here or 'Q' to quit:";
        responseArray[2] = "I see... so you're concerned with "+userResponseRep+". Tell me more about that. Enter response here or 'Q' to quit:";
        responseArray[3] = "I don't understand? Could you elaborate? Enter response here or 'Q' to quit:";
        responseArray[4] = "When you say "+userResponseRep+" what exactly do you mean? Enter response here or 'Q' to quit:";
        responseArray[5] = "Does "+userResponseRep+" make you angry? sad? how does it make you feel? Enter response here or 'Q' to quit:";
        responseArray[6] = "And how does that make you feel? Enter response here or 'Q' to quit:";
        responseArray[7] = "Hmm... Enter response here or 'Q' to quit:";
        String elizaCares = responseArray[r.nextInt(8)];

    return elizaCares;}
}
