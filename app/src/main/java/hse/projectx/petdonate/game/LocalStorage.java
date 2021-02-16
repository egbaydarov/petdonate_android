package hse.projectx.petdonate.game;

import android.content.Context;

public interface LocalStorage<T extends java.io.Serializable> {
    void SaveToStorage(Context context);
    T LoadFromStorage(Context context);
    void DeleteLocalStorage(Context context);

    public class LocalStorageException extends RuntimeException
    {

    }
}
