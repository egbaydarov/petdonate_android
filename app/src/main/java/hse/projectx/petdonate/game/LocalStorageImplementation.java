package hse.projectx.petdonate.game;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.time.LocalDateTime;

public class LocalStorageImplementation implements LocalStorage<VirtualPet> {
    Gson gson = new Gson();

    static LocalStorageImplementation Instance;
    VirtualPet Data;

    private LocalStorageImplementation() {
    }

    public static LocalStorageImplementation getInstance(Context context) {
        if (Instance == null) {
            Instance = new LocalStorageImplementation();
            try {
                Instance.Data = Instance.LoadFromStorage(context);
            } catch (LocalStorageException ex) {
                Instance.Data = new VirtualPet();
                Instance.SaveToStorage(context);
            }
        }
        return Instance;
    }

    @Override
    public void SaveToStorage(Context context) {
        String json = gson.toJson(Data);
        if (json == null)
            return;

        SharedPreferences settings = context.getSharedPreferences
                (GameConstants.PREFS_KEY, Context.MODE_PRIVATE);
        settings.edit().putString(GameConstants.VPET_KEY, json).apply();
    }

    @Override
    public VirtualPet LoadFromStorage(Context context) {
        SharedPreferences settings = context.getSharedPreferences
                (GameConstants.PREFS_KEY, Context.MODE_PRIVATE);
        String json = settings.getString(GameConstants.VPET_KEY, null);

        return gson.fromJson(json, VirtualPet.class);
    }

    @Override
    public void DeleteLocalStorage(Context context) {
        SharedPreferences settings = context.getSharedPreferences
                (GameConstants.PREFS_KEY, Context.MODE_PRIVATE);
        settings.edit().remove(GameConstants.VPET_KEY).apply();
    }

    @Override
    public boolean IsStorageExists(Context context) {
        SharedPreferences settings = context.getSharedPreferences
                (GameConstants.PREFS_KEY, Context.MODE_PRIVATE);
        String json = settings.getString(GameConstants.VPET_KEY, null);
        return json != null;
    }
}
