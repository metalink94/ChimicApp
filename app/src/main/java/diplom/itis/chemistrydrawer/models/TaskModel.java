package diplom.itis.chemistrydrawer.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by denis_000 on 06.11.2016.
 */
public class TaskModel implements Parcelable{

    public String title;
    public String description;
    public int id;

    public TaskModel() {

    }

    public TaskModel(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    protected TaskModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        id = in.readInt();
    }

    public static final Creator<TaskModel> CREATOR = new Creator<TaskModel>() {
        @Override
        public TaskModel createFromParcel(Parcel in) {
            return new TaskModel(in);
        }

        @Override
        public TaskModel[] newArray(int size) {
            return new TaskModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(id);
    }
}
