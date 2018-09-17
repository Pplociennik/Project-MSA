package users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import containers.Period;
import containers.Week;

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

    private void saveProfileSettings(Profile userProfile) {
        Profile tempProfile = new Profile();

        tempProfile.setWeeksCounter(userProfile.getWeeksCounter());
        tempProfile.setWalletPercentage(tempProfile.getWalletPercentage());
        tempProfile.setWallet(userProfile.getWallet());
        tempProfile.setWalletFreeValue(userProfile.getWalletFreeValue());
        tempProfile.setWalletCalculationType(userProfile.getWalletCalculationType());
        tempProfile.setProfileName(userProfile.getProfileName());
        tempProfile.setProductsCounter(userProfile.getProductsCounter());
        tempProfile.setPeriodsCounter(userProfile.getPeriodsCounter());
        tempProfile.setListTwoPercentage(userProfile.getListTwoPercentage());
        tempProfile.setListTwoName(userProfile.getListTwoName());
        tempProfile.setListThreePercentage(userProfile.getListThreePercentage());
        tempProfile.setListThreeName(userProfile.getListThreeName());
        tempProfile.setListOnePercentage(userProfile.getListOnePercentage());
        tempProfile.setListOneName(userProfile.getListOneName());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(tempProfile.getProfileName() + ".json"), tempProfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveProfileHistory(Profile userProfile) {
        ArrayList<Period> periods = new ArrayList<Period>();

        for (int i = 0; i <= userProfile.getHistoryOfPeriods().size(); i++) {
            periods.add(userProfile.getHistoryOfPeriods().get(i));
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(userProfile.getProfileName() + "_Periods.json"), periods);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveProfileActualPeriodWeeks(Profile userProfile) {
        ArrayList<Week> weeks = new ArrayList<Week>();
        ArrayList<Boolean> weekDOne = new ArrayList<Boolean>();

        for (int i = 0; i <= userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getWeeks().length; i++) {
            weeks.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getWeek(i));
            weekDOne.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getWeekDone(i));
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(userProfile.getProfileName() + "_Weeks.json"), weeks);
        } catch (IOException e) {
            e.printStackTrace();

            try {
                mapper.writeValue(new File(userProfile.getProfileName() + "_weekDone.json"), weekDOne);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void saveProfileActualWeekLists(Profile userProfile) {

    }

    public void saveProfile(Profile userProfile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(userProfile.getProfileName() + ".json"), userProfile);
    }

    public Profile readProfile(String profileName) {
        ObjectMapper mapper = new ObjectMapper();
        Profile tempChosenProfile = new Profile(profileName);
        Profile chosenProfile = new Profile("default");

        try {
            tempChosenProfile = mapper.readValue(new File(profileName + ".json"), new TypeReference<Profile>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        chosenProfile.setWallet(tempChosenProfile.getWallet());
        chosenProfile.setHistoryOfPeriods(tempChosenProfile.getHistoryOfPeriods());
        chosenProfile.setListOneName(tempChosenProfile.getListOneName());
        chosenProfile.setListOnePercentage(tempChosenProfile.getListOnePercentage());
        chosenProfile.setListThreeName(tempChosenProfile.getListThreeName());
        chosenProfile.setListThreePercentage(tempChosenProfile.getListThreePercentage());
        chosenProfile.setListTwoName(tempChosenProfile.getListTwoName());
        chosenProfile.setListTwoPercentage(tempChosenProfile.getListTwoPercentage());
        chosenProfile.setPeriodsCounter(tempChosenProfile.getPeriodsCounter());
        chosenProfile.setProductsCounter(tempChosenProfile.getProductsCounter());
        chosenProfile.setProfileName(tempChosenProfile.getProfileName());
        chosenProfile.setWalletCalculationType(tempChosenProfile.getWalletCalculationType());
        chosenProfile.setWalletFreeValue(tempChosenProfile.getWalletFreeValue());
        chosenProfile.setWalletPercentage(tempChosenProfile.getWalletPercentage());
        chosenProfile.setWeeksCounter(tempChosenProfile.getWeeksCounter());
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
            this.profiles = mapper.readValue(new File("profiles.json"), new TypeReference<ArrayList<String>>() {
            });
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
