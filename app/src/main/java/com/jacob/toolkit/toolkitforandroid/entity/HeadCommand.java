package com.jacob.toolkit.toolkitforandroid.entity;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: Jacob
 * @date: 2018/5/6
 */
public class HeadCommand implements Parcelable {

    /**
     * id : 01
     * command : head
     * type : yaw
     * speed : 10
     * angle : 90
     */


    private String id;
    private String command;
    private String type;
    private int speed;
    private int angle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    @Override
    public String toString() {
        return "HeadCommand{" +
                "id='" + id + '\'' +
                ", command='" + command + '\'' +
                ", type='" + type + '\'' +
                ", speed=" + speed +
                ", angle=" + angle +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.command);
        dest.writeString(this.type);
        dest.writeInt(this.speed);
        dest.writeInt(this.angle);
    }

    public HeadCommand() {
    }

    protected HeadCommand(Parcel in) {
        this.id = in.readString();
        this.command = in.readString();
        this.type = in.readString();
        this.speed = in.readInt();
        this.angle = in.readInt();
    }

    public static final Parcelable.Creator<HeadCommand> CREATOR = new Parcelable.Creator<HeadCommand>() {
        @Override
        public HeadCommand createFromParcel(Parcel source) {
            return new HeadCommand(source);
        }

        @Override
        public HeadCommand[] newArray(int size) {
            return new HeadCommand[size];
        }
    };
}
