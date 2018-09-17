package users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileService {

    private ArrayList<String> profiles;

    public ProfileService() {
        profiles = new ArrayList<String>();
    }

    public void saveProfile(Profile userProfile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(userProfile.getProfileName() + ".json"), userProfile);
    }

    public Profile readProfile(String profileName) {
        ObjectMapper mapper = new ObjectMapper();
        Profile chosenProfile = new Profile(profileName);

        try {
            chosenProfile = mapper.readValue(new File(profileName + ".json"), new TypeReference<Profile>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chosenProfile;
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
            this.profiles = mapper.readValue(new File("profiles.json"), new TypeReference<ArrayList<String>>() {} );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNewProfile(String profileName) {
        Profile profile = new Profile(profileName);
        profiles.add(profile.getProfileName());
        try {
            saveProfile(profile);
            saveProfilesList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeProfile(int index) {
        this.profiles.remove(index);
        this.saveProfilesList();
    }

    public ArrayList<String> getProfiles() {
        return profiles;
    }
}
