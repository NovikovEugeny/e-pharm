package by.pharmsystem.medicamentservice.service.util.constant;

public enum FormStorage {
    PILLS,
    CAPSULES,
    POWDER,
    DROPS,
    SOLUTION,
    MIXTURE,
    SUSPENSION,
    AEROSOL,
    SPRAY,
    SYRUP,
    PASTILES,
    ТАБЛЕТКИ,
    КАПСУЛЫ,
    ПОРОШКИ,
    КАПЛИ,
    РАСТВОР,
    МИКСТУРА,
    СУСПЕНЗИЯ,
    АЕРОЗОЛЬ,
    СПРЕЙ,
    СИРОП,
    ПАСТИЛКИ;

    public static boolean isExists(String form) {
        boolean isExists = true;
        try {
            FormStorage.valueOf(form.toUpperCase());
        } catch (IllegalArgumentException exc) {
            isExists = false;
        }
        return isExists;
    }
}
