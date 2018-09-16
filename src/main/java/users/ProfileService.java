package users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileService {

    public ProfileService() {

    }

    public void saveProfile(Profile userProfile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(userProfile.getProfileName() + ".json"), userProfile);
    }

    public void readProfile(Profile userProfile) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.readValue(userProfile.getProfileName() + ".json", new TypeReference<Profile>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
