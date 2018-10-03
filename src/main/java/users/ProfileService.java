package users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import containers.Period;
import containers.Product;
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
            objectMapper.writeValue(new File(tempProfile.getProfileName() + ".json"), userProfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveProfileHistory(Profile userProfile) {
        ArrayList<Period> periods = new ArrayList<Period>();

        for (int i = 0; i < userProfile.getHistoryOfPeriods().size(); i++) {
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

        if (!userProfile.isHistoryOfPeriodsEmpty()) {
            if (!userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).isWeeksEmpty()) {

                for (int i = 0; i <= userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getWeeks().length; i++) {
                    weeks.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getWeek(i));
                    weekDOne.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getWeekDone(i));
                }
            } else {
                System.out.println("Pusta lista tygodni w okresie!");
            }
        } else {
            System.out.println("Pusty kontener okresów!");
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
        ArrayList<Product> listOne = new ArrayList<Product>();
        ArrayList<Product> listTwo = new ArrayList<Product>();
        ArrayList<Product> listThree = new ArrayList<Product>();

        if (!userProfile.isHistoryOfPeriodsEmpty()) {
            if (!userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).isWeeksEmpty()) {

                for (int i = 0; i < userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListOne().size(); i++) {
                    listOne.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListOne().get(i));
                }

                for (int i = 0; i < userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListTwo().size(); i++) {
                    listTwo.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListTwo().get(i));
                }

                for (int i = 0; i < userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListThree().size(); i++) {
                    listThree.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListThree().get(i));
                }
            } else {
                System.out.println("Pusta lista tygodni w okresie!");
            }
        } else {
            System.out.println("Pusty kontener okresów!");
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(userProfile.getProfileName() + "_listOne.json"), listOne);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mapper.writeValue(new File(userProfile.getProfileName() + "_listTwo.json"), listTwo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mapper.writeValue(new File(userProfile.getProfileName() + "_listThree.json"), listThree);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveProfile(Profile userProfile) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//
//        mapper.writeValue(new File(userProfile.getProfileName() + ".json"), userProfile);

        saveProfileSettings(userProfile);
        if (!userProfile.getHistoryOfPeriods().isEmpty()) {
            saveProfileHistory(userProfile);
        }
        saveProfileActualPeriodWeeks(userProfile);
        saveProfileActualWeekLists(userProfile);
    }

    private void readProfileHistory(String profileName, Profile profile) {
        ObjectMapper mapper = new ObjectMapper();

        ArrayList<Period> periods = new ArrayList<Period>();


        try {
            periods = mapper.readValue(new File(profileName + "_Periods.json"), new TypeReference<ArrayList<Period>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <= periods.size(); i++) {
            profile.getHistoryOfPeriods().add(periods.get(i));
        }


    }

    private void readProfileActualPeriodWeeks(String profileName, Profile profile) {

        ObjectMapper mapper = new ObjectMapper();

        ArrayList<Week> weeks = new ArrayList<Week>();
        ArrayList<Boolean> weekDone = new ArrayList<Boolean>();

        try {
            weeks = mapper.readValue(new File(profileName + "_Weeks.json"), new TypeReference<ArrayList<Week>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            weekDone = mapper.readValue(new File(profileName + "_weekDone.json"), new TypeReference<ArrayList<Boolean>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <= weeks.size(); i++) {
            profile.getHistoryOfPeriods().get(profile.getPeriodsCounter()).addWeek(i, weeks.get(i));
        }

        for (int i = 0; i <= weekDone.size(); i++) {
            profile.getHistoryOfPeriods().get(profile.getPeriodsCounter()).addWeekDone(i, weekDone.get(i));
        }
    }

    private void readProfileActualWeekLists(String profileName, Profile profile) {
        ArrayList<Product> listOne = new ArrayList<Product>();
        ArrayList<Product> listTwo = new ArrayList<Product>();
        ArrayList<Product> listThree = new ArrayList<Product>();

        ObjectMapper mapper = new ObjectMapper();

        try {
            listOne = mapper.readValue(new File(profileName + "_listOne.json"), new TypeReference<ArrayList<Product>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            listTwo = mapper.readValue(new File(profileName + "_listTwo.json"), new TypeReference<ArrayList<Product>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            listThree = mapper.readValue(new File(profileName + "_listThree.json"), new TypeReference<ArrayList<Product>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <= listOne.size(); i++) {
            profile.getHistoryOfPeriods().get(profile.getPeriodsCounter()).getPresentWeek().addToListOne(listOne.get(i));
        }
        for (int i = 0; i <= listTwo.size(); i++) {
            profile.getHistoryOfPeriods().get(profile.getPeriodsCounter()).getPresentWeek().addToListTwo(listTwo.get(i));
        }
        for (int i = 0; i <= listThree.size(); i++) {
            profile.getHistoryOfPeriods().get(profile.getPeriodsCounter()).getPresentWeek().addToListThree(listThree.get(i));
        }
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
        // chosenProfile.setHistoryOfPeriods(tempChosenProfile.getHistoryOfPeriods());
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

        readProfileHistory(profileName, chosenProfile);
        readProfileActualPeriodWeeks(profileName, chosenProfile);
        readProfileActualWeekLists(profileName, chosenProfile);
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
