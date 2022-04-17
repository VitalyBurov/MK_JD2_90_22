package web.person.service;

import web.person.core.StorageType;

public interface IFactoryStorage {
    IStorage getStorageByType(StorageType type);
}
