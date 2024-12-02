
import java.util.Locale;

public class WorldNews {
    private String category;
    private String title;
    private String content;
    private double date;
    private String source;

    public Category(string category, string title, string content, double date, string source) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.date = date; 
        this.source = source;
    }

    public static String getcategory(){
        return category;
    }

}
