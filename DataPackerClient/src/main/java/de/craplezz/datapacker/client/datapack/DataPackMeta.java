package de.craplezz.datapacker.client.datapack;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Overload
 * @version 1.0
 */
@Data
public class DataPackMeta implements Serializable {

    private Pack pack;

    @Data
    public class Pack implements Serializable {

        @SerializedName( "pack_format" )
        private int version;
        private String description;

    }

}
