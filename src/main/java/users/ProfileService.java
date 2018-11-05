package users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import containers.Period;
import containers.Product;
import containers.Week;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileService {

    private ArrayList<String> profiles;

    public ProfileService() {
        profiles = new ArrayList<String>();
    }

    public void saveProfile(Profile profile) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(profile.getProfileName() + "_data.bin"));
            outputStream.writeObject(profile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Profile readProfile(String profileName) throws IOException, ClassNotFoundException {
        Profile loadedProfile = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(profileName + "_data.bin"))) {
           loadedProfile = (Profile) inputStream.readObject();

            return loadedProfile;
        }
    }

    public void saveProfilesList() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("profiles.json"), profiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readProfilesList() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.profiles = mapper.readValue(new File("profiles.json"), new TypeReference<ArrayList<String>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNewProfile(String profileName) {
        Profile profile = new Profile(profileName);
        profiles.add(profile.getProfileName());
        saveProfile(profile);
        saveProfilesList();
    }

    public void removeProfile(int index) {
        File delete = new File(profiles.get(index) + "_data.bin");
        delete.delete();
        this.profiles.remove(index);
        this.saveProfilesList();
    }

    public ArrayList<String> getProfiles() {
        return profiles;
    }
}
