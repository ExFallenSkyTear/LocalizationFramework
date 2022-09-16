import com.java.framework.localization.Manager;
import com.java.framework.localization.Category;
import com.java.framework.localization.Entry;

public class Main {
    public static Manager globalLocalization = new Manager();

    public static void main(String[] args) throws Exception {
        Category misc = globalLocalization.createCategory("Misc");
        Entry informalGreetings = misc.createEntry("informalGreetings","Hello");
        Entry planetEarth = misc.createEntry("planetEarth","World");

        System.out.printf("%s %s!%n",
                informalGreetings.getValue(),
                planetEarth.getValue()
        );

        //globalLocalization.exportDefaults("c:\\tmp\\");

        globalLocalization.importConfiguration("c:\\tmp\\test.xml");

        System.out.printf("%s %s!%n",
                informalGreetings.getValue(),
                planetEarth.getValue()
        );
    }
}