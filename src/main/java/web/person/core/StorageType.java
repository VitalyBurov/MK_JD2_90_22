package web.person.core;

public enum StorageType {
    COOKIES,
    SESSION;

    public static StorageType valueOfIgnoreCase(String line){
        for (StorageType type : values()) {
            if(type.name().equalsIgnoreCase(line)){
                return type;
            }
        }
        throw new IllegalArgumentException("Wrong storage!");
    }
}
