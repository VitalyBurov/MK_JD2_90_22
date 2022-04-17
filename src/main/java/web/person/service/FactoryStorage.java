package web.person.service;

import web.person.core.StorageType;

public class FactoryStorage implements IFactoryStorage {
    @Override
    public IStorage getStorageByType(StorageType type) {
        if (type.equals(StorageType.SESSION)) {
            return new SessionStorage();
        } else if (type.equals(StorageType.COOKIES)) {
            return new CookieStorage();
        } else {
            throw new IllegalStateException("No realisation!");
        }
    }
}
