package Converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

//used to convert custom class to and from a known type that room can persist

public class Converters {

    //convert date to long
    @TypeConverter
    public static Date fromTimestamp(Long value){
        return value == null ? null : new Date(value);

    }


    //convert long to date
    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date == null ? null : date.getTime();

    }

}
