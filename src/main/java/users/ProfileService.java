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


//    private void saveProfileSettings(Profile userProfile) {
//        Profile tempProfile = new Profile();
//
//        tempProfile.setWeeksCounter(userProfile.getWeeksCounter());
//        tempProfile.setWalletPercentage(tempProfile.getWalletPercentage());
//        tempProfile.setWallet(userProfile.getWallet());
//        tempProfile.setWalletFreeValue(userProfile.getWalletFreeValue());
//        tempProfile.setWalletCalculationType(userProfile.getWalletCalculationType());
//        tempProfile.setProfileName(userProfile.getProfileName());
//        tempProfile.setProductsCounter(userProfile.getProductsCounter());
//        tempProfile.setPeriodsCounter(userProfile.getPeriodsCounter());
//        tempProfile.setListTwoPercentage(userProfile.getListTwoPercentage());
//        tempProfile.setListTwoName(userProfile.getListTwoName());
//        tempProfile.setListThreePercentage(userProfile.getListThreePercentage());
//        tempProfile.setListThreeName(userProfile.getListThreeName());
//        tempProfile.setListOnePercentage(userProfile.getListOnePercentage());
//        tempProfile.setListOneName(userProfile.getListOneName());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            objectMapper.writeValue(new File(tempProfile.getProfileName() + ".json"), userProfile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private void saveProfileHistory(Profile userProfile) {
//        ArrayList<Period> periods = new ArrayList<Period>();
//
//        for (int i = 0; i < userProfile.getHistoryOfPeriods().size(); i++) {
//            periods.add(userProfile.getHistoryOfPeriods().get(i));
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            mapper.writeValue(new File(userProfile.getProfileName() + "_Periods.json"), periods);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void saveProfileActualPeriodWeeks(Profile userProfile) {
//        ArrayList<Week> weeks = new ArrayList<Week>();
//        ArrayList<Boolean> weekDOne = new ArrayList<Boolean>();
//
//        if (!userProfile.isHistoryOfPeriodsEmpty()) {
//            if (!userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).isWeeksEmpty()) {
//
//                for (int i = 0; i <= userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getWeeks().length; i++) {
//                    weeks.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getWeek(i));
//                    weekDOne.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getWeekDone(i));
//                }
//            } else {
//                System.out.println("Pusta lista tygodni w okresie!");
//            }
//        } else {
//            System.out.println("Pusty kontener okresów!");
//        }
//
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            mapper.writeValue(new File(userProfile.getProfileName() + "_Weeks.json"), weeks);
//        } catch (IOException e) {
//            e.printStackTrace();
//
//            try {
//                mapper.writeValue(new File(userProfile.getProfileName() + "_weekDone.json"), weekDOne);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//
//    private void saveProfileActualWeekLists(Profile userProfile) {
//        ArrayList<Product> listOne = new ArrayList<Product>();
//        ArrayList<Product> listTwo = new ArrayList<Product>();
//        ArrayList<Product> listThree = new ArrayList<Product>();
//
//        if (!userProfile.isHistoryOfPeriodsEmpty()) {
//            if (!userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).isWeeksEmpty()) {
//
//                for (int i = 0; i < userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListOne().size(); i++) {
//                    listOne.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListOne().get(i));
//                }
//
//                for (int i = 0; i < userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListTwo().size(); i++) {
//                    listTwo.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListTwo().get(i));
//                }
//
//                for (int i = 0; i < userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListThree().size(); i++) {
//                    listThree.add(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getListThree().get(i));
//                }
//            } else {
//                System.out.println("Pusta lista tygodni w okresie!");
//            }
//        } else {
//            System.out.println("Pusty kontener okresów!");
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            mapper.writeValue(new File(userProfile.getProfileName() + "_listOne.json"), listOne);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            mapper.writeValue(new File(userProfile.getProfileName() + "_listTwo.json"), listTwo);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            mapper.writeValue(new File(userProfile.getProfileName() + "_listThree.json"), listThree);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void saveProfile(Profile userProfile) throws IOException {
////        ObjectMapper mapper = new ObjectMapper();
////
////        mapper.writeValue(new File(userProfile.getProfileName() + ".json"), userProfile);
//
//        saveProfileSettings(userProfile);
//        if (!userProfile.getHistoryOfPeriods().isEmpty()) {
//            //saveProfileHistory(userProfile);
//        }
//        saveProfileActualPeriodWeeks(userProfile);
//        saveProfileActualWeekLists(userProfile);
//    }
//
//    private void readProfileHistory(String profileName, Profile profile) {
//        ObjectMapper mapper = new ObjectMapper();
//
//        ArrayList<Period> periods = new ArrayList<Period>();
//
//
//        try {
//            if(new File(profileName + "_Periods.json").exists()) {
//            periods = mapper.readValue(new File(profileName + "_Periods.json"), new TypeReference<ArrayList<Period>>() {
//            });}
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < periods.size(); i++) {
//            profile.getHistoryOfPeriods().add(periods.get(i));
//        }
//
//
//    }
//
//    private void readProfileActualPeriodWeeks(String profileName, Profile profile) {
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        ArrayList<Week> weeks = new ArrayList<Week>();
//        ArrayList<Boolean> weekDone = new ArrayList<Boolean>();
//
//        try {
//            if (new File(profileName + "_Weeks.json").exists()) {
//                weeks = mapper.readValue(new File(profileName + "_Weeks.json"), new TypeReference<ArrayList<Week>>() {
//                });
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            if (new File(profileName + "_weekDone.json").exists()) {
//                weekDone = mapper.readValue(new File(profileName + "_weekDone.json"), new TypeReference<ArrayList<Boolean>>() {
//                });
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < weeks.size(); i++) {
//            profile.getHistoryOfPeriods().get(profile.getPeriodsCounter()).addWeek(i, weeks.get(i));
//        }
//
//        for (int i = 0; i < weekDone.size(); i++) {
//            profile.getHistoryOfPeriods().get(profile.getPeriodsCounter()).addWeekDone(i, weekDone.get(i));
//        }
//    }
//
//    private void readProfileActualWeekLists(String profileName, Profile profile) {
//        ArrayList<Product> listOne = new ArrayList<Product>();
//        ArrayList<Product> listTwo = new ArrayList<Product>();
//        ArrayList<Product> listThree = new ArrayList<Product>();
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            if (new File(profileName + "_listOne.json").exists()) {
//                listOne = mapper.readValue(new File(profileName + "_listOne.json"), new TypeReference<ArrayList<Product>>() {
//                });
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            if (new File(profileName + "_listTwo.json").exists()) {
//                listTwo = mapper.readValue(new File(profileName + "_listTwo.json"), new TypeReference<ArrayList<Product>>() {
//                });
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            if (new File(profileName + "_listThree.json").exists()) {
//                listThree = mapper.readValue(new File(profileName + "_listThree.json"), new TypeReference<ArrayList<Product>>() {
//
//                });
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < listOne.size(); i++) {
//            profile.getHistoryOfPeriods().get(profile.getPeriodsCounter()).getPresentWeek().addToListOne(new Product(listOne.get(i).getProductName(), listOne.get(i).getProductPrize(), listOne.get(i).isFromWallet()));
//        }
//        for (int i = 0; i < listTwo.size(); i++) {
//            profile.getHistoryOfPeriods().get(profile.getPeriodsCounter()).getPresentWeek().addToListTwo(new Product(listTwo.get(i).getProductName(), listTwo.get(i).getProductPrize(), listTwo.get(i).isFromWallet()));
//        }
//        for (int i = 0; i < listThree.size(); i++) {
//            profile.getHistoryOfPeriods().get(profile.getPeriodsCounter()).getPresentWeek().addToListThree(new Product(listThree.get(i).getProductName(), listThree.get(i).getProductPrize(), listThree.get(i).isFromWallet()));
//        }
//    }
//
//    public Profile readProfile(String profileName) {
//        ObjectMapper mapper = new ObjectMapper();
//
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//
//        Profile tempChosenProfile = new Profile(profileName);
//        Profile chosenProfile = new Profile("default");
//
//        try {
//            tempChosenProfile = mapper.readValue(new File(profileName + ".json"), new TypeReference<Profile>() {
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        chosenProfile.setWallet(tempChosenProfile.getWallet());
//        // chosenProfile.setHistoryOfPeriods(tempChosenProfile.getHistoryOfPeriods());
//        chosenProfile.setListOneName(tempChosenProfile.getListOneName());
//        chosenProfile.setListOnePercentage(tempChosenProfile.getListOnePercentage());
//        chosenProfile.setListThreeName(tempChosenProfile.getListThreeName());
//        chosenProfile.setListThreePercentage(tempChosenProfile.getListThreePercentage());
//        chosenProfile.setListTwoName(tempChosenProfile.getListTwoName());
//        chosenProfile.setListTwoPercentage(tempChosenProfile.getListTwoPercentage());
//        chosenProfile.setPeriodsCounter(tempChosenProfile.getPeriodsCounter());
//        chosenProfile.setProductsCounter(tempChosenProfile.getProductsCounter());
//        chosenProfile.setProfileName(tempChosenProfile.getProfileName());
//        chosenProfile.setWalletCalculationType(tempChosenProfile.getWalletCalculationType());
//        chosenProfile.setWalletFreeValue(tempChosenProfile.getWalletFreeValue());
//        chosenProfile.setWalletPercentage(tempChosenProfile.getWalletPercentage());
//        chosenProfile.setWeeksCounter(tempChosenProfile.getWeeksCounter());
//
////        readProfileHistory(profileName, chosenProfile);
//        readProfileActualPeriodWeeks(profileName, chosenProfile);
//        readProfileActualWeekLists(profileName, chosenProfile);
//        return chosenProfile;
//    }
//

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
        this.profiles.remove(index);
        this.saveProfilesList();
    }

    public ArrayList<String> getProfiles() {
        return profiles;
    }
}
