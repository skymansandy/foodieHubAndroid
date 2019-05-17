package in.codeshuffle.foodiehub.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity(nameInDb = "restaurant")
public class Restaurant {

    @Expose
    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @Generated(hash = 2015186099)
    public Restaurant(Long id) {
        this.id = id;
    }

    @Generated(hash = 339249590)
    public Restaurant() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
