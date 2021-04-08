package net.microinvest.dollywrapper;

import com.donkey.dolly.Dolly;
import com.donkey.dolly.Type;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import anywheresoftware.b4a.BA;

@BA.Version(0.01F)
@BA.ActivityObject
@BA.DependsOn(values = {"DollyLibrary.aar", "androidx-security.jar", "tink-android-1.5.0.jar"})
@BA.ShortName("Dolly")
public class DollyWrapper {

    Dolly dolly;

    public void Initialize(final BA ba) {
        this.dolly = Dolly.getInstance(ba.context);
    }

    public void putInt(String key, int value, String type) {
        dolly.putInt(key, value, Type.valueOf(type));
    }

    public int getIntWithDefault(String key, int defaultValue, String type) {
        return dolly.getInt(key, defaultValue, Type.valueOf(type));
    }

    public int getInt(String key, String type) {
        return dolly.getInt(key, Type.valueOf(type));
    }

    public void putBoolean(String key, Boolean value, String type) {
        dolly.putBoolean(key, value, Type.valueOf(type));
    }

    public boolean getBooleanWithDefault(String key, boolean defaultValue, String type) {
        return dolly.getBoolean(key, defaultValue, Type.valueOf(type));
    }

    public boolean getBoolean(String key, String type) {
        return dolly.getBoolean(key, Type.valueOf(type));
    }

    public void putFloat(String key, float value, String type) {
        dolly.putFloat(key, value, Type.valueOf(type));
    }

    public float getFloatWithDefault(String key, float defaultValue, String type) {
        return dolly.getFloat(key, defaultValue, Type.valueOf(type));
    }

    public float getFloat(String key, String type) {
        return dolly.getFloat(key, Type.valueOf(type));
    }

    public void putLong(String key, long value, String type) {
        dolly.putLong(key, value, Type.valueOf(type));
    }

    public long getLongWithDefault(String key, long defaultValue, String type) {
        return (long) dolly.getLong(key, defaultValue, Type.valueOf(type));
    }

    public long getLong(String key, String type) {
        return (long) dolly.getLong(key, Type.valueOf(type));
    }


    public void putDouble(String key, double value, String type) {
        dolly.putDouble(key, value, Type.valueOf(type));
    }

    public double getDoubleWithDefault(String key, double defaultValue, String type) {
        return dolly.getDouble(key, defaultValue, Type.valueOf(type));
    }

    public double getDouble(String key, String type) {
        return dolly.getDouble(key, Type.valueOf(type));
    }

    public void putString(String key, String value, String type) {
        dolly.putString(key, value, Type.valueOf(type));
    }

    public String getStringWithDefault(String key, String defaultValue, String type) {
        return dolly.getString(key, defaultValue, Type.valueOf(type));
    }

    public String getString(String key, String type) {
        return dolly.getString(key, Type.valueOf(type));
    }

    public void putStringSet(String key, List<String> value, String type) {
        Set<String> set = ListToSet(value);
        dolly.putStringSet(key, set, Type.valueOf(type));
    }

    public List<String> getStringSetWithDefault(String key, List<String> defaultValue, String type) {
        return SetToList(dolly.getStringSet(key, ListToSet(defaultValue), Type.valueOf(type)));
    }

    public List<String> getStringSet(String key, String type) {
        return SetToList(dolly.getStringSet(key, Type.valueOf(type)));
    }

    private Set<String> ListToSet(List<String> list) {
        return new HashSet<String>(list);
    }

    public void remove(String key, String type) {
        dolly.remove(key, Type.valueOf(type));
    }

    public boolean contains(String key) {
        return dolly.contains(key);
    }

    public void removeAll() {
        dolly.removeAll();
    }

    private List<String> SetToList(Set<String> set) {
        return new ArrayList<>(set);
    }




}
