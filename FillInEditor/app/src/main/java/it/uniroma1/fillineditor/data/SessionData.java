package it.uniroma1.fillineditor.data;

import com.google.gson.GsonBuilder;

public class SessionData {
    //TODO Cambia tutta questa classe conformemente a i dati della sessione di compilazione
//    public Configuration configuration;
    public DeviceData device_data;
    public String name;
    public String surname;
    public int age;
//    public Gender gender;
    public String date;

//    @SuppressLint("SimpleDateFormat")
//    public SessionData(Configuration configuration, DeviceData device_data, String name, String surname, int age, Gender gender, String date) {
//        this.configuration = configuration;
//        this.device_data = device_data;
//        this.name = name;
//        this.surname = surname;
//        this.age = age;
//        this.gender = gender;
//        this.date = date;
//    }

    public SessionData(){
        this.device_data = new DeviceData("telefono_di_federica", "bhsw", 0, 0, 0, 0);
    }


    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

}
