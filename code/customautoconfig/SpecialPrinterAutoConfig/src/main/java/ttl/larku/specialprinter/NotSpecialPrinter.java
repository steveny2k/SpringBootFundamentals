package ttl.larku.specialprinter;

public class NotSpecialPrinter {

    private String prefix = "<!--";
    private String suffix = "-->";

    public NotSpecialPrinter(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public NotSpecialPrinter() {}

    public String log(String message) {
        return prefix + message + suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
