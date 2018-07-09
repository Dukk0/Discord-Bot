import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.entities.*;

public class Command extends ListenerAdapter {
    KeyValueList<Integer> listBattle = new KeyValueList<>();
    KeyValueList<Integer> listWar = new KeyValueList<>();
    KeyValueList<Integer>listDonations = new KeyValueList<>();
    public void onMessageReceived(MessageReceivedEvent e){
        String username = "";                                           // username of the person being added to the db
        User utilizador = e.getAuthor();                                // Get the author if this request
        if (e.getMessage().getContent().startsWith("!")) {
            String[] args = e.getMessage().getContent().replaceFirst("!", "").split(" ");       // args stores the sentence passed to the bot separeted by spaces
            if (args.length >= 3 ){                                                                                     // if a username has spaces  eg. "User Name Name" this if clause sets the username accordingly
                for(int i=1;i<args.length;i++) {
                    username += args[i] + " ";
                }
            }else if(args.length == 2){username = args[1];}

            if (utilizador.getName().equals("IVA") || utilizador.getName().equals("Aatesh") || utilizador.getName().equals("Duk0")) {        //only do the commands if the users are the leaders. Soif's name is "Aatesh"
                switch (args[0]) {
                    case "help":
                        Help.run(e.getMessage());
                        break;
                    case "missedBattle":
                        if (args.length < 2) {
                            e.getChannel().sendMessage("You didn't specify a user!!!").queue();
                        } else {
                            if (listBattle.contains(username)){                                                 // if the database already has the user in it
                                int numMissBattles = listBattle.get(username);                                   // we get the record of that user missing battles
                                listBattle.set(username, numMissBattles + 1);                                    // and update his missing battles, to another one
                                e.getChannel().sendMessage("User Record Updated").queue();
                            } else {
                                listBattle.set(username, 1);                                                     // if the user inst in the database, we put him, and set his missed battles  as 1
                                e.getChannel().sendMessage("User Record Added").queue();
                            }
                        }
                        break;
                    case "deleteBattles":
                        if (listBattle.contains(username)) {
                            listBattle.remove(username);                                                                // deleting a record from the database
                            e.getChannel().sendMessage("User record deleted!").queue();                         // checks if its in the db. if so, then deletes the record
                        } else {
                            e.getChannel().sendMessage("User isn't in the database").queue();                   // if not, doesn't delete.
                        }
                        break;
                    case "deleteWars":
                        if (listWar.contains(username)) {
                            listWar.remove(username);                                                                // deleting a record from the database
                            e.getChannel().sendMessage("User record deleted!").queue();                         		// checks if its in the db. if so, then deletes the record
                        } else {
                            e.getChannel().sendMessage("User isn't in the database").queue();                   	// if not, doesn't delete.
                        }
                        break;
                    case "getUsersBattle":
                        String[] usrMissBattle = listBattle.keys();                                                                                     // created an array with the keys present in the database, aka users.
                        if (usrMissBattle.length==0){e.getChannel().sendMessage("No Users in the database!").queue();}
                        for (int i = 0; i < listBattle.size(); i++) {
                            String userBattle = usrMissBattle[i];                                                                                            // current user in the for loop
                            int missBattleUser = listBattle.get(userBattle);                                                                               // missed battles from said user
                            String formated = "User: " + userBattle + "        Missed Battles: " + missBattleUser;                                             // string to send in the chat
                            e.getChannel().sendMessage(formated).queue();
                        }
                        break;
                    case "getUsersWar":
                        String[] usrMissWar = listWar.keys();                                                                                               // created an array with the keys present in the database, aka users.
                        if (usrMissWar.length==0){e.getChannel().sendMessage("No Users in the database!").queue();}
                        for (int i = 0; i < listWar.size(); i++) {
                            String userWar = usrMissWar[i];                                                                                                 // current user in the for loop
                            int missBattleUser = listWar.get(userWar);                                                                                      // missed wars from said user
                            String formated = "User: " + userWar + "        Missed Wars: " + missBattleUser;                                                // string to send in the chat
                            e.getChannel().sendMessage(formated).queue();
                        }
                        break;
                    case "get":
                        if (listDonations.contains(username)) {																			//get the war missed by said user only if they are in the missing wars list
                            int missDonations = listDonations.get(username);
                            String ending = (missDonations > 1) ? " weeks" : " week";                                                                                   //  check if its "week" or "weeks"
                            String formated = "User:  " + username + "    hasn´t been donating for " + missDonations + ending;
                            e.getChannel().sendMessage(formated).queue();
                        }
                        if (listWar.contains(username)) {																			//get the war missed by said user only if they are in the missing wars list
                            int missWarUser = listWar.get(username);
                            String formated = "User: " + username + "        Missed Wars: " + missWarUser;
                            e.getChannel().sendMessage(formated).queue();
                        }
                        if (listBattle.contains(username)){																			//get the war missed by said user only if they are in the missing wars list
                            int missBattleUser = listBattle.get(username);
                            String formated = "User: " + username + "        Missed Battles: " + missBattleUser;
                            e.getChannel().sendMessage(formated).queue();
                        }else if (!listBattle.contains(username) && !listWar.contains(username) && !listDonations.contains(username)){e.getChannel().sendMessage("User not in the database!").queue();}		//if it isnt on either of the lists, give this message
                        break;
                    case "missedWar":
                        if (args.length < 2) {
                            e.getChannel().sendMessage("You didn't specify a user!!!").queue();
                        } else {
                            if (listWar.contains(username)) {                                                 // if the database already has the user in it
                                int numMissBattles = listWar.get(username);                                   // we get the record of that user missing battles
                                listWar.set(username, numMissBattles + 1);                                    // and update his missing battles, to another one
                                e.getChannel().sendMessage("User Record Updated").queue();
                            } else {
                                listWar.set(username, 1);                                                     // if the user inst in the database, we put him, and set his missed battles  as 1
                                e.getChannel().sendMessage("User Record Added").queue();
                            }
                        }
                        break;
                    case "noDonations":
                        if (args.length < 2) {
                            e.getChannel().sendMessage("You didn't specify a user!!!").queue();
                        }
                            if (listDonations.contains(username)){                                                  // if the database already has the user in it
                                int numWeeksNoDonations = listDonations.get(username);                                   // we get the record of that user consecutive weeks non donating
                                listDonations.set(username, numWeeksNoDonations + 1);                                    // and update his missing Donations, to another one
                                e.getChannel().sendMessage("User Record Updated").queue();
                            } else {
                                listDonations.set(username, 1);                                                     // if the user inst in the database, we put him, and set his missed weeks  as 1
                                e.getChannel().sendMessage("User Record Added").queue();
                            }
                        break;
                    case "getNonDonators":
                        String[] usrMissDonations = listDonations.keys();                                                                                               // created an array with the keys present in the database, aka users.
                        if (usrMissDonations.length==0){e.getChannel().sendMessage("No Users in the database!").queue();}
                        for (int i = 0; i < listDonations.size(); i++) {
                            String userDon = usrMissDonations[i];                                                                                                 // current user in the for loop
                            int missDonations = listDonations.get(userDon);                                                                                         // missed wars from said user
                            String ending = (missDonations > 1) ? " weeks" : " week";                                                                                   //  check if its "week" or "weeks"
                            String formated = "User:  " + userDon + "    hasn´t been donating for " + missDonations + ending;                                                // string to send in the chat
                            e.getChannel().sendMessage(formated).queue();
                        }
                        break;
                    case "deleteDonations":
                        if (listDonations.contains(username)) {
                            listDonations.remove(username);                                                                // deleting a record from the database
                            e.getChannel().sendMessage("User record deleted!").queue();                         		// checks if its in the db. if so, then deletes the record
                        } else {
                            e.getChannel().sendMessage("User isn't in the database").queue();                   	// if not, doesn't delete.
                        }
                        break;
                    case "stream":
                        e.getChannel().sendMessage("https://www.twitch.tv/phonecats").queue();
                        e.getChannel().sendMessage("Phonecats Stream!!!").queue();
                        break;
                    case "dev":
                        e.getChannel().sendMessage("This bot was made by Smoosh from Phonecats3. you can check the source code at --> https://github.com/Dukk0/Discord-Bot").queue();
                        break;
                    default:
                        e.getChannel().sendMessage("Command not Found!").queue();
                        break;
                }
            }else{
                switch (args[0]) {
                    case "help":
                        Help.run(e.getMessage());
                        break;
                    case "stream":
                        e.getChannel().sendMessage("https://www.twitch.tv/phonecats").queue();
                        e.getChannel().sendMessage("Phonecats Stream!!!").queue();
                        break;
                    case "dev":
                        e.getChannel().sendMessage("This bot was made by Smoosh from Phonecats3. you can check the source code at --> https://github.com/Dukk0/Discord-Bot").queue();
                        break;
                    default:
                        e.getChannel().sendMessage("You don't have permission to use that command!").queue();
                        break;
                }
            }
        }
    }
}
