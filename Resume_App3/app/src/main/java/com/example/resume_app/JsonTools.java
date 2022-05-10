package com.example.resume_app;

import android.content.Context;

import com.example.resume_app.data_model.ResumeData;
import com.example.resume_app.data_model.UserData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Locale;

/**
 * Helper class to keep all JSON saving- /loading-related functions in one place.
 * Preferably, keep JSON operations in Activities unless necessary.
 *
 * Also, we use .nfteam as a file extension. How proprietary! :] --arthur
 */
public class JsonTools {

    Context context;

    public JsonTools(Context context) {
        this.context = context;
    }

    /**
     * @param userData The UserData object to save.
     * @return The same UserData that was passed in.
     */
    public UserData saveUserToJson(UserData userData) {
        File file = new File(context.getExternalFilesDir(null), "user_data.nfteam");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(userData, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userData;
    }

    /**
     * @return The UserData that was loaded.
     */
    public UserData loadUserFromJson() {
        File file = new File(context.getExternalFilesDir(null), "user_data.nfteam");
        Gson gson = new Gson();
        UserData u = null;

        try (FileReader reader = new FileReader(file)) {
            u = gson.fromJson(reader, UserData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    /**
     * @param resumeData The ResumeData object to save.
     * @return The same ResumeData that was passed in.
     */
    public ResumeData saveResumeToJson(ResumeData resumeData) {
        String formattedFileName = resumeData.fileName.toLowerCase(Locale.ROOT).replaceAll("\\s+", "_");

        File file = new File(context.getExternalFilesDir(null), formattedFileName + ".nfteam");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(resumeData, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resumeData;
    }

    /**
     * @param fileName The name of the JSON file to load.
     * @return The ResumeData that was loaded.
     */
    public ResumeData loadResumeFromJson(String fileName) {
        String formattedFileName = fileName.toLowerCase(Locale.ROOT).replaceAll("\\s+", "_");

        File file = new File(context.getExternalFilesDir(null), formattedFileName + ".nfteam");
        Gson gson = new Gson();
        ResumeData r = null;

        try (FileReader reader = new FileReader(file)) {
            r = gson.fromJson(reader, ResumeData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }
}