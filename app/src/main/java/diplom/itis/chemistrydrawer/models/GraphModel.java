package diplom.itis.chemistrydrawer.models;

import diplom.itis.chemistrydrawer.R;

/**
 * Created by Денис on 29.01.2017.
 */

public class GraphModel {
    public int xPos;
    public int yPos;
    public int color;
    public int numberOfAngle;
    public String text;
    public int radious;
    public float fontSize;
    public int fontColor;
    public boolean flag;

    public GraphModel() {}

    public GraphModel(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        color = R.color.red;
        text = "";
        numberOfAngle = 5;
        radious = 80;
    }

    public GraphModel(int xPos, int yPos, int numberOfPoint, boolean flag) {
        this.xPos = xPos;
        this.yPos = yPos;
        color = R.color.red;
        text = "";
        numberOfAngle = numberOfPoint;
        radious = 80;
        this.flag = flag;
    }

    public GraphModel(int xPos, int yPos, String text, boolean flag) {
        this.xPos = xPos;
        this.yPos = yPos;
        color = R.color.red;
        this.text = text;
        fontSize = 24f;
        fontColor = R.color.app_text_color;
        this.flag = flag;
    }
}
