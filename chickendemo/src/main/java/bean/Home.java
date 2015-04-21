package bean;

/**
 * Created by aaa on 15-4-16.
 */
public class Home {

    private int id;
    private String shipuName;
    private String name;
    private String shipuNameInFourDiv;
    private String image;
    private String bigimage;

    public Home() {
    }

    public Home(int id, String shipuName, String name, String shipuNameInFourDiv, String image, String bigimage) {
        this.id = id;
        this.shipuName = shipuName;
        this.name = name;
        this.shipuNameInFourDiv = shipuNameInFourDiv;
        this.image = image;
        this.bigimage = bigimage;
    }

    public int  getId() {
        return id;
    }

    public void setId(int  id) {
        this.id = id;
    }

    public String getShipuName() {
        return shipuName;
    }

    public void setShipuName(String shipuName) {
        this.shipuName = shipuName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShipuNameInFourDiv() {
        return shipuNameInFourDiv;
    }

    public void setShipuNameInFourDiv(String shipuNameInFourDiv) {
        this.shipuNameInFourDiv = shipuNameInFourDiv;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBigimage() {
        return bigimage;
    }

    public void setBigimage(String bigimage) {
        this.bigimage = bigimage;
    }
}
