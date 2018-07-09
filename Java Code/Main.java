import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Main extends ListenerAdapter{
    public static void main(String[] args) {
        JDABuilder jda = new JDABuilder(AccountType.BOT);
        jda.setToken("#################################################");
        jda.setAudioEnabled(false);
        jda.addEventListener(new Main());
        jda.addEventListener(new Command());
        try{
            jda.buildAsync();
        }catch (Exception e){
            System.out.print("Bot Exception: " + e.getLocalizedMessage());
        }
    }
}
