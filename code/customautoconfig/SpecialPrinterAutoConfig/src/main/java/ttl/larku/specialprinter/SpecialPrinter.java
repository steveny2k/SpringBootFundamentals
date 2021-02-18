package ttl.larku.specialprinter;

/**
 * Part of the AutoConfiguration example.  This is a class that we provide as
 * part of a library.  We also provide an AutoConfiguration for it.
 * What this means is that the user can simple inject a bean of type
 * SpecialPrinter into their code, and they will get our AutoConfigured
 * bean by default.  Or, if they want to do their own configuration, then
 * they can provide their own SpecialPrinter Bean, and that's is what
 * Spring will use instead of the AutoConfigured default.
 *
 * Javadoc in SpecialPrinterAutoConfig has more info.
 */
public class SpecialPrinter {

    private String prefix = "[";
    private String suffix = "]";

    public SpecialPrinter(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public SpecialPrinter() {}

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
