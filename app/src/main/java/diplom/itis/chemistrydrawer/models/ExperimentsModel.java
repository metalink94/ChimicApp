package diplom.itis.chemistrydrawer.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;


/**
 * Created by denis_000 on 06.11.2016.
 */
public class ExperimentsModel implements Parcelable{

    public String title;
    public String description;
    public int id;
    public ImageView icon;
    public ImageView iconStatus;
    public String status;

    public static final String STATUS_OK = "OK";
    public static final String STATUS_PROGRESS = "PROGRESS";
    public static final String STATUS_ERROR = "ERROR";

    public ExperimentsModel(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    protected ExperimentsModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        id = in.readInt();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(id);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExperimentsModel> CREATOR = new Creator<ExperimentsModel>() {
        @Override
        public ExperimentsModel createFromParcel(Parcel in) {
            return new ExperimentsModel(in);
        }

        @Override
        public ExperimentsModel[] newArray(int size) {
            return new ExperimentsModel[size];
        }
    };

}
