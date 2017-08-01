package by.shyrei.rentbike.action.locale;

/**
 * Project RentBikeAction
 * Created on 14.07.2017.
 * author Shyrei Uladzimir
 */
public enum LocaleType {
    DEFAULT("locale_en_US"),
    RU("locale_ru_RU");
    private String locale;

    public static String getLocale(String key) {
        LocaleType entry = LocaleType.valueOf(key.toUpperCase());
        return entry.locale;
    }

    LocaleType(String locale) {
        this.locale = locale;
    }
}
