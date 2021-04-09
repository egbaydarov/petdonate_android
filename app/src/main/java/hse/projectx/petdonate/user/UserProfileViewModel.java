package hse.projectx.petdonate.user;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.UUID;

import hse.projectx.petdonate.game.GameConstants;
import hse.projectx.petdonate.game.LocalStorage;
import hse.projectx.petdonate.game.VirtualPet;
import hse.projectx.petdonate.network.models.User;

public class UserProfileViewModel extends ViewModel {
    Gson gson = new Gson();

    String UserId = UUID.randomUUID().toString();
    User user = new User();
    VirtualPet vPet = new VirtualPet();
    LocalDateTime LastUpdate;

    public void SaveToStorage(Context context) {
        String jsonUser = gson.toJson(user);
        String jsonVPet = gson.toJson(vPet);

        if (jsonUser == null || jsonVPet == null)
            return;

        SharedPreferences settings = context.getSharedPreferences
                (GameConstants.PREFS_KEY, Context.MODE_PRIVATE);
        settings.edit().
                putString(UserId + "user", jsonUser).
                putString(UserId + "vpet", jsonVPet).
                apply();
    }

    public VirtualPet LoadFromStorage(Context context) {
        SharedPreferences settings = context.getSharedPreferences
                (GameConstants.PREFS_KEY, Context.MODE_PRIVATE);
        String json = settings.getString(GameConstants.VPET_KEY, null);

        if (json == null) {
            throw new LocalStorage.LocalStorageException();
        }

        return gson.fromJson(json, VirtualPet.class);
    }


}
