package by.pharmsystem.medicamentservice.service.util.constant;

public enum GroupStorage {
    ANTIVIRAL,
    ANTIBIOTICS,
    ALLERGY,
    ANTISEPTICS,
    ANTIPYRETICS,
    CARDIOVASCULAR,
    IMMUNITY,
    NERVOUS_SYSTEM,
    OTOLARINGOLOGY,
    GASTROINTERSTINAL,
    ПРОТИВОВИРУСНЫЕ,
    АНТИБИОТИКИ,
    АНТИГИСТАМИННЫЕ,
    АНТИСЕПТИКИ,
    СЕРДЕЧНО_СОСУДИСТЫЕ,
    ИММУНИТЕТ,
    НЕРВНАЯ_СИСТЕМА,
    ЛОР,
    ЖКТ;

    public static boolean isExists(String group) {
        boolean isExists = true;
        try {
            GroupStorage.valueOf(group.toUpperCase()
                    .replace(" ", "_")
                    .replace("-", "_")
            );
        } catch (IllegalArgumentException exc) {
            isExists = false;
        }
        return isExists;
    }
}
