package in.codeshuffle.foodiehub.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "food")
public class Food {

    @Expose
    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @Generated(hash = 743609816)
    public Food(Long id) {
        this.id = id;
    }

    @Generated(hash = 866324199)
    public Food() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
